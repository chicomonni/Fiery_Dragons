package dragoncards;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import locations.Location;
import ascii.ASCIIProcessor;

public class PirateCard extends DragonCard {
    private final String CARD_FRONT_DISPLAY_FILE = "src/ascii/CardPirate.txt";

    public PirateCard(int value) {
        this.value = value;
    }
    
    // @Override
    // public ArrayList<Location> getNext(Location location, int moves) {
        
    // }

    @Override
    public String getFrontDisplay() {
        try {
            return ASCIIProcessor.getArtWithValue(CARD_FRONT_DISPLAY_FILE, value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}