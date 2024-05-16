package game;

import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;
import game.displays.DisplayManager;
import game.displays.GameWindow;
import game.tiles.Cave;

import java.io.IOException;
import java.util.List;
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

    private void createChits() {
        chitFactory.setChit(
                'w',
                "/assets/detailed/Bat.txt",
                "/assets/cards/Bat.txt",
                new AnimalChitStrategy()
        );
        chitFactory.setChit(
                '0',
                "/assets/detailed/Dragon.txt",
                "/assets/cards/Dragon.txt",
                new AnimalChitStrategy()
        );
        chitFactory.setChit('S',
                "/assets/detailed/Salamander.txt",
                "/assets/cards/Salamander.txt",
                new AnimalChitStrategy()
        );
        chitFactory.setChit('*',
                "/assets/detailed/Spider.txt",
                "/assets/cards/Spider.txt",
                new AnimalChitStrategy()
        );
        chitFactory.setChit(
                'P',
                "/assets/detailed/Pirate.txt",
                "/assets/cards/Pirate.txt",
                new PirateChitStrategy()
        );
    }

    private void createBoard() {
        board = new Board("/assets/Board.txt");
        board.createBoard(SQUARE_SRC, CAVE_SRC, CARD_SRC, chitFactory);
    }

    private void createPlayers() {
        List<Cave> caves = board.getCaves();
        Player[] players = new Player[NUM_PLAYERS];
        assert players.length <= caves.size() : "Not enough caves for all players";

        // Create Players and assign them to Caves
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player " + (i + 1), Objects.requireNonNull(caves.get(0)));
            caves.get(0).setResident(players[i]);
        }

        // Link Players sequentially so they can have turns
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
