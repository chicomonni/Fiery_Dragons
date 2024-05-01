package players;

import locations.Cave;
import locations.Location;

/**
 * Player is a class that represents a player in the game.
 */
public class Player {
    /**
     * The character that the player is represented by on the board.
     */
    private char character;
    /**
     * The home cave of the player.
     */
    private Cave home;
    /**
     *  The current location of the player.
     */
    private Location location;
    /**
     *  The total number of moves that the player has made.
     */
    private int totalMoves = 0;
    
    /**
     *  The constructor for the Player class.
     * @param character The character that the player is represented by on the board.
     * @param home The home cave of the player.
     */
    public Player(char character, Cave home) {
        this.character = character;
        this.home = home;
        this.location = home;
    }

    /**
     * The getLocation method returns the current location of the player.
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     * The setLocation method sets the current location of the player.
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * The getTotalMoves method returns the total number of moves that the player has made.
     * @param moves The total number of new moves that the player has made.
     */
    public void updateMoves(int moves) {
        totalMoves += moves;
    }

    /**
     * The checkWin method returns whether the player has won the game.
     * @return
     */
    public boolean checkWin() {
        return location.equals(home);
    }

    /**
     * The toString method returns a the player's character representation
     * indicating their turn.
     */
    public String toString() {
        return "It is player " + this.character + "'s turn.";
    }

    /**
     * The getChar method returns the character that the player
     * is represented by on the board.
     * @return
     */
    public char getChar() {
        return character;
    }
}

