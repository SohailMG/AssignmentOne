package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Task2 {

    static ArrayList<String> pos = new ArrayList<>();
    static int row = 4;
    static int colm = 4;
    static String[][] Board;

    public static void main(String[] args) {

        ArrayList pairsArr = readFile();
        Collections.shuffle(pairsArr);

        fillBoard();
        HashMap boardPos = creatPositions(pairsArr, pos);

        while (true) {
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
     * @return an array list of words duplicated.
     */
    public static ArrayList readFile() {

        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> wPairs = new ArrayList<>();

        File f = new File("small.txt");

        Scanner s;
        int item = 0;
        try {
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
     * @param boardPos
     * @param firstInput
     * @param secondInput
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
                Board[firstrowPos][firstcolmPos] =   (String) boardPos.get(firstInput)  + "*";
                Board[secondrowPos][secondcolmPos] = (String) boardPos.get(secondInput) + "*";
            } else {
                System.out.println("No match found....");
                System.out.println("------------------------------");
                Board[firstrowPos][firstcolmPos] =   "XXXXXX";
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

}
