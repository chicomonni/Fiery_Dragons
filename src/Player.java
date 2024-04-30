import javax.swing.*;
import Utils.Typing;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Player {
    private CaveTile playerCave;
    private int caveDistance;
    private int currentLocation;
    private int nextLocation;
    private boolean bCanUncover;
    private String playerName;

//    public void setPlayerCave(int i) {
//    this.playerCave =
//    }

    public void setPlayerName(int i) {
        this.playerName = "PLAYER " + String.valueOf(i + 1);
    }

    public String getPlayerName() {
        return playerName;
    }

    void startTurn(JTextField promptField, JTextField inputTextField) {
        String playerTurnText = getPlayerName() + "  )    ";
        selectChitCard(promptField, inputTextField, playerTurnText);
    }

    private void selectChitCard(JTextField promptField, JTextField inputTextField, String playerTurnText) {
        String promptText = "Please select a Chit Card to flip (1 to " + GameMaster.getNumChitCards() + "): ";

        // Set initial text to only show the player turn
        Typing.animateTyping(playerTurnText, promptField, promptText, 40);
        GameBoard gameBoard = GameBoard.getInstance();

        //save userInput on enter keyboard press
        inputTextField.addActionListener(e -> {
            String userInput = inputTextField.getText();

            try {
                //check if int
                int userInt = Integer.parseInt(userInput);

                //check if between 0 - numChitCards
                if (userInt >= 1 && userInt <= GameMaster.getNumChitCards()) {
                    //check if already flipped
                    if (gameBoard.getChitCardArray()[userInt - 1].isChitUncovered()) {
                        String flippedPromptText = "This Chit Card has already been flipped. Please select one between 1 and " + GameMaster.getNumChitCards() + ": ";
                        Typing.animateTyping(playerTurnText, promptField, flippedPromptText, 40);

                    } else {
                        String successText = "You picked Chit Card number " + String.valueOf(userInt);
                        Typing.animateTyping(playerTurnText, promptField, successText, 40);
                        flipChitCard(userInt);
                    }

                }  else {
                    //out of range
                    String rangePromptText = "Out of range. Please select a Chit Card between 1 and " + GameMaster.getNumChitCards() + ": ";
                    Typing.animateTyping(playerTurnText, promptField, rangePromptText, 40);
                }

            } catch (NumberFormatException ex) {
                //not integer
                String typePromptText = "You must enter an integer. Please select a Chit Card between 1 and " + GameMaster.getNumChitCards() + ": ";
                Typing.animateTyping(playerTurnText, promptField, typePromptText, 40);
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }

            //clear inputTextField after processing user input
            inputTextField.setText("");
        });
    }


    private void flipChitCard(int userInt) throws IOException, FontFormatException {
        GameBoard.getInstance().getChitCardArray()[userInt - 1].setChitState(true);
        // reprint gameBoard with new flipped display
        GameBoard.getInstance().printGameBoard();
        GameDisplay.getInstance("Fiery Dragons").setText(GameBoard.getInstance().getFormattedGameBoard());
    }

}
