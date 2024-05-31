package game.displays;

import game.FieryDragons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * The Fiery Dragon game window
 */
public class GameWindow {
    public static final float ASCII_FONT_SIZE = 8f;
    public static final float FOOTER_FONT_SIZE = 16f;
    public static final int PADDING = 6;
    private static final String GAME_NAME = "Fiery Dragons";
    private static final String FONT_PATH = "/MxPlus_IBM_BIOS.ttf";
    private static final String LOGO_PATH = "/assets/logo.txt";

    private final JLayeredPane volcano = new JLayeredPane();
    private final JPanel chitCards = new JPanel();
    private final JPanel footer = new JPanel();
    private final JFrame window = new JFrame(GAME_NAME);
    private final JLabel winner = new JLabel();
    private final JTextArea logo = new JTextArea();
    private final JPanel titleScreen = new JPanel();
    private final JPanel gameScreenSeparator = new JPanel();
    private final JPanel titleScreenSeparator = new JPanel();

    Font font = Font.createFont(
            Font.TRUETYPE_FONT,
            Objects.requireNonNull(getClass().getResourceAsStream(FONT_PATH))
    );


    /**
     * Constructor
     */
    public GameWindow() throws IOException, FontFormatException {
        try {
            initialise();
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to initialise window and its contents
     *
     * @throws IOException         if font file cannot be accessed
     * @throws FontFormatException if font file doesn't contain correct data
     */
    private void initialise() throws IOException, FontFormatException {
        // Adjust default font on text components
        UIManager.put("TextArea.font", font.deriveFont(ASCII_FONT_SIZE));
        UIManager.put("TextField.font", font.deriveFont(FOOTER_FONT_SIZE));

        // Configure the window container
        Container container = window.getContentPane();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.BLACK);

        initialiseTitleScreen();
        initialiseGameComponents();

        // Finalize window setup
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //TODO: move to newGame / startGame in FieryDragons
        showTitleScreen();
    }


    private void initialiseTitleScreen() throws IOException {
        titleScreen.setLayout(new BorderLayout());
        titleScreen.setBackground(Color.BLACK);

        // Initialize and configure the logo label
        logo.setFont(font.deriveFont(FOOTER_FONT_SIZE));
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

        customiseButton(newGameButton);
        customiseButton(continueGameButton);
        customiseButton(exitGameButton);

        // Add action listeners
        //TODO: new newGameAction instead of showGameScreen()
        newGameButton.addActionListener(e -> showGameScreen());
        continueGameButton.addActionListener(e -> continueGame());
        exitGameButton.addActionListener(e -> exitGame());

        buttonPanel.add(newGameButton);
        buttonPanel.add(continueGameButton);
        buttonPanel.add(exitGameButton);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        titleScreen.add(buttonPanel, BorderLayout.SOUTH);

        window.getContentPane().add(titleScreen);
    }

    private void initialiseGameComponents() {
        Container container = window.getContentPane();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.BLACK);

        GridBagConstraints constraints = new GridBagConstraints();

        // Configure and add the Volcano component to the window
        constraints.insets = new Insets(PADDING, PADDING, 0, PADDING / 2);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        // Initialise container for the Volcano and add the component to the window
        volcano.setPreferredSize(new Dimension(
                (int) (FieryDragons.VOLCANO_SIZE * ASCII_FONT_SIZE),
                (int) (FieryDragons.VOLCANO_SIZE * ASCII_FONT_SIZE)
        ));
        container.add(volcano, constraints);

        constraints.insets = new Insets(PADDING, PADDING / 2, 0, PADDING + 20);
        constraints.gridx = 1;
        constraints.gridy = 0;

        // Initialise container for the Chit Cards and add the component to the window
        chitCards.setOpaque(false);
        chitCards.setPreferredSize(new Dimension(
                (int) (FieryDragons.CARD_WIDTH * ASCII_FONT_SIZE * 4 + PADDING * 5 + 14), // extra padding required for windows
                (int) (FieryDragons.CARD_HEIGHT * GameWindow.ASCII_FONT_SIZE * 4 + GameWindow.PADDING * 5)
        ));
        chitCards.setLayout(new GridBagLayout());
        container.add(chitCards, constraints);

        // Add a separator panel
        constraints.insets = new Insets(PADDING, 4 * PADDING, PADDING, 4 * PADDING);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        gameScreenSeparator.setPreferredSize(new Dimension(0, PADDING / 2));
        container.add(gameScreenSeparator, constraints);

        constraints.insets = new Insets(0, 4 * PADDING, PADDING, 4 * PADDING);
        constraints.gridy = 2;

        // Initialise container for the input and add the component to the window
        footer.setOpaque(false);
        footer.setBackground(Color.RED);
        footer.setLayout(new GridBagLayout());
        footer.setPreferredSize(new Dimension(1, (int) (FOOTER_FONT_SIZE * 3 + PADDING)));
        container.add(footer, constraints);

        // Initialise and configure the winner label
        winner.setFont(font.deriveFont(FOOTER_FONT_SIZE)); // Use the same font
        winner.setHorizontalAlignment(JLabel.CENTER); // Center the text
        winner.setBounds(50, 50, 200, 50);

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
     * Method to display the winner of the game
     */
    public void showWinnerLabel() {
        Container container = window.getContentPane();
        // Remove all components from the container and add the winner label
        container.removeAll();
        container.add(winner);

        // Revalidate and repaint the container
        container.revalidate();
        container.repaint();
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

    public JPanel getTitleScreen() {
        return titleScreen;
    }

    public void continueGame() {
        //placeholder
    }

    public void exitGame() {
        //placeholder
    }

    public void showGameScreen() {
        titleScreen.setVisible(false);

        volcano.setVisible(true);
        chitCards.setVisible(true);
        gameScreenSeparator.setVisible(true);
        footer.setVisible(true);

        window.revalidate();
        window.repaint();
    }

    public void showTitleScreen() {
        titleScreen.setVisible(true);

        volcano.setVisible(false);
        chitCards.setVisible(false);
        gameScreenSeparator.setVisible(false);
        footer.setVisible(false);

        window.revalidate();
        window.repaint();
    }

    private void customiseButton(JButton button) {
        button.setFont(font.deriveFont(FOOTER_FONT_SIZE + 10));
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
}
