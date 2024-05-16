package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

public class MoveAction implements Action {
    private final Player player;
    private final int dist;


    public MoveAction(Player player, int dist) {
        this.player = player;
        this.dist = dist;
    }

    @Override
    public void execute(Board board, DisplayManager display) {
        player.getPosition().move(player, dist);
    }

    @Override
    public void updateDisplay(DisplayManager display) {
        display.displayMove(player);
    }
}
