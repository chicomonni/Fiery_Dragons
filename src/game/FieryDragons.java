package game;

import boardgenerator.BoardGenerator;
import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;
import game.chits.strategies.RascalChitStrategy;
import game.displays.DisplayManager;
import game.displays.GameWindow;
import game.tiles.Cave;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * The FieryDragons class is responsible for initiating and managing the game.
 * It handles the creation of chits, the game board, players, and the game flow.
 */
public class FieryDragons implements Serializable {
    public static final Path VOLCANO_PATH = Paths.get("config/volcano.txt").toAbsolutePath();
    public static final int MAX_SAVES = 3;
    private static final String CONFIG_SAVES_FILE = "config/saves/";
    private static final char[] passive_chits = new char[]{'w', '0', 'S', '*', 'f', '3', '9', 'a'};
    private static final int CARD_PER_ANIMAL = 3;
    private String squareSrc;
    private String cardSrc;
    private String caveSrc;
    private int numPlayers;
    private ChitFactory chitFactory = new ChitFactory();
    private Player[] players;
    private Board board;
    private int playerTurn = 0;

    /**
     * Creates the string sources used to create the Caves.
     *
     * @param numPlayers the number of players
     * @return the string source for the Caves
     */
    private static String makeCaveSrc(int numPlayers) {
        StringBuilder caves = new StringBuilder(numPlayers);

        for (int i = 0; i < numPlayers; i++) {
            caves.append(passive_chits[i]);
        }
        return caves.toString();
    }

    /**
     * Creates the string sources used to create the Chit Cards.
     *
     * @param numPlayers      the number of players
     * @param isPirateChecked whether the pirate option is selected
     * @param isRascalChecked whether the rascal option is selected
     * @return the string source for the Chit Cards
     */
    private static String makeCardSrc(int numPlayers, boolean isPirateChecked, boolean isRascalChecked) {
        StringBuilder cards = new StringBuilder();

        for (int i = 0; i < numPlayers; i++) {
            for (int val = 1; val <= CARD_PER_ANIMAL; val++) {
                cards.append(passive_chits[i]).append(val).append(',');
            }
        }

        if (isPirateChecked) {
            for (int i = 0; i < numPlayers / 2; i++) {
                int val = i % 2 + 1;
                cards.append('P').append(val).append(',');
            }
        }

        if (isRascalChecked) {
            for (int i = 0; i < numPlayers / 2; i++) {
                cards.append('R').append(0).append(',');
            }
        }
        return cards.toString();
    }

    /**
     * Creates the string sources used to create the Squares.
     *
     * @param numPlayers the number of players
     * @param numSquares the number of squares on the board
     * @return the string source for the Squares
     */
    private static String makeSquareSrc(int numPlayers, int numSquares) {
        StringBuilder squares = new StringBuilder(numSquares);
        int base = numSquares / numPlayers;
        int extra = numSquares % numPlayers;

        for (int i = 0; i < numPlayers; i++) {
            char chit = passive_chits[i];

            squares.append(String.valueOf(chit).repeat(base));

            if (i < extra) {
                squares.append(chit);
            }
        }
        return squares.toString();
    }

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
        chitFactory.setChit('R', "Rascal", new RascalChitStrategy());
    }

    /**
     * Creates the game board using predefined sources and the chit factory.
     */
    public void createBoard() {
        board = new Board(VOLCANO_PATH);
        board.createBoard(squareSrc, caveSrc, cardSrc, chitFactory);
    }

    /**
     * Creates the players and assigns them to their starting positions.
     */
    public void createPlayers() {
        List<Cave> caves = board.getCaves();
        players = new Player[numPlayers];
        assert players.length <= caves.size() : "Not enough caves for all players";

        // Create Players and assign them to Caves
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player " + (i + 1), caves.get(i), (float) i / numPlayers);
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

        display.displayGameScreen(window.getFrame());
        display.createGameComponents(window, board, players);

        players[playerTurn].startTurn(board, display);
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
        this.numPlayers = numPlayers;

        caveSrc = makeCaveSrc(numPlayers);
        squareSrc = makeSquareSrc(numPlayers, numSquares);
        cardSrc = makeCardSrc(numPlayers, isPirateChecked, isRascalChecked);

        try {
            new BoardGenerator(numSquares, numPlayers).make(VOLCANO_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        playGame(display, window);
    }

    /**
     * Continues the game from a saved state.
     *
     * @param display    the display manager handling game displays
     * @param window     the game window
     * @param saveNumber the save file number to load
     */
    public void continueGame(DisplayManager display, GameWindow window, int saveNumber) throws IOException, FontFormatException {
        FieryDragons data = loadGame(saveNumber);

        this.board = data.getBoard();
        this.players = data.getPlayers();
        this.chitFactory = data.getChitFactory();
        this.playerTurn = data.getPlayerTurn();

        display.displayGameScreen(window.getFrame());
        display.createGameComponents(window, board, players);
        players[playerTurn].startTurn(board, display);
    }

    /**
     * Saves the game state to a file.
     */
    public void saveGame() {
        String saveName;
        File[] listOfFiles = checkSaveFolder();

        Objects.requireNonNull(listOfFiles);
        if (listOfFiles.length >= MAX_SAVES) {
            saveName = findOldestFile(listOfFiles);
        } else {
            saveName = "saveData" + listOfFiles.length + ".ser";
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(CONFIG_SAVES_FILE + saveName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the game state from a file.
     *
     * @param saveNumber the save file number to load
     * @return the FieryDragons instance representing the game
     */
    private FieryDragons loadGame(int saveNumber) {
        FieryDragons data;

        try {
            FileInputStream fileIn = new FileInputStream(CONFIG_SAVES_FILE + "saveData" + saveNumber + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (FieryDragons) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * Checks the save folder for save files.
     *
     * @return the array of save files
     */
    public File[] checkSaveFolder() {
        File folder = new File(CONFIG_SAVES_FILE);
        folder.mkdirs();
        return folder.listFiles();
    }

    /**
     * Finds the oldest file in the save folder.
     *
     * @param list the list of files in the save folder
     * @return the name of the oldest file
     */
    private String findOldestFile(File[] list) {
        File oldest = list[0];
        for (File file : list) {
            if (file.lastModified() < oldest.lastModified()) {
                oldest = file;
            }
        }

        return oldest.getName();
    }

    /**
     * Increments the player turn to the next player in the sequence.
     */
    public void incrementPlayerTurn() {
        playerTurn = (playerTurn + 1) % players.length;
    }

    /**
     * Retrieves the current player turn.
     *
     * @return the player turn
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Retrieves the chit factory used in the game.
     *
     * @return the chit factory
     */
    public ChitFactory getChitFactory() {
        return chitFactory;
    }

    /**
     * Retrieves the game board.
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Retrieves the players in the game.
     *
     * @return the game's players
     */
    public Player[] getPlayers() {
        return players;
    }

}
