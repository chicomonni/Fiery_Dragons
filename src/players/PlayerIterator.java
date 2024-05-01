package players;

import java.util.ArrayList;

/**
 *  PlayerIterator is a class that represents an iterator for the players in the game.
 
 */
public class PlayerIterator {
    /**
     * The list of players in the game.
     */
    private ArrayList<Player> players;
    /**
     *  The index of the current player.
     */
    private int currentPlayerIndex = 0;

    /**
     * The constructor for the PlayerIterator class.
     * @param players The list of players in the game.
     */
    public PlayerIterator(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * The default constructor for the PlayerIterator class.
     */
    public PlayerIterator() {
        this.players = new ArrayList<Player>();
    }

    /**
     * The addPlayer method adds a player to the game.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * The getNextPlayer method returns the next player in the game.
     * @return The next player in the game.
     */
    public Player getNextPlayer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return currentPlayer;
    }

    /**
     * The getCurrentPlayer method returns the current player in the game.
     * @return The current player in the game.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * The getPlayers method returns the list of all players in the game.
     * @return The list of all players in the game.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
