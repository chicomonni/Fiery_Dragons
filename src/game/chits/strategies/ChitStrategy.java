package game.chits.strategies;

import game.Player;
import game.actions.GameAction;
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
     * Used to get the action performed after flipping a card with this chit
     *
     * @param player the player whose turn it is
     * @param value  the value on the card
     * @return the Action to be performed
     */
    GameAction getAction(Player player, int value);
}
