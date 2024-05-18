package game.displays;

import game.Board;

import javax.swing.*;

public class InputDisplay {
    private String playerInput;

    public InputDisplay(JTextField playerInputField) {
        playerInputField.addActionListener(e -> playerInput = playerInputField.getText());

    }

    public String getPlayerInput() {
        return playerInput;
    }
}
