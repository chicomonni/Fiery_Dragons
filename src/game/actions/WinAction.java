package game.actions;

import game.Board;
import game.displays.DisplayManager;

/**
 * Class used to represent a game action to end the game.
 */
public class WinAction implements GameAction {

    /**
     * Constructor
     */
    public WinAction() {
    }

    /**
     * Executes the action to end the game.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return null since this action does not generate any new action
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        System.out.println("You win!");
        return null;
    }
}
