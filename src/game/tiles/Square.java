package game.tiles;

import game.Player;
import game.actions.MoveAction;
import game.chits.Chit;
import game.displays.PlayerDisplay;
import game.tiles.strategies.DefaultSquareMoveStrategy;
import game.tiles.strategies.SquareMoveStrategy;
import game.tiles.strategies.SquareWithCaveMoveStrategy;

/**
 * Class representing a Square on the Volcano
 */
public class Square extends GameTile {
    private Square prev;
    private Square next;
    private SquareMoveStrategy strategy = new DefaultSquareMoveStrategy(this);

    /**
     * Constructor.
     *
     * @param position an integer representing its position, so it can be located by the Display
     * @param chit     the Chit instance associated with this tile
     */
    public Square(int position, Chit chit) {
        super(position, chit);
    }

    /**
     * Method to get the Square before this one
     *
     * @return the Square before this one
     */
    public Square getPrev() {
        return prev;
    }

    /**
     * Set previous Square
     *
     * @param prev previous Square
     */
    public void setPrev(Square prev) {
        this.prev = prev;
    }

    /**
     * Method to get the Square after this one
     *
     * @return the Square after this one
     */
    public Square getNext() {
        return next;
    }

    /**
     * Set next Square
     *
     * @param next next Square
     */
    public void setNext(Square next) {
        this.next = next;
    }

    /**
     * Returns the desired MoveAction if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return the desired MoveAction or {@code null} if it cannot be performed
     */
    @Override
    public MoveAction move(Player player, int dist) {
        return strategy.move(player, dist);
    }

    /**
     * Check if a Player can enter this GameTile
     *
     * @param player the Player trying to enter
     * @return {@code true} if the Player can enter, {@code false}
     */
    @Override
    public boolean canEnter(Player player) {
        return !isOccupied;
    }

    @Override
    public int[] calculateLocation(PlayerDisplay playerDisplay) {
        return playerDisplay.calculateSquareLocation(position);
    }

    public void setCave(Cave cave) {
        strategy = new SquareWithCaveMoveStrategy(this, cave);
    }
}
