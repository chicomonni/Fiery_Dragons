package game.displays;

import game.Board;
import game.FieryDragons;
import game.actions.PickSettingsAction;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class SettingsDisplay {
    private static final float TITLE_FONT_SIZE = 50f;
    private static final float SETTINGS_FONT_SIZE = 28f;
    private static final float SLIDER_FONT_SIZE = 16f;

    private final JPanel settingsScreen = new JPanel();
    private final JLabel titleLabel = new JLabel("SETTINGS");
    private final JPanel separator = new JPanel();
    private final JButton startButton = new JButton("START");
    private final JButton backButton = new JButton("BACK");
    private final JSlider playerSlider = new JSlider(2, 8);
    private final JSlider squareSlider = new JSlider();
    private final JCheckBox dragonPirateCheckbox = new JCheckBox("  Dragon Pirate", true);
    private final JCheckBox ratRascalCheckbox = new JCheckBox("  Rat Rascal", false);

    public SettingsDisplay(DisplayManager display, GameWindow window, Board board) {
        initialise(display, window);

        //default values
        playerSlider.setValue(4);
        squareSlider.setValue(24);

        updateSquareSlider(window, playerSlider.getValue());

        playerSlider.addChangeListener(e -> updateSquareSlider(window, playerSlider.getValue()));
        backButton.addActionListener(e -> display.displayTitleScreen(window.getFrame()));
        startButton.addActionListener(e ->
            new PickSettingsAction(
                    FieryDragons.getPlayers()[0],
                    board,
                    display,
                    window,
                    playerSlider.getValue(),
                    squareSlider.getValue(),
                    dragonPirateCheckbox.isSelected(),
                    ratRascalCheckbox.isSelected()
            ).execute(board, display)
        );
//                new PlayGameAction(
//                    FieryDragons.getPlayers()[0],
//                    board,
//                    display,
//                    window)
//                .execute(board, display));


    }

    public void initialise(DisplayManager display, GameWindow window) {
        JFrame frame = window.getFrame();
        settingsScreen.setPreferredSize(new Dimension(1200, 800));
        settingsScreen.setLayout(new GridBagLayout());
        settingsScreen.setBackground(Color.BLACK);

        // Add title label
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(window.getFont().deriveFont(TITLE_FONT_SIZE));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        constraints.anchor = GridBagConstraints.NORTH;
        settingsScreen.add(titleLabel, constraints);

        separator.setPreferredSize(new Dimension(0, 5));
        separator.setBackground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(50, 0, 50, 0);
        constraints.anchor = GridBagConstraints.NORTH;
        settingsScreen.add(separator, constraints);


        constraints.insets = new Insets(15, 0, 15, 0);
        // Configure sliders
        playerSlider.setMajorTickSpacing(1);
        playerSlider.setPaintTicks(true);
        playerSlider.setPaintLabels(true);
        playerSlider.setSnapToTicks(true);
        playerSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playerSlider.setLabelTable(labelSlider(window, 2, 8, 1));

        squareSlider.setMajorTickSpacing(1);
        squareSlider.setPaintTicks(true);
        squareSlider.setPaintLabels(true);
        squareSlider.setSnapToTicks(true);
        squareSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        squareSlider.setLabelTable(labelSlider(window, playerSlider.getValue() * 3, 27, 1));

        // Add components to the settings screen
        constraints.fill = GridBagConstraints.BOTH;

        // Add player slider with label
        constraints.gridx = 0;
        constraints.gridy++;

        constraints.gridy++;
        settingsScreen.add(createLabel("NUMBER OF PLAYERS: ", window.getFont()), constraints);
        constraints.gridy++;
        settingsScreen.add(playerSlider, constraints);

        // Add square slider with label
        constraints.gridy++;
        settingsScreen.add(createLabel("SIZE OF THE VOLCANO: ", window.getFont()), constraints);
        constraints.gridy++;
        settingsScreen.add(squareSlider, constraints);

        // Add checkboxes
        constraints.gridy++;
        settingsScreen.add(createLabel("INCLUDE: ", window.getFont()), constraints);

        constraints.insets = new Insets(15, 20, 15, 0);
        constraints.gridy++;
        dragonPirateCheckbox.setForeground(Color.WHITE);
        settingsScreen.add(dragonPirateCheckbox, constraints);
        window.customiseCheckbox(dragonPirateCheckbox, window.getFont(), SETTINGS_FONT_SIZE);
        dragonPirateCheckbox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        dragonPirateCheckbox.setFont(frame.getFont().deriveFont(SLIDER_FONT_SIZE));

        constraints.gridy++;
        ratRascalCheckbox.setForeground(Color.WHITE);
        settingsScreen.add(ratRascalCheckbox, constraints);
        window.customiseCheckbox(ratRascalCheckbox, window.getFont(), SETTINGS_FONT_SIZE);
        ratRascalCheckbox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        ratRascalCheckbox.setFont(frame.getFont().deriveFont(SLIDER_FONT_SIZE));


        // Add buttons
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;
        window.customiseButton(backButton, window.getFont(), SETTINGS_FONT_SIZE);
        settingsScreen.add(backButton, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        window.customiseButton(startButton, window.getFont(), SETTINGS_FONT_SIZE);
        settingsScreen.add(startButton, constraints);

        frame.add(settingsScreen);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(font.deriveFont(SETTINGS_FONT_SIZE));
        return label;
    }

    private Hashtable<Integer, JLabel> labelSlider(GameWindow window, int min, int max, int step) {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        for (int i = min; i <= max; i += step) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setForeground(Color.WHITE);
            label.setFont(window.getFont().deriveFont(SLIDER_FONT_SIZE));
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

            labelTable.put(i, label);
        }
        return labelTable;
    }

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

    public void showScreen(JFrame window) {
        settingsScreen.setVisible(true);
        window.revalidate();
        window.repaint();
    }

    public void hideScreen(JFrame window) {
        settingsScreen.setVisible(false);
        window.revalidate();
        window.repaint();
    }


}
