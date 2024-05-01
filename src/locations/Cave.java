package locations;

import java.util.ArrayList;
import characters.Chit;
import game.FieryDragons;

/**
 * Cave is a class that represents a home for each player in the game.
 
 */
public class Cave extends Location {
    /**
     * The constructor for the Cave class.
     * @param chit The character that the cave has.
     * @param x    The x coordinate of the cave.
     * @param y    The y coordinate of the cave.
     */
    public Cave(Chit chit, int x, int y) {
        super(chit, x, y);
    }

    @Override
    public ArrayList<Location> getPreviousLocation(int moves) {
        return null;
    }

    @Override
    public boolean canMoveTo(int moves) {
        return moves == FieryDragons.getInstance().getTotalSquares();
    }
}
