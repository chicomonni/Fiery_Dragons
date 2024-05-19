package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

/**
 * Class used to represent an invalid game action.
 * This action is executed when an invalid action is attempted by a player.
 */
public class InvalidGameAction implements GameAction {
    private final String message;
    private final Player player;

    /**
     * Constructs an InvalidGameAction with the specified error message and player.
     *
     * @param message the error message indicating the reason for the invalid action
     * @param player  the player associated with the invalid action
     */
    public InvalidGameAction(String message, Player player) {
        this.message = message;
        this.player = player;
    }

    /**
     * Executes the invalid action, displaying the error message to the player.
     * This method notifies the display manager to show the error message and proceeds to the next turn.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return a NextTurnGameAction to proceed to the next turn
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        display.displayError(message);
        return new NextTurnGameAction(player);
    }
}
