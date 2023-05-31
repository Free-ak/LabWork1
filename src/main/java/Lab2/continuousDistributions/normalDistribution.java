package Lab2.continuousDistributions;

import Lab2.integral;

public class normalDistribution extends continuousDistributions {
    private final double o;
    private final double A;
    public interfaceFunction func= new interfaceFunction() {
        @Override
        public double function(double t) {
            return Math.exp(-(((t - A) * (t - A)) / (2 * Math.pow(o,2))));
        }

        @Override
        public double function(double t, double k) {
            return 0;
        }
    };

    public normalDistribution(double d1, double d2, double d3, double d4, double d5) {
        super(d1,d2,d3);
        this.A=d4;
        this.o=d5;
        integral integral=new integral();
        for (double i = a; i <= b; i += h) {
            x.add((int)i);
            fx.add((1/(o*Math.sqrt(2*Math.PI)))*Math.exp(-((Math.pow((i-A),2)/(2*Math.pow(o,2))))));
            Fx.add((1/(o*Math.sqrt(2*Math.PI)))*integral.calculation(-500,i,func));
        }
    }
    public normalDistribution( double d4, double d5) {
        this.A=d4;
        this.o=d5;
    }
    @Override
    public interfaceFunction func() {
        return null;
    }
}
