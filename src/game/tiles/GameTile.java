package game.tiles;

import game.Player;
import game.actions.EndTurnGameAction;
import game.actions.GameAction;
import game.actions.MoveGameAction;
import game.actions.WinAction;
import game.chits.Chit;
import game.displays.PlayerDisplay;

/**
 * Represents the abstract base class for all locations the Player can move to (e.g. Square, Cave)
 */
public abstract class GameTile {
    private final Chit chit;
    protected boolean vacant = true;

    /**
     * Constructor.
     *
     * @param chit the Chit instance associated with this tile
     */
    protected GameTile(Chit chit) {
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
     * Returns a relevant Action if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return a relevant Action or EndTurnGameAction if it cannot be performed
     */
    public GameAction getAction(Player player, int dist) {
        dist = dist + Integer.signum(dist);

        if (winningMove(player, dist)) {
            return new WinAction(player, dist);
        } else if (canMove(player, dist)) {
            return new MoveGameAction(player, dist);
        }
        return new EndTurnGameAction(player);
    }

    /**
     * Checks if Player can move a specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return {@code true} if Player can move, {@code false} otherwise
     */
    public abstract boolean canMove(Player player, int dist);

    /**
     * Moves the Player the specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     */
    public abstract void move(Player player, int dist);

    /**
     * Check if a Player can enter this GameTile
     *
     * @param player the Player trying to enter
     * @return {@code true} if the Player can enter, {@code false}
     */
    public abstract boolean canEnter(Player player);

    /**
     * Check if a Player can win with the given number of moves
     *
     * @param player the Player trying to win
     * @param dist   the number of moves the Player could move along the Volcano
     * @return {@code true} if the Player can win, {@code false} otherwise
     */

    public abstract boolean winningMove(Player player, int dist);

    /**
     * Sets the vacancy state of this GameTile.
     *
     * @param state {@code true} if the tile should be marked as vacant, {@code false} otherwise
     */
    public void setVacancy(boolean state) {
        vacant = state;
    }

    /**
     * Double dispatch to calculate location of a specific GameTile subclass
     *
     * @param playerDisplay the playerDisplay initiating the dispatch
     * @return an int array containing the coordinates (x, y)
     */
    public abstract int[] calculateLocation(PlayerDisplay playerDisplay);
}
