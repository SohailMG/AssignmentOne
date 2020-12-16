package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Memory Game where user is given a grid of hidden words. Main goal is to
 * find the matching Pairs.
 *
 * @author Sohail Gsais
 *
 */
public class Task2 {

    public static void main(String[] args) {

        String[][] GameGrid
                = {{"1  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"2  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"3  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"4  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "}};
        String[][] GameWords
                = {{"1  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"2  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"3  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"4  ", "  ******   ", "   ******    ", "   ******    ", "   ******    "}};

        showGameGrid(GameGrid);

        Scanner scan = new Scanner(System.in);
        System.out.println("Choose Position > ");
        int pos = scan.nextInt();

        showWord(GameGrid, pos);
        showGameGrid(GameGrid);
        fillTable();

        String word = null;

//        for (int i = 0; i < 7; i++) {
//            
//         word = GetfileData (i);
//         
//        }
    }

    /**
     * displays the Game Grid
     *
     * @param GameGrid
     */
    public static void showGameGrid(String[][] GameGrid) {

        System.out.println("" + "        *" + "        *" + "        *" + "       *");
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
     * @param pos
     */
    public static void showWord(String[][] GameGrid, int pos) {

        switch (pos) {
            case 1:
                GameGrid[0][2] = "Sohail";
                break;
            case 2:
                GameGrid[0][3] = "Aliiaa";
                break;
            case 3:
                GameGrid[0][4] = "Ahmedd";
                break;
            default:
                System.out.println("Position Out of range");

        }

    }

//    public static String GetfileData (int item){
//        ArrayList<String> words = new ArrayList<>();
//        
//      
//            File myObj = new File("small.txt");
//        try {
//           Scanner myReader = new Scanner(myObj);
//                while (myReader.hasNextLine()) {
//                    String data = myReader.nextLine();
//                    words.add(data);
//                    
//                    
//                }
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found");
//        }
//
//        return words.get(item);
//    
//    }
    public static void fillTable() {

        File myObj = new File("small.txt");

        int colms = 4;
        int rows = 2;

        String[][] table = new String[rows][colms];
        
        ArrayList<String> ws = new ArrayList<>();
       int item = 0;
        
        try {
            Scanner scanF = new Scanner(myObj);
            while (scanF.hasNextLine()) {

                 ws.add(scanF.nextLine());
            }
//            System.out.println(ws);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colms; j++) {
                    table[i][j] = ws.get(item);
                    item++;
                }

            }
            for (String[] a : table) {
                for (String i : a) {
                    System.out.print(i + "\t");
                }
                System.out.println("\n");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");;
        }

    }

}
