package dragoncards;

import java.util.ArrayList;
import locations.Location;
import characters.Chit;

/**
 * PirateCard is a class that represents a DragonCard that has a pirate on it.
 */
public class PirateCard extends DragonCard {
    /**
     * The constructor for the PirateCard class.
     * @param chit The character that the pirate has.
     * @param value The value of the pirate card.
     */
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