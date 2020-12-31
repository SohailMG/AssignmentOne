/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;

import java.util.ArrayList;

/**
 * subclass that adds all visuals to the game board such as 
 * adding white space to words of length less than biggest word in a file
 * as well as adding borders to the game board in accordance to the file
 * 
 * @author Sohail Gsais
 * 
 */
class Visuals extends MemoryGame {

    /**
     * adds white space to words in small.txt file
     */
    public static void addSpace2Small(ArrayList<String> words, int item, ArrayList<String> wPairs) {
        // this is for better visuals. adding white space for words less than 8 length
        switch (words.get(item).length()) {
            case 5:
                wPairs.add((words.get(item)) + "   ");
                wPairs.add((words.get(item)) + "   ");
                break;
            case 6:
                wPairs.add((words.get(item)) + "  ");
                wPairs.add((words.get(item)) + "  ");
                break;
            case 7:
                wPairs.add((words.get(item)) + " ");
                wPairs.add((words.get(item)) + " ");
                break;
            default:
                wPairs.add((words.get(item)));
                wPairs.add((words.get(item)));
                break;
        }
    }

    /**
     * adds white space to words in large.txt file
     */
    public static void addSpace2Large(ArrayList<String> words, int item, ArrayList<String> wPairs) {
        // this is for better visuals. adding white space for words less than 8 length
        switch (words.get(item).length()) {
            case 4:
                wPairs.add((words.get(item)) + "        ");
                wPairs.add((words.get(item)) + "        ");
                break;
            case 5:
                wPairs.add((words.get(item)) + "       ");
                wPairs.add((words.get(item)) + "       ");
                break;
            case 6:
                wPairs.add((words.get(item)) + "      ");
                wPairs.add((words.get(item)) + "      ");
                break;
            case 7:
                wPairs.add((words.get(item)) + "     ");
                wPairs.add((words.get(item)) + "     ");
                break;
            case 8:
                wPairs.add((words.get(item)) + "    ");
                wPairs.add((words.get(item)) + "    ");
                break;
            case 9:
                wPairs.add((words.get(item)) + "   ");
                wPairs.add((words.get(item)) + "   ");
                break;
            case 10:
                wPairs.add((words.get(item)) + "  ");
                wPairs.add((words.get(item)) + "  ");
                break;

            default:
                wPairs.add((words.get(item)));
                wPairs.add((words.get(item)));
                break;
        }
    }

    /**
     * adds white space to words in medium.txt file
     */
    public static void addSpace2Medium(ArrayList<String> words, int item, ArrayList<String> wPairs) {
        // this is for better visuals. adding white space for words less than 8 length
        switch (words.get(item).length()) {
            case 4:
                wPairs.add((words.get(item)) + "       ");
                wPairs.add((words.get(item)) + "       ");
                break;
            case 5:
                wPairs.add((words.get(item)) + "      ");
                wPairs.add((words.get(item)) + "      ");
                break;
            case 6:
                wPairs.add((words.get(item)) + "     ");
                wPairs.add((words.get(item)) + "     ");
                break;
            case 7:
                wPairs.add((words.get(item)) + "    ");
                wPairs.add((words.get(item)) + "    ");
                break;
            case 8:
                wPairs.add((words.get(item)) + "   ");
                wPairs.add((words.get(item)) + "   ");
                break;
            case 9:
                wPairs.add((words.get(item)) + "  ");
                wPairs.add((words.get(item)) + "  ");
                break;
            case 10:
                wPairs.add((words.get(item)) + " ");
                wPairs.add((words.get(item)) + " ");
                break;

            default:
                wPairs.add((words.get(item)));
                wPairs.add((words.get(item)));
                break;
        }
    }
        /**
     * adds borders to the game board depending on the file being used
     */
    public static void addBorders() {
        switch (activeFile) {
            
            case "small":
                System.out.println("-----------------------------------------");
                break;
            case "medium":
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case "large":
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                break;
            default:
                break;

        }
    }

}

