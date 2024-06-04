package game;

import game.chitCards.ChitCard;
import game.chitCards.ChitCardArray;
import game.chits.ChitFactory;
import game.tiles.Cave;
import game.tiles.Square;
import game.tiles.Volcano;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyException;
import java.util.List;

/**
 * The Board class represents the game board for Fiery Dragons.
 * It handles the creation of the volcano and chit cards on the board.
 */
public class Board {
    private final Volcano volcano;
    private final ChitCardArray chitCards = new ChitCardArray();

    /**
     * Constructor
     *
     * @param path path to file containing the ASCII Volcano
     */
    public Board(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        char[][] chars = new char[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            chars[i] = s.toCharArray();
        }

        this.volcano = new Volcano(chars);
    }

    /**
     * Creates the volcano on the board using the given sources and chit factory.
     *
     * @param squareSrc a valid String representing all Squares in order
     * @param caveSrc   a valid String representing all Caves in order
     * @param factory   ChitFactory instance used by the game
     */
    private void createVolcano(String squareSrc, String caveSrc, ChitFactory factory) {
        volcano.createVolcano(squareSrc, caveSrc, factory);
    }

    /**
     * Creates the chit cards on the board using the given source and chit factory.
     *
     * @param cardSrc a valid String representing all ChitCards
     * @param factory ChitFactory instance used by the game
     */
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
     * Getter for list of Caves on Volcano
     *
     * @return a list of all Caves on the Volcano
     */
    public List<Cave> getCaves() {
        return volcano.getCaves();
    }

    /**
     * Getter for list of Squares on Volcano
     *
     * @return a list of all Squares on the Volcano
     */
    public List<Square> getSquares() {
        return volcano.getSquares();
    }

    /**
     * Getter for Volcano
     *
     * @return a Volcano instance
     */
    public Volcano getVolcano() {
        return volcano;
    }

    /**
     * Getter for the ChitCardArray.
     *
     * @return the ChitCardArray instance
     */
    public ChitCardArray getChitCards() {
        return chitCards;
    }

    /**
     * Getter for a specific ChitCard.
     *
     * @param i the index of the ChitCard
     * @return the ChitCard at the specified index
     */
    public ChitCard getChitCard(int i) {
        return chitCards.getChitCard(i);
    }
}
