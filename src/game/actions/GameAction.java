package game.actions;

import game.Board;
import game.displays.DisplayManager;

public interface GameAction {
    GameAction execute(Board board, DisplayManager display);
}
