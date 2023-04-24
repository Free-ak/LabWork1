package Lab3;

import java.util.Random;
import java.util.Scanner;

public class UniformDistribution {
    public double a;
    public double b;
    protected int N;

    public void inputOfVariablesN(Scanner in){
//        String ifn = " Введите число h (h > 0) ";

        String ifN = " Введите число N (N > 0): ";
        System.out.print(ifN);
        N = checkingForPositivityInt(in, ifN);
    }

    public Integer checkingForPositivityInt(Scanner in, String s1) {
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
    private final Random random = new Random();

    public UniformDistribution(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public UniformDistribution() {
        this(0, 1);
    }

    public double sample() {
        return a + (b - a) * random.nextDouble();
    }
}
