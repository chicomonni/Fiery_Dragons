package game.actions;

import game.Board;
import game.FieryDragons;
import game.Player;
import game.displays.DisplayManager;
import game.displays.GameWindow;

public class PickSettingsAction implements GameAction{

    public PickSettingsAction(Player player, Board board, DisplayManager display, GameWindow gameWindow) {
        display.displaySettingsScreen(gameWindow.getWindow());
    }

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        return null; //new PlayGameAction(FieryDragons.getPlayers()[0], board, display, display.getWindow()).execute(board, display);
    }
}
