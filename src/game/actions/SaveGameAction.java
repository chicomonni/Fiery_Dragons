package game.actions;

import game.Board;
import game.FieryDragons;
import game.Player;
import game.displays.DisplayManager;

/**
 * Class used to represent a game action to save the game.
 */
public class SaveGameAction implements GameAction {
    private final FieryDragons gameData;
    private final Player player;

    /**
     * Constructor
     *
     * @param gameData the game data to save
     * @param player   the player saving the game
     */
    public SaveGameAction(FieryDragons gameData, Player player) {
        this.gameData = gameData;
        this.player = player;
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
        gameData.saveGame();
        return new DisplayMessageGameAction("GAME SAVED", false, player);
    }
}
