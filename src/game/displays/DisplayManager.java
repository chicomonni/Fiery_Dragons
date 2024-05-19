package game.displays;

import game.Board;
import game.Player;
import game.chitCards.ChitCard;

/**
 * A Facade for interacting with the game Displays and Window
 */
public class DisplayManager {
    private final GameWindow gameWindow;
    private final VolcanoDisplay volcanoDisplay;
    private final PlayerDisplay playerDisplay;
    private final ChitCardDisplay cardDisplay;
    private final InputDisplay inputDisplay;
    private final WinnerDisplay winnerDisplay;

    /**
     * Constructor for the DisplayManager.
     *
     * @param gameWindow the GameWindow instance controlled by the DisplayManager
     * @param board      the Board instance representing the game board
     * @param players    an array of Player instances representing the players in the game
     */
    public DisplayManager(GameWindow gameWindow, Board board, Player[] players) {
        this.gameWindow = gameWindow;
        this.volcanoDisplay = new VolcanoDisplay(board.getVolcano(), gameWindow);
        this.playerDisplay = new PlayerDisplay(players, board.getVolcano(), gameWindow);
        this.cardDisplay = new ChitCardDisplay(board.getChitCards(), gameWindow);
        this.inputDisplay = new InputDisplay(gameWindow);
        this.winnerDisplay = new WinnerDisplay(gameWindow);
    }

    /**
     * Displays the movement of a player.
     *
     * @param player the Player whose movement is to be displayed
     */
    public void displayMove(Player player) {
        playerDisplay.update(player);
    }

    /**
     * Displays the flipping of a ChitCard.
     *
     * @param card the ChitCard to be displayed after flipping
     */
    public void displayFlip(ChitCard card) {
        cardDisplay.update(card);
    }

    /**
     * Starts the turn of a player, enabling input for the player.
     *
     * @param player the Player whose turn is starting
     * @param board  the Board instance representing the game board
     */
    public void startTurn(Player player, Board board) {
        inputDisplay.setPromptText(player, board);
        inputDisplay.disableInput();
        inputDisplay.enableInput(player, board, this);
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to be displayed
     */
    public void displayError(String message) {
        inputDisplay.printError(message);
    }

    /**
     * Ends the turn, disabling input and resetting the card display.
     */
    public void endTurn() {
        inputDisplay.disableInput();
        cardDisplay.reset();
    }

    public void displayWin(Player player) {
        winnerDisplay.displayWinner(player);
        gameWindow.showWinnerLabel();
    }
}
