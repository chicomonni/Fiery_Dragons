package game.displays;

import javax.swing.*;

public class TitleDisplay {
    private final JTextArea logo = new JTextArea();
    private final JPanel optionsContainer = new JPanel();
    private final JTextField inputField = new JTextField();
    private final JTextField inputMarker = new JTextField(1);

    /**
     * Constructor
     *
     * @param gameWindow the GameWindow instance this class affects
     */
    public TitleDisplay(GameWindow gameWindow) {
        initialise(gameWindow.getTitleScreen());

//        gameWindow.showLogoDisplay();
    }

    private void initialise(JPanel container) {
    }

}
