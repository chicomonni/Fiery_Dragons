package game.tiles;

import game.Player;
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
     * @param chit the Chit instance associated with this tile
     */
    public Square(Chit chit) {
        super(chit);
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
    public boolean canMove(Player player, int dist) {
        return strategy.canMove(player, dist);
    }

    /**
     * Moves the Player the specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     */
    @Override
    public void move(Player player, int dist) {
        strategy.move(player, dist);
    }

    /**
     * Check if a Player can enter this GameTile
     *
     * @param player the Player trying to enter
     * @return {@code true} if the Player can enter, {@code false}
     */
    @Override
    public boolean canEnter(Player player) {
        return vacant;
    }

    /**
     * Check if a Player can win with the given number of moves
     * @param player the Player trying to win
     * @param dist the number of moves the Player could move along the Volcano
     * @return {@code true} if the Player can win, {@code false} otherwise
     */
    @Override
    public boolean winningMove(Player player, int dist) {
        return strategy.winningMove(player, dist);
    }

    /**
     * Double dispatch to calculate location of a specific GameTile subclass
     *
     * @param playerDisplay the playerDisplay initiating the dispatch
     * @return an int array containing the coordinates (x, y)
     */
    @Override
    public int[] calculateLocation(PlayerDisplay playerDisplay) {
        return playerDisplay.calculateLocation(this);
    }

    /**
     * Sets a Cave on this Square and updates the move strategy accordingly.
     *
     * @param cave the Cave to be set on this Square
     */
    public void setCave(Cave cave) {
        strategy = new SquareWithCaveMoveStrategy(this, cave);
    }
}
