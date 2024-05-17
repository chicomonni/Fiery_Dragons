package game.tiles.strategies;

import game.Player;
import game.tiles.Square;

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
        if (dist == 0) {
            return square.canEnter(player);
        } else if (dist > 0) {
            return square.getNext().canMove(player, dist - 1);
        } else {
            return square.getPrev().canMove(player, dist + 1);
        }
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
            square.setVacancy(true);
        }

        if (dist == 0) {
            player.setPosition(square);
            square.setVacancy(false);
        } else if (dist > 0) {
            square.getNext().move(player, dist - 1);
        } else {
            square.getPrev().move(player, dist + 1);
        }
    }
}
