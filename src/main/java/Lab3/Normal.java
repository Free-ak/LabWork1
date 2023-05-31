package Lab3;

import umontreal.iro.lecuyer.probdist.NormalDist;
import umontreal.iro.lecuyer.probdist.StudentDist;

import java.util.ArrayList;
import java.util.Scanner;
public class Normal  {
    private UniformDistribution uniform = new UniformDistribution();
    ArrayList<Float> y;
    private double mu;
    public double getmu(){
        return mu;
    }
    public void setmu(double mu){
        this.mu=mu;
    }
      public double getsigma(){
        return sigma;
    }
      public void setsigma(double sigma){
        this.sigma=sigma;
    }
    public double sigma;
    private int N;

    public void inputOfVariables(Scanner in){
        String ifmu = " Введите число M (M > 0) ";
        String ifsigma = " Введите число o (o > 0) ";
        System.out.print(ifmu);
        mu = uniform.checkingForPositivityDouble(in, ifmu);
        System.out.print(ifsigma);
        sigma=uniform.checkingForPositivityDouble(in, ifsigma);
        uniform.inputOfVariablesN(in);
       // expdist.inputOfVariables(in);

    }

//    public Normal( Scanner in) {
//        uniform.inputOfVariablesN(in);
//        this.sigma = sigma;
//        this.expdist = new Exp(uniform.N, sigma);
//    }
     public Normal(){}

//    private float sample() {
//        double u;
//        float e;
//        float e2;
//        do {
//            //u = uniform.sample();
//            e = expdist.sample();
//            e2 = expdist.sample();
//        //} while (!(u < Math.exp(-Math.pow(e - 1, 2))));
//        } while (!(e2 > Math.pow(e - 1, 2)/2));
//        u = uniform.sample();
//        return e * (u < 0.5 ? -1 : 1);
//    }
    public void y(){
        y=new ArrayList<>();
        for(int i=0;i<uniform.N;i++){
            y.add((float) uniform.sample());
        }
    }
    public ArrayList<Float> counting() {
        uniform.a=0;
        uniform.b=1;
        double sum=0.0;
        double sumy=0.0;
        ArrayList<Float> ans = new ArrayList<>();
        for(int i = 0; i < uniform.N; i++){
            y();
            for (var  r:y) {
                sum+=r;
            }
            ans.add((float) ((sum-(uniform.N/2))/Math.sqrt(uniform.N/12)*sigma+mu));
            sum=0.0;
        }
        return ans;
    }
    public static double invCDF(double u ){

        return NormalDist.inverseF01(u);
    }
    public static double invCDF(int k, double alpha){
        return StudentDist.inverseF(k,alpha);
    }
}

