package game.displays;

import game.Board;

/**
 * A Facade for interacting with the game Displays and Window
 */
public class DisplayManager {
    private final GameWindow gameWindow;

    /**
     * Constructor
     *
     * @param gameWindow the GameWindow instance controlled by the DisplayManager
     */
    public DisplayManager(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void createBoard(Board board) {

    }
}
