package game;

import game.chitCards.ChitCard;
import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;
import game.displays.DisplayManager;
import game.displays.GameWindow;
import game.tiles.Cave;

import java.util.List;

/**
 * Class responsible for initiating the game
 */
public class FieryDragons {
    public static final int OUTER_RADIUS = 25;
    public static final int INNER_RADIUS = 17;
    public static final int CAVE_RADIUS = 9;
    public static final int CAVE_OFFSET = 13;
    public static final int VOLCANO_PADDING = 3;
    public static final int VOLCANO_SIZE = 2 * (OUTER_RADIUS + CAVE_OFFSET + CAVE_RADIUS) + 1;
    public static final int CARD_WIDTH = 11;
    public static final int CARD_HEIGHT = 16;
    private static final String SQUARE_SRC = "S0w*SSw**w0*0S0*wS0w0S*w";
    private static final String CAVE_SRC = "S*w0";
    private static final String CARD_SRC = "S1,S2,S3,w1,w2,w3,*1,*2,*3,01,02,03,P1,P1,P2,P2";
    private static final int NUM_PLAYERS = 4;
    private final ChitFactory chitFactory = new ChitFactory();
    private Board board;
    private Player[] players;

    private void createChits() {
        chitFactory.setChit('w', "Bat", new AnimalChitStrategy());
        chitFactory.setChit('0', "Baby Dragon", new AnimalChitStrategy());
        chitFactory.setChit('S', "Salamander", new AnimalChitStrategy());
        chitFactory.setChit('*', "Spider", new AnimalChitStrategy());
        chitFactory.setChit('P', "Pirate", new PirateChitStrategy());
    }

    private void createBoard() {
        board = new Board("/assets/volcano.txt");
        board.createBoard(SQUARE_SRC, CAVE_SRC, CARD_SRC, chitFactory);
    }

    private void createPlayers() {
        List<Cave> caves = board.getCaves();
        players = new Player[NUM_PLAYERS];
        assert players.length <= caves.size() : "Not enough caves for all players";

        // Create Players and assign them to Caves
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player " + (i + 1), caves.get(i), (float) i / NUM_PLAYERS);
            caves.get(i).setResident(players[i]);
        }

        // Link Players sequentially so they can have turns
        for (int i = 0; i < players.length; i++) {
            players[i].setNextPlayer(players[(i + 1) % players.length]);
        }
    }

    /**
     * This method should be called to start a new game of Fiery Dragons
     */
    public void start() {
        createChits();
        createBoard();
        createPlayers();

        GameWindow gameWindow = new GameWindow();

        DisplayManager display = new DisplayManager(gameWindow, board, players);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Player p = players[0];
        p.startTurn(board, display);
//        p.setPosition(board.getVolcano().getSquares().get(10));
//        display.displayMove(p);

//        ChitCard card = board.getChitCards().getChitCards().get(3);
//        board.getChitCards().flip(4);
//        display.displayFlip(card);
    }
}
