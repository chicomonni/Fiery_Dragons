package game.tiles;

import game.Player;
import game.actions.DisplayMessageGameAction;
import game.actions.GameAction;
import game.actions.MoveGameAction;
import game.actions.WinAction;
import game.chits.Chit;
import game.displays.PlayerDisplay;

import java.io.Serializable;

/**
 * Represents the abstract base class for all locations the Player can move to (e.g. Square, Cave)
 */
public abstract class GameTile implements Serializable {
    private final Chit chit;
    protected Player occupiedBy;

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

        // If the player can win, return a WinAction immediately
        if (winningMove(player, dist)) {
            return new WinAction(player, dist);

            // Otherwise if the player can move, return a MoveGameAction
        } else if (canMove(player, dist)) {
            return new MoveGameAction(player, dist);
        }

        // Otherwise it is the end of their turn
        return new DisplayMessageGameAction("CAN'T MOVE", true, player);
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
     * Get the player currently standing on this tile.
     *
     * @return the player standing on this tile, {@code null} if no player is here
     */
    public Player getOccupiedBy() {
        return occupiedBy;
    }

    /**
     * Sets the player currently standing on this tile.
     *
     * @param player the player standing on this tile, {@code null} if no player is here
     */
    public void setOccupiedBy(Player player) {
        occupiedBy = player;
    }

    /**
     * Double dispatch to calculate location of a specific GameTile subclass
     *
     * @param playerDisplay the playerDisplay initiating the dispatch
     * @return an int array containing the coordinates (x, y)
     */
    public abstract int[] calculateLocation(PlayerDisplay playerDisplay);

    /**
     * Get the nearest player to this tile
     *
     * @return the nearest player, {@code null} if no player is found
     */
    public abstract Player getNearestPlayer();
}
