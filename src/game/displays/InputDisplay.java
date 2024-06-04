package game.displays;

import game.Board;
import game.FieryDragons;
import game.Player;
import game.actions.EndTurnGameAction;
import game.actions.GameAction;
import game.actions.NextTurnGameAction;
import game.utils.Typing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * A display for handling user input during the game.
 */
public class InputDisplay {
    private final JTextField inputField = new JTextField();
    private final JPanel promptContainer = new JPanel();
    private final JTextField inputMarker = new JTextField(1);
    private final FieryDragons gameData;

    /**
     * Initializes the InputDisplay and sets it up within the specified GameDisplay.
     *
     * @param gameDisplay the game window to attach this input display to
     */
    public InputDisplay(GameDisplay gameDisplay, FieryDragons gameData) {
        this.gameData = gameData;
        initialise(gameDisplay.getFooter());
    }

    /**
     * Initializes the components of the input display and adds them to the specified container.
     *
     * @param container the container to which the input display components will be added
     */
    private void initialise(JPanel container) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(3, 3, 3, 3);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weighty = 1;

        promptContainer.setOpaque(false);
        promptContainer.setLayout(new BorderLayout());
        container.add(promptContainer, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;

        initialiseTextField(inputMarker);
        container.add(inputMarker, constraints);

        constraints.weightx = 1;
        constraints.gridx = 1;

        initialiseTextField(inputField);
        inputField.setToolTipText("Enter Your Move");
        container.add(inputField, constraints);

        container.revalidate();
    }

    /**
     * Configures the appearance of a JTextField to be used within the input display.
     *
     * @param textField the JTextField to be configured
     */
    private void initialiseTextField(JTextField textField) {
        textField.setEditable(false);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setForeground(Color.WHITE);
    }

    /**
     * Sets the prompt text for the current player and board state, and animates the typing effect.
     *
     * @param player the current player
     * @param board  the current game board
     */
    public void setPromptText(Player player, Board board) {
        promptContainer.removeAll();

        promptContainer.revalidate();
        promptContainer.repaint();

        JTextField prompt = new JTextField();
        initialiseTextField(prompt);
        promptContainer.add(prompt, BorderLayout.CENTER);

        JTextField name = new JTextField(player.getName().toUpperCase() + ":", player.getName().length() + 1);
        initialiseTextField(name);
        name.setForeground(player.getColour());
        name.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, (int) GameWindow.BODY_FONT_SIZE));
        promptContainer.add(name, BorderLayout.WEST);

        String promptText = "SELECT A CHIT CARD (1 - " + board.getChitCards().length() + ") OR 'END TURN'";

        Typing.animateTyping(prompt, promptText, 40);
        inputMarker.setText(">");
        inputMarker.setForeground(player.getColour());
        inputField.setForeground(player.getColour());
        inputField.requestFocus();
    }

    /**
     * Enables user input, allowing the current player to make a move based on their input.
     *
     * @param player  the current player
     * @param board   the current game board
     * @param display the display manager handling game displays
     */
    public void enableInput(Player player, Board board, DisplayManager display) {
        inputField.setEditable(true);

        removeAllActionListeners(inputField);
        inputField.addActionListener((e) -> {
            String input = inputField.getText().strip();
            if (!validateInput(input)) {
                return;
            }
            inputField.setEditable(false);

            player.playTurn(getActionFromString(input, player, board), board, display);
        });
    }

    /**
     * Removes all action listeners from a specified JTextField.
     *
     * @param textField the JTextField from which to remove all action listeners
     */
    private void removeAllActionListeners(JTextField textField) {
        for (ActionListener actionListener : textField.getActionListeners()) {
            textField.removeActionListener(actionListener);
        }
    }

    /**
     * Validates the user input to ensure it is an integer.
     *
     * @param input the user input to validate
     * @return true if the input is a valid integer, false otherwise
     */
    private boolean validateInput(String input) {
        try {
            if (input.toLowerCase().strip().equals("end turn")) {
                return true;
            }

            if (input.toLowerCase().strip().equals("save game")) {
                return true;
            }

            Integer.parseInt(input);
            return true;

        } catch (NumberFormatException ex) {
            printError("INPUT A VALID MOVE");
            return false;
        }
    }

    /**
     * Creates a GameAction based on the user input, or returns a NextTurnGameAction if the input is invalid.
     *
     * @param input  the user input
     * @param player the current player
     * @param board  the current game board
     * @return a GameAction representing the user's move
     */
    private GameAction getActionFromString(String input, Player player, Board board) {
        if (!validateInput(input)) {
            return new NextTurnGameAction(player);
        }

        if (input.toLowerCase().strip().equals("end turn")) {
            return new EndTurnGameAction(player);
        }

        if (input.toLowerCase().strip().equals("save game")) {
            gameData.saveGame();
            return new NextTurnGameAction(player);
        }


        return board.getChitCards().getAction(player, Integer.parseInt(input));
    }

    /**
     * Displays an error message to the user and temporarily disables input.
     *
     * @param message the error message to display
     */
    public void printError(String message) {
        inputField.setText("!!! " + message + " !!!");
        inputField.setEditable(false);

        Timer timer = new Timer(1000, e -> {
            inputField.setText("");
            inputField.setEditable(true);
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Disables user input and clears the input field.
     */
    public void disableInput() {
        inputField.setEditable(false);
        inputField.setText("");
    }

}
