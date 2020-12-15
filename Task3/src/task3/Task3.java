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

            System.out.println("> Press MODE: ");
            Scanner s2 = new Scanner(System.in);
            String mood = s2.next();

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

    }

    @Override
    void checkAction() {

        while (true) {

            System.out.println("> Press Set to set to Hours ");
            Scanner s1 = new Scanner(System.in);
            String btn = s1.next();

            switch (btn) {

                case "Set":
                    current = setHours;
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
            System.out.println("> Press Set to add 1 to Hours: ");
            System.out.println("> Press Mode to set minuts   : ");
            Scanner s1 = new Scanner(System.in);
            String btn = s1.next();

            switch (btn) {

                case "Set":
                    hrs++;
                    System.out.println("Hours : " + hrs);
                    return;
                case "Mode":
                    current = setMins;
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
 * mode = set hours , state
 */
class Altimeter extends State {

    @Override
    void checkState() {
        System.out.println("------------------------------");
        System.out.println("           Altimeter          ");
        System.out.println("------------------------------");

    }

    @Override
    void checkAction() {
        while (true) {
            System.out.println("> Press Set to remain       : ");
            System.out.println("> Press Mode to show Time   : ");
            Scanner s1 = new Scanner(System.in);
            String btn = s1.next();

            switch (btn) {

                case "Set":
                    System.out.println("Mode Altimeter...");
                    return;
                case "Mode":
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
            System.out.println("> Press Set to add 1 to minuts : ");
            System.out.println("> Press Mode to show Time      : ");
            Scanner s1 = new Scanner(System.in);
            String btn = s1.next();

            switch (btn) {

                case "Set":
                    Mins++;
                    System.out.println("Minutes added : " + Mins);
                    return;
                case "Mode":
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

public class Task3 {

    public static void main(String[] args) {

        State.start = new Start();
        State.altimeter = new Altimeter();
        State.time = new Time();
        State.setHours = new SetHours();
        State.setMins = new SetMins();
        State.current = State.start;

        while (!State.current.End) {

            State.current.checkState();
            State.current.checkAction();

        }

    }

}
