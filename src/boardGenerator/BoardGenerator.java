package boardGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardGenerator {
    private static final int INNER_RADIUS = 17;
    private static final int OUTER_RADIUS = 25;
    private static final int CAVE_PADDING = 13;
    private final CircleGraph innerCircle;
    private final CircleGraph outerCircle;
    private final RadialCirclesGraph caves;
    private final RadialLinesGraph sqaures;
    private final int dimensions;


    public BoardGenerator(int numSqaure, int numCave) {
        this.innerCircle = new CircleGraph(INNER_RADIUS);
        this.outerCircle = new CircleGraph(OUTER_RADIUS);
        this.sqaures = new RadialLinesGraph(INNER_RADIUS, OUTER_RADIUS, numSqaure);
        this.caves = new RadialCirclesGraph(numCave, numSqaure, OUTER_RADIUS + CAVE_PADDING);

        this.dimensions = 2 * (caves.getOffset() + caves.getRadius()) + 1;
    }

    public void make(String fileName) throws FileNotFoundException {
        Graph board = (x, y) -> {
            List<Graph> gameBoard = new ArrayList<>(Arrays.asList(innerCircle, outerCircle, sqaures, caves));

            for (Graph graph : gameBoard) {
                if (graph.relation(x, y)) {
                    return true;
                }
            }
            return false;
        };

        String outS = ASCIIGaussianBlur.graphToASCII(board, dimensions, dimensions, 2);
        PrintWriter out = new PrintWriter(fileName);
        out.print(outS);
        out.close();
    }
}
