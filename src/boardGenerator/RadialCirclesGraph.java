package boardGenerator;

import java.util.function.Function;

import static java.lang.Math.*;

public class RadialCirclesGraph implements Graph {
    private final int numCircle;
    private final int numSegment;
    private final int offset;
    private int radius = 9;

    public RadialCirclesGraph(int numCircle, int numSegment, int offset) {
        this.numCircle = numCircle;
        this.numSegment = numSegment;
        this.offset = offset;
    }

    public RadialCirclesGraph(int radius, int offset, int numCircle, int numSegment) {
        this.radius = radius;
        this.offset = offset;
        this.numCircle = numCircle;
        this.numSegment = numSegment;
    }

    @Override
    public boolean relation(double x, double y) {
        CircleGraph circle = new CircleGraph(radius);

        Function<Double, Double> yTrans = (a) -> -x * sin(a) + y * cos(a);
        Function<Double, Double> xTrans = (a) -> x * cos(a) + y * sin(a) - offset;

        for (int i = 0; i < numCircle; i++) {
            double angle = 2 * PI / numSegment * (double) ((2 * i + 1) * numSegment / (2 * numCircle));
            if (circle.relation(xTrans.apply(angle), yTrans.apply(angle))) {
                return true;
            }
        }
        return false;
    }

    public int getOffset() {
        return offset;
    }

    public int getRadius() {
        return radius;
    }
}
