package game.tiles;

import game.Player;
import game.chits.Chit;
import game.displays.PlayerDisplay;

import java.awt.*;

/**
 * Class representing a Cave in the Volcano
 */
public class Cave extends GameTile {
    private final Square next;
    private Player resident;
    private boolean canReturn;

    /**
     * Constructor
     *
     * @param chit the Chit associated with this cave
     * @param next the Square connected to the Cave
     */
    public Cave(Chit chit, Square next) {
        super(chit);
        this.next = next;
    }

    /**
     * Method to check if a Player is this Cave's resident
     *
     * @return {@code true} if the Player instance resides in this Cave, {@code false} otherwise
     */
    public boolean isResident(Player player) {
        return player == resident;
    }

    /**
     * Method to set the Player instance that resides in the Cave
     */
    public void setResident(Player resident) {
        this.resident = resident;
    }

    /**
     * Setter to allow/forbid Player from re-entering Cave
     *
     * @param canReturn {@code true} if Player can enter, {@code false} otherwise
     */
    public void setCanReturn(boolean canReturn) {
        this.canReturn = canReturn;
    }

    /**
     * Returns the desired MoveAction if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return the desired MoveAction or {@code null} if it cannot be performed
     */
    @Override
    public boolean canMove(Player player, int dist) {
        // The only case where dist can be > 0 is if the player is currently in the cave
        // (i.e. the first move to leave the cave)
        if (dist > 1 && player.getPosition() == this) {
            return next.canMove(player, dist - 1);
        }

        // Can only enter a cave by exactly landing on it in the forwards direction
        return dist == 1;
    }

    /**
     * Moves the Player the specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     */
    @Override
    public void move(Player player, int dist) {
        if (player.getPosition() == this) {
            this.setOccupiedBy(null);

            // The only case where dist can be > 1 is if the player is currently in the cave
            // (i.e. the first move to leave the cave)
            if (dist > 1) {
                next.move(player, dist - 1);
                return;
            }
        }

        // Can only enter a cave by exactly landing on it in the forwards direction
        if (dist == 1) {
            player.setPosition(this);
        }
    }

    /**
     * Check if a Player can enter this GameTile
     *
     * @param player the Player trying to enter
     * @return {@code true} if the Player can enter, {@code false}
     */
    @Override
    public boolean canEnter(Player player) {
        return occupiedBy == null && canReturn && player == resident;
    }

    /**
     * Check if a Player can win with the given number of moves
     *
     * @param player the Player trying to win
     * @param dist   the number of moves the Player could move along the Volcano
     * @return {@code true} if the Player can win, {@code false} otherwise
     */

    public boolean winningMove(Player player, int dist) {
        return dist == 1 && canEnter(player);
    }

    /**
     * Double dispatch to calculate location of a specific GameTile subclass
     *
     * @param playerDisplay the playerDisplay initiating the dispatch
     * @return an int array containing the coordinates (x, y)
     */
    @Override
    public int[] calculateLocation(PlayerDisplay playerDisplay) {
        return playerDisplay.calculateLocation(this);
    }

    /**
     * Get the nearest player to this tile
     *
     * @return the nearest player, {@code null} if no player is found
     */
    @Override
    public Player getNearestPlayer() {
        return null;
    }

    /**
     * Get the colour of the Cave, same as the colour of the Player resident
     *
     * @return the colour of the Cave
     */
    public Color getColour() {
        return resident.getColour();
    }
}
