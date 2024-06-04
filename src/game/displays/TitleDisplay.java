package game.displays;

import game.FieryDragons;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * The TitleDisplay class is responsible for displaying the title screen of the game.
 * It includes options for starting a new game, continuing a game, and exiting the game.
 */
public class TitleDisplay {
    private static final String LOGO_PATH = "/assets/logo.txt";
    private final JPanel titleScreen = new JPanel();
    private final JPanel separator = new JPanel();
    private final JTextArea logo = new JTextArea();

    private final JButton newGameButton = new JButton("NEW GAME");
    private final JButton continueGameButton = new JButton("CONTINUE GAME");
    private final JButton exitGameButton = new JButton("EXIT GAME");

    /**
     * Constructor
     *
     * @param fieryDragons the FieryDragons instance to start the game
     * @param display      the DisplayManager handling game displays
     * @param window       the GameWindow instance this class affects
     * @throws IOException if the logo file cannot be read
     */
    public TitleDisplay(FieryDragons fieryDragons, DisplayManager display, GameWindow window) throws IOException {
        initialise(fieryDragons, display, window);
    }

    /**
     * Initializes the title screen with logo and buttons.
     *
     * @param fieryDragons the FieryDragons instance to start the game
     * @param display      the DisplayManager handling game displays
     * @param window       the GameWindow instance this class affects
     * @throws IOException if the logo file cannot be read
     */
    private void initialise(FieryDragons fieryDragons, DisplayManager display, GameWindow window) throws IOException {
        JFrame frame = window.getFrame();
        titleScreen.setLayout(new BorderLayout());
        titleScreen.setBackground(Color.BLACK);

        // Initialize and configure the logo label
        initialiseLogo(window);

        separator.setPreferredSize(new Dimension(0, GameWindow.PADDING / 2));
        separator.setBackground(Color.WHITE);

        // Create titlePanel and add components to it
        initaliseTitlePanel();

        // Initialise buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setOpaque(false);

        window.customiseButton(newGameButton, GameWindow.BODY_FONT_SIZE);
        window.customiseButton(continueGameButton, GameWindow.BODY_FONT_SIZE);
        window.customiseButton(exitGameButton, GameWindow.BODY_FONT_SIZE);

        // Add action listeners
        newGameButton.addActionListener(e -> display.displaySettingsScreen(frame));

        //TODO: change to consider current player and current board setup
        continueGameButton.addActionListener(e -> fieryDragons.playGame(display, window));
        exitGameButton.addActionListener(e -> window.closeWindow()); //change to exitGame() method??

        buttonPanel.add(newGameButton);
        buttonPanel.add(continueGameButton);
        buttonPanel.add(exitGameButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        titleScreen.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(titleScreen);
    }

    private void initialiseLogo(GameWindow window) throws IOException {
         logo.setFont(window.getFont().deriveFont(GameWindow.ASCII_FONT_SIZE));
         logo.setEditable(false);
         logo.setBackground(Color.BLACK);
         logo.setForeground(Color.WHITE);
         logo.setText(readTitleDisplay());
    }

    private void initaliseTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 0, 100));
        titlePanel.add(logo, BorderLayout.CENTER);
        titlePanel.add(separator, BorderLayout.SOUTH);
        titleScreen.add(titlePanel, BorderLayout.CENTER);
    }
    /**
     * Reads the title display from a file.
     *
     * @return the title display as a String
     * @throws IOException if the logo file cannot be read
     */
    public String readTitleDisplay() throws IOException {
        StringBuilder logoText = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream(LOGO_PATH);
             Scanner scanner = new Scanner(Objects.requireNonNull(is))) {
            while (scanner.hasNextLine()) {
                logoText.append(scanner.nextLine()).append("\n");
            }
        }
        return logoText.toString();
    }

    /**
     * Shows the title screen.
     *
     * @param window the JFrame to display the title screen on
     */
    public void showScreen(JFrame window) {
        titleScreen.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.revalidate();
        window.repaint();
    }

    /**
     * Hides the title screen.
     *
     * @param window the JFrame to hide the title screen from
     */
    public void hideScreen(JFrame window) {
        titleScreen.setVisible(false);

        window.revalidate();
        window.repaint();
    }

}
