package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

public class InvalidGameAction implements GameAction {
    private final String message;
    private final Player player;

    public InvalidGameAction(String message, Player player) {
        this.message = message;
        this.player = player;
    }

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        display.displayError(message);
        return new NextTurnGameAction(player);
    }
}
