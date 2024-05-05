package game;

import game.chits.Chit;
import game.chits.ChitFactory;
import game.tiles.Cave;
import game.tiles.Square;

import java.security.KeyException;
import java.util.Objects;

/**
 * Class representing the Volcano
 */
public class Volcano {
    private Square[] squares;
    private Cave[] caves;

    /**
     * Create the Squares on the Volcano from a String
     *
     * @param src     a valid String representing all Squares in order
     * @param factory ChitFactory instance used by the game
     * @throws KeyException if char in src is not recognised by the ChitFactory
     */
    private void createSquares(String src, ChitFactory factory) throws KeyException {
        squares = new Square[src.length()];

        for (int i = 0; i < src.length(); i++) {
            Chit chit = factory.getChit(src.charAt(i));
            squares[i] = new Square(i, chit);
        }

        for (int i = 0; i < squares.length; i++) {
            Square current = Objects.requireNonNull(squares[i]);
            Square prev = Objects.requireNonNull(squares[Math.floorMod(i - 1, squares.length)]);
            Square next = Objects.requireNonNull(squares[(i + 1) % squares.length]);

            current.setPrev(prev);
            current.setNext(next);
        }
    }

    /**
     * Create the Caves on the Volcano from a String
     *
     * @param src     a valid String representing all Caves in order
     * @param factory ChitFactory instance used by the game
     * @throws KeyException if char in src is not recognised by the ChitFactory
     */
    private void createCaves(String src, ChitFactory factory) throws KeyException {
        caves = new Cave[src.length()];

        for (int i = 0; i < caves.length; i++) {
            Chit chit = factory.getChit(src.charAt(i));
            int squareIdx = (i * squares.length) / caves.length;
            caves[i] = new Cave(i, chit, Objects.requireNonNull(squares[squareIdx]));
        }
    }

    /**
     * Create Volcano from Strings
     *
     * @param squareSrc a valid String representing all Squares in order
     * @param caveSrc   a valid String representing all Caves in order
     * @param factory   ChitFactory instance used by the game
     */
    public void createVolcano(String squareSrc, String caveSrc, ChitFactory factory) {
        try {
            createSquares(squareSrc, factory);
            createCaves(caveSrc, factory);
        } catch (KeyException e) {
            throw new RuntimeException(e);
        }
    }
}
