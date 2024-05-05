package game;

import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;
import game.displays.DisplayManager;
import game.displays.GameWindow;

import java.io.IOException;

/**
 * Class responsible for initiating the game
 */
public class FieryDragons {
    public static final DisplayManager display = new DisplayManager(new GameWindow());
    private static final String SQUARE_SRC = "S0w*SSw**w0*0S0*wS0w0S*w";
    private static final String CAVE_SRC = "S*w0";
    private static final String CARD_SRC = "S1S2S3w1w2w3*1*2*3010203P1P1P2P2";
    private final ChitFactory chitFactory = new ChitFactory();
    private Board board;
    private Player player1;

    /**
     * Method for initialising all the Chits in the game
     */
    private void createChits() {
        try {
            chitFactory.setChit('w', "/assets/CardBat.txt", new AnimalChitStrategy());
            chitFactory.setChit('0', "/assets/CardDragon.txt", new AnimalChitStrategy());
            chitFactory.setChit('S', "/assets/CardSalamander.txt", new AnimalChitStrategy());
            chitFactory.setChit('*', "/assets/CardSpider.txt", new AnimalChitStrategy());
            chitFactory.setChit('P', "/assets/CardPirate.txt", new PirateChitStrategy());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createBoard() {
        board = new Board("/assets/Board.txt");
        board.createBoard(SQUARE_SRC, CAVE_SRC, CARD_SRC, chitFactory);
    }

    /**
     * This method should be called to start a new game of Fiery Dragons
     */
    public void start() {
        createChits();
        createBoard();
        display.createBoard(board);
    }
}
