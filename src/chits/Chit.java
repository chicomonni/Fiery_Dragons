package chits;

import chits.strategies.ChitStrategy;

import java.util.List;

/**
 * Shared flyweight class representing chits
 */
public class Chit {
    private final char displayChar;
    private final List<String> displayDetail;
    private final ChitStrategy strategy;

    /**
     * Constructor used by ChitFactory
     *
     * @param displayChar   the single character representation of the chit, for the Volcano
     * @param displayDetail the detailed representation of the chit, for the ChitCards. Each entry in the List
     *                      represents a new line of the representation.
     * @param strategy      the ChitStrategy which controls the behaviour of the Chit (e.g. moves Player backwards)
     */
    Chit(char displayChar, List<String> displayDetail, ChitStrategy strategy) {
        this.displayChar = displayChar;
        this.displayDetail = displayDetail;
        this.strategy = strategy;
    }

    /**
     * Used to check if Chit on card matches Chit on Volcano
     *
     * @param other the Chit this is being compared to
     * @return {@code true} if Chits are compatible, {@code false} otherwise
     */
    public boolean validate(Chit other) {
        return strategy.validate(this, other);
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
    public List<String> getDisplayDetail() {
        return displayDetail;
    }
}
