package Lab3;

import umontreal.iro.lecuyer.probdist.ChiSquareDist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp  {
    private final UniformDistribution uniform = new UniformDistribution();
    private double lambda;
    public void inputOfVariables(Scanner in){
        String ifh = " Введите число h (h > 0) ";
        System.out.print(ifh);
        lambda = uniform.checkingForPositivityDouble(in, ifh);
        uniform.inputOfVariablesN(in);

    }
    public Exp(int N, double lambda) {
        uniform.N=N;
        this.lambda = lambda;
    }
    public int getN(){
        return uniform.N;
    }
    public Exp(){}
    public float sample() {
        return (float) (-Math.log(uniform.sample()) / lambda);
    }
    public List<Float> counting() {

        ArrayList<Float> ans = new ArrayList<>();
        for(int i = 0; i < uniform.N; i++){
            ans.add(sample());
        }
        return ans;
    }
    public static double invCDF(double lambda,int u){
        return ChiSquareDist.inverseF( u,lambda);
    }
    public static double functionExp(double x, double A)
    {
        if (x > 0)
            return 1 - Math.exp(-A * x);
            //return 1 - Math.Exp(-(x / A));
        else
            return 0.0;
    }

}