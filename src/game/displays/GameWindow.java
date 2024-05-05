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
    private final JFrame window = new JFrame(GAME_NAME);
    private final JLayeredPane volcano = new JLayeredPane();
    private final JPanel chitCards = new JPanel();
    private final JPanel input = new JPanel();
    private final int width;
    private final int height;

    /**
     * Constructor
     *
     * @param width  desired width of game window
     * @param height desired height of game window
     */
    public GameWindow(int width, int height) {
        this.width = width;
        this.height = height;

        try {
            initialise();
        } catch (Exception e) {
            e.printStackTrace();
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

        UIManager.put("Label.font", font.deriveFont(8f));
        UIManager.put("TextPane.font", font.deriveFont(8f));

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(new Dimension(width, height));
        window.setMinimumSize(new Dimension(1050, 800));
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

//        TODO: remove colours
        chitCards.setBackground(Color.GREEN);
        JLabel label = new JLabel("Hello World");
        chitCards.add(label);
        container.add(chitCards, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 650;

        volcano.setBackground(Color.BLUE);
        volcano.setOpaque(true);
        container.add(volcano, constraints);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 150;

        input.setBackground(Color.YELLOW);
        container.add(input, constraints);

        window.setVisible(true);
    }
}
