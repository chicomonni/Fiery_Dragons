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
import locations.Location;
import players.Player;
import players.PlayerIterator;

public class FieryDragons {
    private static FieryDragons instance = null;

    public CardsController cardsController;
    private ArrayList<Cave> caves = new ArrayList<Cave>();
    private int totalSquares;
    private PlayerIterator playerIterator;

    private String input = "";
    private boolean displayUpdate = false;

    private ArrayList<String> gameboard;

    private boolean gameRunning = true;

    private FieryDragons() {
    }

    public static FieryDragons getInstance() {
        if (instance == null) {
            instance = new FieryDragons();
        }
        return instance;
    }

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

    private void setupPlayers() {
        HashMap<Chit, ArrayList<Integer>> cavesConfig = BoardConfig.getCaveConfig();

        int i = 1;
        for (Map.Entry<Chit, ArrayList<Integer>> entry : cavesConfig.entrySet()) {
            Chit chit = entry.getKey();
            Cave cave = new Cave(chit, entry.getValue().get(0), entry.getValue().get(1));
            caves.add(cave);
            playerIterator.addPlayer(new Player(String.format("%d", i).charAt(0), cave));
            i++;
        }
    }

    private void setupCards() {
        HashMap<Chit, ArrayList<Integer>> animalConfig = CardsConfig.getAnimalConfig();
        HashMap<Chit, ArrayList<Integer>> pirateConfig = CardsConfig.getPirateConfig();

        for (Map.Entry<Chit, ArrayList<Integer>> entry : animalConfig.entrySet()) {
            Chit chit = entry.getKey();
            ArrayList<Integer> values = entry.getValue();
            for (int value : values) {
                AnimalCard animalCard = new AnimalCard(chit, value);
                cardsController.addCard(animalCard);
            }
        }
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

    public void setInput(String input) {
        this.input = input;
    }
    
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

    public void runNextTurnSimulation(Display gameWindow) {
        cardsController.setAllButOneUnavailable();
        gameWindow.displayCards(cardsController.getCardDisplays());
    }

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

    private void nextPlayer(Display gameWindow) {
        cardsController.resetCards();
        gameWindow.displayCurrentPlayer(playerIterator.getNextPlayer().toString());
        gameWindow.displayInstructions(null);
        gameWindow.displayCards(cardsController.getCardDisplays());
    }

    private boolean pickCard(String string) {
        try {
            int index = Integer.parseInt(string);
            DragonCard card = cardsController.pickCard(index-1);
            if (card != null){;
                // set new location here
                displayUpdate = true;
                return card.getNext(playerIterator.getCurrentPlayer().getLocation()) == null;
            }
            return true;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private void displayBoard(Display gameWindow) {
        try {
            gameboard = ASCIIProcessor.getArt("src/ascii/GameBoard.txt");

            for (Player player : playerIterator.getPlayers()) {
                ASCIIProcessor.placePlayer(gameboard, player.getChar(), player.getLocation().getX(), player.getLocation().getY());
            }
            gameWindow.displayBoard(ASCIIProcessor.getString(gameboard));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean isGameRunning() {
        return gameRunning;
    }

    public int getTotalSquares() {
        return totalSquares;
    }
}
