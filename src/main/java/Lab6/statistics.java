package Lab6;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class statistics {
    public static List<Double> values= new ArrayList<>(List.of(10.8, 11.4, 11.7, 11.4, 11.9, 12.2, 11.9, 10.5, 11.5, 11.7, 11.2, 11.1, 11.9, 11.8, 11.4,
            10.9, 11.7, 11.4, 10.9, 10.8, 12.0, 11.6, 12.0, 12.3));
    public static List<Double> K_G_before = new ArrayList<>(List.of(10.9, 11.7, 11.4, 10.9, 12.0, 10.8, 11.6, 12.0, 12.3));
    public static List<Double> K_G_after =new ArrayList<>(List.of( 11.4, 10.7, 10.9, 11.3, 11.7, 11.9, 10.7, 10.6, 10.5 ));
    public static List<Double> O_G_before = new ArrayList<>(List.of(10.8, 11.4, 11.7, 11.4, 11.9, 12.2, 11.9, 10.5, 11.5, 11.7, 11.2, 11.1, 11.9, 11.8, 11.4 ));
    public static List<Double> O_G_after = new ArrayList<>(List.of( 9.8, 9.8, 9.8, 9.4, 9.7, 9.9, 10.4, 9.9, 9.7, 10.2, 10.0, 10.4, 9.2, 10.2, 10.1 ));
    private static final DecimalFormat df=new DecimalFormat("0.0000");
    private static final DecimalFormat df2=new DecimalFormat("0.00");
    public static double stats;
    public static void for_1(double m, double d)
    {
        System.out.print("\n Выборка генеральной совокупности: ");
        for (Double value : values) {
            System.out.print("\n " + df.format(value));
        }
        double st_inv = 0.0317;
        stats = get_stats_A(values, m);
        System.out.print("\n Результат проверки нулевой гипотезы по МО:");
        System.out.print("\n Значение статистики t: " + df.format(stats));
        if (Math.abs(stats) < st_inv)
            System.out.print("\n Гипотеза о равенстве МО объединенной группы с введенным значением принимается. Введенное значение СОВПАДАЕТ с МО выборки т.к " + df.format(Math.abs(stats)) + " < " + st_inv);
        else
            System.out.print("\n Гипотеза о равенстве МО объединенной группы с введенным значением НЕ принимается. Введенное значение НЕ совпадает с МО выборки т.к " + df.format(Math.abs(stats)) + " > " + st_inv);
        stats = get_stats_D(values, d);
        System.out.print("\n Результат проверки нулевой гипотезы по Д:");
        System.out.print("\n Значение статистики t: " + df.format(stats));
        double chi2_inv_l = 11.6886 , chi2_inv_r = 38.0756;

        if (stats > chi2_inv_l && stats < chi2_inv_r)
            System.out.print("\n Гипотеза о равенстве Д объединенной группы с введенным значением принимается. Введенное значение СОВПАДАЕТ с Д выборки т.к " + chi2_inv_l + " < " + df.format(stats) + " < " + chi2_inv_r);
        else
            System.out.print("\n Гипотеза о равенстве Д объединенной группы с введенным значением НЕ принимается. Введенное значение НЕ совпадает с Д выборки т.к " + df.format(stats) + " > " + chi2_inv_r + " или " + df.format(stats) + " < " + chi2_inv_l);

    }

    public static double get_sigma_zv(List<Double> minus)
    {
        double summ = 0;
        double x_sr = minus.stream().mapToDouble(Double::doubleValue).sum() / minus.size();
        for (Double aDouble : minus) {
            summ += Math.pow(aDouble - x_sr, 2);
        }
        return Math.sqrt(summ / (minus.size() - 1));
    }
    public static double get_stats_A(List<Double> minus, double m)
    {
        double sigma_zv = get_sigma_zv(minus);
        stats = ((minus.stream().mapToDouble(Double::doubleValue).sum() / minus.size()) - m) / (sigma_zv / Math.sqrt(minus.size()));
        return stats;
    }
    public static double get_stats_D(List<Double> minus, double d)
    {
        double sigma_zv = get_sigma_zv(minus);
        stats = ((minus.size() - 1) * sigma_zv* sigma_zv) / d;
        return stats;
    }

    public static void for_2()
    {
        System.out.print("\n Выборки генеральной совокупности(основная группа)");
        System.out.print("\n\tДо\t\t\tПосле");
        for (int i = 0; i < O_G_before.size(); i++)
        {
            System.out.print("\n\t" + df2.format(O_G_before.get(i)) + "\t\t" + df2.format(O_G_after.get(i)));
        }
        List<Double> minus_O_G = new ArrayList<>();
        for (int i = 0; i < O_G_after.size(); i++)
        {
            minus_O_G.add(O_G_before.get(i) - O_G_after.get(i));
        }
        stats = get_stats_A(minus_O_G, 0);
        double st_inv = 2.5096;    //(alpha/2, 14)
        System.out.print("\n Результат проверки нулевой гипотезы:");
        System.out.print("\n Значение статистики по МО: " + df.format(stats));
        if (Math.abs(stats) < st_inv)
            System.out.print("\n Гипотеза о равенстве МО основной группы до и после эксперимента ПРИНИМАЕТСЯ. Занятия в секции по легкой атлетике НЕ повлияли на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
        else
            System.out.print("\n Гипотеза о равенстве МО основной группы с введенным значением НЕ принимается.Занятия в секции по легкой атлетике ПОВЛИЯЛИ на время челночного бега т.к " + df.format(Math.abs(stats))  + " > " + df.format(st_inv));
        System.out.print("\n\n Проверка предположения о том, что занятия по физ-ре повлияли на время челночного бега");
        System.out.print("\n Выборки генеральной совокупности(контрольная группа)");
        System.out.print("\n\tДо\t\t\tПосле");
        for (int i = 0; i < K_G_before.size(); i++)
        {
            System.out.print("\n\t" + K_G_before.get(i) + "\t\t " + K_G_after.get(i));
        }
        List<Double> minus_K_G = new ArrayList<>();
        for (int i = 0; i < K_G_after.size(); i++)
        {
            minus_K_G.add(K_G_before.get(i) - K_G_after.get(i));
        }
        st_inv = 2.7515;     // (alpha/2, 8)
        System.out.print("\n Результат проверки нулевой гипотезы:");
        stats = get_stats_A(minus_K_G, 0);
        System.out.print("\n Значение статистики по МО: " + df.format(stats));
        if (Math.abs(stats) < st_inv)
            System.out.print("\n Гипотеза о равенстве МО контрольной группы до и после эксперимента ПРИНИМАЕТСЯ. Занятия по физ-ре НЕ повлияли на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
        else
            System.out.print("\n Гипотеза о равенстве МО контрольной группы до и после эксперимента НЕ принимается. Занятия по физ-ре ПОВЛИЯЛИ на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
    }

    public static void for_3()
    {
        System.out.print("\n До эксперимента:");
        double s1, s2, s, fi_inv, k, st_inv;
        if (get_sigma_zv(O_G_before) < get_sigma_zv(K_G_before))
        {
            s1 = get_sigma_zv(O_G_before);
            s2 = get_sigma_zv(K_G_before);
            stats = (s1 * s1) / (s2 * s2);
            System.out.print("\n Значение статистики по Д: " + df.format(stats));
            fi_inv = 4.1297;                                            //((alpha / 2), 14, 8)
            if (fi_inv > stats)
            {
                System.out.print("\n Гипотеза о равенстве Д основной и контрольной группы до эксперимента ПРИНИМАЕТСЯ. Группы РАВНОПРАВНЫ т.к " + df.format(fi_inv) + " > " + df.format(stats));
                stats = ((O_G_before.stream().mapToDouble(Double::doubleValue).sum() / O_G_before.size()) - (K_G_before.stream().mapToDouble(Double::doubleValue).sum() / K_G_before.size())) / Math.sqrt((s1 * s1) / O_G_before.size() + (s2 * s2) / K_G_before.size());
                //k = Math.pow((s1 * s1) / O_G_before.size() + (s2 * s2) / K_G_before.size(), 2) / ((Math.pow((s1 * s1) / O_G_before.size(), 2) / (O_G_before.size() - 1)) + (Math.pow((s2 * s2) / K_G_before.size(), 2) / (K_G_before.size() - 1)));
                st_inv = 2.4334; // (alpha/2, k=19.5242)
                System.out.print("\n Значение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента НЕ принимается. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
            }
            else
            {
                System.out.print("\n Гипотеза о равенстве Д основной и контрольной группы до эксперимента НЕ принимается. Группы НЕ равноправны т.к " + df.format(fi_inv) + " < " + df.format(stats));
                s = (((O_G_before.size() - 1) * s1 * s1 + (K_G_before.size() - 1) * s2 * s2) / (O_G_before.size() + K_G_before.size() - 2));
                stats = ((O_G_before.stream().mapToDouble(Double::doubleValue).sum() / O_G_before.size()) - (K_G_before.stream().mapToDouble(Double::doubleValue).sum() / K_G_before.size())) / (s * Math.sqrt((1.0 / O_G_before.size()) + (1.0 / K_G_before.size())));
                st_inv = 2.4055;        //(alpha/2, 22)
                System.out.print("\n Значение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента НЕ принимается. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " +df.format( st_inv));

            }

        }
        else
        {
            s2 = get_sigma_zv(O_G_before);
            s1 = get_sigma_zv(K_G_before);
            stats = s1 * s1 / s2 * s2;
            System.out.print("\n Значение статистики по Д: " + df.format(stats));
            fi_inv = 3.2853;                                              //((alpha / 2), 8, 14)
            if (fi_inv > stats)
            {
                System.out.print("\n Гипотеза о равенстве Д основной и контрольной группы до эксперимента ПРИНИМАЕТСЯ. Группы РАВНОПРАВНЫ т.к " + fi_inv + " > " + df.format(stats));
                stats = ((K_G_before.stream().mapToDouble(Double::doubleValue).sum() / K_G_before.size()) - (O_G_before.stream().mapToDouble(Double::doubleValue).sum() / O_G_before.size())) / Math.sqrt((s1 * s1) / K_G_before.size() + (s2 * s2) / O_G_before.size());
                //k = Math.pow((s1 * s1) / K_G_before.size() + (s2 * s2) / O_G_before.size(), 2) / ((Math.pow((s1 * s1) / K_G_before.size(), 2) / (K_G_before.size() - 1)) + (Math.pow((s2 * s2) / O_G_before.size(), 2) / (O_G_before.size() - 1)));
                st_inv = 2.5096; // (alpha/2, k=14.56956)
                System.out.print("\n Значение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента НЕ приниамется. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
            }
            else
            {
                System.out.print("\n Гипотеза о равенстве Д основной и контрольной группы до эксперимента НЕ принимается. Группы НЕ равноправны т.к " + fi_inv + " < " + stats);
                s = (((K_G_before.size() - 1) * s1 * s1 + (O_G_before.size() - 1) * s2 * s2) / (O_G_before.size() + K_G_before.size() - 2));
                stats = ((K_G_before.stream().mapToDouble(Double::doubleValue).sum() / K_G_before.size()) - (O_G_before.stream().mapToDouble(Double::doubleValue).sum() / O_G_before.size())) / (s * Math.sqrt(1 / O_G_before.size() + 1 / K_G_before.size()));
                st_inv = 2.4055;        //(alpha/2, 22)
                System.out.print("\n Значение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы до эксперимента НЕ принимается. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
            }

        }


        System.out.print("\n\n После эксперимента:");
        if (get_sigma_zv(O_G_after) < get_sigma_zv(K_G_after))
        {
            s1 = get_sigma_zv(O_G_after);
            s2 = get_sigma_zv(K_G_after);
            stats = (s1 * s1) / (s2 * s2);
            System.out.print("\n Значение статистики по Д: " + df.format(stats));
            fi_inv = 4.1297;                                            //((alpha / 2), 14, 8)
            if (fi_inv > stats)
            {
                System.out.print("\n Гипотеза о равенстве Д основной и контрольной группы после эксперимента ПРИНИМАЕТСЯ. Группы РАВНОПРАВНЫ т.к " + df.format(fi_inv) + " > " + df.format(stats));
                stats = ((O_G_after.stream().mapToDouble(Double::doubleValue).sum() / O_G_after.size()) - (K_G_after.stream().mapToDouble(Double::doubleValue).sum() / K_G_after.size())) / Math.sqrt((s1 * s1) / O_G_after.size() + (s2 * s2) / K_G_after.size());
               // k = Math.pow((s1 * s1) / O_G_after.size() + (s2 * s2) / K_G_after.size(), 2) / ((Math.pow((s1 * s1) / O_G_after.size(), 2) / (O_G_after.size() - 1)) + (Math.pow((s2 * s2) / K_G_after.size(), 2) / (K_G_after.size() - 1)));
                st_inv = 2.4138; // (alpha/2, k=21.7230)
                System.out.print("\n Значение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы после эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы после эксперимента НЕ принимается. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
            }
            else
            {
                System.out.print("\nГипотеза о равенстве Д основной и контрольной группы после эксперимента НЕ принимается. Группы НЕ равноправны т.к " + df.format(fi_inv) + " < " + df.format(stats));
                s = (((O_G_after.size() - 1) * s1 * s1 + (K_G_after.size() - 1) * s2 * s2) / (O_G_after.size() + K_G_after.size() - 2));
                stats = ((O_G_after.stream().mapToDouble(Double::doubleValue).sum() / O_G_after.size()) - (K_G_after.stream().mapToDouble(Double::doubleValue).sum() / K_G_after.size())) / (s * Math.sqrt((1.0 / O_G_after.size()) + (1.0 / K_G_after.size())));
                st_inv = 2.4055;        //(alpha/2, 22)
                System.out.print("\nЗначение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\nГипотеза о равенстве МО основной и контрольной группы после эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\nГипотеза о равенстве МО основной и контрольной группы после эксперимента НЕ принимается. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
            }

        }
        else
        {
            s2 = get_sigma_zv(O_G_after);
            s1 = get_sigma_zv(K_G_after);
            stats = s1 * s1 / s2 * s2;
            System.out.print("\n Значение статистики по Д: " + df.format(stats));
            fi_inv = 3.2853;                                              //((alpha / 2), 8, 14)
            if (fi_inv > stats)
            {
                System.out.print("\n Гипотеза о равенстве Д основной и контрольной группы после эксперимента ПРИНИМАЕТСЯ. Группы РАВНОПРАВНЫ т.к " + df.format(fi_inv)+ " > " + df.format(stats));
                stats = ((K_G_after.stream().mapToDouble(Double::doubleValue).sum() / K_G_after.size()) - (O_G_after.stream().mapToDouble(Double::doubleValue).sum() / O_G_after.size())) / Math.sqrt((s1 * s1) / K_G_after.size() + (s2 * s2) / O_G_after.size());
                //k = Math.pow((s1 * s1) / K_G_after.size() + (s2 * s2) / O_G_after.size(), 2) / ((Math.pow((s1 * s1) / K_G_after.size(), 2) / (K_G_after.size() - 1)) + (Math.pow((s2 * s2) / O_G_after.size(), 2) / (O_G_after.size() - 1)));
                st_inv = 2.56; // (alpha/2, k=12.2047)
                System.out.print("\n Значение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы после эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы после эксперимента НЕ приниамется. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
            }
            else
            {
                System.out.print("\n Группы НЕ равноправны т.к " + df.format(fi_inv) + " < " + df.format(stats));
                s = (((K_G_after.size() - 1) * s1 * s1 + (O_G_after.size() - 1) * s2 * s2) / (O_G_after.size() + K_G_after.size() - 2));
                stats = ((K_G_after.stream().mapToDouble(Double::doubleValue).sum() / K_G_after.size()) - (O_G_after.stream().mapToDouble(Double::doubleValue).sum() / O_G_after.size())) / (s * Math.sqrt(1 / O_G_after.size() + 1 / K_G_after.size()));
                st_inv = 2.4055;        //(alpha/2, 22)
                System.out.print("\n Значение статистики по МО: " + df.format(stats));
                if (Math.abs(stats) < st_inv)
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы после эксперимента ПРИНИМАЕТСЯ. Оба вида тренировок оказывают ОДИНАКОВОЕ влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " < " + df.format(st_inv));
                else
                    System.out.print("\n Гипотеза о равенстве МО основной и контрольной группы после эксперимента НЕ принимается. Оба вида тренировок оказывают НЕ одинаковое влияние на время челночного бега т.к " + df.format(Math.abs(stats)) + " > " + df.format(st_inv));
            }
        }
    }
}

