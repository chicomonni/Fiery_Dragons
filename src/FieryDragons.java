import javax.swing.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class to start the game.
 *
 * @author Georgia Kanellis
 */
class FieryDragons {
    public static void main(String[] args) throws IOException, FontFormatException {
        GameDisplay gameDisplay = GameDisplay.getInstance("Fiery Dragons");
        GameBoard gameBoard = GameBoard.getInstance();
        GameMaster gameMaster = new GameMaster();


        try {
            gameMaster.pickSettings();
            gameBoard.createGameBoard();
            gameBoard.printGameBoard();
            gameDisplay.setText(gameBoard.getFormattedGameBoard());
            startGame(gameBoard, gameDisplay); // Pass the inputField object
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void startGame(GameBoard gameBoard, GameDisplay gameDisplay) {
        StringBuilder gameBoardString = new StringBuilder();
        int playerTurnPointer = 0;
        Player[] playerArray = gameBoard.getPlayerArray();
        gameBoard.printMargin(gameBoardString);

        //get the current player
        Player currentPlayer = playerArray[playerTurnPointer];
        currentPlayer.startTurn(gameDisplay.getPromptField(), gameDisplay.getInputField());

    }

    public static Player nextTurn(GameBoard gameBoard, Player[] playerArray, int playerTurnPointer) {
        //increment the playerTurnPointer for the next player's turn
        playerTurnPointer++;
        if (playerTurnPointer >= playerArray.length) {
            //reset to the first player if all players have taken their turns
            playerTurnPointer = 0;
        }

        return playerArray[playerTurnPointer];
    }
}