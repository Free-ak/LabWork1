package Lab5;

import Lab3.Exp;
import inputData.inputData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class harkeBer {
    private double alpha;
    private Scanner in = new Scanner(System.in);
    public harkeBer() {
        inputData input=new inputData();
        List<Float> row=new ArrayList<>(input.input());
        System.out.print("Генеральная совокупность:\n");
        int i=0;
        for (Float aFloat : row) {
            System.out.print(aFloat+" ");
            i++;
            if(i>15){
                System.out.print("\n");
                i=0;
            }
        }
        System.out.print("\nВведите критическое значение уровня значимости alpha : ");
        alpha = in.nextDouble();
        double s, Sk, Kur;
        double sum=0.0;
        for (var  r:row) {
            sum+=r;
        }
        double x_sr=sum/row.size();
        double summ = 0;
        for ( i = 0; i < row.size(); i++) {
            summ += Math.pow(row.get(i) - x_sr, 2);
        }
        s = Math.sqrt(summ / row.size());
        summ = 0;
        for ( i = 0; i < row.size(); i++) {
            summ += Math.pow(row.get(i) - x_sr, 4);
        }
        Kur = summ / (row.size() * Math.pow(s, 4));
        summ = 0;
        for ( i = 0; i < row.size(); i++) {
            summ += Math.pow(row.get(i) - x_sr, 3);
        }
        Sk = summ / (row.size() * Math.pow(s, 3));
        double JBSTAT = row.size() * ((Sk * Sk / 6) + (Math.pow(Kur - 3, 2) / 24));
        System.out.print("\nРезультат проверки нулевой гипотезы для заданного критического уровня значимости Н");
        System.out.print("\nЗначение выборочной статистики JBSTAT: ");
        DecimalFormat dF = new DecimalFormat( "0.0000" );
        System.out.print(dF.format(JBSTAT));
        double kv = Exp.invCDF(1-alpha, 2);
        if (JBSTAT < kv)
            System.out.print("\nНулевая гипотеза принимается т.к " + dF.format(JBSTAT) + " < " + dF.format(kv));
        else
            System.out.print("\nНулевая гипотеза может быть отвергнута т.к " + dF.format(JBSTAT) + " > " +  dF.format(kv));

    }
}
