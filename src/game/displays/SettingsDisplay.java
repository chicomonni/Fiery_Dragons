package game.displays;

import game.FieryDragons;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

/**
 * The SettingsDisplay class is responsible for displaying the settings screen
 * where players can configure game options before starting the game.
 */
public class SettingsDisplay {
    private final JPanel settingsScreen = new JPanel();
    private final JLabel titleLabel = new JLabel("SETTINGS");
    private final JPanel separator = new JPanel();
    private final JButton startButton = new JButton("START");
    private final JButton backButton = new JButton("BACK");
    private final JSlider playerSlider = new JSlider(2, 8);
    private final JSlider squareSlider = new JSlider();
    private final JCheckBox dragonPirateCheckbox = new JCheckBox(" DRAGON PIRATE", true);
    private final JCheckBox ratRascalCheckbox = new JCheckBox(" RAT RASCAL", false);

    /**
     * Constructor.
     *
     * @param fieryDragons the FieryDragons instance to start the game
     * @param display      the DisplayManager handling game displays
     * @param window       the GameWindow instance this class affects
     */
    public SettingsDisplay(FieryDragons fieryDragons, DisplayManager display, GameWindow window) {
        initialise(display, window);

        //default values
        playerSlider.setValue(4);
        squareSlider.setValue(24);

        updateSquareSlider(window, playerSlider.getValue());

        playerSlider.addChangeListener(e -> updateSquareSlider(window, playerSlider.getValue()));
        backButton.addActionListener(e -> display.displayTitleScreen(window.getFrame()));
        startButton.addActionListener(e ->
                fieryDragons.pickSettings(
                        display,
                        window,
                        playerSlider.getValue(),
                        squareSlider.getValue(),
                        dragonPirateCheckbox.isSelected(),
                        ratRascalCheckbox.isSelected()
                )
        );
    }

    /**
     * Initializes the settings screen with title, sliders, checkboxes, and buttons.
     *
     * @param display the DisplayManager handling game displays
     * @param window  the GameWindow instance this class affects
     */
    public void initialise(DisplayManager display, GameWindow window) {
        JFrame frame = window.getFrame();
        settingsScreen.setLayout(new GridBagLayout());
        settingsScreen.setBackground(Color.BLACK);

        GridBagConstraints constraints = initialiseConstraints();

        initaliseTitleLabel(window, constraints);
        initaliseSeparator(constraints);
        initalisePlayerSlider(window, constraints);

        // Add square slider with label
        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 0, 100);

        settingsScreen.add(createLabel(
                "VOLCANO SIZE:",
                window.getFont().deriveFont(GameWindow.HEADING_FONT_SIZE)
        ), constraints);

        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 50, 100);

        // Configure slider
        squareSlider.setMajorTickSpacing(1);
        squareSlider.setPaintTicks(true);
        squareSlider.setPaintLabels(true);
        squareSlider.setSnapToTicks(true);
        squareSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        squareSlider.setLabelTable(labelSlider(window, playerSlider.getValue() * 3, 27, 1));
        squareSlider.setOpaque(false);
        settingsScreen.add(squareSlider, constraints);

        // Add checkboxes
        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 15, 100);

        settingsScreen.add(createLabel(
                "INCLUDE:",
                window.getFont().deriveFont(GameWindow.HEADING_FONT_SIZE)
        ), constraints);

        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 10, 100);

        dragonPirateCheckbox.setForeground(Color.WHITE);
        dragonPirateCheckbox.setFocusPainted(false);
        window.customiseCheckbox(dragonPirateCheckbox, GameWindow.BODY_FONT_SIZE);
        settingsScreen.add(dragonPirateCheckbox, constraints);

        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 0, 100);

        ratRascalCheckbox.setForeground(Color.WHITE);
        ratRascalCheckbox.setFocusPainted(false);
        window.customiseCheckbox(ratRascalCheckbox, GameWindow.BODY_FONT_SIZE);
        settingsScreen.add(ratRascalCheckbox, constraints);

        // Add buttons
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(50, 100, 50, 10);

        window.customiseButton(backButton, GameWindow.BODY_FONT_SIZE);
        settingsScreen.add(backButton, constraints);

        constraints.gridx = 1;
        constraints.insets = new Insets(50, 10, 50, 100);


        window.customiseButton(startButton, GameWindow.BODY_FONT_SIZE);
        settingsScreen.add(startButton, constraints);

        frame.add(settingsScreen);
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
     * Adds a separator to the settings screen.
     * @param constraints the constraints for the layout
     */
    private void initaliseSeparator(GridBagConstraints constraints) {
        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 50, 100);

        separator.setPreferredSize(new Dimension(0, GameWindow.PADDING / 2));
        separator.setBackground(Color.WHITE);
        settingsScreen.add(separator, constraints);
    }

    /**
     * Adds the player slider to the settings screen.
     * @param window the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initalisePlayerSlider(GameWindow window, GridBagConstraints constraints) {
        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 0, 100);

        settingsScreen.add(createLabel(
                "NUMBER OF PLAYERS:",
                window.getFont().deriveFont(GameWindow.HEADING_FONT_SIZE)
        ), constraints);

        constraints.gridy++;
        constraints.insets = new Insets(0, 100, 50, 100);

        // Configure slider
        playerSlider.setMajorTickSpacing(1);
        playerSlider.setPaintTicks(true);
        playerSlider.setPaintLabels(true);
        playerSlider.setSnapToTicks(true);
        playerSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playerSlider.setLabelTable(labelSlider(window, 2, 8, 1));
        playerSlider.setOpaque(false);
        settingsScreen.add(playerSlider, constraints);
    }

    /**
     * Initializes the title label for the settings screen.
     * @param window the GameWindow instance
     * @param constraints the constraints for the layout
     */
    private void initaliseTitleLabel(GameWindow window, GridBagConstraints constraints) {
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(window.getFont().deriveFont(GameWindow.TITLE_FONT_SIZE));
        settingsScreen.add(titleLabel, constraints);
    }

    /**
     * Creates a label with the specified text and font.
     *
     * @param text the text for the label
     * @param font the font for the label
     * @return the created JLabel
     */
    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(font);
        return label;
    }

    /**
     * Creates a hashtable of labels for a slider.
     *
     * @param window the GameWindow instance
     * @param min    the minimum value of the slider
     * @param max    the maximum value of the slider
     * @param step   the step value for the slider
     * @return a hashtable of labels for the slider
     */
    private Hashtable<Integer, JLabel> labelSlider(GameWindow window, int min, int max, int step) {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        for (int i = min; i <= max; i += step) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setForeground(Color.WHITE);
            label.setFont(window.getFont().deriveFont(GameWindow.ASCII_FONT_SIZE));
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

            labelTable.put(i, label);
        }
        return labelTable;
    }

    /**
     * Updates the range and labels of the square slider based on the number of players.
     *
     * @param window     the GameWindow instance
     * @param numPlayers the number of players
     */
    private void updateSquareSlider(GameWindow window, int numPlayers) {
        // Update the range of the square slider based on the number of players
        squareSlider.setMinimum(numPlayers * 3);
        squareSlider.setMaximum(27);

        int stepSize;
        if (numPlayers == 2) {
            stepSize = 3;
        } else if (numPlayers >= 3 && numPlayers <= 5) {
            stepSize = 2;
        } else {
            stepSize = 1;
        }


        squareSlider.setLabelTable(labelSlider(window, numPlayers * 3, 27, stepSize));

    }

    /**
     * Shows the settings screen.
     *
     * @param window the JFrame to display the settings screen on
     */
    public void showScreen(JFrame window) {
        settingsScreen.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.revalidate();
        window.repaint();
    }

    /**
     * Hides the settings screen.
     *
     * @param window the JFrame to hide the settings screen from
     */
    public void hideScreen(JFrame window) {
        settingsScreen.setVisible(false);
        window.revalidate();
        window.repaint();
    }

}
