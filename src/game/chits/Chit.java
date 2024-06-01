package game.chits;

import game.chits.strategies.ChitStrategy;

/**
 * Shared flyweight class representing chits
 */
public class Chit {
    private final char displayChar;
    private final String name;
    private final char[][] displayDetail;
    private final char[][] displayCard;
    private final ChitStrategy strategy;

    /**
     * Constructor used by ChitFactory
     *
     * @param displayChar   the single character representation of the chit, for the Volcano
     * @param name          the name of this Chit, for the GameDisplay
     * @param displayDetail the detailed representation of the chit, for the Caves
     * @param displayCard   the card representation of the chit, for the ChitCards
     * @param strategy      the ChitStrategy which controls the behaviour of the Chit (e.g. moves Player backwards)
     */
    Chit(char displayChar, String name, char[][] displayDetail, char[][] displayCard, ChitStrategy strategy) {
        this.displayChar = displayChar;
        this.name = name;
        this.displayDetail = displayDetail;
        this.displayCard = displayCard;
        this.strategy = strategy;
    }

    /**
     * Used to check if Chit on card will allow a player to move
     * based on a Chit on the Volcano
     *
     * @param other the Chit this is being compared to
     * @return {@code true} if Chits are compatible, {@code false} otherwise
     */
    public boolean validate(Chit other) {
        return strategy.validate(this, other);
    }

    /**
     * Used to check if Chit on card matches given Chit
     * @param other the Chit this is being compared to
     * @return
     */
    public boolean directValidate(Chit other) {
        return this == other;
    }
    
    /**
     * Used to modify value of ChitCard (e.g. negate the value to move backwards)
     *
     * @param value the value to modify
     * @return the modified value
     */
    public int modifyValue(int value) {
        return strategy.modifyValue(value);
    }

    /**
     * Get character representation of this Chit
     *
     * @return character representation of this Chit
     */
    public char getDisplayChar() {
        return displayChar;
    }

    /**
     * Get detailed representation of this Chit
     *
     * @return detailed representation of this Chit
     */
    public char[][] getDisplayDetail() {
        char[][] clone = new char[displayDetail.length][];

        for (int i = 0; i < displayDetail.length; i++) {
            char[] chars = displayDetail[i];
            clone[i] = chars.clone();
        }

        return clone;
    }

    /**
     * Get card representation of this Chit
     *
     * @return card representation of this Chit
     */
    public char[][] getDisplayCard() {
        char[][] clone = new char[displayCard.length][];

        for (int i = 0; i < displayCard.length; i++) {
            char[] chars = displayCard[i];
            clone[i] = chars.clone();
        }

        return clone;
    }

    /**
     * Get name of this Chit
     *
     * @return name of this Chit
     */
    public String getName() {
        return name;
    }
}
