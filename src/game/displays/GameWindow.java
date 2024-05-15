package game.displays;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * The Fiery Dragon game window
 */
public class GameWindow {
    private static final String GAME_NAME = "Fiery Dragons";
    private static final String FONT_PATH = "/MxPlus_IBM_BIOS.ttf";
    private static final int width = 1050;
    private static final int height = 800;
    final JLayeredPane volcano = new JLayeredPane();
    final JPanel chitCards = new JPanel();
    final JPanel input = new JPanel();
    private final JFrame window = new JFrame(GAME_NAME);

    /**
     * Constructor
     */
    public GameWindow() {
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
        Font font = Font.createFont(
                Font.TRUETYPE_FONT,
                Objects.requireNonNull(getClass().getResourceAsStream(FONT_PATH))
        );

        UIManager.put("Label.font", font.deriveFont(6.75f));
        UIManager.put("TextPane.font", font.deriveFont(6.75f));
        UIManager.put("TextArea.font", font.deriveFont(6.75f));

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(width, height);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        Container container = window.getContentPane();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.BLACK);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 400;
        constraints.weighty = 650;

        chitCards.setOpaque(false);
        container.add(chitCards, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 650;

        container.add(volcano, constraints);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 150;

        input.setOpaque(false);
        container.add(input, constraints);

        window.setVisible(true);
    }
}
