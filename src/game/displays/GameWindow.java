package game.displays;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * The GameWindow class is responsible for creating and managing the main game window,
 * including the customization of buttons and checkboxes used in the game interface.
 */
public class GameWindow {

    public static final int PADDING = 6;
    private static final String GAME_NAME = "Fiery Dragons";
    private static final String FONT_PATH = "/MxPlus_IBM_BIOS.ttf";
    private final JFrame window = new JFrame(GAME_NAME);

    Font font = Font.createFont(
            Font.TRUETYPE_FONT,
            Objects.requireNonNull(getClass().getResourceAsStream(FONT_PATH))
    );

    /**
     * Constructor for GameWindow.
     *
     * @throws IOException          if the font file cannot be read
     * @throws FontFormatException if the font format is incorrect
     */
    public GameWindow() throws IOException, FontFormatException {
    }

    /**
     * Returns the main game frame.
     *
     * @return the JFrame of the main game window
     */
    public JFrame getFrame() {
        return window;
    }

    /**
     * Closes the game window.
     */
    public void closeWindow() {
        window.dispose();
    }

    /**
     * Returns the font used in the game.
     *
     * @return the Font used in the game
     */
    public Font getFont() {
        return font;
    }

    /**
     * Customizes a JButton with the specified font size and appearance settings.
     *
     * @param button   the JButton to be customized
     * @param fontSize the font size for the button text
     */
    public void customiseButton(JButton button, float fontSize) {
        button.setFont(font.deriveFont(fontSize));
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

    /**
     * Customizes a JCheckBox with the specified font size and appearance settings.
     *
     * @param checkbox the JCheckBox to be customized
     * @param fontSize the font size for the checkbox text
     */
    public void customiseCheckbox(JCheckBox checkbox, float fontSize) {
        checkbox.setFont(font.deriveFont(fontSize));
        checkbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkbox.setForeground(Color.WHITE);
        checkbox.setBackground(Color.BLACK);
        checkbox.setIcon(createCheckboxIcon(false));
        checkbox.setSelectedIcon(createCheckboxIcon(true));
    }

    /**
     * Creates an Icon for a checkbox with a custom appearance.
     *
     * @param selected true if the checkbox is selected, false otherwise
     * @return the customized Icon for the checkbox
     */
    private Icon createCheckboxIcon(boolean selected) {
        return new Icon() {
            private final int size = 24;

            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw background
                g2.setColor(Color.BLACK);
                g2.fillRoundRect(x, y, size, size, 8, 8);

                // Draw border
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(x, y, size - 1, size - 1, 8, 8);

                if (selected) {
                    // Draw checkmark
                    g2.setColor(Color.WHITE);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine(x + 4, y + 12, x + 10, y + 18);
                    g2.drawLine(x + 10, y + 18, x + 20, y + 6);
                }

                g2.dispose();
            }

            @Override
            public int getIconWidth() {
                return size;
            }

            @Override
            public int getIconHeight() {
                return size;
            }
        };
    }

}
