package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;
import game.displays.GameDisplay;
import game.displays.GameWindow;

public class PlayGameAction implements GameAction{
    private final Player player;

    public PlayGameAction( Player player, Board board, DisplayManager display, GameWindow window) {
        this.player = player;
        display.displayGameScreen(window.getFrame());
    }

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        player.startTurn(board, display);

        return null;
    }
}
