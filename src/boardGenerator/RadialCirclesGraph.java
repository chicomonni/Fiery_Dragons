package boardGenerator;

import java.util.function.Function;

import static java.lang.Math.*;

/**
 * Represents a graph of circles arranged radially around the origin, aligned to a number of segments
 * <p>
 * See <a href="https://www.desmos.com/calculator/eyjjjfsfnr">Fiery Dragons Game Board</a>
 * for a visual representation of the board.
 */
public class RadialCirclesGraph implements Graph {
    private final int numCircle;
    private final int numSegment;
    private final int offset;
    private final int radius;

    /**
     * Constructor for RadialCirclesGraph
     *
     * @param radius     the radius of the circles
     * @param offset     the offset from the origin
     * @param numCircle  the number of circles to generate
     * @param numSegment the number of segments each circle aligns with
     */
    public RadialCirclesGraph(int radius, int offset, int numCircle, int numSegment) {
        this.radius = radius;
        this.offset = offset;
        this.numCircle = numCircle;
        this.numSegment = numSegment;
    }

    /**
     * Returns whether the given point is part of the graph.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @return whether the point is part of the graph
     */
    @Override
    public boolean relation(double x, double y) {
        CircleGraph circle = new CircleGraph(radius);

        // Rotation transformation
        Function<Double, Double> yTrans = (a) -> -x * sin(a) + y * cos(a);
        Function<Double, Double> xTrans = (a) -> x * cos(a) + y * sin(a) - offset;

        for (int i = 0; i < numCircle; i++) {
            // Calculate the optimal angle so circles are spaced evenly while still being aligned with the segments
            double angle = 2 * PI / numSegment * (double) ((2 * i + 1) * numSegment / (2 * numCircle));
            if (circle.relation(xTrans.apply(angle), yTrans.apply(angle))) {
                return true;
            }
        }
        return false;
    }
}
