package game.tiles.strategies;

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
            // If they're moving forward, prioritise cave direction
            if (cave.canEnter(player)) {
                return cave.canMove(player, dist - 1);
            }

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
            // If they're moving forward, prioritise cave direction
            if (cave.canEnter(player)) {
                cave.move(player, dist - 1);
                return;

            } else {
                square.getNext().move(player, dist - 1);
            }

            // If correct Player passes Cave, they should be able to return in the next loop (and win)
            if (cave.isResident(player)) {
                cave.setCanReturn(true);
            }

        } else {
            square.getPrev().move(player, dist + 1);

            // If correct Player passes Cave backwards, they need to pass forward again to be allowed access
            if (cave.isResident(player)) {
                cave.setCanReturn(false);
            }
        }
    }
}
