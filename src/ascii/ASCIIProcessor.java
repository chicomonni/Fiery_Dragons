package ascii;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ASCIIProcessor {    
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

    public static ArrayList<String> getArt(String filename, int value) throws FileNotFoundException {
        ArrayList<String> strings = getArt(filename);
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, strings.get(i).replaceAll("xx", String.format("%02d",value)));
        }
        return strings;
    }

    public static String getString(ArrayList<String> strings) throws FileNotFoundException {
        return String.join("", strings);
    }

    public static void placePlayer(ArrayList<String> base, char character, int x, int y) {
        StringBuffer buffer = new StringBuffer(base.get(y));
        buffer.replace(x, x+1, String.valueOf(character));
        System.out.println(buffer.toString());
        base.set(y, buffer.toString());
    }
}
