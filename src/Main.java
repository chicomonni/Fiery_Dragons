import java.awt.FontFormatException;
import java.io.IOException;
import game.Display;
import game.FieryDragons;

class Main {
    public static void main(String[] args) throws IOException, FontFormatException, InterruptedException {
        FieryDragons game = FieryDragons.getInstance();
        Display gameWindow = new Display(game);
        
        game.startGame(gameWindow);

        // specifically runs to illustrate Sprint 2.
        game.runNextTurnSimulation(gameWindow);

        while(game.isGameRunning()) {
            game.playTurn(gameWindow);
        }

        System.exit(0);
    }
}