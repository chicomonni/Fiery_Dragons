import java.util.ArrayList;

public abstract class DragonCard {
    private int value = 1;
    private boolean available = true;

    public int moves(Location location) {
        return value;
    }

    abstract ArrayList<Location> getNext(Location location, int moves);

    public void reset() {
        available = true;
    }

    public void setUnavailable() {
        available = false;
    }

    public boolean isAvailable() {
        return available;
    }

}
