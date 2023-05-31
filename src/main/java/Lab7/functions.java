package Lab7;

import umontreal.iro.lecuyer.probdist.FisherFDist;

import java.text.DecimalFormat;
import java.util.List;

public class functions {

    public static List<Double> F_10;
    public static List<Double> S10_15;
    public static List<Double> T15_20;
    public static List<Double> F20_25;
    public static List<Double> F25_30;
    private static final DecimalFormat df2=new DecimalFormat("0.00");
    public static double list_pow(List<Double> list)
    {
        double sum = 0;
        for (int i = 0; i < list.size(); i++)
        {
            sum += Math.pow(list.get(i) ,2);
        }
        return sum;
    }

    public static int size( List<Double> list)
    {
        if (list.stream().mapToDouble(Double::doubleValue).sum() == 0)
            return 0;
        else
            return list.size();
    }
    public static Double invCDF(int n1, int n2,Double alpha){
        return FisherFDist.inverseF(n1,n2,alpha);
    }

    public static void DA()
    {
        int a = 5;
        System.out.print("\n Результаты исследований о влиянии протеиносодержащих продуктов в рационе питания больных, перенесших полостную операцию, на увеличение их массы тела");
        System.out.print("\n Менее 10%\t10-15%\t\t15-20%\t\t20-25%\t\t25-30%");
        for (int i = 0; i < F25_30.size(); i++)
        { System.out.print("\n");
            if (i < F_10.size())
                System.out.print(" " + F_10.get(i) + "\t\t");
            else
                System.out.print("\t\t\t");
            if (i < S10_15.size())
                System.out.print(S10_15.get(i) + "\t\t\t");
            else
                System.out.print("\t\t\t");
            if (i < T15_20.size())
                System.out.print(T15_20.get(i) + "\t\t\t");
            else
                System.out.print("\t\t\t");
            if (i < F20_25.size())
                System.out.print(F20_25.get(i) + "\t\t\t");
            else
                System.out.print("\t\t\t");
            if (i < F25_30.size())
                System.out.print(F25_30.get(i) + "\t\t\t");
            else
                System.out.print("\t\t\t");
            System.out.print("");
        }
        double sum_of_squares = list_pow(F_10) + list_pow(S10_15) + list_pow(T15_20) + list_pow(F20_25) + list_pow(F25_30);
        System.out.print("\n\n Число градаций фактора a = " + a);
        System.out.print("\n |Число вариант в градациях \t\t\t\t\t\t\t|Общее число наблюдений N");
        int N = size(F_10) + size(S10_15) + size(T15_20) + size(F20_25) + size(F25_30);
        System.out.print("\n |"+size(F_10)+"\t\t\t|"+size(S10_15)+"\t\t\t|"+ size(T15_20)+"\t\t\t|"+size(F20_25)+"\t\t\t|"+size(F25_30)+"\t\t|"+N);
        System.out.print("\n\n Сумма квадратов вариант = " + df2.format(sum_of_squares));
        System.out.print("\n |Квадрат суммы вариант в градациях/число вариант в градациях \t\t\t\t\t|Сумма");
        double sum = (F_10.stream().mapToDouble(Double::doubleValue).sum() * F_10.stream().mapToDouble(Double::doubleValue).sum()) / F_10.size()
                + (S10_15.stream().mapToDouble(Double::doubleValue).sum() * S10_15.stream().mapToDouble(Double::doubleValue).sum()) / S10_15.size()
                + (T15_20.stream().mapToDouble(Double::doubleValue).sum() * T15_20.stream().mapToDouble(Double::doubleValue).sum()) / T15_20.size()
                + (F20_25.stream().mapToDouble(Double::doubleValue).sum() * F20_25.stream().mapToDouble(Double::doubleValue).sum()) / F20_25.size()
                + (F25_30.stream().mapToDouble(Double::doubleValue).sum() * F25_30.stream().mapToDouble(Double::doubleValue).sum()) / F25_30.size();
        System.out.print("\n |"+df2.format((F_10.stream().mapToDouble(Double::doubleValue).sum() * F_10.stream().mapToDouble(Double::doubleValue).sum()) / F_10.size())+" \t\t\t|"+
                df2.format((S10_15.stream().mapToDouble(Double::doubleValue).sum() * S10_15.stream().mapToDouble(Double::doubleValue).sum()) / S10_15.size())+" \t\t\t|"+
                df2.format((T15_20.stream().mapToDouble(Double::doubleValue).sum() * T15_20.stream().mapToDouble(Double::doubleValue).sum()) / T15_20.size())+" \t\t\t|"+
                df2.format((F20_25.stream().mapToDouble(Double::doubleValue).sum() * F20_25.stream().mapToDouble(Double::doubleValue).sum()) / F20_25.size())+" \t\t\t|"+
                df2.format((F25_30.stream().mapToDouble(Double::doubleValue).sum() * F25_30.stream().mapToDouble(Double::doubleValue).sum()) / F25_30.size())+" \t\t\t|"+
                df2.format(sum));
        System.out.print("\n\n\t\t\t\t\tДисперсионная таблица\n");
        System.out.print("\t\t|Девиаты D\t|Степени свободы k\t|Выборочн.дисперc s^2\t|Дисперc.отн. F\t|F(alpha)(kA;ke)");
        double temp = (F_10.stream().mapToDouble(Double::doubleValue).sum() + S10_15.stream().mapToDouble(Double::doubleValue).sum() + T15_20.stream().mapToDouble(Double::doubleValue).sum() + F20_25.stream().mapToDouble(Double::doubleValue).sum() + F25_30.stream().mapToDouble(Double::doubleValue).sum());
        double D_y = sum_of_squares - ((temp * temp) / N);
        double D_A = sum - ((temp * temp) / N);
        double F = (D_A / (a - 1)) / ((D_y - D_A) / (N - a));
        double kvant = invCDF(a - 1, N - a       ,1-0.05);
        System.out.print("\n |y|\t|"+df2.format(D_y)+"\t\t|"+(N - 1)+"\t\t\t\t\t|"+df2.format(D_y / (N - 1))+"\t\t\t\t\t|"+"\t\t\t\t|");
        System.out.print("\n |A|\t|"+df2.format(D_A)+"\t\t|"+(a - 1)+"\t\t\t\t\t|"+df2.format(D_A / (a - 1))+"\t\t\t\t\t|"+df2.format(F)+"\t\t\t|"+df2.format(kvant));
        System.out.print("\n |e|\t|"+df2.format(D_y - D_A)+"\t\t|"+(N - a )+"\t\t\t\t\t|"+df2.format((D_y - D_A) / (N - a))+"\t\t\t\t\t|"+"\t\t\t\t|");
        if(F>=kvant)
            System.out.print("\n\n Вывод: т.к " + df2.format(F) + " >= " + df2.format(kvant) + " то с вероятностью 95% гипотезу о равенстве средних в градациях следует ОТВЕРГНУТЬ.\n Следовательно, исследуемый фактор(объем протеиносодержащих продуктов) ВЛИЯЕТ на признак(прибавка веса после операции)");
        else
            System.out.print("\n\n Вывод: т.к " + df2.format(F) + " < " + df2.format(kvant) + " то с вероятностью 95% гипотезу о равенстве средних в градациях следует ПРИНЯТЬ.\n Следовательно, исследуемый фактор(объем протеиносодержащих продуктов) НЕ влияет на признак(прибавка веса после операции)");
    }
}
