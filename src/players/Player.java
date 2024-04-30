package players;

import locations.Cave;
import locations.Location;

public class Player {
    private Cave home;
    private Location location;
    private int totalMoves = 0;
    
    public Player(Cave home) {
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
}
