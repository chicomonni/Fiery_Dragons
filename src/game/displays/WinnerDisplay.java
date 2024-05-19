package game.displays;

import javax.swing.JLabel;
import game.Player;

public class WinnerDisplay {
    
    private final JLabel winnerPane;
    /**
     * Constructor
     *
     * @param volcano    the Volcano instance used by the game
     * @param gameWindow the GameWindow instance this class affects
     */
    public WinnerDisplay(GameWindow gameWindow) {
        winnerPane = gameWindow.getWinnerDisplay();
    }

    /**
     * Displays the winner of the game
     * @param player the Player that won the game
     */
    public void displayWinner(Player player) {
        winnerPane.setText(player.getName() + " wins!");
        winnerPane.setForeground(player.getColour());
    }
}
