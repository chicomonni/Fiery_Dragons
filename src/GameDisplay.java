import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Holds the JFrame GameDisplay implementation
 * Created by: Tye Samuels
 * Modified by: Georgia Kanellis
 *
 */
class GameDisplay {
    private final String gameName;
    private final Font squareFont = Font.createFont(Font.TRUETYPE_FONT, new File("MxPlus_IBM_BIOS.ttf"));
    private JFrame frame;
    private JLabel label;

    GameDisplay(String gameName) throws IOException, FontFormatException {
        this.gameName = gameName;
        initialise();
    }

    void setText(String text) {
        label.setText(text);
        label.setFont(new Font("MxPlus IBM BIOS", Font.PLAIN, 8));
    }

    private void initialise() {
        frame = new JFrame(gameName);
        label = new JLabel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        label.setForeground(Color.WHITE);
        label.setSize(800, 800);

        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(squareFont);
        frame.add(label);
    }
}
