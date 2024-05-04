package game.tiles;

import game.MoveAction;
import game.Player;
import game.chits.Chit;

/**
 * Class representing a Cave in the Volcano
 */
public class Cave extends GameTile {
    private final Square next;
    private Player resident;
    private boolean canReturn = false;

    /**
     * Constructor
     *
     * @param next the Square connected to the Cave
     * @param chit the Chit associated with this cave
     */
    public Cave(Square next, Chit chit, int position) {
        super(position, chit);
        this.next = next;
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
     * Used by the Display to locate where to print the Position to the window
     *
     * @return the X coordinate of the location to print
     */
    @Override
    public int getX() {
        return 0;
    }

    /**
     * Used by the Display to locate where to print the Position to the window
     *
     * @return the Y coordinate of the location to print
     */
    @Override
    public int getY() {
        return 0;
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
        if (dist == 0 & canReturn & player == resident) {
//            TODO: update with correct constructor
            return new MoveAction();
        }

        if (dist > 0) {
            return next.move(player, dist - 1);
        }

        return null;
    }
}
