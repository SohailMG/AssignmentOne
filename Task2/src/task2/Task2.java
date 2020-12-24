package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This is a Memory Game where the user is given a grid of hidden words and the
 * goal is to get all the matching words and complete the grid. the higher the
 * level the bigger the grid.
 *
 * @author Sohail Gsais
 *
 */
public class Task2 {

    // declaring static variables that will be used by functions
    static String firstInput,secondInput,makeGuess;

    
    static ArrayList<String> pos = new ArrayList<>();
    
    static String[][] Board;
    static int row , colm;
    
    static boolean userQuit = false;
    static int matches = 1;

    // brdRow and brdColm are used to displays row and colm positions of the board
    static int brdRow = 0,brdColm = 0;

    public static void main(String[] args) {

        int level = 0;

        Set<Integer> validInput = new HashSet<>();
        validInput.add(1);
        validInput.add(2);
        validInput.add(3);
        validInput.add(4);

        boolean inValidInput = true;

        /**
         * checking for mismatch input exception. getLeve function will keep
         * looping until the input is an Integer. also checking if input is in
         * range [1, 2 , 3 ,4]
         */
        while (inValidInput) {
            try {
                level = getLeve();

                if (level == 4) {
                    inValidInput = false;
                    userQuit = true;

                } else if (validInput.contains(level)) {

                    inValidInput = false;

                } else {
                    System.out.println("\n" + "-------------------------------------");
                    System.out.println(level + " is out of range choose 1 2 3 or 4  " + "\n");

                }

            } catch (Exception e) {
                System.out.println("\n" + "********** Invalid Entry ***********" + "\n");

            }
        }
        HashMap boardPos = null;

        try {

            ArrayList pairsArr = readFile(level);
            Collections.shuffle(pairsArr);

            fillBoard();
            boardPos = creatPositions(pairsArr, pos);
        } catch (Exception e) {
            userQuit = true;
        }

        while (!userQuit) {
            try {

                Scanner action = new Scanner(System.in);
                System.out.print("Make a Guess 'yes' or 'no' ?  >  ");
                makeGuess = action.nextLine();

                if (makeGuess.equals("no")) {
                    userQuit = true;

                } else if (makeGuess.equals("yes")) {

                    /*
                    storing position of first word 
                     */
                    Scanner firstPos = new Scanner(System.in);
                    System.out.print("Enter first Position > ");
                    firstInput = firstPos.nextLine();
                    showFst(boardPos, firstInput);

                    /*
                    storing position of second word 
                     */
                    Scanner secondPos = new Scanner(System.in);
                    System.out.print("Enter Second Position > ");
                    secondInput = secondPos.nextLine();

//               displying words from chosen postions
                    reveal(boardPos, firstInput, secondInput);
                }

            } catch (Exception e) {
                System.out.print("Enter a valid Position > ");
            }

        }

    }

    /**
     * parses through a file of words and adds them into an array, then
     * duplicates the items from that array into another array. a file is chosen
     * in accordance to user input.
     *
     * @param level level chosen by the user either 1,2 3 or 4
     * @return an array list of words duplicated.
     */
    public static ArrayList readFile(int level) {

        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> wPairs = new ArrayList<>();

        File f = null;

        Scanner s;
        int item = 0;
        try {

            switch (level) {
                case 1:
                    row = 4;
                    colm = 4;
                    f = new File("small.txt");
                    break;
                case 2:
                    row = 4;
                    colm = 8;
                    f = new File("medium.txt");
                    break;
                case 3:
                    row = 8;
                    colm = 8;
                    f = new File("large.txt");
                    break;
                case 4:

                    userQuit = true;
                    break;
                default:
                    break;
            }

            s = new Scanner(f);

            while (s.hasNextLine()) {

                words.add(s.nextLine());

                wPairs.add((words.get(item)));
                wPairs.add((words.get(item)));

                item++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return wPairs;

    }

    /**
     * displays game board with hidden words
     */
    public static void fillBoard() {

        Board = new String[row][colm];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colm; j++) {
                Board[i][j] = "XXXXXX";
                pos.add(i + "" + j);

            }

        }

        showBoard();

    }

    /**
     * creates a hash map of positions as keys and words as values
     *
     * @param pairsArr
     * @param pos
     * @return returns a hash map
     */
    public static HashMap creatPositions(ArrayList pairsArr, ArrayList pos) {

        HashMap<String, String> positions = new HashMap<>();

        for (int i = 0; i < pos.size(); i++) {

            positions.put((String) pos.get(i), (String) pairsArr.get(i));
        }
        return positions;

    }

    /**
     * converts user input from string to integer. splits input into two chars,
     * index at 0 = row and index at 1 = column. replaces the chosen position
     * with a word from hash map using user input as key.
     *
     * @param boardPos a hashmap with array index as keys and words as values.
     * @param firstInput the first word position chosen by the user.
     * @param secondInput do you second work position chosen by the user
     */
    public static void reveal(HashMap boardPos, String firstInput, String secondInput) {

        int firstrowPos = Character.getNumericValue(firstInput.charAt(0));
        int firstcolmPos = Character.getNumericValue(firstInput.charAt(1));

        int secondrowPos = Character.getNumericValue(secondInput.charAt(0));
        int secondcolmPos = Character.getNumericValue(secondInput.charAt(1));

        String word1 = (String) boardPos.get(firstInput);
        String word2 = (String) boardPos.get(secondInput);

        if (boardPos.containsKey(firstInput)) {

            System.out.println("\n" + "Your guesses....");
            System.out.println("------------------------------");

            Board[firstrowPos][firstcolmPos] = (String) boardPos.get(firstInput);
            showBoard();
            Board[secondrowPos][secondcolmPos] = (String) boardPos.get(secondInput);

            showBoard();
            if (word1.equals(word2)) {
                System.out.println("\n" + "Match found : " + matches + "\n");

                matches++;
                Board[firstrowPos][firstcolmPos] = (String) boardPos.get(firstInput) + "*";
                Board[secondrowPos][secondcolmPos] = (String) boardPos.get(secondInput) + "*";
            } else {
                System.out.println("\n" + "No match found...." + "\n");
                Board[firstrowPos][firstcolmPos] = "XXXXXX";
                Board[secondrowPos][secondcolmPos] = "XXXXXX";

            }
        } else {
            System.out.println(firstInput + " is out of range");
        }
        showBoard();

    }

    /**
     * reveals first word chosen by the user
     *
     * @param boardPos a hashmap of 2D array indexes as keys and words as
     * values.
     * @param firstInput the first position chosen by the user i.e 01
     */
    public static void showFst(HashMap boardPos, String firstInput) {
        int firstrowPos = Character.getNumericValue(firstInput.charAt(0));
        int firstcolmPos = Character.getNumericValue(firstInput.charAt(1));

        if (boardPos.containsKey(firstInput)) {

            System.out.println("\n" + "Your guesses...." + "\n");

            Board[firstrowPos][firstcolmPos] = (String) boardPos.get(firstInput);
            showBoard();

        }

    }

    /**
     * displays first screen to the user with level choices.
     *
     * @return integer which is used to determine the level.
     */
    public static int getLeve() {

        System.out.println("------------------------------------");
        System.out.println("   Welcome to Memory Square Game    ");
        System.out.println("------------------------------------" + "\n");

        System.out.println("               Easy         > 1     ");
        System.out.println("            Intermediat     > 2     ");
        System.out.println("               Master       > 3     ");
        System.out.println("              Give up       > 4     ");

        System.out.print("\n" + "Choose Difficulty > ");

        Scanner lvl = new Scanner(System.in);
        int levelChosen = lvl.nextInt();

        return levelChosen;

    }
    
    /**
     * displays column positions at the top of gameboard
     * @param colm is the number of columns a gameboard has
     */
    public static void showColmPos(int colm) {

        for (int i = 0; i < colm; i++) {
            System.out.print("   " + i + "     ");

        }
        System.out.println();

    }
    
    /**
     * displays the game board
     */

    public static void showBoard() {

        showColmPos(colm);
        for (String[] r : Board) {
            System.out.print(brdRow);
            for (String s : r) {
                System.out.print("[" + s + "]");
            }
            brdRow++;
            System.out.println();
        }
        brdRow = 0;

    }

}
