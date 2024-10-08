package boardgenerator;

import static java.lang.Math.round;

/**
 * Utility class for graph operations
 */
public class GraphUtil {

    /**
     * Compares two doubles, with a given accuracy
     *
     * @param d1       the first double to compare
     * @param d2       the second double to compare
     * @param accuracy the accuracy to compare with
     * @return After adjusting values based on accuracy, returns {@code 0} if values are equal,
     * negative integer if d1 is less than d2, positive if d1 is greater than d2
     */
    public static int compare(double d1, double d2, double accuracy) {
        return Double.compare(round(d1 / accuracy), round(d2 / accuracy));
    }
}
