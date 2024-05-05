package game.tiles;

import game.Player;
import game.actions.MoveAction;
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
     * @param next the Square connected to the Cave
     * @param chit the Chit associated with this cave
     */
    public Cave(int position, Chit chit, Square next) {
        super(position, chit);
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
    public MoveAction move(Player player, int dist) {
        if (!canEnter(player)) {
            return null;
        }

        if (dist == 0) {
            return new MoveAction(this, player);
        }

        if (dist > 0) {
            return next.move(player, dist - 1);
        }

        return null;
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

    @Override
    public int[] calculateLocation(PlayerDisplay playerDisplay) {
        return playerDisplay.calculateCaveLocation(position);
    }
}
