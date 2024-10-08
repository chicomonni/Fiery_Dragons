package game.chits.strategies;

import game.Player;
import game.actions.GameAction;
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
     * Used to get the action performed after flipping a card with this chit
     *
     * @param player the player whose turn it is
     * @param value  the value on the card
     * @return the Action to be performed
     */
    @Override
    public GameAction getAction(Player player, int value) {
        return player.getPosition().getAction(player, -value);
    }
}
