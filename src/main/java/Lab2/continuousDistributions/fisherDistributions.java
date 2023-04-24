package Lab2.continuousDistributions;

import Lab3.integral;

public class fisherDistributions extends continuousDistributions{
    private final double k1;
    private final double k2;
    continuousDistributions.interfaceFunction func= new continuousDistributions.interfaceFunction() {
        @Override
        public double function(double t,double k) {
            return Math.pow(t, (k - 1)) * Math.exp(-t);
        }
        @Override
        public double function(double t) {
            integral integral=new integral();
            return (integral.calculation(0,50,(k1 + k2) / 2,func) / ((integral.calculation(0,50,k1 / 2,func)) * integral.calculation(0,50,k2 / 2,func))) * Math.pow(k1 / k2, k1 / 2) * ((Math.pow(t, (k1 - 2) / 2) / Math.pow(1 + (k1 / k2) * t, (k1 + k2) / 2)));
        }
    };
//    continuousDistributions.interfaceFunction func2=new continuousDistributions.interfaceFunction(){
//        @Override
//        public double function(double t,double k) {
//            return Math.pow(t, (k - 1)) * Math.exp(-t);
//        }
//
//        @Override
//        public double function(double t) {
//            integral integral=new integral();
//            return ((integral.calculation(0, 50, (k +1)/ 2, func2)/
//                    integral.calculation(0, 50, k / 2, func2))*
//                    (1/(Math.sqrt(Math.PI*k)))*(1/(Math.pow(1+Math.pow(t,2)/k,(k+1)/2))));
//        }
//    };

    public fisherDistributions(double d1, double d2, double d3, double d4,double d5) {
        super(d1, d2, d3);
        this.k1 = d4;
        this.k2 = d5;
        integral integral = new integral();
        for (double i = a; i <= b; i += h) {
            x.add((int) i);
            if(i<0)  fx.add(0.0);
            else {
                fx.add((integral.calculation(0, 50, (k1 + k2) / 2, func) /
                        (integral.calculation(0, 50, k1 / 2, func)*
                                integral.calculation(0, 50, k2 / 2, func)) *
                                Math.pow(k1,k1/2)*Math.pow(k2,k2/2)*(Math.pow(i,(k1/2)-1)/Math.pow(k2+k1*i,(k1+k2)/2))));
            }
              if(i<=0) Fx.add(0.0);
              else {
                  Fx.add(integral.calculation(0,i,func));
              }
              }
        }
    }
