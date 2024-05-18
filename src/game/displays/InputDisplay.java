package game.displays;

import utils.Typing;

import javax.swing.*;
import java.awt.*;

public class InputDisplay {
    private final JTextField inputField = new JTextField();
    private final JTextField prompt = new JTextField();
    private final JTextField inputMarker = new JTextField(1);
    private String playerInput;

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

        prompt.setEditable(false);
        prompt.setForeground(Color.WHITE);
        prompt.setBackground(Color.BLUE);
        prompt.setBorder(null);
        prompt.setOpaque(false);
        container.add(prompt, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;

        inputMarker.setEditable(false);
        inputMarker.setOpaque(false);
        inputMarker.setBorder(null);
        inputMarker.setForeground(Color.WHITE);
        container.add(inputMarker, constraints);

        constraints.weightx = 1;
        constraints.gridx = 1;

        inputField.setEditable(false);
        inputField.setOpaque(false);
        inputField.setBorder(null);
        inputField.setForeground(Color.WHITE);
        inputField.setBackground(Color.GREEN);
        inputField.setToolTipText("Enter Your Move");
        inputField.addActionListener(e -> playerInput = inputField.getText());
        container.add(inputField, constraints);

        container.revalidate();
    }

    public void setPromptText(String currentPlayer, String promptText) {
        Typing.animateTyping(currentPlayer, prompt, promptText, 40);
        inputMarker.setText(">");
        inputField.setEditable(true);
        inputField.requestFocus();
    }

    public String getPlayerInput() {
        return playerInput;
    }
}
