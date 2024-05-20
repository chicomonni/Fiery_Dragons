package game.displays;

import game.FieryDragons;
import game.tiles.Cave;
import game.tiles.Square;
import game.tiles.Volcano;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

/**
 * Class used to update the display of the Volcano
 */
public class VolcanoDisplay {
    private final Volcano volcano;
    private final JTextArea volcanoPane = new JTextArea();
    private final JLayeredPane volcanoContainer;

    /**
     * Constructor
     *
     * @param volcano    the Volcano instance used by the game
     * @param gameWindow the GameWindow instance this class affects
     */
    public VolcanoDisplay(Volcano volcano, GameWindow gameWindow) {
        this.volcano = volcano;
        this.volcanoContainer = gameWindow.getVolcanoComponent();

        initialiseComponent(volcanoPane, volcanoContainer.getWidth(), volcanoContainer.getHeight());
        volcanoPane.setText(getVolcanoForDisplay());
        volcanoContainer.add(volcanoPane, Integer.valueOf(volcanoContainer.highestLayer() + 1));
    }

    /**
     * Method to convert ASCII art in the form of char[][] into a String, so it can be printed using Swing
     *
     * @param chars ASCII art represented as char[][]
     * @return String representation of ASCII art
     */
    private static String ASCIItoString(char[][] chars) {
        List<String> ASCIIRep = new ArrayList<>(chars.length);
        for (char[] aChar : chars) {
            ASCIIRep.add(String.copyValueOf(aChar));
        }

        return String.join("\n", ASCIIRep);
    }

    /**
     * Initialize the display component with specified width and height.
     *
     * @param textArea the JTextArea to be initialised
     * @param width    the width of the component
     * @param height   the height of the component
     */
    private void initialiseComponent(JTextArea textArea, int width, int height) {
        textArea.setBounds(0, 0, width, height);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    /**
     * Generates the ASCII representation of the volcano for display.
     *
     * @return a string representing the volcano in ASCII
     */
    private String getVolcanoForDisplay() {
        char[][] chars = volcano.getASCIIRep();

        initialiseSquares(chars);
        initialiseCaves();

        return ASCIItoString(chars);
    }

    /**
     * Places the cave characters on the ASCII representation of the volcano.
     */
    private void initialiseCaves() {
        List<Square> squares = volcano.getSquares();
        List<Cave> caves = volcano.getCaves();

        int numSquares = squares.size();
        int numCaves = caves.size();

        int offset = FieryDragons.VOLCANO_SIZE / 2;
        int radius = FieryDragons.OUTER_RADIUS + FieryDragons.CAVE_OFFSET;

        Function<Integer, Double> angle = (i) -> 2 * PI / numSquares * (double) ((2 * i + 1) * numSquares / (2 * numCaves));
        Function<Integer, Integer> caveX = (i) -> (int) round(radius * cos(angle.apply(i)) + offset);
        Function<Integer, Integer> caveY = (i) -> (int) round(-radius * sin(angle.apply(i)) + offset);

        for (int i = 0; i < numCaves; i++) {
            Cave cave = caves.get(i);
            createColouredCave(cave, caveX.apply(i), caveY.apply(i));
        }
    }

    /**
     * Method to create coloured caves with detailed Chit in the center
     *
     * @param cave a Cave instance
     * @param x    the center x position to print the detailed Chit
     * @param y    the center y position to print the detailed Chit
     */
    private void createColouredCave(Cave cave, int x, int y) {
        char[][] chars = new char[FieryDragons.VOLCANO_SIZE][FieryDragons.VOLCANO_SIZE];

        for (char[] row : chars) {
            Arrays.fill(row, ' ');
        }

        char[][] chit = cave.getChit().getDisplayDetail();

        for (int i = 0; i < chit.length; i++) {
            if (chit[i].length - 1 >= 0)
                System.arraycopy(chit[i], 0, chars[y + i - chit.length / 2], x - (chit[i].length - 1) / 2, chit[i].length - 1);
        }

        JTextArea textArea = new JTextArea();
        initialiseComponent(textArea, volcanoPane.getWidth(), volcanoPane.getHeight());
        textArea.setText(ASCIItoString(chars));
        textArea.setForeground(cave.getColour());

        volcanoContainer.add(textArea, Integer.valueOf(volcanoContainer.highestLayer() + 1));
    }

    /**
     * Places the square characters on the ASCII representation of the volcano.
     *
     * @param chars the ASCII representation of the volcano
     */
    private void initialiseSquares(char[][] chars) {
        List<Square> squares = volcano.getSquares();
        int numSquares = squares.size();

        int offset = FieryDragons.VOLCANO_SIZE / 2;
        int radius = FieryDragons.INNER_RADIUS + FieryDragons.VOLCANO_PADDING;

        Function<Integer, Integer> squareX = (i) -> (int) round(radius * cos(i * 2 * PI / numSquares) + offset);
        Function<Integer, Integer> squareY = (i) -> (int) round(-radius * sin(i * 2 * PI / numSquares) + offset);

        for (int i = 0; i < numSquares; i++) {
            char chit = squares.get(i).getChit().getDisplayChar();

            chars[squareY.apply(i)][squareX.apply(i)] = chit;
        }
    }
}
