package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

/**
 * Class used to represent a game action to move the player.
 */
public class MoveGameAction implements GameAction {
    protected final Player player;
    private final int dist;

    /**
     * Constructor
     *
     * @param player the player performing the move action
     * @param dist   the distance to move
     */
    public MoveGameAction(Player player, int dist) {
        this.player = player;
        this.dist = dist;
    }

    /**
     * Executes the action to move the player on the game board.
     * This method moves the player and updates the display accordingly.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return a NextTurnGameAction to proceed to the next turn
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        player.getPosition().move(player, dist);
        display.displayMove(player);

        int distance = Math.abs(dist - 1);
        String direction = dist >= 0 ? "FORWARD" : "BACKWARD";
        String message = player.getName().toUpperCase() + " MOVED " + direction + " " + distance + " TILES";

        return new DisplayMessageGameAction(message, false, player);
    }
}
