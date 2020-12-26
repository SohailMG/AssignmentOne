package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
   static  File f ;
   static Scanner fileReader;

    public static void main(String[] args) {

        String dataFile = System.getProperty("user.dir") + File.separator + "datafile.txt";
        
//        array of both vowels and consosntants 
        char[] vowels = {' ', 'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U', 'y', 'Y'};
        char[] consonants = {' ', 'b', 'B', 'c', 'C', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H',
            'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'p', 'P',
            'q', 'Q', 'r', 'R', 's', 'S', 't', 'T', 'v', 'V', 'w', 'W', 'x', 'X', 'z', 'Z'};

//       file object of encrypted file 
         f = new File(dataFile);

        /**
         * try and catch block to catch filenotfound exception.
        */
        try {
//          creating a filereader scanner object
             fileReader = new Scanner(f);

            while (fileReader.hasNextLine()) {

//              storing encrypted file content into a string variable 
                String encryption = fileReader.nextLine();
                
//              regular expression to extract digits from a given 
                String regex = "\\d+";
                
                System.out.println("");
                writeToFile('\n');

                /**
                 * parsing through encrypted string 
                 */
                for (int i = 0; i < encryption.length(); i++) {

                    char c = encryption.charAt(i);

                    /**
                     * checking if a character is a C.
                     * if so then a regex is used to extract any numbers of followed by C
                     */
                    if (c == 'C') {
                        try {
                            String Cnum = encryption.substring(i, i + 3);

                            Pattern p = Pattern.compile(regex);
                            Matcher m = p.matcher(Cnum);

                            while (m.find()) {
//                                converting character to an integer
                                int num = Integer.parseInt(m.group());
                                System.out.print(consonants[num]);
                                
//                                writing deciphered character into a file
                                char content = consonants[num];
                                writeToFile(content);
                            }
                           /** 
                            * exception is thrown when line ends with a char followed by one digit
                            * when exception is thrown.
                            * checking if the second last char is a C.
                            * then will convert char followed by C into an integer and print out it's value
                            */
                        } catch (StringIndexOutOfBoundsException siobe) {
                            char num = encryption.charAt(encryption.length() - 2);
                            if (num == 'C') {
                                char d = encryption.charAt(encryption.length() - 1);
                                System.out.print(consonants[Character.getNumericValue(d)]);
                                
                                char content = consonants[Character.getNumericValue(d)];
                                writeToFile(content);
                            }

                        }

                    }
                    if (c == 'V') {
                        try {
                            String Vnum = encryption.substring(i, i + 3);

                            Pattern p = Pattern.compile(regex);
                            Matcher m = p.matcher(Vnum);

                            while (m.find()) {
                                int num = Integer.parseInt(m.group());
                                System.out.print(vowels[num]);
                                
                                char content = vowels[num];
                                writeToFile(content);
                            }
                            /** 
                            * exception is thrown when line ends with a char followed by one digit
                            * when exception is thrown.
                            * check if the second last char is a V.
                            * then will convert char followed by V into an integer and print out it's value
                            */
                        } catch (StringIndexOutOfBoundsException siobe) {
                            char num = encryption.charAt(encryption.length() - 2);
                            if (num == 'V') {
                                char d = encryption.charAt(encryption.length() - 1);
                                System.out.print(vowels[Character.getNumericValue(d)]);
                                
                                char content = vowels[Character.getNumericValue(d)];
                                writeToFile(content);
                            }

                        }

                    }
//                    checking if a file contains a space character
                    if (c == ' ') {
                        System.out.print(' ');
                        writeToFile(' ');

                    }

            
                }

            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exit");
        }finally{
            fileReader.close();
        
        }

    }

    /**
     * writes the deciphered content into a text file.
     * takes deciphered letter as a parameter.
     * @param content
     */
    public static void writeToFile(char content) {
        
        boolean FileCreated;

        try {
            File Results = new File("Results.txt");
            if (Results.createNewFile()) {
                FileCreated = true;
            } else {
                try (FileWriter w = new FileWriter("Results.txt", true)) {
                    w.write(content);
                }
            }
        } catch (IOException e) {
            FileCreated = false;
            System.out.println("An error occurred.");
        }
    }
}
