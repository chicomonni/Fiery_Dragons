
class CircleGraph implements Graph {
    private final double radius;

    CircleGraph(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean relation(double x, double y, int deviation) {
        double res = Math.pow(radius, 2);
        double val = Math.pow(x, 2) + Math.pow(y, 2);

        return (res - deviation < val) & (val < res + deviation);
    }
}
