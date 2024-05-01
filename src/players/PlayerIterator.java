package players;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerIterator {
    private ArrayList<Player> players;
    private int currentPlayerIndex = 0;

    public PlayerIterator(ArrayList<Player> players) {
        this.players = players;
    }

    public PlayerIterator() {
        this.players = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getNextPlayer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return currentPlayer;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
