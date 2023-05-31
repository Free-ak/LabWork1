package Lab5;

import Lab2.continuousDistributions.normalDistribution;
import Lab2.integral;
import Lab3.Exp;
import Lab3.Normal;
import Lab3.binomialLaw;
import inputData.inputData;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task5_1 {
    private String menu() {
        return System.lineSeparator() + "  Выберите распределение: " +
                System.lineSeparator() + " 1. Биномиальное распределение" +
                System.lineSeparator() + " 2. Экспоненциальное распределение" +
                System.lineSeparator() + " 3. Нормальное распределение" +
                System.lineSeparator() + " 4. Вариант" +
                System.lineSeparator() + " 0. Выйти" + System.lineSeparator();
    }
    public void Task5() throws InputMismatchException {
        Scanner in = new Scanner(System.in);
        while (true) {
        try{
            System.out.print("Проверка гипотезы о нормальном распределении генеральной совокупности с помощью теста Харке-Бера\n");
            new harkeBer();
            System.out.print("\nПроверка гипотезы о биномиальном, экспоненциальном и нормальном распределении генеральной совокупности с помощью критерия Х^2\n");
            System.out.print(menu());
            double choice = in.nextDouble();
            List<Float> row;
            switch ((int)choice) {
                case (1) -> {
                    binomialLaw bd= new binomialLaw();
                    bd.inputOfVariables(in);
                    row = new ArrayList<>(bd.counting());
                    System.out.print(" Введите критическое значение уровня значимости alpha : ");
                    double alpha = in.nextDouble();
                    System.out.print(" Генеральная совокупность:\n");
                    int j=0;
                    for (Float aFloat : row) {
                        System.out.print(aFloat+" ");
                        j++;
                        if(j>15){
                            System.out.print("\n");
                            j=0;
                        }
                    }
                    double h=0;
                    for (var  r:row) {
                        h+=r;
                    }
                    h=h/(row.size()*bd.getn());
                    System.out.print("\n Точечная оценка вероятности ");
                    DecimalFormat dF = new DecimalFormat( "0.0000" );
                    System.out.print(dF.format(h));
                    row.sort(Comparator.naturalOrder());
                    ArrayList<Double> upper_bounds = new ArrayList<>();
                    Map<Float, Long> statistical_series = row.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                    ArrayList<Double> experienced_frequencies = new ArrayList<>();
                    int column = 0;
                    for(var i : statistical_series.entrySet())
                    {
                        upper_bounds.add(Double.valueOf(i.getKey()));
                        experienced_frequencies.add(Double.valueOf(i.getValue())); //опытные частоты
                        column++;
                    }

                    double temp_1, s = 0, s1 = 0;

                    ArrayList<Double> experienced_frequencies_sum = new ArrayList<>();
                    ArrayList<Double> theoretical_frequencies = new ArrayList<>();
                    ArrayList<Double> theoretical_frequencies_sum = new ArrayList<>();
                    for (Double upper_bound : upper_bounds) {

                        temp_1 = bd.functionBin(upper_bound, bd.getp());
                        theoretical_frequencies.add(row.size() * temp_1);

                    }

                    int last = 0;
                    for (int i = 0; i < theoretical_frequencies.size(); i++)
                    {
                        if (theoretical_frequencies.get(i) < 5)
                        {
                            s += theoretical_frequencies.get(i);
                            s1 += experienced_frequencies.get(i);
                            theoretical_frequencies_sum.add(i,  0.);
                            experienced_frequencies_sum.add(i,  0.);
                        }
                        else
                        {
                            theoretical_frequencies_sum.add(i, s + theoretical_frequencies.get(i));
                            experienced_frequencies_sum.add(i, s1 + experienced_frequencies.get(i));
                            last = i;
                            s = 0;
                            s1 = 0;
                        }
                    }
                    if (s != 0)
                    {
                        theoretical_frequencies_sum.add(last, theoretical_frequencies_sum.get(last)+s);
                        experienced_frequencies_sum.add(last, experienced_frequencies_sum.get(last)+s1);
                    }
                    ArrayList<Double> last_column = new ArrayList<>();
                    for ( j = 0; j < theoretical_frequencies_sum.size(); j++)
                    {
                        if (experienced_frequencies_sum.get(j) != 0)
                        {
                            last_column.add(((experienced_frequencies_sum.get(j) - theoretical_frequencies_sum.get(j) ) * (experienced_frequencies_sum.get(j)  - theoretical_frequencies_sum.get(j) )) / theoretical_frequencies_sum.get(j) );
                        }
                        else
                            last_column.add((double) 0);
                    }
                    System.out.print("\nЗначения        Опытн.част      Теор.част       Сл.оп.част      Сл.теор.част   (nk-n*pk)^2/n*pk");
                    for (int i = 0; i < upper_bounds.size(); i++)
                    {
                        System.out.print("\n"+dF.format(upper_bounds.get(i)) + "\t\t\t" + dF.format(experienced_frequencies.get(i)) + "\t\t\t" + dF.format(theoretical_frequencies.get(i))+ "\t\t\t" + dF.format(experienced_frequencies_sum.get(i)) + "\t\t\t" + dF.format(theoretical_frequencies_sum.get(i)) + "\t\t\t" + dF.format(last_column.get(i)));

                    }
                    int r = 0;
                    for ( j = 0; j < experienced_frequencies_sum.size(); j++)
                    {
                        if (experienced_frequencies_sum.get(j) != 0)
                            r++;
                    }
                   double ch = (Exp.invCDF(1-alpha, r - 2));
                    System.out.print("\nУровень значимости статистики = " + alpha);
                    System.out.print("\nЧисло интервалов(после сложения) = " + r);
                    System.out.print("\nЧисло неизвестных параметров = 1");
                    double sum=0.0;
                    for (var  a:last_column) {
                        sum+=a;
                    }
                    System.out.print("\nВыборочное значение статистики критерия хи-квадрат = " + dF.format(sum));
                    System.out.print("\nКвантиль распределения хи-квадрат = " + dF.format(ch));
                    if (sum < ch)
                      System.out.print("\nВывод: так как " + dF.format(sum ) + " < " + dF.format(ch ) + " , то с вероятностью " + dF.format((1 - alpha)) + " выборка взята из генеральной совокупности, имеющей биномиальное распределение");
                    else
                      System.out.print("\nВывод: так как " +dF.format(sum )+ " > " + dF.format(ch ) + " , то с вероятностью " + dF.format((1 - alpha)) + " выборка взята из генеральной совокупности, имеющей НЕ биномиальное распределение");


                }
                case (2) -> {
                    Exp exp = new Exp();
                    exp.inputOfVariables(in);
                    row = new ArrayList<>(exp.counting());
                    System.out.print(" Введите параметр аlpha: ");
                    double alpha = in.nextDouble();
                    System.out.print(" Генеральная совокупность:\n");
                    int j=0;
                    for (Float aFloat : row) {
                        System.out.print(new DecimalFormat("0.000").format(aFloat)+" ");
                        j++;
                        if(j>15){
                            System.out.print("\n");
                            j=0;
                        }
                    }
                    double sum=0.0;
                    for (var  a:row) {
                        sum+=a;
                    }
                    double a_zv = ( exp.getN()- 1) / sum;
                    System.out.print("\n\nТочечная оценка вероятности ");
                    DecimalFormat dF = new DecimalFormat( "0.0000" );
                    System.out.print(dF.format(a_zv));
                    int k = (int) Math.round(1 + 3.32 * Math.log10(row.size()));
                    row.sort(Comparator.naturalOrder());
                    double w =row.get(row.size()-1) -row.get(0); //размах выборки
                    double b = w / k; //длина интервала
                    double lower_bound = row.get(0);
                    int t = 0;
                    double temp_1, temp_2, s = 0, s1 = 0;
                    List<Double> upper_bounds = new ArrayList<>();
                    List<Double> experienced_frequencies = new ArrayList<>();
                    List<Double> experienced_frequencies_sum = new ArrayList<>();
                    List<Double> theoretical_frequencies = new ArrayList<>();
                    List<Double> theoretical_frequencies_sum = new ArrayList<>();
                    for (int i = 0; i < k; i++)
                    {
                        for (j = 0; j < row.size(); j++)
                        {
                            if (row.get(j) >= lower_bound && row.get(j) < lower_bound + b)
                            {
                                t++;
                            }
                            if (i == k - 1 && row.get(j) == lower_bound + b)
                                t++;
                        }
                        temp_1 = Exp.functionExp(lower_bound, a_zv);
                        lower_bound = lower_bound+b;
                        temp_2 = Exp.functionExp(lower_bound, a_zv);
                        theoretical_frequencies.add(row.size() * (temp_2 - temp_1));
                        upper_bounds.add(lower_bound);
                        experienced_frequencies.add((double) t); //опытные частоты

                        t = 0;
                    }

                    int last = 0;
                    for (int i = 0; i < theoretical_frequencies.size(); i++)
                    {
                        if (theoretical_frequencies.get(i) < 5)
                        {
                            s += theoretical_frequencies.get(i);
                            s1 += experienced_frequencies.get(i);
                            theoretical_frequencies_sum.add(i,  0.);
                            experienced_frequencies_sum.add(i,  0.);
                        }
                        else
                        {
                            theoretical_frequencies_sum.add(i, s + theoretical_frequencies.get(i));
                            experienced_frequencies_sum.add(i, s1 + experienced_frequencies.get(i));
                            last = i;
                            s = 0;
                            s1 = 0;
                        }
                    }
                    if (s != 0)
                    {
                        theoretical_frequencies_sum.add(last, theoretical_frequencies_sum.get(last)+s);
                        experienced_frequencies_sum.add(last, experienced_frequencies_sum.get(last)+s1);
                    }
                    List<Double> last_column = new ArrayList<>();
                    for ( j = 0; j < theoretical_frequencies_sum.size(); j++)
                    {
                        if (experienced_frequencies_sum.get(j) != 0)
                        {
                            last_column.add(((experienced_frequencies_sum.get(j) - theoretical_frequencies_sum.get(j) ) * (experienced_frequencies_sum.get(j)  - theoretical_frequencies_sum.get(j) )) / theoretical_frequencies_sum.get(j) );
                        }
                        else
                            last_column.add((double) 0);
                    }
                    System.out.print("\nЗначения        Опытн.част      Теор.част       Сл.оп.част      Сл.теор.част   (nk-n*pk)^2/n*pk");
                    for (int i = 0; i < upper_bounds.size(); i++)
                    {
                        System.out.print("\n"+dF.format(upper_bounds.get(i)) + "\t\t\t" + dF.format(experienced_frequencies.get(i)) + "\t\t\t" + dF.format(theoretical_frequencies.get(i))+ "\t\t\t" + dF.format(experienced_frequencies_sum.get(i)) + "\t\t\t" + dF.format(theoretical_frequencies_sum.get(i)) + "\t\t\t" + dF.format(last_column.get(i)));

                    }
                    int r = 0;
                    for ( j = 0; j < experienced_frequencies_sum.size(); j++)
                    {
                        if (experienced_frequencies_sum.get(j) != 0)
                            r++;
                    }
                    double ch = (Exp.invCDF(1-alpha, r - 2));
                    System.out.print("\nУровень значимости статистики = " + alpha);
                    System.out.print("\nЧисло интервалов(после сложения) = " + r);
                    System.out.print("\nЧисло неизвестных параметров = 1");
                    sum=0.0;
                    for (var  a:last_column) {
                        sum+=a;
                    }
                    System.out.print("\nВыборочное значение статистики критерия хи-квадрат = " + dF.format(sum));
                    System.out.print("\nКвантиль распределения хи-квадрат = " + dF.format(ch));
                    if (sum < ch)
                        System.out.print("\nВывод: так как " + dF.format(sum )+ " < " + dF.format(ch )+ " , то с вероятностью " + dF.format((1 - alpha)) + " выборка взята из генеральной совокупности, имеющей экспоненциальное распределение");
                    else
                        System.out.print("\nВывод: так как " + dF.format(sum ) + " > " + dF.format(ch )+ " , то с вероятностью " + dF.format((1 - alpha))+ " выборка взята из генеральной совокупности, имеющей НЕ экспоненциальное распределение");


                }
                case (3),(4)->{
                    Normal normal = new Normal();
                    if(choice==3){
                    normal.inputOfVariables(in);
                    row=new ArrayList<>(normal.counting());}
                    else{
                        inputData input=new inputData();
                        row=new ArrayList<>( input.input());
                    }
                    System.out.print(" Введите параметр аlpha");
                    double alpha = in.nextDouble();
                    DecimalFormat dF = new DecimalFormat( "0.0000" );
                    System.out.print(" Генеральная совокупность:\n");
                    int j=0;
                    for (Float aFloat : row) {
                        System.out.print(new DecimalFormat("0.000").format(aFloat)+" ");
                        j++;
                        if(j>15){
                            System.out.print("\n");
                            j=0;
                        }
                    }
                    double sum=0.0;
                    for (var  a:row) {
                        sum+=a;
                    }
                    double x_sr = sum/ row.size();

                    System.out.print("\nТочечная оценка mu: ");
                    System.out.print(dF.format(x_sr));
                    double summ = 0;
                    for (int i = 0; i < row.size(); i++)
                    {
                        summ += Math.pow(row.get(i) - x_sr, 2);
                    }
                    double sigma_zv = Math.sqrt(summ / (row.size() - 1));
                    System.out.print("\nТочечная оценка sigma: ");
                    System.out.print(dF.format(sigma_zv));
                    if(choice==4){
                        normal.setmu(x_sr);
                        normal.setsigma(sigma_zv);
                    }
                    int k = (int) Math.round(1 + 3.32 * Math.log10(row.size()));
                    row.sort(Comparator.naturalOrder());
                    double w =row.get(row.size()-1) -row.get(0); //размах выборки
                    double b = w / k; //длина интервала
                    double lower_bound = row.get(0);
                    int t = 0;
                    double temp_1, temp_2, s = 0, s1 = 0;
                    List<Double> upper_bounds = new ArrayList<>();
                    List<Double> experienced_frequencies = new ArrayList<>();
                    List<Double> experienced_frequencies_sum = new ArrayList<>();
                    List<Double> theoretical_frequencies = new ArrayList<>();
                    List<Double> theoretical_frequencies_sum = new ArrayList<>();
                    integral integral=new integral();
                    normalDistribution normal2= new normalDistribution(normal.getmu(), normal.getsigma());
                    for (int i = 0; i < k; i++)
                    {
                        for (j = 0; j < row.size(); j++)
                        {
                            if (row.get(j) >= lower_bound && row.get(j) < lower_bound + b)
                            {
                                t++;
                            }
                            if (i == k - 1 && row.get(j) == lower_bound + b)
                                t++;
                        }
                        temp_1 = (1 / (sigma_zv * Math.sqrt(2 * Math.PI))) * integral.calculation(-500,lower_bound,normal2.func);
                        lower_bound += b;
                        temp_2 = (1 / (sigma_zv * Math.sqrt(2 * Math.PI))) * integral.calculation(-500,lower_bound,normal2.func);
                        theoretical_frequencies.add(row.size() * (temp_2 - temp_1));
                        upper_bounds.add(lower_bound);
                        experienced_frequencies.add((double) t); //опытные частоты
                        t = 0;
                    }
                    List<Double> coppy_theoretical_frequencies = new ArrayList<>();
                    for ( j = 0; j < theoretical_frequencies.size(); j++)
                    {
                        coppy_theoretical_frequencies.add(theoretical_frequencies.get(j));
                    }
                    int last = 0;
                    for (int i = 0; i < theoretical_frequencies.size(); i++)
                    {
                        if (theoretical_frequencies.get(i) < 5)
                        {
                            s += theoretical_frequencies.get(i);
                            s1 += experienced_frequencies.get(i);
                            theoretical_frequencies_sum.add(i,  0.);
                            experienced_frequencies_sum.add(i,  0.);
                        }
                        else
                        {
                            theoretical_frequencies_sum.add(i, s + theoretical_frequencies.get(i));
                            experienced_frequencies_sum.add(i, s1 + experienced_frequencies.get(i));
                            last = i;
                            s = 0;
                            s1 = 0;
                        }
                    }
                    if (s != 0)
                    {
                        theoretical_frequencies_sum.add(last, theoretical_frequencies_sum.get(last)+s);
                        experienced_frequencies_sum.add(last, experienced_frequencies_sum.get(last)+s1);
                    }
                    List<Double> last_column = new ArrayList<>();
                    for ( j = 0; j < theoretical_frequencies_sum.size(); j++)
                    {
                        if (experienced_frequencies_sum.get(j) != 0)
                        {
                            last_column.add(((experienced_frequencies_sum.get(j) - theoretical_frequencies_sum.get(j) ) * (experienced_frequencies_sum.get(j)  - theoretical_frequencies_sum.get(j) )) / theoretical_frequencies_sum.get(j) );
                        }
                        else
                            last_column.add((double) 0);
                    }
                    System.out.print("\nЗначения        Опытн.част      Теор.част       Сл.оп.част      Сл.теор.част   (nk-n*pk)^2/n*pk");
                    for (int i = 0; i < upper_bounds.size(); i++) {
                        System.out.print("\n" + dF.format(upper_bounds.get(i)) + "\t\t\t" + dF.format(experienced_frequencies.get(i)) + "\t\t\t" + dF.format(theoretical_frequencies.get(i)) + "\t\t\t" + dF.format(experienced_frequencies_sum.get(i)) + "\t\t\t" + dF.format(theoretical_frequencies_sum.get(i)) + "\t\t\t" + dF.format(last_column.get(i)));
                    }
                    int r = 0;
                    for ( j = 0; j < experienced_frequencies_sum.size(); j++)
                    {
                        if (experienced_frequencies_sum.get(j) != 0)
                            r++;
                    }
                    double ch = (Exp.invCDF(1-alpha, r - 2));
                    System.out.print("\nУровень значимости статистики = " + alpha);
                    System.out.print("\nЧисло интервалов(после сложения) = " + r);
                    System.out.print("\nЧисло неизвестных параметров = 2");
                    sum=0.0;
                    for (var  a:last_column) {
                        sum+=a;
                    }
                    System.out.print("\nВыборочное значение статистики критерия хи-квадрат = " + dF.format(sum));
                    System.out.print("\nКвантиль распределения хи-квадрат = " + dF.format(ch));
                     if (sum < ch)
                         System.out.print("\nВывод: так как " + dF.format(sum ) + " < " + dF.format(ch ) + " , то с вероятностью " + dF.format((1 - alpha)) + " выборка взята из генеральной совокупности, имеющей нормальное распределение");
                    else
                         System.out.print("\nВывод: так как " + dF.format(sum ) + " > " + dF.format(ch ) + " , то с вероятностью " + dF.format((1 - alpha)) + " выборка взята из генеральной совокупности, имеющей НЕ нормальное распределение");

                }
                case (0) -> {
                    in.close();
                    in.close();
                    System.exit(0);
                }
                default -> System.out.println(System.lineSeparator() + "Это недопустимый вариант меню! Пожалуйста, выберите другой");
            }
        }
        catch(InputMismatchException ex){
            System.out.print(" Введите целое число. Повторите ввод.");
            System.out.print(menu());
        }
        }
    }
}
