package dragoncards;

import java.util.ArrayList;
import locations.Location;
import characters.Chit;

public class PirateCard extends DragonCard {
    public PirateCard(Chit chit, int value) {
        super(chit, value);
    }
    
    @Override
    public ArrayList<Location> getNext(Location currentLocation, int moves) {
        // not fully implemented yet!
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(currentLocation);
        return locations;
    }
}