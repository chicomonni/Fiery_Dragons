package locations;

import java.util.ArrayList;
import characters.Chit;

public class Square extends Location{
    private Square previousSquare;
    private Cave cave;

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

    public void setPreviousSquare(Square square) {
        this.previousSquare = square;
    }

    public void setCave(Cave cave) {
        this.cave = cave;
    }    
}
