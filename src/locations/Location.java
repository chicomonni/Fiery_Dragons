package locations;

import java.util.ArrayList;
import characters.Chit;

public abstract class Location {
    public final Chit chit;
    private final int x;
    private final int y;
    private boolean occupied = false;
    protected Square nextSquare;

    public Location(Chit chit, int x, int y) {
        this.chit = chit;
        this.x = x;
        this.y = y;
    }

    abstract ArrayList<Location> getPreviousLocation(int moves);
    
    public ArrayList<Location> getNextLocation(int moves) {
        ArrayList<Location> nextLocations = new ArrayList<Location>();
        if (nextSquare != null) {
            nextLocations.add(nextSquare);
        }
        return nextLocations;
    }

    public final boolean matchAnimal(Chit chit) {
        return this.chit.equals(chit);
    }

    public boolean canMoveTo(int moves) {
        return occupied ? false : true;
    }
    
    public final void setOccupied() {
        occupied = true;
    }

    public final void setUnoccupied() {
        occupied = false;
    }

    public final void setNextSquare(Square square) {
        this.nextSquare = square;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }  
}
