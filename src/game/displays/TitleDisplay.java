package game.displays;

import game.Board;
import game.FieryDragons;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class TitleDisplay {
    private static final String LOGO_PATH = "/assets/logo.txt";
    private static final float LOGO_FONT_SIZE = 14f;
    public static final float BUTTON_FONT_SIZE = 26f;
    private final JPanel titleScreen = new JPanel();
    private final JPanel separator = new JPanel();
    private final JTextArea logo = new JTextArea();

    private final JButton newGameButton = new JButton("NEW GAME");
    private final JButton continueGameButton = new JButton("CONTINUE GAME");
    private final JButton exitGameButton = new JButton("EXIT GAME");

    /**
     * Constructor
     *
     * @param window the GameWindow instance this class affects
     */
    public TitleDisplay(FieryDragons fieryDragons, DisplayManager display, GameWindow window, Board board) throws IOException {
        initialise(fieryDragons, display, window, board);
    }

    private void initialise(FieryDragons fieryDragons, DisplayManager display, GameWindow window, Board board) throws IOException {

        JFrame frame = window.getFrame();
        titleScreen.setLayout(new BorderLayout());
        titleScreen.setBackground(Color.BLACK);

        // Initialize and configure the logo label
        logo.setFont(window.getFont().deriveFont(LOGO_FONT_SIZE));
        logo.setEditable(false);
        logo.setBackground(Color.BLACK);
        logo.setForeground(Color.WHITE);
        logo.setText(readTitleDisplay());

        JScrollPane logoPane = new JScrollPane(logo);
        logoPane.setBorder(BorderFactory.createEmptyBorder());

        separator.setPreferredSize(new Dimension(0, 5));
        separator.setBackground(Color.WHITE);

        // Create titlePanel and add components to it
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(logoPane, BorderLayout.CENTER);
        titlePanel.add(separator, BorderLayout.SOUTH);
        titleScreen.add(titlePanel, BorderLayout.CENTER);

        // Initialise buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setOpaque(false);

        window.customiseButton(newGameButton, BUTTON_FONT_SIZE);
        window.customiseButton(continueGameButton, BUTTON_FONT_SIZE);
        window.customiseButton(exitGameButton, BUTTON_FONT_SIZE);

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

    public void showScreen(JFrame window) {
        titleScreen.setVisible(true);

        window.revalidate();
        window.repaint();
    }

    public void hideScreen(JFrame window) {
        titleScreen.setVisible(false);

        window.revalidate();
        window.repaint();
    }

}
