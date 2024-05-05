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

    public void createBoard(Board board) {
        volcanoDisplay = new VolcanoDisplay(board.getBoardRep(), gameWindow);
        playerDisplay = new PlayerDisplay(board.getNoSquares(), board.getNoCaves());
    }

    public void displayMove(Player player) {
//        TODO: implement
    }
}
