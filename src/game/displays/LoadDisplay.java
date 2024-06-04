package game.displays;

import game.FieryDragons;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoadDisplay {
    private final JPanel loadScreen = new JPanel();
    private final JLabel titleLabel = new JLabel("SELECT SAVED FILE");
    private final JPanel separator = new JPanel();
    private final JButton loadGameButton1 = new JButton("LOAD GAME 1");
    private final JButton loadGameButton2 = new JButton("LOAD GAME 2");
    private final JButton loadGameButton3 = new JButton("LOAD GAME 3");
    private final JButton backButton = new JButton("BACK");

    public LoadDisplay(FieryDragons fieryDragons, DisplayManager display, GameWindow window) {
        initialise(display, window);
        addListeners(fieryDragons, display, window, window.getFrame());

    }

    private void initialise(DisplayManager display, GameWindow window) {
        JFrame frame = window.getFrame();
        loadScreen.setLayout(new GridBagLayout());
        loadScreen.setBackground(Color.BLACK);

        GridBagConstraints constraints = initialiseConstraints();
        initialiseTitleLabel(window, constraints);
        initialiseSeparator(constraints);
        initialiseLoadButtons(window, constraints);
        initialiseBridgeButtons(window, constraints);


        frame.add(loadScreen);
    }

    private void addListeners(FieryDragons fieryDragons, DisplayManager display, GameWindow window, JFrame frame) {
        // Add action listeners
        backButton.addActionListener(e -> display.displayTitleScreen(window.getFrame()));


        loadGameButton1.addActionListener(e -> {
            try {
                fieryDragons.continueGame(display, window,1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });
        loadGameButton2.addActionListener(e -> {
            try {
                fieryDragons.continueGame(display, window, 2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }); //fieryDragons.playGame(display, window));
        loadGameButton3.addActionListener(e -> {
            try {
                fieryDragons.continueGame(display, window, 3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }); //change to exitGame() method??
    }

    /**
     * Initializes the constraints for the settings screen layout.
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
     * Initializes the title label for the settings screen.
     * @param window the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initialiseTitleLabel(GameWindow window, GridBagConstraints constraints) {
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(window.getFont().deriveFont(GameWindow.TITLE_FONT_SIZE));
        loadScreen.add(titleLabel, constraints);
    }

    private void initialiseLoadButtons(GameWindow window, GridBagConstraints constraints) {
        constraints.gridy++;
        constraints.gridwidth = 1;
        window.customiseButton(loadGameButton1, GameWindow.BODY_FONT_SIZE);
        loadScreen.add(loadGameButton1, constraints);

        constraints.gridy++;
        window.customiseButton(loadGameButton2, GameWindow.BODY_FONT_SIZE);
        loadScreen.add(loadGameButton2, constraints);

        constraints.gridy++;
        window.customiseButton(loadGameButton3, GameWindow.BODY_FONT_SIZE);
        loadScreen.add(loadGameButton3, constraints);
    }

    private void initialiseBridgeButtons(GameWindow window, GridBagConstraints constraints) {
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(50, 100, 50, 10);

        window.customiseButton(backButton, GameWindow.BODY_FONT_SIZE);
        loadScreen.add(backButton, constraints);
    }

    /**
     * Adds a separator to the settings screen.
     * @param constraints the constraints for the layout
     */
    private void initialiseSeparator(GridBagConstraints constraints) {
        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 50, 100);

        separator.setPreferredSize(new Dimension(0, GameWindow.PADDING / 2));
        separator.setBackground(Color.WHITE);
        loadScreen.add(separator, constraints);
    }

    public void showScreen(JFrame window) {
        loadScreen.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.revalidate();
        window.repaint();
    }

    public void hideScreen(JFrame window) {
        loadScreen.setVisible(false);
        window.revalidate();
        window.repaint();
    }
}
