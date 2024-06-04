package game.actions;

import game.Board;
import game.Player;
import game.displays.DisplayManager;
import game.tiles.GameTile;

public class SwapPositionGameAction implements GameAction {
    private final Player player;
    private final Player nearest;

    public SwapPositionGameAction(Player player, Player nearest) {
        this.player = player;
        this.nearest = nearest;
    }


    /**
     * Executes the game action.
     *
     * @param board   the game board
     * @param display the display manager for updating the game interface
     * @return the next game action to be executed
     */
    @Override
    public GameAction execute(Board board, DisplayManager display) {
        GameTile from = player.getPosition();
        GameTile to = nearest.getPosition();

        player.setPosition(to);
        display.displayMove(player);

        nearest.setPosition(from);
        display.displayMove(nearest);

        return new NextTurnGameAction(player);
    }
}
