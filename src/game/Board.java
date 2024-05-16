package game;

import game.chitCards.ChitCardArray;
import game.chits.ChitFactory;
import game.tiles.Cave;
import game.tiles.Square;
import game.tiles.Volcano;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyException;
import java.util.List;
import java.util.Objects;

/**
 * Class representing Fiery Dragon Board
 */
public class Board {
    private final Volcano volcano;
    private final ChitCardArray chitCards = new ChitCardArray();

    /**
     * Constructor
     *
     * @param path path to file containing the ASCII Volcano
     */
    public Board(String path) {
        InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream(path));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> volcanoASCII = bufferedReader.lines().toList();

        this.volcano = new Volcano(volcanoASCII);
    }

    private void createVolcano(String squareSrc, String caveSrc, ChitFactory factory) {
        volcano.createVolcano(squareSrc, caveSrc, factory);
    }

    private void createChitCards(String cardSrc, ChitFactory factory) {
        try {
            chitCards.createCards(cardSrc, factory);
        } catch (KeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to create Board
     *
     * @param squareSrc a valid String representing all Squares in order
     * @param caveSrc   a valid String representing all Caves in order
     * @param cardSrc   a valid String representing all ChitCards
     * @param factory   ChitFactory instance used by the game
     */
    public void createBoard(String squareSrc, String caveSrc, String cardSrc, ChitFactory factory) {
        createVolcano(squareSrc, caveSrc, factory);
        createChitCards(cardSrc, factory);
    }

    /**
     * Getter for array of Caves on Volcano
     *
     * @return an array of all Caves on the Volcano
     */
    public Cave[] getCaves() {
        return volcano.getCaves();
    }

    /**
     * Getter for array of Squares on Volcano
     *
     * @return an array of all Squares on the Volcano
     */
    public Square[] getSquares() {
        return volcano.getSquares();
    }

    public List<String> getASCIIRep() {
        return boardRep;
    }
}
