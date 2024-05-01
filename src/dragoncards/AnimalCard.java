package dragoncards;

import java.util.ArrayList;
import characters.Chit;
import locations.Location;

public class AnimalCard extends DragonCard{

    public AnimalCard(Chit chit, int value) {
        super(chit, value);
    }

    @Override
    public ArrayList<Location> getNext(Location currentLocation, int moves) {
        // not fully implemented yet!
        if (currentLocation.matchAnimal(getChit())) {
            ArrayList<Location> locations = new ArrayList<>();
            locations.add(currentLocation);
            return locations;
        }
        return null;
    }
}
