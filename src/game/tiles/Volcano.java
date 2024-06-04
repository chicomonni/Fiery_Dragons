package game.tiles;

import game.chits.Chit;
import game.chits.ChitFactory;

import java.io.Serializable;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the Volcano
 */
public class Volcano implements Serializable {
    private final char[][] ASCIIRep;
    private List<Square> squares;
    private List<Cave> caves;

    /**
     * Constructor
     *
     * @param asciiRep a 2D char array representing the Volcano
     */
    public Volcano(char[][] asciiRep) {
        ASCIIRep = asciiRep;
    }

    /**
     * Create the Squares on the Volcano from a String
     *
     * @param src     a valid String representing all Squares in order
     * @param factory ChitFactory instance used by the game
     * @throws KeyException if char in src is not recognised by the ChitFactory
     */
    private void createSquares(String src, ChitFactory factory) throws KeyException {
        squares = new ArrayList<>(src.length());

        for (int i = 0; i < src.length(); i++) {
            Chit chit = factory.getChit(src.charAt(i));
            squares.add(new Square(chit));
        }

        Collections.shuffle(squares);

        for (int i = 0; i < squares.size(); i++) {
            Square current = squares.get(i);
            Square prev = squares.get(Math.floorMod(i - 1, squares.size()));
            Square next = squares.get((i + 1) % squares.size());

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
        caves = new ArrayList<>(src.length());

        for (int i = 0; i < src.length(); i++) {
            Chit chit = factory.getChit(src.charAt(i));

            int squareIdx = (2 * i + 1) * squares.size() / (2 * src.length());
            Square square = squares.get(squareIdx);

            Cave cave = new Cave(chit, square);
            caves.add(cave);
            square.setCave(cave);
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

    /**
     * Getter for list of Caves on Volcano
     *
     * @return a list of all Caves on the Volcano
     */
    public List<Cave> getCaves() {
        return new ArrayList<>(caves);
    }

    /**
     * Getter for list of Squares on Volcano
     *
     * @return a list of all Squares on the Volcano
     */
    public List<Square> getSquares() {
        return new ArrayList<>(squares);
    }

    /**
     * Getter for the ASCII art representing the Volcano, used by the displays
     *
     * @return a copy of the ASCII art representing the Volcano
     */
    public char[][] getASCIIRep() {
        char[][] clone = new char[ASCIIRep.length][];

        for (int i = 0; i < ASCIIRep.length; i++) {
            char[] chars = ASCIIRep[i];
            clone[i] = chars.clone();
        }

        return clone;
    }
}
