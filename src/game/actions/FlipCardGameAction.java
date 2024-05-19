package game.actions;

import game.Board;
import game.Player;
import game.chitCards.ChitCard;
import game.displays.DisplayManager;

public class FlipCardGameAction implements GameAction {
    private final Player player;
    private final ChitCard card;

    public FlipCardGameAction(Player player, ChitCard card) {
        this.player = player;
        this.card = card;
    }

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        card.flip();
        System.out.println("Value is: " + card.getValue());
        display.displayFlip(card);

        if (card.isMatch(player.getPosition())) {
            System.out.println(1);
            return player.getPosition().getAction(player, card.getValue());
        }
        return new EndTurnGameAction(player);
    }
}
