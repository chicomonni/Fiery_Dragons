package game.actions;

import game.Board;
import game.displays.DisplayManager;

public interface Action {
    public void execute(Board board, DisplayManager display);

    void updateDisplay(DisplayManager display);
}
