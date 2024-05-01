package players;

import locations.Cave;
import locations.Location;

public class Player {
    private String name;
    private Cave home;
    private Location location;
    private int totalMoves = 0;
    
    public Player(String name, Cave home) {
        this.name = name;
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
        return "It is player " + this.name + "'s turn.";
    }
}

