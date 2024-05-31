package game.displays;

import game.Board;
import game.FieryDragons;
import game.actions.PlayGameAction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class TitleDisplay {
    private static final String LOGO_PATH = "/assets/logo.txt";
    private static final float LOGO_FONT_SIZE = 14f;
    public static final float BUTTON_FONT_SIZE = 26f;
    private final JPanel titleScreen = new JPanel();
    private final JPanel titleScreenSeparator = new JPanel();
    private final JTextArea logo = new JTextArea();

    /**
     * Constructor
     *
     * @param gameWindow the GameWindow instance this class affects
     */
    public TitleDisplay(DisplayManager display, GameWindow gameWindow, Board board) throws IOException {
        initialise(display, gameWindow, board);
    }

    private void initialise(DisplayManager display, GameWindow gameWindow, Board board) throws IOException {

        JFrame window = gameWindow.getWindow();
        titleScreen.setLayout(new BorderLayout());
        titleScreen.setBackground(Color.BLACK);

        // Initialize and configure the logo label
        logo.setFont(gameWindow.getFont().deriveFont(LOGO_FONT_SIZE));
        logo.setEditable(false);
        logo.setBackground(Color.BLACK);
        logo.setForeground(Color.WHITE);
        logo.setText(readTitleDisplay());

        JScrollPane logoPane = new JScrollPane(logo);
        logoPane.setBorder(BorderFactory.createEmptyBorder());

        titleScreenSeparator.setPreferredSize(new Dimension(0, 6));
        titleScreenSeparator.setBackground(Color.WHITE);

        // Create titlePanel and add components to it
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.BLACK);
        titlePanel.add(logoPane, BorderLayout.CENTER);
        titlePanel.add(titleScreenSeparator, BorderLayout.SOUTH);
        titleScreen.add(titlePanel, BorderLayout.CENTER);

        // Initialise buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setOpaque(false);

        JButton newGameButton = new JButton("NEW GAME");
        JButton continueGameButton = new JButton("CONTINUE GAME");
        JButton exitGameButton = new JButton("EXIT GAME");

        customiseButton(gameWindow.getFont(), newGameButton);
        customiseButton(gameWindow.getFont(), continueGameButton);
        customiseButton(gameWindow.getFont(), exitGameButton);

        // Add action listeners
        //TODO: change to pickSettingsAction later
        newGameButton.addActionListener(e -> new PlayGameAction(FieryDragons.getPlayers()[0], board, display, gameWindow).execute(board, display));
        //TODO: change to consider current player and current board setup
        continueGameButton.addActionListener(e -> new PlayGameAction(FieryDragons.getPlayers()[0], board, display, gameWindow).execute(board, display));
        exitGameButton.addActionListener(e -> gameWindow.closeWindow()); //change to exitGame() method??

        buttonPanel.add(newGameButton);
        buttonPanel.add(continueGameButton);
        buttonPanel.add(exitGameButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        titleScreen.add(buttonPanel, BorderLayout.SOUTH);

        window.getContentPane().add(titleScreen);
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

    private void customiseButton(Font font, JButton button) {
        button.setFont(font.deriveFont(BUTTON_FONT_SIZE));
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Border emptyBorder = BorderFactory.createEmptyBorder(15, 0, 15, 0);
        button.setBorder(emptyBorder);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Border lineBorder = BorderFactory.createLineBorder(Color.WHITE, 4, true);
                button.setBorder(lineBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBorder(emptyBorder);
            }
        });
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
