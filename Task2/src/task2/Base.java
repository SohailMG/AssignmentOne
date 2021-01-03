/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;

import java.util.ArrayList;

/**
 * an abstract base class with all attributes
 * to be inherited by the MemoryGame class
 *
 * @author sohailgsais
 */
public  abstract class Base {
    
        // declaring String vaariables that will store game data 
    static String firstInput, secondInput, makeGuess, activeFile;

    // pos list stores the 2d array indexes
    // matchedWords stores the position of all matched words
    static ArrayList<String> pos = new ArrayList<>();
    static ArrayList<String> matchedWords = new ArrayList<>();

    static String[][] Board;
    static int row, colm;

    static boolean userQuit = false;
    static int matches = 0, numOfTries = 0;
    static int firstrowPos, firstcolmPos, secondrowPos, secondcolmPos;

    // brdRow and brdColm are used to displays row and colm positions of the board
    static int brdRow = 0, brdColm = 0;
    
    
}
