import Animals.Animal;
import Animals.AnimalFactory;
import Utils.FancyMessage;

import java.io.IOException;
import java.util.*;

/**
 * Contains all the information about the physical board (e.g. the game pieces, tokens cards, etc.)
 * Responsible for linking the behind the scene and the visual game components together.
 *
 * @author Georgia Kanellis
 */
class GameBoard {
    private static GameBoard instance;
    private AnimalFactory animalFactory;

    private String formattedGameBoard;
    private ChitCard[] chitCardArray;
    private VolcanoTile[] volcanoTileArray;
    private CaveTile[] caveTileArray;
    private Player[] playerArray;

    //initialize AnimalFactory in constructor
    private GameBoard() {
        animalFactory = new AnimalFactory();
    }

    //static method to get the singleton instance
    static GameBoard getInstance() {
        if (instance == null) {
            instance = new GameBoard();
        }
        return instance;
    }

    //collates gameBoard label for the JFrame
    public void printGameBoard() throws IOException {
        clearGameBoard();
        GameBoardPrinter.printGameBoard(this);
    }

    public void clearGameBoard() {
        formattedGameBoard = null;
        GameDisplay.clearGameDisplay();
    }

    public void setFormattedGameBoard(String formattedGameBoard) {
        this.formattedGameBoard = formattedGameBoard;
    }

    public String getFormattedGameBoard() {
        return formattedGameBoard;
    }

    public void createGameBoard() {
        initialiseChitCards();
        initialiseVolcanoTiles();
        initialiseCaveTiles();
        initialisePlayers();
    }

    private void initialiseChitCards() {
        chitCardArray = new ChitCard[GameMaster.getNumChitCards()];
        Animal[] possibleAnimals = {
                animalFactory.createSpider(),
                animalFactory.createSalamander(),
                animalFactory.createBat(),
                animalFactory.createBabyDragon(),
                animalFactory.createPirate()
        };

        //make sure relatively even spread
        List<Animal> animalTypes = new ArrayList<>();
        int numCardsPerPlayer = chitCardArray.length / GameMaster.getNumPlayers() - 1;
        int cardsLeft = chitCardArray.length;
        Random random = new Random();

        // put in minimum amount of chitAnimals first
        for (Animal animal : possibleAnimals) {
            int count = Math.min(numCardsPerPlayer, cardsLeft);
            for (int i = 0; i < count; i++) {
                animalTypes.add(animal);
                cardsLeft--;
            }
        }

        while (cardsLeft > 0) {
            animalTypes.add(possibleAnimals[random.nextInt(possibleAnimals.length)]);
            cardsLeft--;
        }

        // randomise chit Cards
        Collections.shuffle(animalTypes);

        //link chitCard Array to animal types
        for (int i = 0; i < chitCardArray.length; i++) {
            ChitCard chitCard = new ChitCard();
            chitCard.setChitAnimal(animalTypes.get(i));
            chitCard.setChitNum();
            chitCard.setCardNum(i + 1);
            chitCard.setChitState(false);
            chitCardArray[i] = chitCard;
        }
    }

    private void initialiseVolcanoTiles() {
        volcanoTileArray = new VolcanoTile[GameMaster.getNumVolcanoTiles()];
        Animal[] possibleAnimals = {
                animalFactory.createBat(),
                animalFactory.createSpider(),
                animalFactory.createSalamander(),
                animalFactory.createBabyDragon()
        };
        List<Animal> animalTypes = new ArrayList<>();
        //numVolcanoTiles will always be a multiple of numPlayers
        int numTilesPerPlayer = volcanoTileArray.length / GameMaster.getNumPlayers();
        int cardsLeft = volcanoTileArray.length;

        Random random = new Random();

    for (Animal animal : possibleAnimals) {
        int count = Math.min(numTilesPerPlayer, cardsLeft);
        for (int i = 0; i < count; i++) {
            animalTypes.add(animal);
            cardsLeft--;
        }
    }

    Collections.shuffle(animalTypes);

        for (int i = 0; i < volcanoTileArray.length; i++) {
            VolcanoTile volcanoTile = new VolcanoTile();

            volcanoTile.setVolcanoTileNum(i);
            volcanoTile.setVolcanoTileAnimal(animalTypes.get(i));
            volcanoTile.setState(false);
            volcanoTileArray[i] = volcanoTile;
        }
    }

    private void initialiseCaveTiles() {
        caveTileArray = new CaveTile[GameMaster.getNumPlayers()];
        for (int i = 0; i < caveTileArray.length; i++) {
            CaveTile cave = new CaveTile();
            Animal[] possibleAnimals = {
                    animalFactory.createBat(),
                    animalFactory.createSpider(),
                    animalFactory.createSalamander(),
                    animalFactory.createBabyDragon()
            };

            cave.setCaveNum(i);
            cave.setCaveState(true);
            cave.setCaveAnimal(possibleAnimals[i]);
            caveTileArray[i] = cave;
        }
    }

    private void initialisePlayers() {
        playerArray = new Player[GameMaster.getNumPlayers()];
        for (int i = 0; i < playerArray.length; i++) {
            Player player = new Player();

            player.setPlayerCave(getCaveTileArray()[i]);
            player.setPlayerName(i);

            playerArray[i] = player;
        }
    }

    public ChitCard[] getChitCardArray() {
        return chitCardArray;
    }

    public VolcanoTile[] getVolcanoTileArray() {
        return volcanoTileArray;
    }

    public CaveTile[] getCaveTileArray() {
        return caveTileArray;
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }
}


