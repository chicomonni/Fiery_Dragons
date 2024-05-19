package game.actions;

import game.Board;
import game.displays.DisplayManager;

public class WinAction implements GameAction {

    @Override
    public GameAction execute(Board board, DisplayManager display) {
        System.out.println("You win!");
        return null;
    }
    
}
