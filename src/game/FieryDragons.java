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

    private String gameboard;

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

        // temporarily hardcoded
        try {
            gameboard = ASCIIProcessor.getArt("src/ascii/GameBoard.txt");
            gameWindow.displayBoard(gameboard);
            totalSquares = 24;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        setupCards();
        setupPlayers();
        nextPlayer(gameWindow);

        gameWindow.displayCards(cardsController.getCardDisplays());
    }  

    private void setupPlayers() {
        ArrayList<Chit> cavesConfig = BoardConfig.getCaveConfig();

        for (int i = 0; i < cavesConfig.size(); i++) {
            Chit chit = cavesConfig.get(i);
            Cave cave = new Cave(chit, 0, 0);
            caves.add(cave);
            playerIterator.addPlayer(new Player(String.format("%d", i+1), cave));
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

    public boolean isGameRunning() {
        return gameRunning;
    }

    public int getTotalSquares() {
        return totalSquares;
    }
}
