package game.displays;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

public class GameWindow {

    public static final int PADDING = 6;
    private static final String GAME_NAME = "Fiery Dragons";
    private static final String FONT_PATH = "/MxPlus_IBM_BIOS.ttf";
    private final JFrame window = new JFrame(GAME_NAME);

    Font font = Font.createFont(
            Font.TRUETYPE_FONT,
            Objects.requireNonNull(getClass().getResourceAsStream(FONT_PATH))
    );


    public GameWindow() throws IOException, FontFormatException {
        initialise();
    }

    private void initialise() {

    }

    public JFrame getFrame() {
        return window;
    }

    public void closeWindow() {
        window.dispose();
    }

    public Font getFont() {
        return font;
    }

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

    public void customiseCheckbox(JCheckBox checkbox, float fontSize) {
        checkbox.setFont(font.deriveFont(fontSize));
        checkbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkbox.setForeground(Color.WHITE);
        checkbox.setBackground(Color.BLACK);
        checkbox.setIcon(createCheckboxIcon(false));
        checkbox.setSelectedIcon(createCheckboxIcon(true));
    }

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
