package boardgenerator;

/**
 * Represents a graph.
 */
public interface Graph {
    /**
     * Returns whether the given point is part of the graph.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @return whether the point is part of the graph
     */
    boolean relation(double x, double y);
}
