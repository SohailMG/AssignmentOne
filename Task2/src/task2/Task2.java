package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task2 {

    static ArrayList<String> pos = new ArrayList<>();
    static int row;
    static int colm;
    static String[][] Board;
    static boolean userQuit = true;

    public static void main(String[] args) {

        int level = 0;

        Set<Integer> validInput = new HashSet<>();
        validInput.add(1);
        validInput.add(2);
        validInput.add(3);
        validInput.add(4);

        boolean inValidInput = true;


        /**
         * checking for mismatch input exception. startGame function will keep
         * looping until the input is an Integer.
         * also checking if input is in range [1, 2 , 3 ,4]
         */
        while (inValidInput) {
            try {
                level = startGame();
                
                if(level == 4){
                    inValidInput = false;
                    userQuit = false;
                    
                
                
                }

                if (validInput.contains(level)) {

                    inValidInput = false;
                    

                } else {
                    System.out.println("\n" + "-------------------------------------");
                   System.out.println(level + " is out of range choose 1 2 3 or 4 > " + "\n");

                }

            } catch (Exception e) {
                    System.out.println("\n");
                System.out.println("********** Invalid Entry ***********" + "\n");

            }
        }
        HashMap boardPos = null ;

        try {
            
      
        ArrayList pairsArr = readFile(level);
        Collections.shuffle(pairsArr);
        

        fillBoard();
         boardPos = creatPositions(pairsArr, pos);
        } catch (Exception e) {
            userQuit = false;
            inValidInput = false;
        }
        

        while (userQuit) {
            try {

                /*
                    storing position of first word 
                 */
                Scanner firstPos = new Scanner(System.in);
                System.out.print("Enter first Position > ");
                String firstInput = firstPos.nextLine();

                /*
                    storing position of second word 
                 */
                Scanner secondPos = new Scanner(System.in);
                System.out.print("Enter Second Position > ");
                String secondInput = secondPos.nextLine();

//               displying words from chosen postions
                reveal(boardPos, firstInput, secondInput);

            } catch (Exception e) {
                System.out.print("Enter a valid Position > ");
            }
        }

    }

    /**
     * parses through file and adds words within the file into an array
     *
     * @param level level chosen by the user either 1,2 or 3
     * @return an array list of words duplicated.
     */
    public static ArrayList readFile(int level) {

        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> wPairs = new ArrayList<>();

        File f = null;

        Scanner s;
        int item = 0;
        try {

            if (level == 1) {
                row = 4;
                colm = 4;
                f = new File("small.txt");
            } else if (level == 2) {
                row = 4;
                colm = 8;
                f = new File("medium.txt");
            } else if (level == 3) {
                row = 8;
                colm = 8;
                f = new File("large.txt");
            }
             else if (level == 8) {
                userQuit = false;
            }
            System.out.println(row);
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

        Task2.Board = new String[row][colm];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colm; j++) {
                Board[i][j] = "XXXXXX";
                pos.add(i + "" + j);

            }

        }
        for (String[] r : Board) {
            for (String s : r) {
                System.out.print("    [" + s + "]");
            }
            System.out.println();
        }

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

        int matches = 1;

        int firstrowPos = Character.getNumericValue(firstInput.charAt(0));
        int firstcolmPos = Character.getNumericValue(firstInput.charAt(1));

        int secondrowPos = Character.getNumericValue(secondInput.charAt(0));
        int secondcolmPos = Character.getNumericValue(secondInput.charAt(1));

        String word1 = (String) boardPos.get(firstInput);
        String word2 = (String) boardPos.get(secondInput);

        if (boardPos.containsKey(firstInput)) {

            System.out.println("Your guesses....");
            System.out.println("------------------------------");

            Board[firstrowPos][firstcolmPos] = (String) boardPos.get(firstInput);
            Board[secondrowPos][secondcolmPos] = (String) boardPos.get(secondInput);

            for (String[] r : Board) {
                for (String s : r) {
                    System.out.print("[" + s + "]");
                }
                System.out.println();
            }
            if (word1.equals(word2)) {
                System.out.println("Match found : " + matches);
                System.out.println("------------------------------");
                matches++;
                Board[firstrowPos][firstcolmPos] = (String) boardPos.get(firstInput) + "*";
                Board[secondrowPos][secondcolmPos] = (String) boardPos.get(secondInput) + "*";
            } else {
                System.out.println("No match found....");
                System.out.println("------------------------------");
                Board[firstrowPos][firstcolmPos] = "XXXXXX";
                Board[secondrowPos][secondcolmPos] = "XXXXXX";

            }
        } else {
            System.out.println(firstInput + " is out of range");
        }

        for (String[] r : Board) {
            for (String s : r) {
                System.out.print("[" + s + "]");
            }
            System.out.println();
        }

    }

    /**
     * displays first screen to the user with level choices.
     *
     * @return integer which is used to determine the level.
     */
    public static int startGame() {

        System.out.println("------------------------------------");
        System.out.println("   Welcome to Memory Square Game    ");
        System.out.println("------------------------------------" + "\n");

        System.out.println("               Easy         > 1     ");
        System.out.println("            Intermediat     > 2     ");
        System.out.println("               Master       > 3     ");
        System.out.println("              Give up       > 4     " );

        System.out.print( "\n" + "Choose Difficulty > ");

        Scanner lvl = new Scanner(System.in);
        int levelChosen = lvl.nextInt();

        return levelChosen;

    }

}
