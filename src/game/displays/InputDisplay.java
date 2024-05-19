package game.displays;

import game.Board;
import game.Player;
import game.actions.GameAction;
import game.actions.NextTurnGameAction;
import utils.Typing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputDisplay {
    private final JTextField inputField = new JTextField();
    private final JPanel promptContainer = new JPanel();
    private final JTextField inputMarker = new JTextField(1);


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
        container.add(inputField, constraints);

        container.revalidate();
    }

    private void initialiseTextField(JTextField textField) {
        textField.setEditable(false);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setForeground(Color.WHITE);
    }

    public void setPromptText(Player player, Board board) {
        promptContainer.removeAll();

        JTextField prompt = new JTextField();
        initialiseTextField(prompt);
        promptContainer.add(prompt, BorderLayout.CENTER);

        JTextField name = new JTextField(player.getName().toUpperCase() + ":", player.getName().length() + 1);
        initialiseTextField(name);
        name.setForeground(player.getColour());
        name.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, (int) GameWindow.FOOTER_FONT_SIZE));
        promptContainer.add(name, BorderLayout.WEST);
        promptContainer.revalidate();

        String promptText = "SELECT A CHIT CARD (1 - " + board.getChitCards().length() + ")";

        Typing.animateTyping(prompt, promptText, 40);
        inputMarker.setText(">");
        inputMarker.setForeground(player.getColour());
        inputField.setForeground(player.getColour());
        inputField.requestFocus();
    }

    public void enableInput(Player player, Board board, DisplayManager display) {
        inputField.setEditable(true);

        removeAllActionListeners(inputField);
        inputField.addActionListener((e) -> {
            String input = inputField.getText();
            if (!validateInput(input)) {
                return;
            }
            inputField.setEditable(false);

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
