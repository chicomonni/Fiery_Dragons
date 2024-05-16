package game.displays;

import game.Board;
import game.Player;

/**
 * A Facade for interacting with the game Displays and Window
 */
public class DisplayManager {
    private final GameWindow gameWindow;
    private VolcanoDisplay volcanoDisplay;
    private PlayerDisplay playerDisplay;

    /**
     * Constructor
     *
     * @param gameWindow the GameWindow instance controlled by the DisplayManager
     */
    public DisplayManager(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void displayMove(Player player) {
        playerDisplay.update(player);
    }
}
