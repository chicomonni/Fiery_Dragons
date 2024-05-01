import java.awt.FontFormatException;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException, FontFormatException, InterruptedException {
        FieryDragons game = FieryDragons.getInstance();
        Display gameWindow = new Display(game);
        
        game.startGame(gameWindow);
        game.runNextTurnSimulation(gameWindow);

        while(game.isGameRunning()) {
            game.playTurn(gameWindow);
        }

        System.exit(0);
    }
}