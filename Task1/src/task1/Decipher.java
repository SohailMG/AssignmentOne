package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decipher {

    static File f;
    static Scanner fileReader;

    public static void main(String[] args) {

        String dataFile = System.getProperty("user.dir") + File.separator + "datafile.txt";

        // array of both vowels and consosntants 
        char[] vowels = {' ', 'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U', 'y', 'Y'};
        char[] consonants = {' ', 'b', 'B', 'c', 'C', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H',
            'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'p', 'P',
            'q', 'Q', 'r', 'R', 's', 'S', 't', 'T', 'v', 'V', 'w', 'W', 'x', 'X', 'z', 'Z'};

        // file object of encrypted file 
        f = new File(dataFile);

        /**
         * try and catch block to catch filenotfound exception.
         */
        try {
            // creating a filereader scanner object
            fileReader = new Scanner(f);

            while (fileReader.hasNextLine()) {

                // storing encrypted file content into a string variable 
                String encryption = fileReader.nextLine();

                // regular expression to extract digits from a given 
                String regex = "\\d+";

                System.out.println("");
                writeToFile('\n');

                /**
                 * parsing through encrypted string
                 */
                for (int i = 0; i < encryption.length(); i++) {

                    char c = encryption.charAt(i);

                    /*
                        checking if a character is a C,V or a Space
                     */
                    switch (c) {
                        case 'C':
                            try {
                                // calling extractNums to get numbers following by char C
                                extractNums(encryption, i, regex, consonants);

                            } catch (StringIndexOutOfBoundsException siobe) {
                                // handleExce is called to handle exception
                                handleExce(encryption, consonants);

                            }
                            break;
                        case 'V':
                            try {
                                // calling extractNums to get numbers following by char V
                                extractNums(encryption, i, regex, vowels);

                            } catch (StringIndexOutOfBoundsException siobe) {
                                // handleExce is called to handle exception
                                handleExce(encryption, vowels);

                            }
                            break;
                        case ' ':
                            System.out.print(' ');
                            writeToFile(' ');
                            break;
                        default:
                            break;
                    }

                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exit");
        } finally {
            fileReader.close();

        }

    }

    /**
     * handles stringOutOfBounds exception thrown when line ends with a char
     * followed by one digit. when exception is thrown. method checks if the
     * second last char is a V. then will convert char followed by V into an
     * integer and print out it's value and the same if it is a C
     *
     * @param encryption encrypted char from datafile
     * @param arr a vowels or consonants array passed
     */
    public static void handleExce(String encryption, char[] arr) {

        char num = encryption.charAt(encryption.length() - 2);
        if (num == 'C') {
            char d = encryption.charAt(encryption.length() - 1);
            System.out.print(arr[Character.getNumericValue(d)]);

            char content = arr[Character.getNumericValue(d)];
            writeToFile(content);
        } else if (num == 'V') {
            char d = encryption.charAt(encryption.length() - 1);
            System.out.print(arr[Character.getNumericValue(d)]);

            char content = arr[Character.getNumericValue(d)];
            writeToFile(content);
        }
    }

    /**
     * extracts numbers from a given string using regular expression then prints
     * the deciphered letter vowel or consonant at index of extracted number
     *
     * @param encryption encrypted text from file such as C12V3
     * @param i iterator
     * @param regex regular expression to extract numbers "\\d+"
     * @param arr a consonants array or vowels depending on the character
     * @throws StringIndexOutOfBoundsException
     */
    public static void extractNums(String encryption, int i, String regex, char[] arr) {
        String nums = encryption.substring(i, i + 3);

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(nums);

        while (m.find()) {
            // converting character to an integer
            int num = Integer.parseInt(m.group());
            System.out.print(arr[num]);

            // writing deciphered character into a file
            char content = arr[num];
            writeToFile(content);
        }

    }

    /**
     * writes the deciphered content into a text file. takes deciphered letter
     * as a parameter.
     *
     * @param content deciphered char
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
