package game.positions;

import game.MoveAction;
import game.Player;

/**
 * Represents the expected methods for all locations the Player can move to (e.g. Square, Cave)
 */
public interface Position {
    /**
     * Used by the Display to locate where to print the Position to the window
     *
     * @return the X coordinate of the location to print
     */
    public int getX();

    /**
     * Used by the Display to locate where to print the Position to the window
     *
     * @return the Y coordinate of the location to print
     */
    public int getY();

    /**
     * Returns the desired MoveAction if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return the desired MoveAction or {@code null} if it cannot be performed
     */
    public MoveAction move(Player player, int dist);
}
