package game.actions;

import game.Board;
import game.Player;
import game.chitCards.ChitCard;
import game.displays.DisplayManager;


/**
 * Class used to represent a game action to flip a chit card.
 */
public class FlipCardGameAction implements GameAction {
    private final Player player;
    private final ChitCard card;

    /**
     * Constructor
     *
     * @param player the player performing the action
     * @param card   the chit card being flipped
     */
    public FlipCardGameAction(Player player, ChitCard card) {
        this.player = player;
        this.card = card;
    }

    /**
     * Executes the flip card game action.
     * Flips the chit card, updates the display, and determines the next action based on whether there is a match.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return the next game action to be executed
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        card.flip();
        display.displayFlip(card);

        if (card.isMatch(player.getPosition())) {
            return player.getPosition().getAction(player, card.getValue(player));
        }
        return new EndTurnGameAction(player);
    }
}
