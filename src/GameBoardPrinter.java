import Utils.FancyMessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class GameBoardPrinter {

    public static void printGameBoard(GameBoard gameBoard) throws IOException {
        StringBuilder gameBoardString = new StringBuilder();
        InputStream gameBoardFile = Objects.requireNonNull(GameBoard.class.getResourceAsStream("GameBoard.txt"));

        try (Scanner scanner = new Scanner(gameBoardFile)) {
//            printTitleCard(gameBoardString);
            skipLines(scanner, 5); // Skip lines 1 to 6
            gameBoardString.append("<br>");
            printKey(gameBoardString);
            gameBoardString.append("<br><br><br><br>");

            int cardRowCount = 0;
            for (int i = 6; i <= 89 && scanner.hasNextLine(); i++) {
                String gameBoardLine = scanner.nextLine();
                printMargin(gameBoardString);
                printChitCardLine(gameBoard, gameBoardString, cardRowCount);
                printMargin(gameBoardString);
                printMargin(gameBoardString);
                printMargin(gameBoardString);
                printGameBoardLine(gameBoardString, gameBoardLine);
                cardRowCount++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gameBoard.setFormattedGameBoard(formatGameBoardHTML(gameBoardString.toString()));
    }

    private static void skipLines(Scanner scanner, int numLines) throws IOException {
        for (int i = 0; i < numLines; i++) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            } else {
                throw new IOException("GameBoard.txt file is empty or doesn't contain enough lines.");
            }
        }
    }

    public static void printKey(StringBuilder gameBoardString) {
        String[] animalKey = {
                "* → SPIDER            ",
                "S → SALAMANDER        ",
                "w → BAT               ",
                "0 → BABY DRAGON       "
        };

        String[] playerKey = {
                "<font color='#39aaff'>PLAYER 1 → BLUE (1)   </font>",
                "<font color='#ff79bb'>PLAYER 2 → PINK (2)   </font>",
                "<font color='#33f021'>PLAYER 3 → GREEN (3)  </font>",
                "<font color='yellow'>PLAYER 4 → YELLOW (4) </font>"
        };

        printMargin(gameBoardString);
        for (int i = 0; i < animalKey.length; i++) {
            gameBoardString.append(playerKey[i]);
        }
        gameBoardString.append("<br><br>");
        printMargin(gameBoardString);
        for (int i = 0; i < animalKey.length; i++) {
            gameBoardString.append(animalKey[i]);
        }
    }

    private static String formatGameBoardHTML(String gameBoardString) {
        return "<html><pre style=\"font-family: MxPlus IBM BIOS\">" + gameBoardString + "</pre></html>";
    }

    public static void printMargin(StringBuilder gameBoardString) {
        gameBoardString.append("    ");
    }

    private static void printChitCardLine(GameBoard gameBoard, StringBuilder gameBoardString, int cardRowCount) {
        //default chit card size display
        int sizeChitCardHeight = 17;
        int sizeChitCardWidth = 11;
        int maxCardsPerRow = 4;

        double cardRow = (double) cardRowCount / (sizeChitCardHeight);
        int rowModifier = (int) Math.floor(cardRow);

        //need to have a check for if chitNumCards does not cleanly divide into rows
        //otherwise index error will occur
        for (int i = 0; i < maxCardsPerRow && i < gameBoard.getChitCardArray().length; i++) {
            int currentCardIndex = (maxCardsPerRow * rowModifier) + i;
            if (rowModifier < Math.ceil((double) gameBoard.getChitCardArray().length / maxCardsPerRow)) {
                ChitCard chitCard = gameBoard.getChitCardArray()[currentCardIndex];

                //print covered or uncovered chit card based on its state
                if (cardRowCount < GameMaster.getNumChitCards() / maxCardsPerRow * sizeChitCardHeight) {
                    gameBoardString.append(ChitCard.printChitCard(chitCard, cardRowCount % sizeChitCardHeight));
                }
            } else {
                //run out of card rows
                //print buffer for gameBoard
                String buffer = " ".repeat(sizeChitCardWidth);
                gameBoardString.append(buffer);
            }

            gameBoardString.append(" ");
        }
    }

    private static void printGameBoardLine(StringBuilder gameBoardString, String gameBoardLine) {
        gameBoardString.append("    ").append(gameBoardLine).append("<br>");
    }

    private static void printTitleCard(StringBuilder gameBoardString) {
        for (String line : FancyMessage.TITLE_CARD) {
            gameBoardString.append(line).append("\n");
        }
    }

}