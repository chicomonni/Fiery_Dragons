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
     * @throws IOException         if the font file cannot be read
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
        int padding = 15;
        button.setFont(font.deriveFont(fontSize));
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Border emptyBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
        button.setBorder(emptyBorder);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                int thickness = 3;
                int rem_padding = padding - thickness;
                Border lineBorder = BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, thickness, false),
                        BorderFactory.createEmptyBorder(rem_padding, rem_padding, rem_padding, rem_padding)
                );
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
            private final int font_size = 16;

            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics g2 = g.create();

                g2.setColor(Color.WHITE);
                g2.setFont(font.deriveFont((float) font_size));

                if (selected) {
                    g2.drawString("[X]", x, y + font_size);
                } else {
                    g2.drawString("[ ]", x, y + font_size);
                }

                g2.dispose();
            }

            @Override
            public int getIconWidth() {
                return font_size * 3;
            }

            @Override
            public int getIconHeight() {
                return font_size;
            }
        };
    }

}
