package game;

import game.chits.ChitFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class representing Fiery Dragon Board
 */
public class Board {
    private final Volcano volcano = new Volcano();
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
    }

    public List<String> getBoardRep() {
        return boardRep;
    }
}
