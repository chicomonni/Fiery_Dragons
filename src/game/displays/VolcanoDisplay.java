package game.displays;

import game.tiles.Cave;
import game.tiles.Square;
import game.tiles.Volcano;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

/**
 * Class used to update the display of the Volcano
 */
public class VolcanoDisplay {
    private final Volcano volcano;
    private final JTextArea volcanoPane = new JTextArea();

    /**
     * Constructor
     *
     * @param volcano    the Volcano instance used by the game
     * @param gameWindow the GameWindow instance this class affects
     */
    public VolcanoDisplay(Volcano volcano, GameWindow gameWindow) {
        this.volcano = volcano;

        JLayeredPane volcanoContainer = gameWindow.getVolcanoComponent();
        initialiseComponent(volcanoContainer.getWidth(), volcanoContainer.getHeight());
        volcanoContainer.add(volcanoPane, Integer.valueOf(volcanoContainer.highestLayer() + 1));
    }

    private void initialiseComponent(int width, int height) {
        volcanoPane.setBounds(0, 0, width, height);
        volcanoPane.setEditable(false);
        volcanoPane.setOpaque(false);
        volcanoPane.setForeground(Color.WHITE);
        volcanoPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        volcanoPane.setText(getVolcanoForDisplay());
    }

    private String getVolcanoForDisplay() {
        char[][] chars = volcano.getASCIIRep();

        initialiseSquares(chars);
        initialiseCaves(chars);

        List<String> volcanoASCIIRep = new ArrayList<>(chars.length);
        for (char[] aChar : chars) {
            volcanoASCIIRep.add(String.copyValueOf(aChar));
        }

        return String.join("\n", volcanoASCIIRep);
    }

    private void initialiseCaves(char[][] chars) {
        List<Square> squares = volcano.getSquares();
        List<Cave> caves = volcano.getCaves();

        int numSquares = squares.size();
        int numCaves = caves.size();

        Function<Integer, Double> angle = (i) -> 2 * PI / numSquares * (2 * i + 1) * numSquares / (2 * numCaves);
        Function<Integer, Integer> caveX = (i) -> (int) round(44 * cos(angle.apply(i)) + 53);
        Function<Integer, Integer> caveY = (i) -> (int) round(-44 * sin(angle.apply(i)) + 53);

        for (int i = 0; i < numCaves; i++) {
            char[][] chit = caves.get(i).getChit().getDisplayDetail();

            // TODO: find way to force inner array to always be length 9
            for (int y = 0; y < chit.length; y++) {
                for (int x = 0; x < chit[y].length; x++) {
                    chars[caveY.apply(i) + y - chit.length / 2][caveX.apply(i) + x - 9 / 2] = chit[y][x];
                }
            }
        }
    }

    private void initialiseSquares(char[][] chars) {
        List<Square> squares = volcano.getSquares();
        int numSquares = squares.size();

        // 53 = 106 / 2
        Function<Integer, Integer> squareX = (i) -> (int) round(23 * cos(i * 2 * PI / numSquares) + 53);
        Function<Integer, Integer> squareY = (i) -> (int) round(-23 * sin(i * 2 * PI / numSquares) + 53);

        for (int i = 0; i < numSquares; i++) {
            char chit = squares.get(i).getChit().getDisplayChar();

            chars[squareY.apply(i)][squareX.apply(i)] = chit;
        }
    }
}
