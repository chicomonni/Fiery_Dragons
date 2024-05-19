package game.displays;

import game.Board;
import game.Player;
import game.actions.Action;
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

    /**
     * Constructor
     *
     * @param gameWindow the GameWindow instance controlled by the DisplayManager
     */
    public DisplayManager(GameWindow gameWindow, Board board, Player[] players) {
        this.gameWindow = gameWindow;
        this.volcanoDisplay = new VolcanoDisplay(board.getVolcano(), gameWindow);
        this.playerDisplay = new PlayerDisplay(players, board.getVolcano(), gameWindow);
        this.cardDisplay = new ChitCardDisplay(board.getChitCards(), gameWindow);
        this.inputDisplay = new InputDisplay(gameWindow);
    }

    public void displayMove(Player player) {
        playerDisplay.update(player);
    }

    public void displayFlip(ChitCard card) {
        cardDisplay.update(card);
    }

    public void playTurn(Player player) {
        String promptFlipText = " FLIP A CHIT CARD (1 - 16)"; //TODO: change to get number of cards later
        inputDisplay.setPromptText(player, promptFlipText);
    }

    public Action getInput(Player player, Board board) {
        while (!inputDisplay.isInputReady()) {
            // Waiting for the input to be ready (after pressing Enter)
            try {
                Thread.sleep(100); // Sleep briefly to avoid busy-waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Action action = inputDisplay.getInput(player, board);
        inputDisplay.resetInput(); // Reset input after reading
        return action;
    }
}
