package game;

import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;
import game.displays.DisplayManager;
import game.displays.GameWindow;
import game.tiles.Cave;

import java.io.Serializable;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * The FieryDragons class is responsible for initiating and managing the game.
 * It handles the creation of chits, the game board, players, and the game flow.
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
    private static final char[] passive_chits = new char[]{'w', '0', 'S', '*', 'f', '3', '9', 'a'};
    private static String squareSrc = "S0w*SSw**w0*0S0*wS0w0S*w";
    private static String cardSrc = "S1,S2,S3,w1,w2,w3,*1,*2,*3,01,02,03,P1,P1,P2,P2";
    private static String caveSrc = "S*w0";
    private static int NUM_PLAYERS = 4;
    private static Player[] players;
    private final ChitFactory chitFactory = new ChitFactory();
    private Board board;

    /**
     * Creates the chits used in the game with specific strategies.
     */
    public void createChits() {
        chitFactory.setChit('w', "Bat", new AnimalChitStrategy());
        chitFactory.setChit('0', "Baby Dragon", new AnimalChitStrategy());
        chitFactory.setChit('S', "Salamander", new AnimalChitStrategy());
        chitFactory.setChit('*', "Spider", new AnimalChitStrategy());
        chitFactory.setChit('f', "Flamingo", new AnimalChitStrategy());
        chitFactory.setChit('3', "Seahorse", new AnimalChitStrategy());
        chitFactory.setChit('9', "Pig", new AnimalChitStrategy());
        chitFactory.setChit('a', "Cat", new AnimalChitStrategy());

        chitFactory.setChit('P', "Pirate", new PirateChitStrategy());
        chitFactory.setChit('R', "Rascal", new PirateChitStrategy()); //TODO: change to rascalChitStrategy later
    }

    /**
     * Creates the game board using predefined sources and the chit factory.
     */
    public void createBoard() {
        board = new Board(Paths.get("assets/volcano.txt").toAbsolutePath());
        board.createBoard(squareSrc, caveSrc, cardSrc, chitFactory);
    }

    /**
     * Creates the players and assigns them to their starting positions.
     */
    public void createPlayers() {
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
     * Starts the game by initializing the game window, display manager, and displaying the title screen.
     *
     * @throws IOException         if an input or output exception occurs
     * @throws FontFormatException if a font format exception occurs
     */
    public void start() throws IOException, FontFormatException {
        GameWindow window = new GameWindow();
        DisplayManager display = new DisplayManager(this, window);

        //start new game
        display.displayTitleScreen(window.getFrame());
    }

    /**
     * Initializes the game components and starts the game.
     *
     * @param display the display manager handling game displays
     * @param window  the game window
     */
    public void playGame(DisplayManager display, GameWindow window) {
        createChits();
        createBoard();
        createPlayers();

        display.createGameComponents(window, board, players);

        display.displayGameScreen(window.getFrame());
        players[0].startTurn(board, display);
    }

    /**
     * Configures game settings based on user selections and starts the game.
     *
     * @param display         the display manager handling game displays
     * @param window          the game window
     * @param numPlayers      the number of players
     * @param numSquares      the number of squares on the board
     * @param isPirateChecked whether the pirate option is selected
     * @param isRascalChecked whether the rascal option is selected
     */
    public void pickSettings(DisplayManager display, GameWindow window, int numPlayers, int numSquares, boolean isPirateChecked, boolean isRascalChecked) {
        NUM_PLAYERS = numPlayers;

        // TODO: randomise
        StringBuilder caves = new StringBuilder(numPlayers);

        for (int i = 0; i < numPlayers; i++) {
            caves.append(passive_chits[i]);
        }

        StringBuilder squares = new StringBuilder(numSquares);
        int base = numSquares / numPlayers;
        int extra = numSquares % numPlayers;

        for (int i = 0; i < caves.length(); i++) {
            char chit = caves.charAt(i);

            squares.append(String.valueOf(chit).repeat(base));

            if (i < extra) {
                squares.append(chit);
            }
        }

        caveSrc = caves.toString();
        squareSrc = squares.toString();

        //TODO: set others
        try {
            new BoardGenerator(numSquares, numPlayers).make(Paths.get("assets/volcano.txt").toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        playGame(display, window);
    }

    public void continueGame(FieryDragons data) {
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
