import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import ascii.ASCIIProcessor;
import dragoncards.PirateCard;

class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        Display gameWindow = Display.getInstance();

        gameWindow.displayBoard(ASCIIProcessor.getArt("src/ascii/gameboard.txt"));
        
        PirateCard pirateCard = new PirateCard(2);
    
        ArrayList<String> cards = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            cards.add(pirateCard.getBackDisplay(i));
        }
        gameWindow.displayCards(cards);
    }
}