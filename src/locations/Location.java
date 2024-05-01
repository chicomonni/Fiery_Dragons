package locations;

import java.util.ArrayList;
import characters.Chit;

/**
 * Location is an abstract class that represents a location on the game board.
 
 */
public abstract class Location {
    /**
     * The chit that the location has.
     */
    public final Chit chit;

    /**
     * The coordinates of the location.
     */
    private final int x;
    private final int y;

    /**
     * Whether the location is occupied by a player.
     */
    private boolean occupied = false;

    /**
     * The next square that the player can move to.
     */
    protected Square nextSquare;

    /**
     * The constructor for the Location class.
     * @param chit The character that the location has.
     * @param x The x coordinate of the location.
     * @param y The y coordinate of the location.
     */
    public Location(Chit chit, int x, int y) {
        this.chit = chit;
        this.x = x;
        this.y = y;
    }

    /**
     * The getPreviousLocation method returns the previous location that
     * the player can move to within a given number of moves.
     * @param moves The number of moves that the player can make.
     * @return
     */
    abstract ArrayList<Location> getPreviousLocation(int moves);
    
    /**
     * The getNextLocation method returns the next location
     * that the player can move to within a given number of moves.
     * @param moves
     * @return
     */
    public ArrayList<Location> getNextLocation(int moves) {
        ArrayList<Location> nextLocations = new ArrayList<Location>();
        if (nextSquare != null) {
            nextLocations.add(nextSquare);
        }
        return nextLocations;
    }

    /**
     * The matchAnimal method returns whether the location's animal matches
     * the given chit.
     * @param chit The chit to match.
     * @return Whether the location's animal matches the given chit.
     */
    public final boolean matchAnimal(Chit chit) {
        return this.chit.equals(chit);
    }

    /**
     * The canMoveTo method returns whether the player can move to the location.
     * @param moves The number of moves that the player can make.
     * @return Whether the player can move to the location.
     */
    public boolean canMoveTo(int moves) {
        return occupied ? false : true;
    }
    
    /**
     * The setOccupied method sets the location to be occupied by a player.
     */
    public final void setOccupied() {
        occupied = true;
    }

    /**
     * The setUnoccupied method sets the location to be unoccupied by a player.
     */
    public final void setUnoccupied() {
        occupied = false;
    }

    /**
     * The setNextSquare method sets the next square that the player can move to.
     * @param square
     */
    public final void setNextSquare(Square square) {
        this.nextSquare = square;
    }

    /**
     * The getX method returns the x coordinate of the location.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * The getY method returns the y coordinate of the location.
     * @return
     */
    public int getY() {
        return y;
    }  
}
