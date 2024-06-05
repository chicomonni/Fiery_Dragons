package boardgenerator;

/**
 * Represents a circle graph, centred at the origin
 * <p>
 * See <a href="https://www.desmos.com/calculator/eyjjjfsfnr">Fiery Dragons Game Board</a>
 * for a visual representation of the board.
 */
public class CircleGraph implements Graph {
    private final double radius;

    /**
     * Constructor for CircleGraph
     *
     * @param radius the radius of the circle
     */
    public CircleGraph(double radius) {
        this.radius = radius;
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
        double accuracy = 4;

        double res = Math.pow(radius, 2);
        double val = Math.pow(x, 2) + Math.pow(y, 2);

        return GraphUtil.compare(res, val, accuracy) == 0;
    }
}
