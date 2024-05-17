package game.chits.strategies;

import game.chits.Chit;

/**
 * Concrete strategy class for an animal Chit. Game rule: animal Chits move the Player forward if the Chit they're
 * standing on matches the ChitCard flipped
 */
public class AnimalChitStrategy implements ChitStrategy {
    /**
     * Used to check if Chit on card matches Chit on Volcano
     *
     * @param self  the Chit calling the method
     * @param other the other Chit being compared
     * @return {@code true} if Chits are compatible, {@code false} otherwise
     */
    @Override
    public boolean validate(Chit self, Chit other) {
        return self == other;
    }

    /**
     * Used to modify value of ChitCard (e.g. negate the value to move backwards)
     *
     * @param value the value to modify
     * @return the modified value
     */
    @Override
    public int modifyValue(int value) {
        return value;
    }
}
