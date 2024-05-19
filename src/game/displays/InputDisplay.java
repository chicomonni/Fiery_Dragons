package game.displays;

import game.Board;
import game.Player;
import game.actions.Action;
import game.actions.SelectCardAction;
import utils.Typing;

import javax.swing.*;
import java.awt.*;

public class InputDisplay {
    private final JTextField inputField = new JTextField();
    private final JPanel promptContainer = new JPanel();
    private final JTextField inputMarker = new JTextField(1);
    private String playerInputText;
    private boolean inputReady = false;


    public InputDisplay(GameWindow gameWindow) {
        initialise(gameWindow.getFooter());
    }

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
        inputField.addActionListener(e -> handleInput());
        container.add(inputField, constraints);

        container.revalidate();
    }

    private void initialiseTextField(JTextField textField) {
        textField.setEditable(false);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setForeground(Color.WHITE);
    }

    public void setPromptText(Player player, String promptText) {
        promptContainer.removeAll();

        JTextField prompt = new JTextField();
        initialiseTextField(prompt);
        promptContainer.add(prompt, BorderLayout.CENTER);

        JTextField name = new JTextField(player.getName().toUpperCase() + ":", player.getName().length() + 1);
        initialiseTextField(name);
        name.setForeground(player.getColour());
        promptContainer.add(name, BorderLayout.WEST);
        promptContainer.revalidate();

        Typing.animateTyping(prompt, promptText, 40);
        inputMarker.setText(">");
        inputMarker.setForeground(player.getColour());
        inputField.setEditable(true);
        inputField.setForeground(player.getColour());
        inputField.requestFocus();
    }

    public String getPlayerInputText() {
        return playerInputText;
    }

            player.playTurn(getActionFromString(input, player, board), board, display);
        });
    }

    private void removeAllActionListeners(JTextField textField) {
        for (ActionListener actionListener : textField.getActionListeners()) {
            textField.removeActionListener(actionListener);
        }
    }

    private boolean validateInput(String input) {
        try {
            Integer.parseInt(input);
            return true;

        } catch (NumberFormatException ex) {
            printError("INPUT AN INTEGER");
            return false;
        }
    }

    private GameAction getActionFromString(String input, Player player, Board board) {
        if (!validateInput(input)) {
            return new NextTurnGameAction(player);
        }

        return board.getChitCards().getAction(player, Integer.parseInt(input));
    }

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

    public void disableInput() {
        inputField.setEditable(false);
        inputField.setText("");
    }

}
