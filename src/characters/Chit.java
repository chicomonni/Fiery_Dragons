package characters;

/**
 * Chit is a class that represents a character in the game. It has a name, a display character, and a card art source.
 */
public class Chit {
    /**
     * The display character is the character that represents the chit on the game board.
     */
    private char displayChar;
    /**
     * The name is the name of the character for use in the Flyweight.
     */
    private String name;
    /**
     * The card art source is the location of the card art for the chit.
     */
    private String cardArtSource;

    /**
     * The constructor for the Chit class.
     * @param name The name of the chit.
     * @param displayChar The display character of the chit.
     * @param cartArtSource The location of the card art for the chit.
     */
    protected Chit(String name, char displayChar, String cartArtSource) {
        this.name = name;
        this.displayChar = displayChar;
        this.cardArtSource = cartArtSource;
    }

    /**
     * Returns the name of the chit.
     * @return The name of the chit.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the display character of the chit.
     * @return The display character of the chit.
     */
    public char getDisplayChar() {
        return displayChar;
    }

    /**
     * Returns the location of the card art for the chit.
     * @return The location of the card art for the chit.
     */
    public String getCardArtSource() {
        return cardArtSource;
    }
}
