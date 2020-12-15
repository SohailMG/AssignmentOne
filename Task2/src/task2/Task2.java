package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A Memory Game where user is given a grid of hidden words.
 * Main goal is to find the matching Pairs.
 * 
 *@author Sohail Gsais
 * 
 */
public class Task2 {

    public static void main(String[] args) {
        
        
        String[][] GameGrid = 
               {{"1  ","  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"2  ","  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"3  ","  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"4  ","  ******   ", "   ******    ", "   ******    ", "   ******    "}};
        String[][] GameWords = 
               {{"1  ","  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"2  ","  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"3  ","  ******   ", "   ******    ", "   ******    ", "   ******    "},
                {"4  ","  ******   ", "   ******    ", "   ******    ", "   ******    "}};
        
        showGameGrid(GameGrid);
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose Position > ");
        int pos = scan.nextInt();
        
        showWord(GameGrid,pos);
        showGameGrid(GameGrid);
        FILEdata();
        
        
        
    }
    
    /**
     * displays the Game Grid
     * @param GameGrid
     */
    public static void showGameGrid(String[][] GameGrid){
        
        System.out.println("" +  "        *" + "        *" + "        *" + "       *");
        System.out.println("  ____________________________________");
        
        for(String[]row : GameGrid){
            for (String s : row){
                System.out.print(s);
            }
            System.out.println( );
        }
    
    
    
    }
    /**
     * shows word in selected position
     * @param GameGrid
     * @param pos
     */
    public static void showWord(String[][] GameGrid,int pos){
        
         switch(pos){
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
    
    
    
    public static void fillGrid(String[][] GameWords ){
            int [][] arr = new int [5][5];
        
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr[i][j] =  (int) (Math.random()* 11) ;
            }
        }
        for(int k = 0; k < 3; k++){
            for(int l = 0; l < 3; l++){
                System.out.print(arr[k][l] + " ");
            }
            System.out.println();
        }
    }
    
    public static void FILEdata(){
        
        try {
      File myObj = new File("easy.txt");
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }     }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    } 

    
    }
}