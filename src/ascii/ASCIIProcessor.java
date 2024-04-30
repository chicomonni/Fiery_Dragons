package ascii;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ASCIIProcessor {    
    public static String getArt(String filename) throws FileNotFoundException {
        File asciiArt = new File(filename);
        Scanner scanner = new Scanner(asciiArt);
        try {            
            StringBuilder string = new StringBuilder();

            while (scanner.hasNextLine()) {
                string.append(scanner.nextLine());
                string.append("<br>");
            }
            scanner.close();
            string.insert(0, "<html><pre style=\"font-family: MxPlus IBM BIOS\">");
            string.append("</pre></html>");
            return string.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getArtWithValue(String filename, int value) throws FileNotFoundException {
        String string = getArt(filename);
        return string.toString().replaceAll("xx", String.format("%02d",value));
    }
}
