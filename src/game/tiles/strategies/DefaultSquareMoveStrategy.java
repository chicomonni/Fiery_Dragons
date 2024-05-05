package game.tiles.strategies;

import game.Player;
import game.actions.MoveAction;
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
     * Returns the desired MoveAction if it can be performed
     *
     * @param player the Player instance moving
     * @param dist   how far the Player moves along the Volcano
     * @return the desired MoveAction or {@code null} if it cannot be performed
     */
    @Override
    public MoveAction move(Player player, int dist) {
        if (dist == 0) {
            return square.canEnter(player) ? new MoveAction(square, player) : null;
        } else if (dist > 0) {
            return square.getNext().move(player, dist - 1);
        } else {
            return square.getPrev().move(player, dist + 1);
        }
    }
}
