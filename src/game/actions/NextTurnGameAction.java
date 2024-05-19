package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

public class NextTurnGameAction implements GameAction {
    private final Player player;

    public NextTurnGameAction(Player player) {
        this.player = player;
    }


    @Override
    public GameAction execute(Board board, DisplayManager display) {
        player.startTurn(board, display);
        return null;
    }
}
