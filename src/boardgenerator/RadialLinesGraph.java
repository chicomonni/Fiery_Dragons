package boardgenerator;

import java.util.function.Function;

import static java.lang.Math.*;

/**
 * Represents a graph of radial lines, bounded by two radii
 * <p>
 * See <a href="https://www.desmos.com/calculator/eyjjjfsfnr">Fiery Dragons Game Board</a>
 * for a visual representation of the board.
 */
public class RadialLinesGraph implements Graph {

    private final double radialBound1;
    private final double radialBound2;
    private final int numberOfSegments;

    /**
     * Constructor for RadialLinesGraph
     *
     * @param radialBound1     the inner radius of the radial lines
     * @param radialBound2     the outer radius of the radial lines
     * @param numberOfSegments the number of line segments to generate
     */
    public RadialLinesGraph(double radialBound1, double radialBound2, int numberOfSegments) {
        this.radialBound1 = radialBound1;
        this.radialBound2 = radialBound2;
        this.numberOfSegments = numberOfSegments;
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
        double accuracy = 0.1;

        // Functions representing a rotated line
        Function<Double, Double> valueCalc = (a) -> -x * sin(a) + y * cos(a);
        // Function representing the domain value of the point
        Function<Double, Double> domainCalc = (a) -> x * cos(a) + y * sin(a);

        for (double angle = PI / numberOfSegments; angle < 2 * PI; angle += 2 * PI / numberOfSegments) {
            double value = valueCalc.apply(angle);

            // A value close to 0 means the point is on the line
            boolean valueComp = GraphUtil.compare(value, 0, accuracy) == 0;

            // Check the point is within the radial bounds
            double domain = domainCalc.apply(angle);
            boolean domainComp = GraphUtil.compare(domain, radialBound1, accuracy) *
                    GraphUtil.compare(domain, radialBound2, accuracy) <= 0;

            if (valueComp & domainComp) {
                return true;
            }
        }
        return false;
    }
}
