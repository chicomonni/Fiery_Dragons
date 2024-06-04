package game.chits.strategies;

import game.Player;
import game.actions.EndTurnGameAction;
import game.actions.GameAction;
import game.actions.SwapPositionGameAction;
import game.chits.Chit;

public class RascalChitStrategy implements ChitStrategy {
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
        Player nearest = player.getPosition().getNearestPlayer();
        if (nearest == null) {
            return new EndTurnGameAction(player);
        }
        return new SwapPositionGameAction(player, nearest);
    }
}
