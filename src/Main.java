import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import ascii.ASCIIProcessor;
import dragoncards.PirateCard;

class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        Display gameWindow = Display.getInstance();
        FieryDragons game = FieryDragons.getInstance();
        
        game.startGame(gameWindow);
    }
}