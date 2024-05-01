
import java.util.function.Function;

import static java.lang.Math.*;

class RadialLinesGraph implements Graph {
    private final double radialBound1;
    private final double radialBound2;
    private final int numberOfSegments;

    RadialLinesGraph(double radialBound1, double radialBound2, int numberOfSegments) {
        this.radialBound1 = radialBound1;
        this.radialBound2 = radialBound2;
        this.numberOfSegments = numberOfSegments;
    }

    @Override
    public boolean relation(double x, double y, int deviation) {
//        18 seems to produce the best values
        double adjustment = 30;
        double adjustedDeviation = deviation / adjustment;

        Function<Double, Double> valueCalc = (a) -> -x * sin(a) + y * cos(a);
        Function<Double, Double> domainCalc = (a) -> x * cos(a) + y * sin(a);

        for (double angle = 0; angle < 2 * PI; angle += 2 * PI / numberOfSegments) {
            double value = valueCalc.apply(angle);
            boolean valueComp = (-adjustedDeviation < value) & (value < adjustedDeviation);

            double domain = domainCalc.apply(angle);
            boolean domainComp = Double.compare(domain, radialBound1) *
                    Double.compare(domain, radialBound2) <= 0;

            if (valueComp & domainComp) {
                return true;
            }
        }

        return false;
    }
}
