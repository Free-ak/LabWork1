package Lab2.continuousDistributions;

public class exponentialDistribution extends continuousDistributions {
    private final double A;
    public exponentialDistribution(double d1, double d2,double d3 , double d4) {
       super(d1,d2,d3);
        this.A=d4;
        for (double i = a; i <= b; i += h) {
            x.add((int)i);
            if (i >= 0) {
                fx.add((1 / A) * Math.exp(-(i / A)));
            } else {
                fx.add((double) 0);
            }
            if (i > 0) {
                Fx.add(1 - Math.exp(-(i / A)));
            } else {
                Fx.add((double) 0);
            }
        }
    }
    @Override
    public interfaceFunction func() {
        return null;
    }
}
