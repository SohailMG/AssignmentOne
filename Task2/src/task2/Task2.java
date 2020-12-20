package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A Memory Game where user is given a grid of hidden words. Main goal is to
 * find the matching Pairs.
 *
 * @author Sohail Gsais
 *
 */
public class Task2 {

    public static void main(String[] args) {
        
        int colms = 4;
        int rows = 4;

        String[][] table = new String[rows][colms];

        ArrayList<String> posRanges = new ArrayList<>();

        String[][] GameGrid
                = {{"0  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"1  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"2  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"3  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "}};

        boolean inRange = true;
        while (inRange) {

            showGameGrid(GameGrid);

            Scanner row = new Scanner(System.in);
            Scanner colm = new Scanner(System.in);

            System.out.println("Choose Row and Col > ");

            int Rowpos = row.nextInt();
            int Colpos = colm.nextInt();

            String inpt = Rowpos + "" + Colpos;
            
            

            fillTable(posRanges,table,rows,colms);
            showGameGrid(GameGrid);
            showWord(GameGrid, Rowpos,Colpos,table);

            System.out.println(inRange);

        }
    }

    /**
     * displays the Game Grid
     *
     * @param GameGrid
     */
    public static void showGameGrid(String[][] GameGrid) {

        System.out.println("" + "        0" + "          1" + "          2" + "         3");
        System.out.println("  ____________________________________");

        for (String[] row : GameGrid) {
            for (String s : row) {
                System.out.print(s);
            }
            System.out.println();
        }

    }

    /**
     * shows word in selected position
     *
     * @param GameGrid
     * @param row
     * @param colm
     * @param table
     *
     * 
     */
    public static void showWord(String[][] GameGrid, int row,int colm,String[][] table) {
        
        GameGrid[row][colm] = table[row][colm];
        
//        for (String[] r : table) {
//                for (String s : r) {
//                    System.out.print("[" + s + "]" + "\t");
//                }
//                System.out.println("\n");
//            }

    }

    /**
     * parses through data file and adds data to a 1D array. then creates a 2D
     * array and add items from the 1d array to the 2D array
     *
     * @param posRanges
     * @param table
     * @param rows
     * @param colms
     */
    public static void fillTable(ArrayList posRanges,String[][] table,int rows,int colms) {

        File small = new File("small.txt");

        ArrayList<String> ws = new ArrayList<>();
        ArrayList<String> ws2 = new ArrayList<>();

        int item = 0;
        int item2 = 0;

        try {
            Scanner scanF = new Scanner(small);
            while (scanF.hasNextLine()) {

                ws.add(scanF.nextLine());

                ws2.add(ws.get(item2));
                ws2.add(ws.get(item2));

                Collections.shuffle(ws2);

                item2++;
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colms; j++) {
                    table[i][j] = ws2.get(item);
                    posRanges.add(i + "" + j);
                    item++;
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        

    }

}
