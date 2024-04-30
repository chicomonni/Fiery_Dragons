import java.awt.FontFormatException;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        Display gameWindow = Display.getInstance();
        FieryDragons game = FieryDragons.getInstance();
        
        game.startGame(gameWindow);
        game.runNextTurnSimulation(gameWindow);
        
    }
}