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
    private final JPanel footer = new JPanel();
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

        constraints.insets = new Insets(5, 10, 5, 20);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;

        // Initialise container for the Volcano and add the component to the window
        volcano.setPreferredSize(new Dimension((int) (107 * FONT_SIZE), (int) (107 * (FONT_SIZE + 1))));
//        volcano.setBorder(new LineBorder(Color.WHITE, 5));
        container.add(volcano, constraints);

        constraints.insets = new Insets(5, 20, 5, 55);
        constraints.gridx = 1;
        constraints.gridy = 1;

        // Initialise container for the Chit Cards and add the component to the window
        chitCards.setOpaque(false);
        chitCards.setPreferredSize(new Dimension((int) ceil(11 * FONT_SIZE * 4 + 30 * 3), 20));
        chitCards.setLayout(new GridBagLayout());
        container.add(chitCards, constraints);

        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 2;

        // Initialise container for the input and add the component to the window
        // input.setOpaque(false);
        footer.setBackground(Color.YELLOW);
        footer.setPreferredSize(new Dimension(1, (int) (FOOTER_FONT_SIZE * 2 + PADDING)));
        container.add(footer, constraints);

        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
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
}
