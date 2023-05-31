package Lab3;

import Lab1.Lab1;
import jfree__.scatterChart;

import java.util.*;

public class Task3_1 {
    private String menu() {
        return System.lineSeparator() + "  Выберите распределение: " +
                System.lineSeparator() + " 1. Биномиальное распределение" +
                System.lineSeparator() + " 2. Экспоненциальное распределение" +
                System.lineSeparator() + " 3. Нормальное распределение" +
                System.lineSeparator() + " 0. Выйти" + System.lineSeparator();
    }

    public void Task3() throws InputMismatchException {
        Scanner in = new Scanner(System.in);
//        while (true) {
            try{
                System.out.print(menu());
                double choice = in.nextDouble();
                List<Float> row;
                    switch ((int)choice) {
                        case (1) -> {
                            binomialLaw bd= new binomialLaw();
                            bd.inputOfVariables(in);
                            row = new ArrayList<>(bd.counting());
                            new scatterChart(row);
                            row.sort(Comparator.naturalOrder());
                            new Lab1(row,true);

                        }
                        case (2) -> {
                            Exp exp = new Exp();
                            exp.inputOfVariables(in);
                            row = new ArrayList<>(exp.counting());
                            new scatterChart(row);
                            row.sort(Comparator.naturalOrder());
                            new Lab1(row,false);
                        }
                        case (3)->{
                            Normal normal = new Normal();
                            normal.inputOfVariables(in);
                            row=new ArrayList<>(normal.counting());
                            new scatterChart(row);
                            row.sort(Comparator.naturalOrder());
                            new Lab1(row,false);
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
//            }
        }
    }


