package game.tiles;

import game.Player;
import game.chits.Chit;
import game.displays.PlayerDisplay;

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
        if (dist > 0 && player.getPosition() == this) {
            return next.canMove(player, dist - 1);
        }

        // Can only enter a cave by exactly landing on it
        return dist == 0;
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
            this.setVacancy(true);

            // The only case where dist can be > 0 is if the player is currently in the cave
            // (i.e. the first move to leave the cave)
            if (dist > 0) {
                next.move(player, dist - 1);
                return;
            }
        }

        // Can only enter a cave by exactly landing on it
        if (dist == 0) {
            player.setPosition(this);
            setVacancy(false);
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
        return canReturn && player == resident;
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
}
