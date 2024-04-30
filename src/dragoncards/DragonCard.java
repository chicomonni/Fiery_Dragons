package dragoncards;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import ascii.ASCIIProcessor;
import locations.Location;

public abstract class DragonCard {
    protected int value = 1;
    protected boolean available = true;
    private final String CARD_BACK_DISPLAY_FILE = "src/ascii/cardback.txt";

    public int moves(Location location) {
        return value;
    }

    // abstract ArrayList<Location> getNext(Location location, int moves);

    public void reset() {
        available = true;
    }

    public void setUnavailable() {
        available = false;
    }

    public boolean isAvailable() {
        return available;
    }

    abstract String getFrontDisplay();

    public String getBackDisplay(int value) {
        try {
            return ASCIIProcessor.getArtWithValue(CARD_BACK_DISPLAY_FILE, value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
