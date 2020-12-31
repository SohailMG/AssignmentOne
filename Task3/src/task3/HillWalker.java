package task3;

import java.util.Scanner;

/**
 * Implementing an FSM simulation of a Hill Walker Watch Each state is
 * determined by action of choosing SET or MODE
 *
 * @author Sohail Gsais
 *
 */

/**
 * abstract class State holds all states for the watch and member functions
 */
abstract class State {

//    memeber variables used for setMins and setHours
    public int Mins;
    public int hrs;
    public final int MAX_ALTIMETER = 1500;
    int meters = 0;
    public Scanner scan;
//    End used to quite program when it is set to True
    public boolean End;

//    collection of static states
    static State time, altimeter, setHours, setMins, current, start;

    /**
     * checkState prints out the current state the user is in
     */
    void checkState() {
    }

    /**
     * checkAction checks for user input depending on user input an Action is
     * performed
     */
    void checkAction() {
    }

}

/**
 * beginning state MODE > changes state to altimeter. no SET action in this
 */
class Start extends State {

    @Override
    void checkState() {
        System.out.println("-----------------------------------");
        System.out.println("          Hill Walker         ");
        System.out.println("-----------------------------------");

    }

    @Override
    void checkAction() {

        while (true) {

            System.out.println("\n" + "Change Mode to Altimeter.... MODE" + "\n");
            System.out.println("Performs no Action.......... SET " + "\n");
            System.out.print(">");

            scan = new Scanner(System.in);

            String btn = scan.next();

            switch (btn.toUpperCase()) {
                case "MODE":
                    current = altimeter;
                    return;
                case "QUIT":
                    End = true;

                    return;

                default:
                    System.out.println("\n" + "> Please Press MODE");

            }
        }

    }

}

/**
 * Time state. Time added is shown in this state. MODE changes state to
 * altimeter. SET changes to setHours state
 *
 */
class Time extends State {

    @Override
    void checkState() {
        System.out.println("-----------------------------------");
        System.out.println("          Time Mode           ");
        System.out.println("-----------------------------------");
        System.out.println("Time: " + State.setHours.hrs + " hours" + ":" + State.setMins.Mins + " Minuts");

    }

    @Override
    void checkAction() {

        while (true) {

            System.out.println("\n" + "Change Mode to Altimeter.... MODE" + "\n");
            System.out.println("Set Mode to setHours........ SET " + "\n");
            System.out.print(">");
            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn.toUpperCase()) {

                case "SET":
                    current = setHours;
                    return;
                case "MODE":
                    current = altimeter;
                    return;
                case "QUIT":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose Set or Mode: ");

            }
        }

    }
}

/**
 * mode = altimeter , state
 */
class SetHours extends State {

//    int Hours = 0;
    @Override
    void checkState() {
        System.out.println("-----------------------------------");
        System.out.println("           Set Hours          ");
        System.out.println("-----------------------------------");
        System.out.println("Hours added : " + hrs);

    }

    @Override
    void checkAction() {
        while (true) {

            System.out.println("\n" + "Change display to set mins.... MODE" + "\n");
            System.out.println("Add 1 to Hours................ SET " + "\n");
            System.out.print(">");
            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn.toUpperCase()) {

                case "SET":
                    hrs++;

                    return;
                case "MODE":
                    current = setMins;
                    return;
                case "QUIT":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose SET or MODE: ");

            }
        }

    }

}

/**
 * Altimeter State SET > performs no actions MODE > changes state to Time
 */
class Altimeter extends State {

    Scanner s1;

    @Override
    void checkState() {
        System.out.println("-----------------------------------");
        System.out.println("           Altimeter          ");
        System.out.println("-----------------------------------");
        System.out.println("Meters: " + meters);

    }

    @Override
    void checkAction() {

        while (true) {

            System.out.println("\n" + "Change display to Time.... MODE" + "\n");
            System.out.println("Remain in Altimeter....... SET " + "\n");
            System.out.print(">");
            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn.toUpperCase()) {

                case "SET":
                    if (meters < MAX_ALTIMETER) {
                        current = altimeter;
                        meters += 100;
                    } else {
                        System.out.println("Reached Maximum Altemeter " + MAX_ALTIMETER);

                    }
                    return;
                case "MODE":
                    current = time;

                    return;
                case "QUIT":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose Set or Mode: ");

            }
        }

    }
}

/**
 * setMins State SET > adds one minute to time MODE > changes state to Time
 */
class SetMins extends State {

    @Override
    void checkState() {
        System.out.println("-----------------------------------");
        System.out.println("            Set Mins               ");
        System.out.println("-----------------------------------");

    }

    @Override
    void checkAction() {
        while (true) {
            System.out.println("\n" + "Change Mode to Time.... MODE" + "\n");
            System.out.println("Add 1 minut to Time.... SET " + "\n");
            System.out.println("Minutes added : " + Mins + "\n");
            System.out.print(">");

            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn.toUpperCase()) {

                case "SET":
                    Mins++;

                    return;
                case "MODE":
                    current = time;
                    return;
                case "QUIT":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose SET or MODE: ");

            }
        }

    }

}

public class HillWalker {

    public static void main(String[] args) {

        State.start = new Start();
        State.altimeter = new Altimeter();
        State.time = new Time();
        State.setHours = new SetHours();
        State.setMins = new SetMins();
        State.current = State.start;

//        program loops while End is true
//        end is false when user enters QUIT
        try {
            while (!State.current.End) {

                State.current.checkState();

                State.current.checkAction();

            }

//            closing scanner object from all states once program ends
        } catch (Exception ex) {
        } finally {
            State s = null;
            try {

                s.scan.close();
            } catch (NullPointerException e) {

            }
        }

    }

}
