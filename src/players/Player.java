package players;

import locations.Cave;
import locations.Location;

public class Player {
    private char character;
    private Cave home;
    private Location location;
    private int totalMoves = 0;
    
    public Player(char character, Cave home) {
        this.character = character;
        this.home = home;
        this.location = home;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void updateMoves(int moves) {
        totalMoves += moves;
    }

    public boolean checkWin() {
        return location.equals(home);
    }

    public String toString() {
        return "It is player " + this.character + "'s turn.";
    }

    public char getChar() {
        return character;
    }
}

