import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import characters.Chit;
import dragoncards.AnimalCard;
import dragoncards.PirateCard;
import locations.Cave;
import players.PlayerIterator;

public class FieryDragons {
    private static FieryDragons instance = null;
    public CardsController cardsController;
    private ArrayList<Cave> caves;
    private int totalSquares;
    private PlayerIterator playerIterator;

    private FieryDragons() {
    }

    public static FieryDragons getInstance() {
        if (instance == null) {
            instance = new FieryDragons();
        }
        return instance;
    }

    public void startGame() {

        // playerIterator = new PlayerIterator();
        HashMap<Chit, ArrayList<Integer>> animalConfig = CardsConfig.getAnimalConfig();
        HashMap<Chit, ArrayList<Integer>> pirateConfig = CardsConfig.getPirateConfig();
        cardsController = new CardsController();

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
        // playerIterator.getNextPlayer();
    }  
}
