package Lab3;

import java.util.ArrayList;
import java.util.Scanner;

public class Normal  {
    private UniformDistribution uniform = new UniformDistribution();
    private  Exp expdist=new Exp();
    private double mu;
    private double sigma;

    public void inputOfVariables(Scanner in){
//        String ifmu = " Введите число M (M > 0) ";
//        String ifsigma = " Введите число d (d > 0) ";
//        System.out.print(ifmu);
//        mu = uniform.checkingForPositivityInt(in, ifmu);
//        System.out.print(ifsigma);
//        sigma=uniform.checkingForPositivityInt(in, ifsigma);
       // uniform.inputOfVariablesN(in);
        expdist.inputOfVariables(in);

    }

    public Normal(int count, double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
        this.expdist = new Exp(count, sigma);
    }
     public Normal(){}

    private float sample() {
        double u;
        float e;
        float e2;
        do {
            //u = uniform.sample();
            e = expdist.sample();
            e2 = expdist.sample();
        //} while (!(u < Math.exp(-Math.pow(e - 1, 2))));
        } while (!(e2 > Math.pow(e - 1, 2)/2));
        u = uniform.sample();
        return e * (u < 0.5 ? -1 : 1);
    }

//    public ArrayList<Float> counting() {
//        float sum = 0;
//        var N = expdist.getN();
//        ArrayList<Float> ans = new ArrayList<>();
//        for (int i = 0; i < N; i++) {
//            sum += Math.random();
//            ans.add((float) ((sum - mu * N) / Math.sqrt(mu * sigma)));
//        }
//        return ans;
//    }
    public ArrayList<Float> counting() {

        ArrayList<Float> ans = new ArrayList<>();
        for(int i = 0; i < expdist.getN(); i++){
            ans.add(sample());
        }
        return ans;
    }
}

