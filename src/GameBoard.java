import Animals.Animal;
import Animals.Characters.Spider;
import Animals.Traitors.Pirate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class GameBoard {
    private String formattedGameBoard;
    private ChitCard[] chitCardArray;
    private VolcanoTile[] volcanoTileArray;
    private Player[] playerArray;


    public void printGameBoard() throws IOException {
        StringBuilder gameBoardString = new StringBuilder();
        File gameBoardFile = new File("src/GameBoard.txt");

        try (Scanner scanner = new Scanner(gameBoardFile)) {
            skipLines(scanner, 5); // Skip lines 1 to 6

            int cardRowCount = 0;
            for (int i = 6; i <= 88 && scanner.hasNextLine(); i++) {
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

    private void printMargin(StringBuilder gameBoardString) {
        gameBoardString.append("    ");
    }

    private void printChitCardLine(StringBuilder gameBoardString, int cardRowCount) {
        //default chit card size display
        int sizeChitCardHeight = 16;
        int sizeChitCardWidth = 11;

        double cardRow = (double) cardRowCount / (sizeChitCardHeight);
        int rowModifier = (int) Math.floor(cardRow);

        for (int i = 0; i < 4 && i < chitCardArray.length; i++) {
            int currentCardIndex = (4*rowModifier) + i;
            if (rowModifier < Math.ceil((double) chitCardArray.length / 4)) {
                ChitCard chitCard = chitCardArray[currentCardIndex];

                // Print covered or uncovered chit card based on its state
                if (cardRowCount < GameMaster.getNumChitCards() / 4 * sizeChitCardHeight) {
                    gameBoardString.append(ChitCard.printChitCardCovered(chitCard, cardRowCount % sizeChitCardHeight));
                }
            } else {
                // Print buffer for game board
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
        initializeChitCards();
        initializeVolcanoTiles();
        initializePlayers();
    }

    private void initializeChitCards() {
        chitCardArray = new ChitCard[GameMaster.getNumChitCards()];
        Animal[] possibleAnimals = {new Spider(), new Pirate()};
        for (int i = 0; i < chitCardArray.length; i++) {
            ChitCard chitCard = new ChitCard();

            chitCard.setChitAnimal(possibleAnimals);
            chitCard.setChitNum();
            chitCard.setCardNum(i + 1);
            chitCard.setChitState(false);

            chitCardArray[i] = chitCard;
        }
    }

    private void initializeVolcanoTiles() {
        volcanoTileArray = new VolcanoTile[GameMaster.getNumVolcanoTiles()];
        for (int i = 0; i < volcanoTileArray.length; i++) {
            volcanoTileArray[i] = new VolcanoTile();


        }
    }

    private void initializePlayers() {
        int numPlayers = 4; //default number
        playerArray = new Player[numPlayers];
        for (int i = 0; i < playerArray.length; i++) {
            playerArray[i] = new Player(); // Initialize with appropriate parameters if needed
        }
    }
}
