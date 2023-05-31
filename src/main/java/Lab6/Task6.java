package Lab6;

import Lab3.UniformDistribution;

import java.util.Scanner;

public class Task6 {

    public void Task6() {
        Scanner in = new Scanner(System.in);
        System.out.print("\n Проверка гипотез о параметрах нормально распределенной генеральной совокупности."
                +"\n\n Проверка гипотезы о равенстве МО и Д генеральной совокупности по конкретному значению");
        System.out.print("\n Введите конкретное значение МО : ");
        double m= UniformDistribution.checkingForPositivityDouble(in,"\n Введите конкретное значение МО : ");
        System.out.print(" Введите конкретное значение Д : ");
        double d = UniformDistribution.checkingForPositivityDouble(in,"\n Введите конкретное значение Д : ");
        statistics.for_1(m, d);
        System.out.print("\n\n Проверка предположения о том, что занятия в секции по легкой атлетике повлияли на время челночного бега");
        statistics.for_2();
        System.out.print("\n\n Проверка предположения о том, какой из видов тренировок оказывает наибольшее влияние на время челночного бега");
        statistics.for_3();
    }
}
