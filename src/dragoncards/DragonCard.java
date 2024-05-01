package dragoncards;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import ascii.ASCIIProcessor;
import characters.Chit;
import locations.Location;


public abstract class DragonCard {
    protected int value = 1;
    protected boolean available = true;
    private final String CARD_BACK_DISPLAY_FILE = "src/ascii/cardback.txt";

    private final Chit chit;

    public DragonCard(Chit chit, int value) {
        this.chit = chit;
        this.value = value;
    }

    final public int moves(Location location) {
        return value;
    }

    abstract ArrayList<Location> getNext(Location currentLocation, int moves);

    public ArrayList<Location> getNext(Location currentlLocation) {
        return getNext(currentlLocation, value);
    }
    
    final public void reset() {
        available = true;
    }

    public void setUnavailable() {
        available = false;
    }

    public boolean isAvailable() {
        return available;
    }

    final public String getDisplay(int index) {
        if (available) {
            return getDisplayString(CARD_BACK_DISPLAY_FILE, index);
        }
        else {
            return getDisplayString(chit.getCardArtSource(), value);
        }
    }

    final protected String getDisplayString(String artSource, int value) {
        try {
            return ASCIIProcessor.getString(ASCIIProcessor.getArt(artSource, value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    final public Chit getChit() {
        return chit;
    }
}
