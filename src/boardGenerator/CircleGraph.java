package boardGenerator;

public class CircleGraph implements Graph {
    private final double radius;

    public CircleGraph(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean relation(double x, double y) {
        double accuracy = 4;

        double res = Math.pow(radius, 2);
        double val = Math.pow(x, 2) + Math.pow(y, 2);

        return GraphUtil.compare(res, val, accuracy) == 0;
    }
}
