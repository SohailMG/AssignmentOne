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
 * beginning state
 */
class Start extends State {

    @Override
    void checkState() {
        System.out.println("------------------------------");
        System.out.println("          Hill Walker         ");
        System.out.println("------------------------------");

    }

    @Override
    void checkAction() {

        while (true) {

            System.out.println("           >MODE<             " + "\n" + ">");
            scan = new Scanner(System.in);
            String mood = scan.next();

            switch (mood) {
                case "MODE":
                    current = altimeter;
                    return;
                case "q":
                    End = true;

                    return;

                default:
                    System.out.println("> Please Press MODE");

            }
        }

    }

}

/**
 * mode = time , state
 */
class Time extends State {

    @Override
    void checkState() {
        System.out.println("------------------------------");
        System.out.println("          Time Mode         ");
        System.out.println("------------------------------");
        System.out.println("Time: " + State.setHours.hrs + " hours" + ":" + State.setMins.Mins + " Minuts");

    }

    @Override
    void checkAction() {

        while (true) {

            System.out.println("         >MODE< | >SET<        " + "\n" + ">");
            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn) {

                case "SET":
                    current = setHours;
                    return;
                case "MODE":
                    current = altimeter;
                    return;
                case "q":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose Set: ");

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
        System.out.println("------------------------------");
        System.out.println("           Set Hours          ");
        System.out.println("------------------------------");

    }

    @Override
    void checkAction() {
        while (true) {

            System.out.println("         >MODE< | >SET<        " + "\n" + ">");
            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn) {

                case "SET":
                    hrs++;
                    System.out.println("Hours added : " + hrs);
                    return;
                case "MODE":
                    current = setMins;
                    return;
                case "q":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose SET or MODE: ");

            }
        }

    }

}

/**
 * mode = set hours , state
 */
class Altimeter extends State {

    Scanner s1;

    @Override
    void checkState() {
        System.out.println("------------------------------");
        System.out.println("           Altimeter          ");
        System.out.println("------------------------------");

    }

    @Override
    void checkAction() {
        while (true) {

            System.out.println("         >MODE< | >SET<        " + "\n" + ">");
            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn) {

                case "SET":
                    System.out.println("Mode Altimeter...");
                    return;
                case "MODE":
                    current = time;

                    return;
                case "q":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose Set or Mode: ");

            }
        }

    }
}

/**
 * mode = set minutes , state
 */
class SetMins extends State {

    @Override
    void checkState() {
        System.out.println("------------------------------");
        System.out.println("            Set Mins          ");
        System.out.println("------------------------------");

    }

    @Override
    void checkAction() {
        while (true) {
            System.out.println("         >MODE< | >SET<        " + "\n" + ">");

            scan = new Scanner(System.in);
            String btn = scan.next();

            switch (btn) {

                case "SET":
                    Mins++;
                    System.out.println("Minutes added : " + Mins);
                    return;
                case "MODE":
                    current = time;
                    return;
                case "q":
                    End = true;
                    return;
                default:
                    System.out.println("> Please Choose SET or MODE: ");

            }
        }

    }

}

public class Task3 {

    public static void main(String[] args) {

        State.start = new Start();
        State.altimeter = new Altimeter();
        State.time = new Time();
        State.setHours = new SetHours();
        State.setMins = new SetMins();
        State.current = State.start;
        try {
            while (!State.current.End) {

                State.current.checkState();
                State.current.checkAction();

            }
        } finally {
            State.start.scan.close();
            State.altimeter.scan.close();
            State.time.scan.close();
            State.setHours.scan.close();
            State.setMins.scan.close();
        }

    }

}
