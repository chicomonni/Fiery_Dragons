package game;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ascii.ASCIIProcessor;
import characters.Chit;
import dragoncards.AnimalCard;
import dragoncards.PirateCard;
import locations.Cave;
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
        ArrayList<Chit> cavesConfig = BoardConfig.getCaveConfig();

        for (int i = 0; i < cavesConfig.size(); i++) {
            Chit chit = cavesConfig.get(i);
            Cave cave = new Cave(chit, 9, 17);
            caves.add(cave);
            playerIterator.addPlayer(new Player(String.format("%d", i+1).charAt(0), cave));
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
    
    public void takeInput(Display gameWindow, String text) {
        text = text.toLowerCase();
        if (text.equals("x")) {
            nextPlayer(gameWindow);
        }
        else if (text.equals("q")) {
            gameRunning = false;
            System.exit(0);
        }
        else {
            pickCard(text);
        }
        this.input = ""; // reset input
    }

    public void runNextTurnSimulation(Display gameWindow) {
        cardsController.setAllButOneUnavailable();
        gameWindow.displayCards(cardsController.getCardDisplays());
    }

    public void playTurn(Display gameWindow) throws InterruptedException {
        while (cardsController.checkAnyAvailable()) {
            Thread.sleep(50);
            takeInput(gameWindow, this.input);
            
            if (displayUpdate) {
                gameWindow.displayCards(cardsController.getCardDisplays());
                displayUpdate = false;
            }
        }
        gameWindow.displayInstructions("All cards have been picked!!");
        Thread.sleep(2000);
        nextPlayer(gameWindow);
    }

    private void nextPlayer(Display gameWindow) {
        cardsController.resetCards();
        gameWindow.displayCurrentPlayer(playerIterator.getNextPlayer().toString());
        gameWindow.displayInstructions(null);
        gameWindow.displayCards(cardsController.getCardDisplays());
    }

    private void pickCard(String string) {
        try {
            int index = Integer.parseInt(string);
            if (cardsController.pickCard(index-1) != null){;
                playerIterator.getCurrentPlayer(); // set new location here
                displayUpdate = true;
            }
        } catch (NumberFormatException e) {
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
