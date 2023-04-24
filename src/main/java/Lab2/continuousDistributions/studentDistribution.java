package Lab2.continuousDistributions;

import Lab3.integral;

public class studentDistribution extends continuousDistributions{
    private final double k;
    continuousDistributions.interfaceFunction func= new continuousDistributions.interfaceFunction() {
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
    continuousDistributions.interfaceFunction func2=new continuousDistributions.interfaceFunction(){
        @Override
        public double function(double t,double k) {
            return Math.pow(t, (k - 1)) * Math.exp(-t);
        }

        @Override
        public double function(double t) {
            integral integral=new integral();
            return ((integral.calculation(0, 50, (k +1)/ 2, func2)/
                    integral.calculation(0, 50, k / 2, func2))*
                            (1/(Math.sqrt(Math.PI*k)))*(1/(Math.pow(1+Math.pow(t,2)/k,(k+1)/2))));
        }
    };

    public studentDistribution(double d1, double d2, double d3, double d4) {
        super(d1, d2, d3);
        this.k = d4;
        integral integral = new integral();
        for (double i = a; i <= b; i += h) {
            x.add((int) i);
            fx.add((integral.calculation(0, 50, (k +1)/ 2, func)/
                    (integral.calculation(0, 50, k / 2, func)*Math.sqrt(Math.PI*k)))*
                    (Math.pow(1+Math.pow(i,2)/k,-(k+1)/2)));
            Fx.add(integral.calculation(-50,i,func2));
            }
    }
}

