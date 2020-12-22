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

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList pairsArr = readFile();
        Collections.shuffle(pairsArr);

        fillBoard();
        HashMap boardPos = creatPositions(pairsArr, pos);
        System.out.println(pairsArr);

        while (true) {
            try {

                Scanner inpt = new Scanner(System.in);
                String userInput = inpt.nextLine();

                reveal(boardPos, userInput);
                
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
                System.out.print("[" + s + "]");
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
        System.out.println(positions);
        return positions;

    }

    /**
     * converts user input from string to integer. splits input into two chars,
     * index at 0 = row and index at 1 = column. replaces the chosen position
     * with a word from hash map using user input as key.
     *
     * @param boardPos
     * @param userInput
     */
    public static void reveal(HashMap boardPos, String userInput) {

        int rowPos = Character.getNumericValue(userInput.charAt(0));
        int colmPos = Character.getNumericValue(userInput.charAt(1));

        if (boardPos.containsKey(userInput)) {
            Board[rowPos][colmPos] = (String) boardPos.get(userInput);
        } else {
            System.out.println(userInput + " is out of range");
        }

        for (String[] r : Board) {
            for (String s : r) {
                System.out.print("[" + s + "]");
            }
            System.out.println();
        }

    }

}
