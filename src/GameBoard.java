import Animals.Animal;
import Animals.Characters.BabyDragon;
import Animals.Characters.Bat;
import Animals.Characters.Salamander;
import Animals.Characters.Spider;
import Animals.Traitors.Pirate;
import Utils.FancyMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class GameBoard {
    private static GameBoard instance;

    private String formattedGameBoard;
    private ChitCard[] chitCardArray;
    private VolcanoTile[] volcanoTileArray;
    private Player[] playerArray;

    //static method to get the singleton instance
    public static GameBoard getInstance() {
        if (instance == null) {
            instance = new GameBoard();
        }
        return instance;
    }

    public void printGameBoard() throws IOException {
        StringBuilder gameBoardString = new StringBuilder();
        File gameBoardFile = new File("src/GameBoard.txt");

        try (Scanner scanner = new Scanner(gameBoardFile)) {
//            printTitleCard(gameBoardString);
            skipLines(scanner, 5); // Skip lines 1 to 6

            int cardRowCount = 0;
            for (int i = 6; i <= 89 && scanner.hasNextLine(); i++) {
                String gameBoardLine = scanner.nextLine();
                printMargin(gameBoardString);
                printChitCardLine(gameBoardString, cardRowCount);
                printMargin(gameBoardString);
                printGameBoardLine(gameBoardString, gameBoardLine);
                cardRowCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        formattedGameBoard = formatGameBoardHTML(gameBoardString.toString());
    }

    private void skipLines(Scanner scanner, int numLines) throws IOException {
        for (int i = 0; i < numLines; i++) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            } else {
                throw new IOException("GameBoard.txt file is empty or doesn't contain enough lines.");
            }
        }
    }

    public void printTitleCard(StringBuilder gameBoardString) {
        for (String line : FancyMessage.TITLE_CARD) {
            gameBoardString.append(line).append("\n");
        }
    }

    public void printMargin(StringBuilder gameBoardString) {
        gameBoardString.append("    ");
    }

    private void printChitCardLine(StringBuilder gameBoardString, int cardRowCount) {
        //default chit card size display
        int sizeChitCardHeight = 17;
        int sizeChitCardWidth = 11;
        int maxCardsPerRow = 4;

        double cardRow = (double) cardRowCount / (sizeChitCardHeight);
        int rowModifier = (int) Math.floor(cardRow);

        // need to have a check for if chitNumCards does not cleanly divide into rows
        // otherwise index error will occur
        for (int i = 0; i < maxCardsPerRow && i < chitCardArray.length; i++) {
            int currentCardIndex = (maxCardsPerRow*rowModifier) + i;
            if (rowModifier < Math.ceil((double) chitCardArray.length / maxCardsPerRow)) {
                ChitCard chitCard = chitCardArray[currentCardIndex];

                //print covered or uncovered chit card based on its state
                if (cardRowCount < GameMaster.getNumChitCards() / maxCardsPerRow * sizeChitCardHeight) {
                    gameBoardString.append(ChitCard.printChitCard(chitCard, cardRowCount % sizeChitCardHeight));

                }
            } else {
                //print buffer for gameBoard
                String buffer = " ".repeat(sizeChitCardWidth);
                gameBoardString.append(buffer);
            }

            gameBoardString.append(" ");
        }

    }

    private void printGameBoardLine(StringBuilder gameBoardString, String gameBoardLine) {
        gameBoardString.append("    ").append(gameBoardLine).append("<br>");
    }

    public String getFormattedGameBoard() {
        return formattedGameBoard;
    }

    private String formatGameBoardHTML(String gameBoardString) {
        return "<html><pre style=\"font-family: MxPlus IBM BIOS\">" + gameBoardString + "</pre></html>";
    }


    public void createGameBoard() {
        initialiseChitCards();
        initialiseVolcanoTiles();
        initialisePlayers();
    }

    private void initialiseChitCards() {
        chitCardArray = new ChitCard[GameMaster.getNumChitCards()];
        Animal[] possibleAnimals = {new Spider(), new Pirate(), new Salamander(), new Bat(), new BabyDragon()};
        for (int i = 0; i < chitCardArray.length; i++) {
            ChitCard chitCard = new ChitCard();

            chitCard.setChitAnimal(possibleAnimals);
            chitCard.setChitNum();
            chitCard.setCardNum(i + 1);
            chitCard.setChitState(false);

            chitCardArray[i] = chitCard;
        }
    }

    private void initialiseVolcanoTiles() {
        volcanoTileArray = new VolcanoTile[GameMaster.getNumVolcanoTiles()];
        Animal[] possibleAnimals = {new Spider(), new Salamander(), new Bat(), new BabyDragon()};
        for (int i = 0; i < volcanoTileArray.length; i++) {
            VolcanoTile volcanoTile = new VolcanoTile();

//            volcanoTile.setVolcanoTileAnimal(possibleAnimals);
//            volcanoTile.setVolcanoState(false);
//            volcanoTile.setVolcanoTileNum(i);

            volcanoTileArray[i] = volcanoTile;


        }
    }

    private void initialisePlayers() {
        playerArray = new Player[GameMaster.getNumPlayers()];
        for (int i = 0; i < playerArray.length; i++) {
            Player player = new Player();

//            player.setPlayerCave(i);
            player.setPlayerName(i);

            playerArray[i] = player;
        }
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }

    public ChitCard[] getChitCardArray() {
        return chitCardArray;
    }
}
