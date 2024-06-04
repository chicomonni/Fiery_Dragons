package game.displays;

import game.FieryDragons;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class responsible for displaying the load screen of the game,
 * allowing players to restore a previous game state.
 */
public class LoadDisplay {
    private final JPanel loadScreen = new JPanel();
    private final JLabel titleLabel = new JLabel("SELECT SAVE");
    private final JPanel separator = new JPanel();
    private final JButton[] loadGameButtons = new JButton[FieryDragons.MAX_SAVES];
    private final JButton backButton = new JButton("BACK");
    private final FieryDragons fieryDragons;

    /**
     * Constructor.
     *
     * @param fieryDragons the FieryDragons instance
     * @param display      the DisplayManager instance
     * @param window       the GameWindow instance
     */
    public LoadDisplay(FieryDragons fieryDragons, DisplayManager display, GameWindow window) {
        this.fieryDragons = fieryDragons;

        initialise(display, window);
        addListeners(display, window);
    }

    /**
     * Initializes the load screen.
     *
     * @param display the DisplayManager instance
     * @param window  the GameWindow instance
     */
    private void initialise(DisplayManager display, GameWindow window) {
        JFrame frame = window.getFrame();
        loadScreen.setLayout(new GridBagLayout());
        loadScreen.setBackground(Color.BLACK);

        GridBagConstraints constraints = initialiseConstraints();
        initialiseTitleLabel(window, constraints);
        initialiseSeparator(constraints);
        initialiseLoadButtons(window, constraints);
        initialiseBackButton(display, window, constraints);


        frame.add(loadScreen);
    }

    /**
     * Adds action listeners to the buttons on the load screen.
     *
     * @param display the DisplayManager instance
     * @param window  the GameWindow instance
     */
    private void addListeners(DisplayManager display, GameWindow window) {
        File[] fileList = fieryDragons.checkSaveFolder();

        for (int i = 0; i < fileList.length; i++) {
            int finalI = i;
            loadGameButtons[i].setText("LOAD SAVE FILE " + (i + 1));
            loadGameButtons[i].addActionListener(e -> {
                try {
                    fieryDragons.continueGame(display, window, finalI);
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    /**
     * Initializes the constraints for the settings screen layout.
     *
     * @return the constraints for the layout
     */
    private GridBagConstraints initialiseConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();

        // Add title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.insets = new Insets(50, 100, 15, 100);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        return constraints;
    }

    /**
     * Initializes the title label for the settings screen.
     *
     * @param window      the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initialiseTitleLabel(GameWindow window, GridBagConstraints constraints) {
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(window.getFont().deriveFont(GameWindow.TITLE_FONT_SIZE));
        loadScreen.add(titleLabel, constraints);
    }

    /**
     * Initializes the load buttons for the settings screen.
     *
     * @param window      the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initialiseLoadButtons(GameWindow window, GridBagConstraints constraints) {
        String emptySaveName = "EMPTY SAVE";

        for (int i = 0; i < loadGameButtons.length; i++) {
            loadGameButtons[i] = new JButton(emptySaveName);
        }

        int padding = 15;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 100, 10, 100);

        for (JButton loadGameButton : loadGameButtons) {
            constraints.gridy++;
            window.customiseButton(loadGameButton, GameWindow.BODY_FONT_SIZE, padding);
            loadScreen.add(loadGameButton, constraints);
        }
    }

    /**
     * Initializes the back button for the settings screen.
     *
     * @param display     the DisplayManager instance
     * @param window      the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initialiseBackButton(DisplayManager display, GameWindow window, GridBagConstraints constraints) {
        int padding = 15;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(20, 200, 50, 200);

        window.customiseButton(backButton, GameWindow.BODY_FONT_SIZE, padding);
        backButton.addActionListener(e -> display.displayTitleScreen(window.getFrame()));
        loadScreen.add(backButton, constraints);
    }

    /**
     * Adds a separator to the settings screen.
     *
     * @param constraints the constraints for the layout
     */
    private void initialiseSeparator(GridBagConstraints constraints) {
        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 50, 100);

        separator.setPreferredSize(new Dimension(0, GameWindow.PADDING / 2));
        separator.setBackground(Color.WHITE);
        loadScreen.add(separator, constraints);
    }

    /**
     * Displays the load screen.
     *
     * @param window the JFrame instance
     */
    public void showScreen(JFrame window) {
        loadScreen.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.revalidate();
        window.repaint();
    }

    /**
     * Hides the load screen.
     *
     * @param window the JFrame instance
     */
    public void hideScreen(JFrame window) {
        loadScreen.setVisible(false);
        window.revalidate();
        window.repaint();
    }
}
