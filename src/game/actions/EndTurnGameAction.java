package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

/**
 * Class used to represent a game action to end a player's turn.
 */
public class EndTurnGameAction implements GameAction {
    private final Player player;

    /**
     * Constructor
     *
     * @param player the player whose turn is ending
     */
    public EndTurnGameAction(Player player) {
        this.player = player;
    }

    /**
     * Executes the end turn game action.
     * Ends the player's turn by calling the endTurn method of the player object.
     * Null is returned because no subsequent action is triggered immediately after ending the turn.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return null, indicating no subsequent action is immediately triggered
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        player.endTurn(board, display);
        return null;
    }
}
