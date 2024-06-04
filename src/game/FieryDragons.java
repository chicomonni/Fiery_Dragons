package game;

import boardGenerator.BoardGenerator;
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

/**
 * The FieryDragons class is responsible for initiating and managing the game.
 * It handles the creation of chits, the game board, players, and the game flow.
 */
public class FieryDragons implements Serializable {
    public static final Path VOLCANO_PATH = Paths.get("config/volcano.txt").toAbsolutePath();
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

        // TODO: test updated move
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

        caveSrc = caves.toString();
        squareSrc = squares.toString();
        cardSrc = cards.toString();

        //TODO: set others
        try {
            new BoardGenerator(numSquares, numPlayers).make(VOLCANO_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        playGame(display, window);
    }

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

    public void saveGame() {
        int saveNumber = 0;
        String saveName = "saveData" + saveNumber + ".ser";
        File[] listOfFiles = checkSaveFolder();
        assert listOfFiles != null;
        if (listOfFiles.length >= 3) {
            saveName = findOldestFile(listOfFiles);
        } else {
            for (File file : listOfFiles) {
                if (file.getName().equals(saveName)) {
                    saveNumber += 1;
                    saveName = "saveData" + saveNumber + ".ser";
                }
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("config/saves/" + saveName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private FieryDragons loadGame(int saveNumber) {
        try {
            FieryDragons data;
            FileInputStream fileIn = new FileInputStream("config/saves/saveData" + saveNumber + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (FieryDragons) in.readObject();
            in.close();
            fileIn.close();
            return data;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Game Data not found");
            c.printStackTrace();
        }
        return null;

    }

    public File[] checkSaveFolder() {
//        TODO: make file name a constant
        File folder = new File("config/saves/");
        folder.mkdirs();
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            return listOfFiles;
        }

        return null;
    }

    private String findOldestFile(File[] list) {
        File oldest = list[0];
        for (int i = 0; i < list.length; i++) {
            if (list[i].lastModified() < oldest.lastModified()) {
                oldest = list[i];
            }
        }

        return oldest.getName();
    }

    public void incrementPlayerTurn() {
        this.playerTurn += 1;
        if (this.playerTurn > players.length - 1) {
            this.playerTurn = 0;
        }
    }

    public int getPlayerTurn() {
        return playerTurn;
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
