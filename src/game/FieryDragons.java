package game;

import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;
import game.displays.DisplayManager;
import game.displays.GameWindow;
import game.tiles.Cave;

import java.io.IOException;
import java.util.Objects;

/**
 * Class responsible for initiating the game
 */
public class FieryDragons {
    private static final String SQUARE_SRC = "S0w*SSw**w0*0S0*wS0w0S*w";
    private static final String CAVE_SRC = "S*w0";
    private static final String CARD_SRC = "S1,S2,S3,w1,w2,w3,*1,*2,*3,01,02,03,P1,P1,P2,P2";
    private static final int NUM_PLAYERS = 4;
    private final DisplayManager display = new DisplayManager(new GameWindow());
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

    private void createPlayers() {
        Cave[] caves = board.getCaves();
        Player[] players = new Player[NUM_PLAYERS];
        assert players.length <= caves.length : "Not enough caves for all players";

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player " + (i + 1), Objects.requireNonNull(caves[0]));
            caves[0].setResident(players[i]);
        }

        for (int i = 0; i < players.length; i++) {
            players[i].setNextPlayer(players[(i + 1) % players.length]);
        }

        player1 = players[0];
    }

    /**
     * This method should be called to start a new game of Fiery Dragons
     */
    public void start() {
        createChits();
        createBoard();
        createPlayers();
        display.createBoard(board);
    }
}
