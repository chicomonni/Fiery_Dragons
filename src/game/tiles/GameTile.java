package game.tiles;

import game.Player;
import game.actions.MoveAction;
import game.chits.Chit;
import game.displays.PlayerDisplay;

/**
 * Represents the abstract base class for all locations the Player can move to (e.g. Square, Cave)
 */
public abstract class GameTile {
    protected final int position;
    private final Chit chit;
    protected boolean isOccupied;

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

    /**
     * Get Chit instance associated with this tile
     *
     * @return the Chit instance associated with this tile
     */
    public Chit getChit() {
        return chit;
    }

    /**
     * Returns the desired MoveAction if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return the desired MoveAction or {@code null} if it cannot be performed
     */
    public abstract MoveAction move(Player player, int dist);

    /**
     * Check if a Player can enter this GameTile
     *
     * @param player the Player trying to enter
     * @return {@code true} if the Player can enter, {@code false}
     */
    public abstract boolean canEnter(Player player);

    public void occupied(boolean state) {
        isOccupied = state;
    }

    public abstract int[] calculateLocation(PlayerDisplay playerDisplay);
}
