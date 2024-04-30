package players;

import java.util.ArrayList;

public class PlayerIterator {
    private ArrayList<Player> players;
    private int currentPlayerIndex = 0;

    public PlayerIterator(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getNextPlayer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return currentPlayer;
    }
}
