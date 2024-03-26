package spike;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        GameWindow gameWindow = new GameWindow("Fiery Dragon");

        Graph circle1 = new CircleGraph(30);
        Graph circle2 = new CircleGraph(20);

        Graph line = new RadialLinesGraph(30, 20, 24);

        Graph doubleCircle = (x, y, deviation) -> {
            List<Graph> gameBoard = new ArrayList<>(Arrays.asList(circle1, circle2, line));

            for (Graph graph : gameBoard) {
                if (graph.relation(x, y, deviation)) {
                    return true;
                }
            }
            return false;
        };

        gameWindow.setText(AsciiUtil.graphToAscii(100, 100, doubleCircle));
    }
}