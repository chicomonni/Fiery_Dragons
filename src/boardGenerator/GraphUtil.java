package boardGenerator;

import static java.lang.Math.round;

public class GraphUtil {
    public static int compare(double d1, double d2, double accuracy) {
        return Float.compare(round(d1 / accuracy), round(d2 / accuracy));
    }
}
