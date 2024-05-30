package game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainMenu {
    public static final float ASCII_FONT_SIZE = 8f;
    public static final float FOOTER_FONT_SIZE = 16f;
    public static final int PADDING = 6;
    private static final String GAME_NAME = "Fiery Dragons";
    private static final String FONT_PATH = "/MxPlus_IBM_BIOS.ttf";
    private final JFrame window = new JFrame(GAME_NAME);


    public MainMenu() {
        try {
            initialise();
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialise() throws IOException, FontFormatException {
        Font font = Font.createFont(
                Font.TRUETYPE_FONT,
                Objects.requireNonNull(getClass().getResourceAsStream(FONT_PATH))
        );
        // TODO: remove colours used for testing
        // Adjust default font on text components
        UIManager.put("TextArea.font", font.deriveFont(ASCII_FONT_SIZE));
        UIManager.put("TextField.font", font.deriveFont(FOOTER_FONT_SIZE));

        // Configure the window container
        Container container = window.getContentPane();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.BLACK);

        GridBagConstraints constraints = new GridBagConstraints();
    }

    public static void newGame() {

    }

    public static boolean saveGame() {
        return true;
    }

    private void loadGame() {

    }

    public void startGameFromSave() {

    }
}
