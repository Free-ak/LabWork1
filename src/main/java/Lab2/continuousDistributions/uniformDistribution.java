package Lab2.continuousDistributions;

public class uniformDistribution extends continuousDistributions {
    public uniformDistribution(double d1, double d2, double d3) {
        super(d1,d2,d3);
        for (double i = a; i <= b; i += h) {
            x.add((int)i);
            fx.add(1 / (b - a));
            Fx.add((i- a)/(b - a));
        }
    }
    @Override
    public interfaceFunction func() {
        return null;
    }
}
