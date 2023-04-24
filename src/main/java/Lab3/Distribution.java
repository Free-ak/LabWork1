package Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Distribution {
    protected int n;
    protected double p;
    protected int N;
    protected List<Float> values=new ArrayList<>();
//    public Distribution() {
////        this.n=n;
////        this.p=p;
////        this.N=N;
//    }

    public void inputOfVariables(Scanner in){
        String ifn = " Введите число n (n > 0): ";
        System.out.print(ifn);
        n =checkingForPositivityInt(in, ifn);
        String ifP = " Введите значение вероятности р (0 <= p <= 1): ";
        System.out.print(ifP);
        p = checkingForPositivityFloat(in,ifP);
        String ifN = " Введите число N (N > 0): ";
        System.out.print(ifN);
        N = checkingForPositivityInt(in, ifN);
    }

    private Integer checkingForPositivityInt(Scanner in,String s1) {
        String uncorrected = " Введены некорректные данные.";
        int value=in.nextInt();
        while (value <= 0) {
            System.out.print(uncorrected + s1);
            value = in.nextInt();
        }
        return value;
    }
    private Double checkingForPositivityFloat(Scanner in, String s1) {
        String uncorrected = " Введены некорректные данные.";
        double value=in.nextDouble();;
        while (value < 0.0||value>1.0) {
            System.out.print(uncorrected + s1);
            value = in.nextFloat();
        }
        return value;
    }

}
