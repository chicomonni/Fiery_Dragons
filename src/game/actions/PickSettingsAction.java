package game.actions;

import game.Board;
import game.FieryDragons;
import game.Player;
import game.displays.DisplayManager;
import game.displays.GameWindow;

public class PickSettingsAction implements GameAction{
    private final int numPlayers;
    private final int numSquares;
    private final boolean isPirateChecked;
    private final boolean isRascalChecked;


    public PickSettingsAction(Player player, Board board, DisplayManager display, GameWindow window, int numPlayers, int numSquares, boolean isPirateChecked, boolean isRascalChecked) {
        this.numPlayers = numPlayers;
        this.numSquares = numSquares;
        this.isPirateChecked = isPirateChecked;
        this.isRascalChecked = isRascalChecked;


        //re-create board
//        display.displayGameScreen(window.getFrame());
        new PlayGameAction(player, board, display, window).execute(board, display);
    }

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        FieryDragons.setPlayers(numPlayers);
        //TODO: add board updates for others

        return null;
    }
}
