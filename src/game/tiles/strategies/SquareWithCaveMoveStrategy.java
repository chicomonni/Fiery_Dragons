package game.tiles.strategies;

import game.MoveAction;
import game.Player;
import game.tiles.Cave;
import game.tiles.Square;

public class SquareWithCaveMoveStrategy implements SquareMoveStrategy {
    private final Square square;
    private final Cave cave;

    /**
     * Constructor
     *
     * @param square the Square instance that uses this strategy
     * @param cave   the Cave instance connected to the Square
     */
    public SquareWithCaveMoveStrategy(Square square, Cave cave) {
        this.square = square;
        this.cave = cave;

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
        if (!square.canEnter(player)) {
            return null;
        }

        MoveAction action;

        if (dist == 0) {
            action = new MoveAction();
        } else if (dist > 0) {
            if (cave.canEnter(player)) {
                return cave.move(player, dist - 1);
            } else {
                action = square.getNext().move(player, dist - 1);
            }

            // If correct Player passes Cave, they should be able to return in the next loop (and win)
            if (action != null && cave.isResident(player)) {
                cave.setCanReturn(true);
            }
        } else {
            action = square.getNext().move(player, dist - 1);

            // If correct Player passes Cave backwards, they need to pass forward again to be allowed access
            if (action != null && cave.isResident(player)) {
                cave.setCanReturn(false);
            }
        }

        return action;
    }
}
