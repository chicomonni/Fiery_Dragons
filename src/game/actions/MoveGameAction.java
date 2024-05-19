package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;

public class MoveGameAction implements GameAction {
    private final Player player;
    private final int dist;


    public MoveGameAction(Player player, int dist) {
        this.player = player;
        this.dist = dist;
    }

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        player.getPosition().move(player, dist);
        display.displayMove(player);
        return new NextTurnGameAction(player);
    }
}
