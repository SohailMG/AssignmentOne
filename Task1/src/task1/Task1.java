package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    public static void main(String[] args) {

        String dataFile = System.getProperty("user.dir") + File.separator + "datafile.txt";

        char[] vowels = {' ', 'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U', 'y', 'Y'};
        char[] consonants = {' ', 'b', 'B', 'c', 'C', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H',
            'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'p', 'P',
            'q', 'Q', 'r', 'R', 's', 'S', 't', 'T', 'v', 'V', 'w', 'W', 'x', 'X', 'z', 'Z'};

        File f = new File(dataFile);

        try {
            Scanner fileReader = new Scanner(f);

            while (fileReader.hasNextLine()) {

                String encryption = fileReader.nextLine();
                String regex = "\\d+";
                System.out.println("");
                writeToFile('\n');

                for (int i = 0; i < encryption.length(); i++) {

                    char c = encryption.charAt(i);

                    if (c == 'C') {
                        try {
                            String Cnum = encryption.substring(i, i + 3);

                            Pattern p = Pattern.compile(regex);
                            Matcher m = p.matcher(Cnum);

                            while (m.find()) {
                                int num = Integer.parseInt(m.group());
                                System.out.print(consonants[num]);
                                
                                char content = consonants[num];
                                writeToFile(content);
                            }
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
                    if (c == ' ') {
                        System.out.print(' ');
                        writeToFile(' ');

                    }

                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exit");
        }

    }

    public static void writeToFile(char content) {

        try {
            File output = new File("output.txt");
            if (output.createNewFile()) {
                System.out.println("File created: " + output.getName());
            } else {
                FileWriter myWriter = new FileWriter("output.txt",true);
                myWriter.write(content);
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
