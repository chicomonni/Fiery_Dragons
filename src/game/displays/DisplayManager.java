package game.displays;

import game.Board;
import game.FieryDragons;
import game.Player;
import game.chitCards.ChitCard;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A Facade for interacting with the game Displays and Window
 */
public class DisplayManager {
    private final GameWindow window;
    private final TitleDisplay titleDisplay;
    private final SettingsDisplay settingsDisplay;
    private final LoadDisplay loadDisplay;
    private final GameDisplay gameDisplay;
    private final WinnerDisplay winnerDisplay;
    private final FieryDragons gameData;
    private VolcanoDisplay volcanoDisplay;
    private PlayerDisplay playerDisplay;
    private ChitCardDisplay cardDisplay;
    private InputDisplay inputDisplay;

    /**
     * Constructor.
     *
     * @param fieryDragons the FieryDragons instance representing the game
     * @param window       the GameWindow instance representing the game window
     * @throws IOException         if there is an error accessing the font file
     * @throws FontFormatException if the font file does not contain valid data
     */
    public DisplayManager(FieryDragons fieryDragons, GameWindow window) throws IOException, FontFormatException {
        this.window = window;
        this.gameData = fieryDragons;
        this.titleDisplay = new TitleDisplay(this, window);
        this.settingsDisplay = new SettingsDisplay(fieryDragons, this, window);
        this.loadDisplay = new LoadDisplay(fieryDragons, this, window);
        this.winnerDisplay = new WinnerDisplay(this, window);
        this.gameDisplay = new GameDisplay(window);
    }

    /**
     * Creates the game components and initializes their displays.
     *
     * @param window  the GameWindow instance
     * @param board   the Board instance
     * @param players an array of Player instances
     */
    public void createGameComponents(GameWindow window, Board board, Player[] players) {
        this.volcanoDisplay = new VolcanoDisplay(board.getVolcano(), gameDisplay);
        this.playerDisplay = new PlayerDisplay(players, board.getVolcano(), gameDisplay);
        this.cardDisplay = new ChitCardDisplay(board.getChitCards(), gameDisplay);
        this.inputDisplay = new InputDisplay(gameDisplay, gameData);
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
        gameData.incrementPlayerTurn();
        inputDisplay.disableInput();
        cardDisplay.reset();
    }

    /**
     * Displays the winner of the game.
     *
     * @param player the Player who has won the game
     */
    public void displayWinScreen(Player player) {
        winnerDisplay.setWinnerText(player);
        displayWinScreen(window.getFrame());
    }

    /**
     * Displays the title screen.
     *
     * @param frame the JFrame representing the game window
     */
    public void displayTitleScreen(JFrame frame) {
        gameDisplay.hideScreen(frame);
        settingsDisplay.hideScreen(frame);
        loadDisplay.hideScreen(frame);
        winnerDisplay.hideScreen(frame);
        titleDisplay.showScreen(frame);
    }

    /**
     * Displays the game screen.
     *
     * @param frame the JFrame representing the game window
     */
    public void displayGameScreen(JFrame frame) {
        titleDisplay.hideScreen(frame);
        settingsDisplay.hideScreen(frame);
        loadDisplay.hideScreen(frame);
        winnerDisplay.hideScreen(frame);
        gameDisplay.showScreen(frame);
    }

    /**
     * Displays the settings screen.
     *
     * @param frame the JFrame representing the game window
     */
    public void displaySettingsScreen(JFrame frame) {
        titleDisplay.hideScreen(frame);
        gameDisplay.hideScreen(frame);
        loadDisplay.hideScreen(frame);
        winnerDisplay.hideScreen(frame);
        settingsDisplay.showScreen(frame);
    }

    /**
     * Displays the load game screen.
     *
     * @param frame the JFrame representing the game window
     */
    public void displayLoadScreen(JFrame frame) {
        titleDisplay.hideScreen(frame);
        gameDisplay.hideScreen(frame);
        settingsDisplay.hideScreen(frame);
        winnerDisplay.hideScreen(frame);
        loadDisplay.showScreen(frame);
    }

    /**
     * Displays the load game screen.
     *
     * @param frame the JFrame representing the game window
     */
    public void displayWinScreen(JFrame frame) {
        titleDisplay.hideScreen(frame);
        gameDisplay.hideScreen(frame);
        settingsDisplay.hideScreen(frame);
        loadDisplay.hideScreen(frame);
        winnerDisplay.showScreen(frame);

    }

    /**
     * Closes the game.
     */
    public void closeGame() {
        window.closeWindow();
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to be displayed
     */
    public void displayMessage(String message) {
        inputDisplay.printMessage(message);
    }
}
