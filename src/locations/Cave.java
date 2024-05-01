package locations;

import java.util.ArrayList;
import characters.Chit;
import game.FieryDragons;

public class Cave extends Location {
    
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
