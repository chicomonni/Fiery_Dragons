package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

public class EndTurnGameAction implements GameAction {
    private final Player player;

    public EndTurnGameAction(Player player) {
        this.player = player;
    }

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        player.endTurn(board, display);
        return null;
    }
}
