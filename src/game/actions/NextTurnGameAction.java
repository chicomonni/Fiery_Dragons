package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

/**
 * Class used to represent a game action to proceed to the next turn.
 */
public class NextTurnGameAction implements GameAction {
    private final Player player;

    /**
     * Constructor
     *
     * @param player the player for whom the next turn action is being executed
     */
    public NextTurnGameAction(Player player) {
        this.player = player;
    }

    /**
     * Executes the action to proceed to the next turn for the player.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return null since this action does not generate any new action
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        player.startTurn(board, display);
        return null;
    }
}
