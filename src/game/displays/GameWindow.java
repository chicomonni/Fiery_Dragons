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

    public GameWindow(int width, int height) {
        try {
            initialise(width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialise(int width, int height) throws IOException, FontFormatException {
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
