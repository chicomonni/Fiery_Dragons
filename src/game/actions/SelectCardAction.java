package game.actions;

import game.Board;
import game.Player;
import game.chitCards.ChitCard;
import game.displays.DisplayManager;

public class SelectCardAction implements Action {
    private final Player player;
    private ChitCard selectedCard;

    public SelectCardAction(Player player) {
        this.player = player;
    }

    @Override
    public void execute(Board board, DisplayManager display) {

    }

    @Override
    public void updateDisplay(DisplayManager display) {

    }
}
