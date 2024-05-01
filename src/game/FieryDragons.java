package game;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ascii.ASCIIProcessor;
import characters.Chit;
import dragoncards.AnimalCard;
import dragoncards.DragonCard;
import dragoncards.PirateCard;
import locations.Cave;
import players.Player;
import players.PlayerIterator;

/**
 * FieryDragons is a class that represents the game.
 */
public class FieryDragons {
    /**
     * The instance of the FieryDragons class.
     */
    private static FieryDragons instance = null;

    /**
     * The controller for the cards in the game.
     */
    public CardsController cardsController;
    private ArrayList<Cave> caves = new ArrayList<Cave>();
    private int totalSquares;
    private PlayerIterator playerIterator;

    /**
     * The input that the player has entered.
     */
    private String input = "";
    private boolean displayUpdate = false;

    /**
     *  The gameboard
     */
    private ArrayList<String> gameboard;

    private boolean gameRunning = true;

    /**
     * The constructor for the FieryDragons class is private to prevent instantiation.
     */
    private FieryDragons() {
    }

    /**
     * The getInstance method returns the instance of the FieryDragons class.
     * @return The instance of the FieryDragons class.
     */
    public static FieryDragons getInstance() {
        if (instance == null) {
            instance = new FieryDragons();
        }
        return instance;
    }

    /**
     * The startGame method starts the game.
     * @param gameWindow The display for the game.
     */
    public void startGame(Display gameWindow) {
        cardsController = new CardsController();
        playerIterator = new PlayerIterator();

        setupCards();
        setupPlayers();
        nextPlayer(gameWindow);

        // temporarily hardcoded
        try {
            gameboard = ASCIIProcessor.getArt("src/ascii/GameBoard.txt");
            totalSquares = 24;
            displayBoard(gameWindow);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gameWindow.displayCards(cardsController.getCardDisplays());
    }  

    /**
     * The setupPlayers method sets up the players for the game.
     */
    private void setupPlayers() {
        HashMap<Chit, ArrayList<Integer>> cavesConfig = BoardConfig.getCaveConfig();

        int i = 1;
        // Creates a new player for each cave
        for (Map.Entry<Chit, ArrayList<Integer>> entry : cavesConfig.entrySet()) {
            Chit chit = entry.getKey();
            Cave cave = new Cave(chit, entry.getValue().get(0), entry.getValue().get(1));
            caves.add(cave);
            playerIterator.addPlayer(new Player(String.format("%d", i).charAt(0), cave));
            i++;
        }
    }

    /**
     * The setupCards method sets up the cards for the game.
     */
    private void setupCards() {
        HashMap<Chit, ArrayList<Integer>> animalConfig = CardsConfig.getAnimalConfig();
        HashMap<Chit, ArrayList<Integer>> pirateConfig = CardsConfig.getPirateConfig();

        // Creates a new card for each animal config
        for (Map.Entry<Chit, ArrayList<Integer>> entry : animalConfig.entrySet()) {
            Chit chit = entry.getKey();
            ArrayList<Integer> values = entry.getValue();
            for (int value : values) {
                AnimalCard animalCard = new AnimalCard(chit, value);
                cardsController.addCard(animalCard);
            }
        }
        // Creates a new card for each pirate config
        for (Map.Entry<Chit, ArrayList<Integer>> entry : pirateConfig.entrySet()) {
            Chit chit = entry.getKey();
            ArrayList<Integer> values = entry.getValue();
            for (int value : values) {
                PirateCard pirateCard = new PirateCard(chit, value);
                cardsController.addCard(pirateCard);
            }
        }

        cardsController.shuffleCards();
    }

    /**
     * The setInput method sets the input that the player has entered.
     * @param input The input that the player has entered.
     */
    public void setInput(String input) {
        this.input = input;
    }
    
    /**
     * The takeInput method takes the input from the player.
     * @param gameWindow The display for the game.
     * @param text The text that the player has entered.
     * @return Whether the player will have another chance to select a card
     */
    public boolean takeInput(Display gameWindow, String text) {
        this.input = ""; // reset input
        text = text.toLowerCase();
        if (text.equals("x")) {
        }
        else if (text.equals("q")) {
            gameRunning = false;
            System.exit(0);
        }
        else {
            return pickCard(text);
        }
        return false;
    }

    /**
     * The runNextTurnSimulation method runs the next turn simulation for the game for the purpose of Sprint 2.
     * @param gameWindow    The display for the game.
     */
    public void runNextTurnSimulation(Display gameWindow) {
        cardsController.setAllButOneUnavailable();
        gameWindow.displayCards(cardsController.getCardDisplays());
    }

    /**
     * The playTurn method plays a turn for the game.
     * @param gameWindow The display for the game.
     * @throws InterruptedException
     */
    public void playTurn(Display gameWindow) throws InterruptedException {
        boolean nextPlayer = false;
        while (cardsController.checkAnyAvailable() && !nextPlayer) {
            Thread.sleep(100);
            nextPlayer = !takeInput(gameWindow, this.input);

            if (displayUpdate) {
                gameWindow.displayCards(cardsController.getCardDisplays());
                displayUpdate = false;
            }
        }

        if (!cardsController.checkAnyAvailable()) {
            gameWindow.displayInstructions("All cards have been picked!!");
        }
        else {
            gameWindow.displayInstructions("This card doesn't match!");
        }
        Thread.sleep(2000);
        nextPlayer(gameWindow);
    }

    /**
     * The nextPlayer method moves to the next player in the game.
     * @param gameWindow
     */
    private void nextPlayer(Display gameWindow) {
        cardsController.resetCards();
        gameWindow.displayCurrentPlayer(playerIterator.getNextPlayer().toString());
        gameWindow.displayInstructions(null);
        gameWindow.displayCards(cardsController.getCardDisplays());
    }

    /**
     * The pickCard method picks a card from the game and determines if the
     * player will have another chance to select a card.
     * @param string The string that the player has entered.
     * @return Whether the player will have another chance to select a card.
     */
    private boolean pickCard(String string) {
        try {
            int index = Integer.parseInt(string);
            DragonCard card = cardsController.pickCard(index-1);
            if (card != null){;
                // set new location here
                displayUpdate = true;
                return card.getNext(playerIterator.getCurrentPlayer().getLocation()) != null;
            }
            return true;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     *  The displayBoard method displays the board for the game.
     * @param gameWindow
     */
    private void displayBoard(Display gameWindow) {
        try {
            gameboard = ASCIIProcessor.getArt("src/ascii/GameBoard.txt");

            // Place players on the board
            for (Player player : playerIterator.getPlayers()) {
                ASCIIProcessor.placePlayer(gameboard, player.getChar(), player.getLocation().getX(), player.getLocation().getY());
            }
            gameWindow.displayBoard(ASCIIProcessor.getString(gameboard));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The isGameRunning method returns whether the game is running.
     * @return
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * The getTotalSquares method returns the total squares in the game.
     * @return
     */
    public int getTotalSquares() {
        return totalSquares;
    }
}
