package Lab2.continuousDistributions;

import Lab2.integral;

public class chiDistribution extends continuousDistributions{
    private final double k;
    interfaceFunction func= new interfaceFunction() {
    @Override
        public double function(double t,double k) {
            return Math.pow(t, (k - 1)) * Math.exp(-t);
        }
        @Override
        public double function(double t) {
            integral integral=new integral();
            return (Math.pow(t, (k - 2) / 2) * Math.exp(-t / 2)) / (Math.pow(2, k / 2) * integral.calculation(0,50,k/2,func));
        }
    };

    public chiDistribution(double d1, double d2, double d3, double d4) {
        super(d1,d2,d3);
        this.k=d4;
        integral integral=new integral();
        for (double i = a; i <= b; i += h) {
            x.add((int)i);
            if ( i<=0){
                fx.add(0.0);
            }
            else{
                fx.add(1 / (Math.pow(2, k / 2) * integral.calculation(0,50,k / 2,func)) * Math.pow(i, (k - 2) / 2) * Math.exp(-i / 2));
                }
            if(i<0){
                Fx.add(0.0);
            }
            else{
                Fx.add(integral.calculation(0,i,func));

            }
        }
    }

    @Override
    public interfaceFunction func() {
        return null;
    }
}
