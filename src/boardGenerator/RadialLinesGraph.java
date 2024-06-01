package boardGenerator;

import java.util.function.Function;

import static java.lang.Math.*;

public class RadialLinesGraph implements Graph {

    private final double radialBound1;
    private final double radialBound2;
    private final int numberOfSegments;

    public RadialLinesGraph(double radialBound1, double radialBound2, int numberOfSegments) {
        this.radialBound1 = radialBound1;
        this.radialBound2 = radialBound2;
        this.numberOfSegments = numberOfSegments;
    }

    @Override
    public boolean relation(double x, double y) {
        double accuracy = 0.1;

        Function<Double, Double> valueCalc = (a) -> -x * sin(a) + y * cos(a);
        Function<Double, Double> domainCalc = (a) -> x * cos(a) + y * sin(a);

        for (double angle = PI / numberOfSegments; angle < 2 * PI; angle += 2 * PI / numberOfSegments) {
            double value = valueCalc.apply(angle);

            boolean valueComp = GraphUtil.compare(value, 0, accuracy) == 0;

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
