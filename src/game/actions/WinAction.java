package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

/**
 * Class used to represent a game action to end the game.
 */
public class WinAction extends MoveGameAction {

    /**
     * Constructor
     *
     * @param player the player performing the move action
     * @param dist   the distance to move
     */
    public WinAction(Player player, int dist) {
        super(player, dist);
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
        super.execute(board, display);
        System.out.println("Player " + player.getName() + "has won the game!");
        
        return null;
    }
}
