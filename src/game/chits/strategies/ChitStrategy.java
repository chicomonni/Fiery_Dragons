package game.chits.strategies;

import game.chits.Chit;

/**
 * Strategy interface used by ChitStrategy realisations to define Chit methods at run-time
 */
public interface ChitStrategy {
    /**
     * Used to check if Chit on card matches Chit on Volcano
     *
     * @param self  the Chit calling the method
     * @param other the other Chit being compared
     * @return {@code true} if Chits are compatible, {@code false} otherwise
     */
    boolean validate(Chit self, Chit other);

    /**
     * Used to modify value of ChitCard (e.g. negate the value to move backwards)
     *
     * @param value the value to modify
     * @return the modified value
     */
    int modifyValue(int value);
}
