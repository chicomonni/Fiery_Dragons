package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

public class DisplayMessageGameAction implements GameAction {
    private final String message;
    private final boolean endTurn;
    private final Player player;

    public DisplayMessageGameAction(String message, boolean endTurn, Player player) {
        this.message = message;
        this.endTurn = endTurn;
        this.player = player;
    }


    /**
     * Executes the game action.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return the next game action to be executed
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        display.displayMessage(message);

        return (endTurn) ? new EndTurnGameAction(player) : new NextTurnGameAction(player);
    }
}
