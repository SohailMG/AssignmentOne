package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sohailgsais
 */
public class Task1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // file to parse from 
        File myObj = new File("datafile.txt");

        // file to write decripted FileContent to 
        FileWriter myWriter = new FileWriter("dataout.txt");

        // list  of Vowels
        List<String> Vowels = new ArrayList<>();
        Collections.addAll(Vowels, " ", "a", "A", "e", "E", "i", "I", "o", "O", "u", "U", "y", "Y");

        //list of consonants
        List<String> Consonants = new ArrayList<>();
        Collections.addAll(Consonants, " ", "b", "B", "c", "C", "d", "D", "f", "F", "g", "G",
                "h", "H", "j", "J", "k", "K", "l", "L", "m", "M",
                "n", "N", "p", "P", "q", "Q", "r", "R", "s", "S",
                "t", "T", "v", "V", "w", "W", "x", "X", "z", "Z");

        // initialising string to hold file encrypted file FileContent
        String FileContent = null;

        try (Scanner myReader = new Scanner(myObj)) {

            while (myReader.hasNextLine()) {

                // storing file content 
                FileContent = myReader.nextLine();

                System.out.println("\n");
                myWriter.write("\n");

                /**
                 * parsing through file content to check if char is a C or a V
                 * if it's a C it will check if the next two items are digits if
                 * it is a digit it will convert it to an integer
                 */
                for (int i = 0; i < FileContent.length() - 2; i++) {

                    if (FileContent.charAt(i) == 'C') {
                        if (Character.isDigit(FileContent.charAt(i + 2))) {
                            String x = FileContent.substring(i + 1, i + 3);
                            int num = Integer.parseInt(x);

                            System.out.print(Consonants.get(num));
                            myWriter.write(Consonants.get(num));

                        } else {
                            char c = FileContent.charAt(i + 1);
                            int Cnum = Character.getNumericValue(c);
                            System.out.print(Consonants.get(Cnum));
                            myWriter.write(Consonants.get(Cnum));

                        }

                    }
                    if (FileContent.charAt(i) == 'V') {
                        if (Character.isDigit(FileContent.charAt(i + 2))) {
                            String x = FileContent.substring(i + 1, i + 3);
                            int num = Integer.parseInt(x);

                            System.out.print(Vowels.get(num));
                            myWriter.write(Vowels.get(num));

                        } else {
                            char b = FileContent.charAt(i + 1);
                            int bnum = Character.getNumericValue(b);
                            System.out.print(Vowels.get(bnum));
                            myWriter.write(Vowels.get(bnum));
                        }

                    }
                    if (FileContent.charAt(i) == ' ') {

                        System.out.print(FileContent.charAt(i));
                        myWriter.write(FileContent.charAt(i));
                    }

                }

            }
            myReader.close();
        }
        myWriter.close();
    }

}