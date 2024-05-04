package game.tiles;

import game.MoveAction;
import game.Player;
import game.chits.Chit;

/**
 * Represents the abstract base class for all locations the Player can move to (e.g. Square, Cave)
 */
public abstract class GameTile {
    protected final int position;
    private final Chit chit;

    /**
     * Constructor.
     *
     * @param position an integer representing its position, so it can be located by the Display
     * @param chit     the Chit instance associated with this tile
     */
    protected GameTile(int position, Chit chit) {
        this.position = position;
        this.chit = chit;
    }

    public Chit getChit() {
        return chit;
    }

    /**
     * Used by the Display to locate where to print the GameTile to the window
     *
     * @return the X coordinate of the location to print
     */
    public abstract int getX();

    /**
     * Used by the Display to locate where to print the GameTile to the window
     *
     * @return the Y coordinate of the location to print
     */
    public abstract int getY();

    /**
     * Returns the desired MoveAction if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return the desired MoveAction or {@code null} if it cannot be performed
     */
    public abstract MoveAction move(Player player, int dist);
}
