package game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class MainMenu {
    public static final float ASCII_FONT_SIZE = 8f;
    public static final float FOOTER_FONT_SIZE = 16f;
    public static final int PADDING = 6;
    private static final String GAME_NAME = "Fiery Dragons";
    private static final String FONT_PATH = "/MxPlus_IBM_BIOS.ttf";
    private final JButton newGame = new JButton("New Game");
    private final JButton loadGame = new JButton("Load Game");
    private final JFrame window = new JFrame(GAME_NAME);
    private static FieryDragons fieryDragons = new FieryDragons();


    public MainMenu() {
//        try {
//            initialise();
//        } catch (IOException | FontFormatException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void initialise() throws IOException, FontFormatException {
        Font font = Font.createFont(
                Font.TRUETYPE_FONT,
                Objects.requireNonNull(getClass().getResourceAsStream(FONT_PATH))
        );
        // Adjust default font on text components
        UIManager.put("TextArea.font", font.deriveFont(ASCII_FONT_SIZE));
        UIManager.put("TextField.font", font.deriveFont(FOOTER_FONT_SIZE));

        // Configure the window container
        Container container = window.getContentPane();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.BLACK);

        GridBagConstraints constraints = new GridBagConstraints();

        container.add(newGame);
        container.add(loadGame);


        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void newGame() {
        fieryDragons.start();
    }

    public static boolean saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("gameData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(fieryDragons);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in gameData.ser");
            return true;
        } catch (IOException i) {
            i.printStackTrace();
        }
        return false;
    }

    private static FieryDragons loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("gameData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fieryDragons = (FieryDragons) in.readObject();
            in.close();
            fileIn.close();
//            System.out.println(fieryDragons);
            return fieryDragons;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Game Data not found");
            c.printStackTrace();
        }
        return null;

    }

    public static void continueGame() {
        FieryDragons data = loadGame();
//        System.out.println(data);
        fieryDragons.continueGame(data);
    }


}
