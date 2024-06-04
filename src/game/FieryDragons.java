package game;

import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;
import game.displays.DisplayManager;
import game.displays.GameWindow;
import game.tiles.Cave;

import java.io.Serializable;
import java.util.List;

/**
 * Class responsible for initiating the game
 */
public class FieryDragons implements Serializable {
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
    private ChitFactory chitFactory = new ChitFactory();
    private Board board;
    private Player[] players;

    /**
     * Creates the chits used in the game with specific strategies.
     */
    private void createChits() {
        chitFactory.setChit('w', "Bat", new AnimalChitStrategy());
        chitFactory.setChit('0', "Baby Dragon", new AnimalChitStrategy());
        chitFactory.setChit('S', "Salamander", new AnimalChitStrategy());
        chitFactory.setChit('*', "Spider", new AnimalChitStrategy());
        chitFactory.setChit('P', "Pirate", new PirateChitStrategy());
    }

    /**
     * Creates the game board using predefined sources and the chit factory.
     */
    private void createBoard() {
        board = new Board("/assets/volcano.txt");
        board.createBoard(SQUARE_SRC, CAVE_SRC, CARD_SRC, chitFactory);
    }

    /**
     * Creates the players and assigns them to their starting positions.
     */
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
        players[0].startTurn(board, display);
    }

    public void continueGame(FieryDragons data) {
        System.out.println(data.getPlayers()[0].getPosition());
        this.board = data.getBoard();
        this.players = data.getPlayers();
        this.chitFactory = data.getChitFactory();

        GameWindow gameWindow = new GameWindow();

        DisplayManager display = new DisplayManager(gameWindow, board, players);
        players[0].startTurn(board, display);
    }

    public ChitFactory getChitFactory() {
        return chitFactory;
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }
}
