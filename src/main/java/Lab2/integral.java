package Lab2;

import Lab2.continuousDistributions.continuousDistributions;

public class integral {
    private double S;
    private double h;
    public double calculation(double a, double b, continuousDistributions.interfaceFunction func){
        h=0.01;S=0;
        double n=(b-a)/h;
        S=h*(func.function(a)+func.function(b))/2;
        for(int i=1;i<n;i++){
            S+=h*func.function(a+h*i);
        }
        return S;
    }
    public double calculation(double a, double b, double k, continuousDistributions.interfaceFunction func){
        h=0.01;
        S=0;
        double n=(b-a)/h;
        S=h*(func.function(a,k)+func.function(b,k))/2;
        for(int i=1;i<n;i++){
            S+=h*func.function(a+h*i,k);
        }
        return S;
    }

}
