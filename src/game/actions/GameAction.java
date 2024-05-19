package game.actions;

import game.Board;
import game.displays.DisplayManager;

/**
 * Class used to represent a game action that can be executed.
 */
public interface GameAction {
    /**
     * Executes the game action.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return the next game action to be executed
     */
    GameAction execute(Board board, DisplayManager display);
}
