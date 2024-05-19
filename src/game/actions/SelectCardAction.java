package game.actions;

import game.Board;
import game.Player;
import game.chitCards.ChitCard;
import game.displays.DisplayManager;

public class SelectCardAction implements Action {
    private final Player player;
    private String playerInput;
    private ChitCard selectedCard;

    public SelectCardAction(Player player) {
        this.player = player;

    }

    @Override
    public void execute(Board board, DisplayManager display) {
        //get player input
//        playerInput = display.getInput(display.getGameWindow().getInputField());

        //verify if card is valid
        if(cardIsValid(playerInput)) {
            new FlipCardAction().execute(board, display);
        }
        else {
            player.selectCard(board, display);
        }

    }

    private boolean cardIsValid(String playerInput) {
        return true;
    }

    @Override
    public void updateDisplay(DisplayManager display) {

    }
}
