package game.displays;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.ceil;

/**
 * The Fiery Dragon game window
 */
public class GameWindow {
    private static final String GAME_NAME = "Fiery Dragons";
    private static final String FONT_PATH = "/MxPlus_IBM_BIOS.ttf";
    private static final float FONT_SIZE = 5f;
    final JLayeredPane volcano = new JLayeredPane();
    final JPanel chitCards = new JPanel();
    final JPanel input = new JPanel();
    final JPanel header = new JPanel();
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

        UIManager.put("Label.font", font.deriveFont(FONT_SIZE));
        UIManager.put("TextPane.font", font.deriveFont(FONT_SIZE));
        UIManager.put("TextArea.font", font.deriveFont(FONT_SIZE));

        Container container = window.getContentPane();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.BLACK);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(5, 10, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;

        volcano.setPreferredSize(new Dimension((int) (107 * FONT_SIZE), (int) (107 * (FONT_SIZE + 1))));
        volcano.setBackground(Color.RED);
        volcano.setOpaque(true);
        container.add(volcano, constraints);

        constraints.insets = new Insets(5, 5, 5, 10);
        constraints.gridx = 1;
        constraints.gridy = 1;

//        chitCards.setOpaque(false);
        chitCards.setPreferredSize(new Dimension((int) ceil(53 * FONT_SIZE), 20));
        chitCards.setBackground(Color.BLUE);
        container.add(chitCards, constraints);

        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 2;

//        input.setOpaque(false);
        input.setBackground(Color.YELLOW);
        input.setPreferredSize(new Dimension(32, 32));
        container.add(input, constraints);

        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.gridy = 0;

        header.setBackground(Color.YELLOW);
        header.setPreferredSize(new Dimension(32, 32));
        container.add(header, constraints);

        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
