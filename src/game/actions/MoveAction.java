package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;
import game.tiles.GameTile;

public class MoveAction implements Action {

    public final GameTile to;
    public final Player player;

    public MoveAction(GameTile to, Player player) {
        this.to = to;
        this.player = player;
    }

    @Override
    public void execute(Board board, DisplayManager display) {
        player.position.occupied(false);
        to.occupied(true);
        player.position = to;
    }

    @Override
    public void updateDisplay(DisplayManager display) {
        display.displayMove(player);
    }
}
