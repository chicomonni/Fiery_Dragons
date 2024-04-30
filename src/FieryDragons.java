import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class to start the game.
 * Created by: Georgia Kanellis
 */
class FieryDragons {
    public static void main(String[] args) throws IOException, FontFormatException {
        GameDisplay gameDisplay = new GameDisplay("Fiery Dragons");
        GameBoard gameBoard = new GameBoard();
        ArrayList chitCardArray = new ArrayList();
        GameMaster gameMaster= new GameMaster();


        try {
            //titleScreen();
            gameMaster.pickSettings();
            gameBoard.createGameBoard();
            gameBoard.printGameBoard();
            gameDisplay.setText(gameBoard.getFormattedGameBoard());
            startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void startGame() {

    }
}
