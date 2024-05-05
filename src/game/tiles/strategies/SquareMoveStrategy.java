package game.tiles.strategies;

import game.MoveAction;
import game.Player;

/**
 * A strategy interface so that Square implementation can change at run-time
 */
public interface SquareMoveStrategy {
    /**
     * Returns the desired MoveAction if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return the desired MoveAction or {@code null} if it cannot be performed
     */
    MoveAction move(Player player, int dist);
}
