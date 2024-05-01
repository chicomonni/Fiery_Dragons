package ascii;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The ASCIIProcessor class is responsible for reading ASCII art from a file.
 * It reads the file line by line and stores each line as a separate string in an ArrayList.
 * The ArrayList is then returned by the getArt method.
 * The ASCII art file is expected to be in the same directory as the ASCIIProcessor class.
 */public class ASCIIProcessor {   
    
    /**
     * The getArt method reads an ASCII art file and returns an ArrayList of Strings.
     * Each string in the ArrayList represents a line of ASCII art.
     * The method adds HTML tags at the beginning and end of the ArrayList to allow for proper display in a HTML context.
     * 
     * @param filename The name of the ASCII art file to read.
     * @return An ArrayList of Strings representing the ASCII art.
     * @throws FileNotFoundException If the specified file does not exist.
     */
    public static ArrayList<String> getArt(String filename) throws FileNotFoundException {
        File asciiArt = new File(filename);
        Scanner scanner = new Scanner(asciiArt);
        try {            
            // StringBuilder string = new StringBuilder();
            ArrayList<String> strings = new ArrayList<>();

            strings.add("<html><pre style=\"font-family: MxPlus IBM BIOS\">");

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine() + "<br>");
            }
            scanner.close();
            strings.add("</pre></html>");
            return strings;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The getArt method reads an ASCII art file and returns an ArrayList of Strings.
     * @param filename The name of the ASCII art file to read.
     * @param value The value to replace "xx" with in the ASCII art.
     * @return An ArrayList of Strings representing the ASCII art.
     * @throws FileNotFoundException
     */
    public static ArrayList<String> getArt(String filename, int value) throws FileNotFoundException {
        ArrayList<String> strings = getArt(filename);
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, strings.get(i).replaceAll("xx", String.format("%02d",value)));
        }
        return strings;
    }

    /**
     * The getString method concatenates an ArrayList of Strings into a single String.
     * @param strings The ArrayList of Strings to concatenate.
     * @return A single String containing all the Strings in the ArrayList.
     * @throws FileNotFoundException
     */
    public static String getString(ArrayList<String> strings) throws FileNotFoundException {
        return String.join("", strings);
    }

    /**
     * The placePlayer method replaces a character in the ASCII art with a player character.
     * @param base The ASCII art represented as an ArrayList of Strings.
     * @param character The character to replace in the ASCII art.
     * @param x The x-coordinate of the character to replace.
     * @param y The y-coordinate of the character to replace.
     */
    public static void placePlayer(ArrayList<String> base, char character, int x, int y) {
        StringBuffer buffer = new StringBuffer(base.get(y));
        buffer.replace(x, x+1, String.valueOf(character));
        base.set(y, buffer.toString());
    }
}
