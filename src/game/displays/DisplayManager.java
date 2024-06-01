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
    private final GameDisplay gameDisplay;
    private VolcanoDisplay volcanoDisplay;
    private PlayerDisplay playerDisplay;
    private ChitCardDisplay cardDisplay;
    private InputDisplay inputDisplay;
    private WinnerDisplay winnerDisplay;

    /**
     * Constructor for the DisplayManager.
     *
     * @param board      the Board instance representing the game board
     * @param players    an array of Player instances representing the players in the game
     */
    public DisplayManager(FieryDragons fieryDragons, GameWindow window, Board board, Player[] players) throws IOException, FontFormatException {
        this.window = window;
        this.titleDisplay = new TitleDisplay(fieryDragons, this, window, board);
        this.settingsDisplay = new SettingsDisplay(fieryDragons,this, window, board);
        this.gameDisplay = new GameDisplay(window);
    }

    public void createGameComponents(GameWindow window, Board board, Player[] players) {
        this.volcanoDisplay = new VolcanoDisplay(board.getVolcano(), gameDisplay);
        this.playerDisplay = new PlayerDisplay(players, board.getVolcano(), gameDisplay);
        this.cardDisplay = new ChitCardDisplay(board.getChitCards(), gameDisplay);
        this.inputDisplay = new InputDisplay(gameDisplay);
        this.winnerDisplay = new WinnerDisplay(gameDisplay);
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

    /**
     * Displays the winner of the game.
     * @param player the Player who has won the game
     */
    public void displayWin(Player player) {
        winnerDisplay.displayWinner(player);
        gameDisplay.showWinnerLabel(window.getFrame());
    }

    public void displayTitleScreen(JFrame frame) {
        gameDisplay.hideScreen(frame);
        settingsDisplay.hideScreen(frame);
        titleDisplay.showScreen(frame);
    }

    public void displayGameScreen(JFrame frame) {
        titleDisplay.hideScreen(frame);
        settingsDisplay.hideScreen(frame);
        gameDisplay.showScreen(frame);
    }

    public void displaySettingsScreen(JFrame frame) {
        titleDisplay.hideScreen(frame);
        gameDisplay.hideScreen(frame);
        settingsDisplay.showScreen(frame);
    }



}
