package dragoncards;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import ascii.ASCIIProcessor;
import characters.Chit;
import locations.Location;

/**
 * DragonCard is an abstract class that represents a card in the game.
 */
public abstract class DragonCard {
    /**
     * The value of the card.
     */
    protected int value = 1;
    /**
     * Whether the card is available to be picked.
     */
    protected boolean available = true;
    /**
     * The location of the card back display file.
     */
    private final String CARD_BACK_DISPLAY_FILE = "src/ascii/cardback.txt";

    /**
     * The character that the card has.
     */
    private final Chit chit;

    /**
     * The constructor for the DragonCard class.
     * @param chit The character that the card has.
     * @param value The value of the card.
     */
    public DragonCard(Chit chit, int value) {
        this.chit = chit;
        this.value = value;
    }

    /**
     * The moves method returns the number of moves that the player can make with the card.
     * @param location
     * @return
     */
    final public int moves(Location location) {
        return value;
    }

    /**
     * The getNext method returns the next location that the player can move to with the card.
     * @param currentLocation   The current location of the player.
     * @param moves The number of moves that the player can make.
     * @return The next location that the player can move to with the card.
     */
    abstract ArrayList<Location> getNext(Location currentLocation, int moves);

    /**
     * The getNext method returns the next location that the player can move to with the card.
     * @param currentlLocation The current location of the player.
     * @return The next location that the player can move to with the card.
     */
    public ArrayList<Location> getNext(Location currentlLocation) {
        return getNext(currentlLocation, value);
    }
    
    /**
     * The reset method resets the card to be available to be picked.
     */
    final public void reset() {
        available = true;
    }

    /**
     * The setUnavailable method sets the card to be unavailable to be picked.
     */
    public void setUnavailable() {
        available = false;
    }

    /**
     * The isAvailable method returns whether the card is available to be picked.
     * @return
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * The getDisplay method returns the display of the card.
     * @param index The index of the card.
     * @return The display of the card.
     */
    final public String getDisplay(int index) {
        if (available) {
            return getDisplayString(CARD_BACK_DISPLAY_FILE, index);
        }
        else {
            return getDisplayString(chit.getCardArtSource(), value);
        }
    }

    /**
     * The getDisplayString method returns the display of the card.
     * @param artSource The location of the card art.
     * @param value The value of the card.
     * @return The display of the card.
     */
    final protected String getDisplayString(String artSource, int value) {
        try {
            return ASCIIProcessor.getString(ASCIIProcessor.getArt(artSource, value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The getChit method returns the character that the card has.
     * @return  The character that the card has.
     */
    final public Chit getChit() {
        return chit;
    }
}
