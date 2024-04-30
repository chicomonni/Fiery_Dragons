import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class FieryDragons {
    public static void main(String[] args) throws IOException, FontFormatException{
        Display gameWindow = new Display("Fiery Dragon");

        File gameboard = new File("src/GameBoard.txt");
        StringBuilder data = new StringBuilder();
        try {
            Scanner scanner = new Scanner(gameboard);
            for (int i = 0; i < 6; i++){
                scanner.nextLine();
            }
            for (int i = 6; i < 88; i++){
                data.append(scanner.nextLine());
                data.append("<br>");
            }
            scanner.close();
            data.insert(0, "<html><pre style=\"font-family: MxPlus IBM BIOS\">");
            data.append("</pre></html>");
        
            gameWindow.displayBoard(data.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}