package game;

import game.chits.ChitFactory;
import game.chits.strategies.AnimalChitStrategy;
import game.chits.strategies.PirateChitStrategy;

import java.io.IOException;

/**
 * Class responsible for initiating the game
 */
public class FieryDragons {
//    TODO: implement these classes
//    private Board board;
//    private Player player1;

    /**
     * Method for initialising all the Chits in the game
     */
    private void createChits() {
        ChitFactory chitFactory = new ChitFactory();

        try {
            chitFactory.setChit('w', "/assets/CardBat.txt", new AnimalChitStrategy());
            chitFactory.setChit('0', "/assets/CardDragon.txt", new AnimalChitStrategy());
            chitFactory.setChit('S', "/assets/CardSalamander.txt", new AnimalChitStrategy());
            chitFactory.setChit('*', "/assets/CardSpider.txt", new AnimalChitStrategy());
            chitFactory.setChit('P', "/assets/CardPirate.txt", new PirateChitStrategy());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method should be called to start a new game of Fiery Dragons
     */
    public void start() {
        createChits();
    }
}
