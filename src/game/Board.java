package game;

import game.chitCards.ChitCardArray;
import game.chits.ChitFactory;
import game.tiles.Volcano;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class representing Fiery Dragon Board
 */
public class Board {
    private final Volcano volcano = new Volcano();
    private final ChitCardArray chitCards = new ChitCardArray();
    private final List<String> boardRep;

    /**
     * Constructor
     *
     * @param path path to file containing the ASCII Board
     */
    public Board(String path) {
        InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream(path));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.boardRep = bufferedReader.lines().collect(Collectors.toList());
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

    public int getNoCaves() {
        return volcano.getNoCaves();
    }

    public int getNoSquares() {
        return volcano.getNoSquares();
    }

    public List<String> getBoardRep() {
        return boardRep;
    }
}
