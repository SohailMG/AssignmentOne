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
 * @see Base 
 *
 */
public class MemoryGame extends Base {


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
                    System.out.println("\n");
                    System.out.println(level + " is out of range choose 1 2 3 or 4  " + "\n");

                }

            } catch (Exception e) {
                System.out.println("\n" + "********** Invalid Entry ***********" + "\n");

            }
        }
        // hashmap of 2d array indexes as keys and words as values
        HashMap boardPos = new HashMap<>();

        /*
          try and catch block. reading file data into an array of pairs.
          shuffling the array each run to randomise elements. calling
          fillBoard() to fill Game Board with Xs. creating a hashmap of 2d array
          indexes as keys and file words as values
         
         */
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
                System.out.print("\n" + " Make a Guess 'yes' or 'no' ?  >  ");
                makeGuess = action.nextLine();
                showBoard();

                if (makeGuess.equals("no")) {
                    userQuit = true;

                } else if (makeGuess.equals("yes")) {

                    /*
                    storing position of first word 
                     */
                    Scanner firstPos = new Scanner(System.in);
                    System.out.print("\n" + " Enter first Position > ");

                    // checking if input is not a valid positon
                    firstInput = firstPos.nextLine();
                    if (!(boardPos.containsKey(firstInput))) {
                        System.out.println("\n" + firstInput + "  Invalid Entry...try again" + "\n");
                        hideWords(firstrowPos, firstcolmPos, secondrowPos, secondcolmPos);

                        // checking if match has already been made
                    } else if (matchedWords.contains(firstInput)) {
                        System.out.println("\n" + "Word already Matched try again");

                    } else {
                        // revealing first word chosen by user
                        showFst(boardPos, firstInput);

                        // second input validation
                        Scanner secondPos = new Scanner(System.in);
                        System.out.print("\n" + "Enter Second Position > ");
                        secondInput = secondPos.nextLine();
                        // checking if input is not a valid position
                        if (!(boardPos.containsKey(secondInput))) {
                            System.out.println("\n" + secondInput + "  Invalid Entry...try again" + "\n");
                            hideWords(firstrowPos, firstcolmPos, secondrowPos, secondcolmPos);

                            // checking if user chooses same position twice
                        } else if (firstInput.equals(secondInput)) {
                            System.out.println("\n" + "Cannot choose same position again...." + "\n");
                            hideWords(firstrowPos, firstcolmPos, secondrowPos, secondcolmPos);

                            // checking if word has already been matched
                        } else if (matchedWords.contains(secondInput)) {
                            resetBoard();

                        } else {
                            numOfTries++;

                            //  displying words from chosen postions
                            reveal(boardPos, firstInput, secondInput);
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("\n" + "Invalid entry try again ");
            }

            // gameEnd is called once user matches all words
            gameEnd(matches);

        }

    }

    /**
     * called when second input has already been matched. allows user to retry
     * and hides their first guess
     */
    public static void resetBoard() {
        System.out.println("\n" + "Word already Matched try again");
        int r = Character.getNumericValue(firstInput.charAt(0));
        int c = Character.getNumericValue(firstInput.charAt(1));
        switch (activeFile) {

            case "small":
                Board[r][c] = "XXXXXXXX";

                break;
            case "medium":
                Board[r][c] = "XXXXXXXXXXX";

                break;
            case "large":
                Board[r][c] = "XXXXXXXXXXXX";
                break;
            default:
                break;

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

        String small = System.getProperty("user.dir") + File.separator + "small.txt";
        String medium = System.getProperty("user.dir") + File.separator + "medium.txt";
        String large = System.getProperty("user.dir") + File.separator + "large.txt";

        // two array lists used to make pairs
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
                    f = new File(small);
                    activeFile = "small";
                    break;
                case 2:
                    row = 6;
                    colm = 6;
                    f = new File(medium);
                    activeFile = "medium";
                    break;
                case 3:
                    row = 8;
                    colm = 8;
                    f = new File(large);
                    activeFile = "large";
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

                switch (activeFile) {

                    case "small":
                        Visuals.addSpace2Small(words, item, wPairs);
                        break;
                    case "medium":
                        Visuals.addSpace2Medium(words, item, wPairs);
                        break;
                    case "large":
                        Visuals.addSpace2Large(words, item, wPairs);
                        break;
                    default:
                        break;

                }
                item++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return wPairs;

    }

    /**
     * fills Game board with Xs in accordance to the file name
     */
    public static void fillBoard() {

        Board = new String[row][colm];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colm; j++) {
                switch (activeFile) {
                    case "small":
                        Board[i][j] = "XXXXXXXX";
                        pos.add(i + "" + j);
                        break;
                    case "medium":
                        Board[i][j] = "XXXXXXXXXXX";
                        pos.add(i + "" + j);
                        break;
                    case "large":
                        Board[i][j] = "XXXXXXXXXXXX";
                        pos.add(i + "" + j);
                        break;
                    default:
                        break;
                }

            }

        }

    }

    /**
     * creates a hash map of positions as keys and words as values
     *
     * @param pairsArr an array of words in pairs
     * @param pos index positions of 2D array
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

        firstrowPos = Character.getNumericValue(firstInput.charAt(0));
        firstcolmPos = Character.getNumericValue(firstInput.charAt(1));

        secondrowPos = Character.getNumericValue(secondInput.charAt(0));
        secondcolmPos = Character.getNumericValue(secondInput.charAt(1));

        String word1 = (String) boardPos.get(firstInput);
        String word2 = (String) boardPos.get(secondInput);

        if (boardPos.containsKey(firstInput)) {

            System.out.println("\n" + "Your guesses...." + "\n");

            Board[firstrowPos][firstcolmPos] = (String) boardPos.get(firstInput);
            Board[secondrowPos][secondcolmPos] = (String) boardPos.get(secondInput);

            showBoard();
            if (word1.equals(word2)) {
                System.out.println("\n" + "Match found  " + "\n");

                matchedWords.add(firstInput);
                matchedWords.add(secondInput);

                matches++;
                Board[firstrowPos][firstcolmPos] = (String) boardPos.get(firstInput);
                Board[secondrowPos][secondcolmPos] = (String) boardPos.get(secondInput);
            } else {
                System.out.println("\n" + "No match found...." + "\n");

                hideWords(firstrowPos, firstcolmPos, secondrowPos, secondcolmPos);

            }
            System.out.println("\n" + "Number of Guesses  " + numOfTries + "\n");
        } else {
            System.out.println(firstInput + " is out of range");
        }

    }

    /**
     * hides the words with Xs
     *
     * @param firstrowPos row of first word chosen
     * @param firstcolmPos column of first word chosen
     * @param secondrowPos row of second word chosen
     * @param secondcolmPos column of second word chosen
     */
    public static void hideWords(int firstrowPos, int firstcolmPos, int secondrowPos, int secondcolmPos) {

        switch (activeFile) {

            case "small":
                Board[firstrowPos][firstcolmPos] = "XXXXXXXX";
                Board[secondrowPos][secondcolmPos] = "XXXXXXXX";
                break;
            case "medium":
                Board[firstrowPos][firstcolmPos] = "XXXXXXXXXXX";
                Board[secondrowPos][secondcolmPos] = "XXXXXXXXXXX";
                break;
            case "large":
                Board[firstrowPos][firstcolmPos] = "XXXXXXXXXXXX";
                Board[secondrowPos][secondcolmPos] = "XXXXXXXXXXXX";
                break;
            default:
                break;

        }
    }

    /**
     * reveals first word chosen by the user
     *
     * @param boardPos a hashmap of 2D array indexes as keys and words as
     * values.
     * @param firstInput the first position chosen by the user i.e 01
     */
    public static void showFst(HashMap boardPos, String firstInput) {
        firstrowPos = Character.getNumericValue(firstInput.charAt(0));
        firstcolmPos = Character.getNumericValue(firstInput.charAt(1));

        if (boardPos.containsKey(firstInput)) {

            System.out.println("\n" + "Your first Guess...." + "\n");

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

        System.out.println("\n" + "Words can be revealed by choosing a row and column for instance to get the first square on the top "
                + "\n" + "left you would enter  01 without any spaces" + "\n");

        System.out.print("\n" + "Choose Difficulty > ");

        Scanner lvl = new Scanner(System.in);
        int levelChosen = lvl.nextInt();

        return levelChosen;

    }

    /**
     * displays column positions at the top of gameboard
     *
     * @param colm is the number of columns a gameboard has
     */
    public static void showColmPos(int colm) {

        for (int i = 0; i < colm; i++) {

            switch (activeFile) {
                case "small":
                    System.out.print("     " + i + "     ");
                    break;
                case "medium":
                    System.out.print("       " + i + "     ");
                    break;
                case "large":
                    System.out.print("        " + i + "     ");
                    break;
                default:
                    break;
            }

        }
        System.out.println();

    }

    /**
     * displays the game board 
     */
    public static void showBoard() {

        System.out.println();
        showColmPos(colm);
        Visuals.addBorders();
        for (String[] r : Board) {
            System.out.print(brdRow);
            for (String s : r) {
                System.out.print("[" + s + "]");
            }
            brdRow++;
            System.out.println();
        }
        Visuals.addBorders();
        brdRow = 0;

    }

    /**
     * called when all words are matched, and displays the end results including
     * number of attempts.
     *
     * @param matches is the number of matches a user has made
     */
    public static void gameEnd(int matches) {

        int maxMatches = (row * colm) / 2;

        if (matches == maxMatches) {

            System.out.println("\n" + "****** All Matches Found *******" + "\n");
            System.out.println("Number of Tries : " + numOfTries + "\n");
            System.out.println("Matches Made: " + matches + "\n");
            userQuit = true;
        }

    }

}
