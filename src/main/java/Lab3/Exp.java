package Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp  {
    private final UniformDistribution uniform = new UniformDistribution();
    private double lambda;
    public void inputOfVariables(Scanner in){
        String ifh = " Введите число h (h > 0) ";
        System.out.print(ifh);
        lambda = uniform.checkingForPositivityInt(in, ifh);
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

}