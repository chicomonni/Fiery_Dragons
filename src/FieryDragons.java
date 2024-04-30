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

    private String gameboard;

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        

        setupCards();
        setupPlayers(4);
        playerIterator.getNextPlayer();

        gameWindow.displayCards(cardsController.getCardDisplays());
        cardsController.setAllUnavailable();
        gameWindow.displayCards(cardsController.getCardDisplays());
    }  

    private void setupPlayers(int players) {
        for (int i = 0; i < players; i++) {
            Cave cave = new Cave();
            caves.add(cave);
            playerIterator.addPlayer(new Player(cave));
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
}
