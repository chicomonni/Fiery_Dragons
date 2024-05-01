package dragoncards;

import java.util.ArrayList;
import characters.Chit;
import locations.Location;

/**
 * AnimalCard is a class that represents a DragonCard that has an animal on it.

 */
public class AnimalCard extends DragonCard{

    /**
     * The constructor for the AnimalCard class.
     * @param chit The character that the animal has.
     * @param value The value of the animal card.
     */
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
