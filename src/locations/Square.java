package locations;

import java.util.ArrayList;
import characters.Chit;

/**
 * Square is a class that represents a square on the game board.
 */
public class Square extends Location{
    /**
     * The previous square that the player can move to.
     */
    private Square previousSquare;

    /**
     * The cave that the player can move to from the square.
     */
    private Cave cave;

    /**
     * The constructor for the Square class.
     * @param chit The character that the square has.
     * @param x The x coordinate of the square.
     * @param y The y coordinate of the square.
     */
    public Square(Chit chit, int x, int y) {
        super(chit, x, y);
    }

    @Override
    ArrayList<Location> getPreviousLocation(int moves) {
        ArrayList<Location> previousLocations = new ArrayList<Location>();
        if (previousSquare != null) {
            previousLocations.add(previousSquare);
        }
        return previousLocations;
    }

    @Override
    public ArrayList<Location> getNextLocation(int moves) {
        ArrayList<Location> nextLocations = new ArrayList<Location>();
        if (nextSquare != null) {
            nextLocations.add(nextSquare);
        }
        if (cave != null) {
            nextLocations.add(cave);
        }
        return nextLocations;
    }

    /**
     * The setPreviousSquare method sets the previous square that the player can move to.
     * @param square The previous square that the player can move to.
     */
    public void setPreviousSquare(Square square) {
        this.previousSquare = square;
    }

    /**
     * The setCave method sets the cave that the player can move to that
     * is connected to this square.
     * @param cave The cave that the player can move to.
     */
    public void setCave(Cave cave) {
        this.cave = cave;
    }    
}
