package game.displays;

import game.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Class used to display the winner of the game
 */
public class WinnerDisplay {
    private final JPanel winnerScreen = new JPanel();
    private final JLabel winnerLabel = new JLabel();
    private final JPanel separator = new JPanel();

    private final JButton exitGameButton = new JButton("EXIT GAME");

    /**
     * Constructor
     *
     * @param display the DisplayManager handling game displays
     * @param window  the GameWindow instance this class affects
     */
    public WinnerDisplay(DisplayManager display, GameWindow window) {
        initialise(window);
        exitGameButton.addActionListener(e -> display.closeGame());

    }

    /**
     * Initialises the winner screen
     *
     * @param window the GameWindow instance
     */
    private void initialise(GameWindow window) {
        JFrame frame = window.getFrame();

        winnerScreen.setLayout(new GridBagLayout());
        winnerScreen.setBackground(Color.BLACK);

        GridBagConstraints constraints = initialiseConstraints();

        initialiseWinnerLabel(window, constraints);
        initialiseSeparator(constraints);
        initialiseButtons(window, constraints);

        frame.add(winnerScreen);
    }

    /**
     * Initialises the separator for the display
     *
     * @param constraints the constraints for the layout
     */
    private void initialiseSeparator(GridBagConstraints constraints) {
        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 50, 100);

        separator.setPreferredSize(new Dimension(0, GameWindow.PADDING / 2));
        winnerScreen.add(separator, constraints);
    }

    /**
     * Initialises the winner label
     *
     * @param window      the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initialiseWinnerLabel(GameWindow window, GridBagConstraints constraints) {
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setFont(window.getFont().deriveFont(GameWindow.TITLE_FONT_SIZE));
        winnerScreen.add(winnerLabel, constraints);
    }

    /**
     * Initialises the buttons for the display
     *
     * @param window      the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initialiseButtons(GameWindow window, GridBagConstraints constraints) {
        int padding = 30;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(50, 300, 50, 300);

        window.customiseButton(exitGameButton, GameWindow.BODY_FONT_SIZE, padding);
        winnerScreen.add(exitGameButton, constraints);
    }

    /**
     * Initialises the constraints for the layout
     *
     * @return the constraints for the layout
     */
    private GridBagConstraints initialiseConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();

        // Add title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.insets = new Insets(50, 100, 15, 100);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        return constraints;
    }

    /**
     * Displays the winner of the game
     *
     * @param player the Player that won the game
     */
    public void setWinnerText(Player player) {
        winnerLabel.setText(player.getName().toUpperCase() + " WINS!");
        winnerLabel.setForeground(player.getColour());
        separator.setBackground(player.getColour());

    }

    /**
     * Shows the winner screen.
     *
     * @param window the JFrame to hide the settings screen from
     */
    protected void showScreen(JFrame window) {
        winnerScreen.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.revalidate();
        window.repaint();
    }

    /**
     * Hides the winner screen.
     *
     * @param window the JFrame to hide the settings screen from
     */
    protected void hideScreen(JFrame window) {
        winnerScreen.setVisible(false);
        window.revalidate();
        window.repaint();
    }
}
