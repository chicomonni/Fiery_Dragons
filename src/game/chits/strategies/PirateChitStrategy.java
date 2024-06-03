package game.chits.strategies;

import game.chits.Chit;

import java.io.Serializable;

/**
 * Concrete strategy class for a pirate Chit. Game rule: pirate Chits move the Player backwards no matter the Chit
 * they're standing on
 */
public class PirateChitStrategy implements ChitStrategy, Serializable {
    /**
     * Used to check if Chit on card matches Chit on Volcano
     *
     * @param self  the Chit calling the method
     * @param other the other Chit being compared
     * @return {@code true} if Chits are compatible, {@code false} otherwise
     */
    @Override
    public boolean validate(Chit self, Chit other) {
        return true;
    }

    /**
     * Used to modify value of ChitCard (e.g. negate the value to move backwards)
     *
     * @param value the value to modify
     * @return the modified value
     */
    @Override
    public int modifyValue(int value) {
        return -value;
    }
}
