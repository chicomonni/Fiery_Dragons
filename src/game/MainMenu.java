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

    }

    public void newGame() {
        fieryDragons.start();
    }

    private void checkName() {
        int count = 0;

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
        fieryDragons.continueGame(data);
    }


}
