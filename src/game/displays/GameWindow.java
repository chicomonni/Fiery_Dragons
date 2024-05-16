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
    private final JLayeredPane volcano = new JLayeredPane();
    private final JPanel chitCards = new JPanel();
    private final JPanel input = new JPanel();
    private final JPanel header = new JPanel();
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
        // TODO: remove colours used for testing
        // Adjust default font on text components
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

        // Initialise container for the Volcano and add the component to the window
        volcano.setPreferredSize(new Dimension((int) (107 * FONT_SIZE), (int) (107 * (FONT_SIZE + 1))));
        volcano.setOpaque(true);
        container.add(volcano, constraints);

        constraints.insets = new Insets(5, 5, 5, 10);
        constraints.gridx = 1;
        constraints.gridy = 1;

        // Initialise container for the Chit Cards and add the component to the window
//        chitCards.setOpaque(false);
        chitCards.setPreferredSize(new Dimension((int) ceil(53 * FONT_SIZE), 20));
        chitCards.setBackground(Color.BLUE);
        container.add(chitCards, constraints);

        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 2;

        // Initialise container for the input and add the component to the window
//        input.setOpaque(false);
        input.setBackground(Color.YELLOW);
        input.setPreferredSize(new Dimension(32, 32));
        container.add(input, constraints);

        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.gridy = 0;

        // Initialise container for the header and add the component to the window
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
