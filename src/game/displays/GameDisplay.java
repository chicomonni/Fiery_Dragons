package game.displays;

import boardGenerator.BoardGenerator;
import game.chitCards.ChitCard;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The GameDisplay class is responsible for setting up and managing the display
 * elements of the Fiery Dragons game, including the volcano, chit cards, footer,
 * and the winner label.
 */
public class GameDisplay {
    private final JLayeredPane volcano = new JLayeredPane();
    private final JPanel chitCards = new JPanel();
    private final JPanel footer = new JPanel();
    private final JLabel winner = new JLabel();
    private final JPanel separator = new JPanel();

    /**
     * Constructor.
     *
     * @param window the GameWindow instance
     * @throws IOException         if the font file cannot be accessed
     * @throws FontFormatException if the font file contains incorrect data
     */
    public GameDisplay(GameWindow window) throws IOException, FontFormatException {
        try {
            initialise(window);
            hideScreen(window.getFrame());
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the game window and its components.
     *
     * @param window the GameWindow instance
     * @throws IOException         if the font file cannot be accessed
     * @throws FontFormatException if the font file contains incorrect data
     */
    private void initialise(GameWindow window) throws IOException, FontFormatException {
        // Configure the window container
        Container container = window.getFrame().getContentPane();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.BLACK);

        initaliseVolcanoDisplay(container);

        initialiseChitCardDisplay(container);

        initialiseSeparator(container);

        initialiseFooter(container);

        // Initialise and configure the winner label
        winner.setFont(window.getFont().deriveFont(GameWindow.BODY_FONT_SIZE)); // Use the same font
        winner.setHorizontalAlignment(JLabel.CENTER); // Center the text
        winner.setBounds(50, 50, 200, 50);
    }

    /**
     * Initialise container for the Volcano and add the component to the window
     *
     * @param container the container to add the volcano to
     */
    private void initaliseVolcanoDisplay(Container container) {
        GridBagConstraints constraints = new GridBagConstraints();

        // Configure constraints
        constraints.insets = new Insets(GameWindow.PADDING, GameWindow.PADDING, 0, GameWindow.PADDING / 2);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        volcano.setPreferredSize(new Dimension(
                (int) (BoardGenerator.VOLCANO_SIZE * GameWindow.ASCII_FONT_SIZE),
                (int) (BoardGenerator.VOLCANO_SIZE * GameWindow.ASCII_FONT_SIZE)
        ));
        container.add(volcano, constraints);
    }

    /**
     * Initialise container for the Chit Cards and add the component to the window
     *
     * @param container the container to add the chit cards to
     */
    private void initialiseChitCardDisplay(Container container) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(GameWindow.PADDING, GameWindow.PADDING / 2, 0, GameWindow.PADDING);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;

        chitCards.setOpaque(false);
        chitCards.setPreferredSize(new Dimension(
                (int) (ChitCard.CARD_WIDTH * GameWindow.ASCII_FONT_SIZE * 7 + 7),
                (int) (ChitCard.CARD_HEIGHT * GameWindow.ASCII_FONT_SIZE * 5)
        ));
        chitCards.setLayout(new GridBagLayout());
        container.add(chitCards, constraints);
    }

    /**
     * Initialise separator panel and add it to the window
     *
     * @param container the container to add the separator to
     */
    private void initialiseSeparator(Container container) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(
                GameWindow.PADDING, 4 * GameWindow.PADDING, GameWindow.PADDING, 4 * GameWindow.PADDING
        );
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        separator.setPreferredSize(new Dimension(0, GameWindow.PADDING / 2));
        container.add(separator, constraints);
    }

    /**
     * Initialise container for the input and add the component to the window
     *
     * @param container the container to add the footer to
     */
    private void initialiseFooter(Container container) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(
                0, 4 * GameWindow.PADDING, GameWindow.PADDING, 4 * GameWindow.PADDING
        );
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        footer.setOpaque(false);
        footer.setLayout(new GridBagLayout());
        footer.setPreferredSize(new Dimension(1, (int) (GameWindow.BODY_FONT_SIZE * 3 + GameWindow.PADDING)));
        container.add(footer, constraints);

        container.revalidate();
        container.repaint();
    }

    /**
     * Getter for the swing component that displays the Volcano
     *
     * @return swing component that displays the Volcano
     */
    public JLayeredPane getVolcanoComponent() {
        return volcano;
    }

    /**
     * Getter for the swing component that displays the ChitCards
     *
     * @return swing component that displays the ChitCards
     */
    public JPanel getChitCardsComponent() {
        return chitCards;
    }

    /**
     * Getter for the swing component that holds the input field
     *
     * @return swing component that holds the input field
     */
    public JPanel getFooter() {
        return footer;
    }

    /**
     * Getter for the winner JLabel
     *
     * @return the winner JLabel
     */
    public JLabel getWinnerDisplay() {
        return winner;
    }

    /**
     * Displays the winner label, removing all other components from the frame.
     *
     * @param frame the JFrame to show the winner label on
     */
    public void showWinnerLabel(JFrame frame) {
        Container container = frame.getContentPane();
        // Remove all components from the container and add the winner label
        container.removeAll();
        container.add(winner);

        // Revalidate and repaint the container
        container.revalidate();
        container.repaint();
    }

    /**
     * Shows the game screen components.
     *
     * @param frame the JFrame to show the game screen components on
     */
    public void showScreen(JFrame frame) {
        volcano.setVisible(true);
        chitCards.setVisible(true);
        separator.setVisible(true);
        footer.setVisible(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Hides the game screen components.
     *
     * @param frame the JFrame to hide the game screen components on
     */
    public void hideScreen(JFrame frame) {
        volcano.setVisible(false);
        chitCards.setVisible(false);
        separator.setVisible(false);
        footer.setVisible(false);

        frame.revalidate();
        frame.repaint();
    }

}
