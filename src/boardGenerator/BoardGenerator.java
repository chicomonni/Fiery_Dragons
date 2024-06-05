package boardGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generates a game board for Fiery Dragons.
 */
public class BoardGenerator {
    public static final int INNER_RADIUS = 17;
    public static final int OUTER_RADIUS = 25;
    public static final int CAVE_PADDING = 13;
    public static final int CAVE_RADIUS = 9;
    public static final int VOLCANO_SIZE = 2 * (OUTER_RADIUS + CAVE_PADDING + CAVE_RADIUS) + 1;
    public static final int VOLCANO_PADDING = 3;
    private final CircleGraph innerCircle;
    private final CircleGraph outerCircle;
    private final RadialCirclesGraph caves;
    private final RadialLinesGraph sqaures;


    /**
     * Constructor for BoardGenerator.
     *
     * @param numSquare the number of squares to generate
     * @param numCave   the number of caves to generate
     */
    public BoardGenerator(int numSquare, int numCave) {
        this.innerCircle = new CircleGraph(INNER_RADIUS);
        this.outerCircle = new CircleGraph(OUTER_RADIUS);
        this.sqaures = new RadialLinesGraph(INNER_RADIUS, OUTER_RADIUS, numSquare);
        this.caves = new RadialCirclesGraph(CAVE_RADIUS, OUTER_RADIUS + CAVE_PADDING, numCave, numSquare);
    }

    /**
     * Generates a game board and saves it to a file.
     *
     * @param fileName the file to save the game board to
     * @throws IOException if the file cannot be created
     */
    public void make(Path fileName) throws IOException {
        // Combine the sub-graphs into a single graph
        Graph board = (x, y) -> {
            List<Graph> gameBoard = new ArrayList<>(Arrays.asList(innerCircle, outerCircle, sqaures, caves));

            for (Graph graph : gameBoard) {
                if (graph.relation(x, y)) {
                    return true;
                }
            }
            return false;
        };

        // Convert the graph to ASCII and save it to a file
        int sDev = 2;
        String outS = ASCIIGaussianBlur.graphToASCII(board, VOLCANO_SIZE, VOLCANO_SIZE, sDev);

        File file = new File(String.valueOf(fileName));
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileWriter out = new FileWriter(file);
        out.write(outS);
        out.close();
    }
}
