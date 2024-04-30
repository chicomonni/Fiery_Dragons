import java.util.ArrayList;
import locations.Cave;
import players.PlayerIterator;

public class FieryDragons {
    private FieryDragons instance = null;
    private CardsController cardsController;
    private ArrayList<Cave> caves;
    private int totalSquares;
    private PlayerIterator playerIterator;

    private FieryDragons() {
    }

    public FieryDragons getInstance() {
        if (instance == null) {
            instance = new FieryDragons();
        }
        return instance;
    }
    
    public void startGame() {

        // playerIterator = new PlayerIterator();
        playerIterator.getNextPlayer();
    }  
}
