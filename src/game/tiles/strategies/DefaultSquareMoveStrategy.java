package game.tiles.strategies;

import game.Player;
import game.tiles.Square;

import static java.lang.Math.abs;

/**
 * The default implementation of move for a Square not connected to a Cave
 */
public class DefaultSquareMoveStrategy implements SquareMoveStrategy {
    private final Square square;

    /**
     * Constructor
     *
     * @param square the Square instance that uses this strategy
     */
    public DefaultSquareMoveStrategy(Square square) {
        this.square = square;
    }

    /**
     * Checks if Player can move a specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return {@code true} if Player can move, {@code false} otherwise
     */
    @Override
    public boolean canMove(Player player, int dist) {
        if (abs(dist) == 1) {
            return square.canEnter(player);
        } else if (dist > 1) {
            return square.getNext().canMove(player, dist - 1);
        } else {
            return square.getPrev().canMove(player, dist + 1);
        }
    }

    /**
     * Check if a Player can win with the given number of moves
     *
     * @param player the Player trying to win
     * @param dist   the number of moves the Player could move along the Volcano
     * @return {@code true} if the Player can win, {@code false} otherwise
     */
    @Override
    public boolean winningMove(Player player, int dist) {
        if (dist > 1) {
            return square.getNext().winningMove(player, dist - 1);
        }
        // If they're moving backwards, they can't win,
        // if they're landing on the square, they can't win.
        return false;
    }

    /**
     * Moves the Player the specified distance
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     */
    @Override
    public void move(Player player, int dist) {
        if (player.getPosition() == square) {
            square.setOccupiedBy(null);
        }

        if (abs(dist) == 1) {
            player.setPosition(square);
        } else if (dist > 1) {
            square.getNext().move(player, dist - 1);
        } else {
            square.getPrev().move(player, dist + 1);
        }
    }
}
