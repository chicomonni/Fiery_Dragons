package game.tiles.strategies;

import game.Player;

/**
 * A strategy interface so that Square implementation can change at run-time
 */
public interface SquareMoveStrategy {
    /**
     * Checks if Player can move a specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return {@code true} if Player can move, {@code false} otherwise
     */
    boolean canMove(Player player, int dist);

    /**
     * Check if a Player can win with the given number of moves
     * @param player the Player trying to win
     * @param dist the number of moves the Player could move along the Volcano
     * @return {@code true} if the Player can win, {@code false} otherwise
     */

    boolean winningMove(Player player, int dist);
    
    /**
     * Moves the Player the specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     */
    void move(Player player, int dist);
}
