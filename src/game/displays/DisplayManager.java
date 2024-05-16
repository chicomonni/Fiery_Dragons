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
    }

    public void displayMove(Player player) {
        playerDisplay.update(player);
    }

    public void displayFlip(ChitCard card) {
        cardDisplay.update(card);
    }
}
