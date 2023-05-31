package Lab4;

import Lab3.Exp;
import Lab3.Normal;
import Lab3.binomialLaw;
import inputData.inputData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class Task4_1 {
    private String menu() {
        return System.lineSeparator() + "  Выберите распределение: " +
                System.lineSeparator() + " 1. Биномиальное распределение" +
                System.lineSeparator() + " 2. Экспоненциальное распределение" +
                System.lineSeparator() + " 3. Нормальное распределение" +
                System.lineSeparator() + " 4. Вариант" +
                System.lineSeparator() + " 0. Выйти" + System.lineSeparator();
    }

    public void Task4() throws InputMismatchException {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print(menu());
            int choice = in.nextInt();
            List<Float> row;
//                if(in.hasNextInt()) {
            switch (choice) {
                case (1) -> {
                    binomialLaw bd= new binomialLaw();
                    bd.inputOfVariables(in);
                    row = new ArrayList<>(bd.counting()) ;
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
                        double h=0;
                        for (var  r:row) {
                            h+=r;
                        }
                        h=h/(row.size()*bd.getn());
                        System.out.print("\n\nТочечная оценка вероятности ");
                        DecimalFormat dF = new DecimalFormat( "0.0000" );
                        System.out.print(dF.format(h));
                        System.out.print("\nИнтервальные оценки вероятности ");
                        System.out.print("""

                                 Альфа\t\tВероятность\t\tНижняя граница\t\tВерхняя граница
                                 """);
                        double  alpha = 1 - 0.9;
                        DecimalFormat dF2= new DecimalFormat("0.00");
                        System.out.print(dF2.format(alpha) + "\t\t" +
                                dF2.format(0.9) + "\t\t\t"+
                                dF.format(h - Normal.invCDF(1 - (alpha / 2)) * Math.sqrt((h * (1 - h)) / bd.getn())) + "\t\t\t\t" +
                                dF.format(h + Normal.invCDF(1 - (alpha / 2)) * Math.sqrt((h * (1 - h)) / bd.getn())));
                        alpha = 1-0.95;
                        System.out.print("\n"+dF2.format(alpha)  + "\t\t"
                                + dF2.format(0.95) + "\t\t\t" +
                                dF.format(h - Normal.invCDF(1 - (alpha / 2)) * Math.sqrt((h * (1 - h)) / bd.getn())) + "\t\t\t\t" +
                                dF.format(h + Normal.invCDF(1 - (alpha / 2)) * Math.sqrt((h * (1 - h)) / bd.getn())));

                        alpha = 1 - 0.99;
                        System.out.print("\n"+dF2.format(alpha) + "\t\t" +
                                dF2.format(0.99) + "\t\t\t" +
                                dF.format(h - Normal.invCDF(1 - (alpha / 2)) * Math.sqrt((h * (1 - h)) / bd.getn())) + "\t\t\t\t" +
                                dF.format(h + Normal.invCDF(1 - (alpha / 2)) * Math.sqrt((h * (1 - h)) / bd.getn())));

                    }
                case (2) -> {
                    Exp exp = new Exp();
                    exp.inputOfVariables(in);
                    row = new ArrayList<>(exp.counting());
                   // row.sort(Comparator.naturalOrder());
                    DecimalFormat dF = new DecimalFormat( "0.0000" );
                    System.out.print("Генеральная совокупность:\n");
                    int i=0;
                    for (Float aFloat : row) {
                        System.out.print(dF.format(aFloat)+" ");
                        i++;
                        if(i>15){
                            System.out.print("\n");
                            i=0;
                        }
                    }
                  double sum=0.0;
                    for (var  r:row) {
                        sum+=r;
                    }
                    double a_z=(exp.getN()-1)/sum;
                    System.out.print("\n\nТочечная оценка вероятности ");
                    System.out.print(dF.format(a_z));
                    System.out.print("\nИнтервальные оценки вероятности ");
                    System.out.print("""

                                 Альфа\t\tВероятность\t\tНижняя граница\t\tВерхняя граница
                                 """);
                    double  alpha = 1 - 0.9;
                    DecimalFormat dF2= new DecimalFormat("0.00");
                    System.out.print(dF2.format(alpha) + "\t\t" +
                            dF2.format(0.9) + "\t\t\t"+
                            dF.format((a_z*Exp.invCDF((1-0.9)/2,2*row.size()))/(2*(row.size()-1)) ) + "\t\t\t\t" +
                            dF.format((a_z*Exp.invCDF((1+0.9)/2,2*row.size()))/(2*(row.size()-1)) ));
                    alpha = 1-0.95;
                    System.out.print("\n"+dF2.format(alpha)  + "\t\t"
                            + dF2.format(0.95) + "\t\t\t" +
                            dF.format((a_z*Exp.invCDF((1-0.9)/2,2*row.size()))/(2*(row.size()-1)) ) + "\t\t\t\t" +
                            dF.format((a_z*Exp.invCDF((1+0.9)/2,2*row.size()))/(2*(row.size()-1)) ));

                    alpha = 1 - 0.99;
                    System.out.print("\n"+dF2.format(alpha) + "\t\t" +
                            dF2.format(0.99) + "\t\t\t" +
                            dF.format((a_z*Exp.invCDF((1-0.9)/2,2*row.size()))/(2*(row.size()-1)) ) + "\t\t\t\t" +
                            dF.format((a_z*Exp.invCDF((1+0.9)/2,2*row.size()))/(2*(row.size()-1)) ));
                }
                case (3)->{
                    Normal normal = new Normal();
                   normal.inputOfVariables(in);
                    row=new ArrayList<>(normal.counting());
                   // row.sort(Comparator.naturalOrder());
                    DecimalFormat dF = new DecimalFormat( "0.0000" );
                    System.out.print("Генеральная совокупность:\n");
                    int i=0;
                    for (Float aFloat : row) {
                        System.out.print(dF.format(aFloat)+" ");
                        i++;
                        if(i>15){
                            System.out.print("\n");
                            i=0;
                        }
                    }
                    double sum=0.0;
                    for (var  r:row) {
                        sum+=r;
                    }
                    double x_sr=sum/row.size();
                    System.out.print("\n\nТочечная оценка вероятности mu: ");
                    System.out.print(dF.format(x_sr));
                    sum=0.0;
                    for(int j=0;j<row.size();j++){
                        sum+=Math.pow(row.get(j)-x_sr,2);
                    }
                    double sigma_zv=Math.sqrt(sum/(row.size()-1));
                    System.out.print("\n\nТочечная оценка вероятности sigma: ");
                    System.out.print(dF.format(sigma_zv));
                    System.out.print("\nИнтервальные оценки вероятности ");
                    System.out.print("""

                     Альфа\t\tВероятность\t\tНижняя граница(mu)\t\tВерхняя граница(mu)\t\tНижняя граница(sigma)\tВерхняя граница(sigma)
                     """);
                    double  alpha = 1 - 0.9;
                    double t= Normal.invCDF(row.size()-1,alpha);
                    double chi_r= Exp.invCDF(1-alpha/2, row.size()-1);
                    double chi_l=Exp.invCDF(alpha/2, row.size()-1);
                    DecimalFormat dF2= new DecimalFormat("0.00");
                    System.out.print(dF2.format(alpha) + "\t\t" +
                            dF2.format(0.9) + "\t\t\t"+
                            dF.format(x_sr + ((t * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(x_sr - ((t * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r))+ "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l)));
                    alpha = 1-0.95;
                    t= Normal.invCDF(row.size()-1,alpha);
                    chi_r=Exp.invCDF(1-alpha/2, row.size()-1);
                    chi_l=Exp.invCDF(alpha/2, row.size()-1);
                    System.out.print("\n"+dF2.format(alpha) + "\t\t" +
                            dF2.format(0.95) + "\t\t\t"+
                            dF.format(x_sr + ((t * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(x_sr - ((t * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r))+ "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l)));

                    alpha = 1 - 0.99;
                    t= Normal.invCDF(row.size()-1,alpha);
                    chi_r=Exp.invCDF(1-alpha/2, row.size()-1);
                    chi_l=Exp.invCDF(alpha/2, row.size()-1);
                    System.out.print("\n"+dF2.format(alpha) + "\t\t" +
                            dF2.format(0.99) + "\t\t\t"+
                            dF.format(x_sr + ((t * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(x_sr - ((t * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r))+ "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l)));

                }case (4)->{
                    inputData input=new inputData();
                    row=new ArrayList<>(input.input());
                    DecimalFormat dF = new DecimalFormat( "0.0000" );
                    System.out.print("Генеральная совокупность:\n");
                    int i=0;
                    for (Float aFloat : row) {
                        System.out.print(dF.format(aFloat)+" ");
                        i++;
                        if(i>15){
                            System.out.print("\n");
                            i=0;
                        }
                    }
                    double sum=0.0;
                    for (var  r:row) {
                        sum+=r;
                    }
                    double x_sr=sum/row.size();
                    System.out.print("\n\nТочечная оценка вероятности mu: ");
                    System.out.print(dF.format(x_sr));
                    sum=0.0;
                    for(int j=0;j<row.size();j++){
                        sum+=Math.pow(row.get(j)-x_sr,2);
                    }
                    double sigma_zv=Math.sqrt(sum/(row.size()-1));
                    System.out.print("\nТочечная оценка вероятности sigma: ");
                    System.out.print(dF.format(sigma_zv));
                    System.out.print("\nИнтервальные оценки вероятности ");
                    System.out.print("\nСодержательный вывод по варианту ");
                    double  alpha = 1 - 0.9;
                    double t1= Normal.invCDF(row.size()-1,alpha);
                    double chi_r1= Exp.invCDF(1-alpha/2, row.size()-1);
                    double chi_l1=Exp.invCDF(alpha/2, row.size()-1);
                    DecimalFormat dF2= new DecimalFormat("0.00");

                    System.out.print("\n"+dF2.format(alpha) + "\t\t" +
                            dF2.format(0.9) + "\t\t\t"+
                            dF.format(x_sr + ((t1 * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(x_sr - ((t1 * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r1))+ "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l1)));
                    alpha = 1-0.95;
                   double t2= Normal.invCDF(row.size()-1,alpha);
                    double chi_r2=Exp.invCDF(1-alpha/2, row.size()-1);
                    double chi_l2=Exp.invCDF(alpha/2, row.size()-1);
                    System.out.print("\n"+dF2.format(alpha) + "\t\t" +
                            dF2.format(0.95) + "\t\t\t"+
                            dF.format(x_sr + ((t2 * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(x_sr - ((t2* sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r2))+ "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l2)));
                    alpha = 1 - 0.99;
                    double t3= Normal.invCDF(row.size()-1,alpha);
                   double  chi_r3=Exp.invCDF(1-alpha/2, row.size()-1);
                   double  chi_l3=Exp.invCDF(alpha/2, row.size()-1);
                    System.out.print("\n"+dF2.format(alpha) + "\t\t" +
                            dF2.format(0.95) + "\t\t\t"+
                            dF.format(x_sr + ((t3 * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(x_sr - ((t3 * sigma_zv) / Math.sqrt(row.size())) ) + "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r3))+ "\t\t\t\t\t" +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l3)));


                    System.out.print("\nСредняя длина пищевода у мужчин с вероятностью "+dF2.format(0.9) +
                            " находится в пределах от "+
                            dF.format(x_sr + ((t1 * sigma_zv) / Math.sqrt(row.size())) )  +" до "+
                            dF.format(x_sr - ((t1 * sigma_zv) / Math.sqrt(row.size())) ) + " ,среднее отклонение от средней длины пищевода - в пределах от " +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r1))+ " до " +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l1)));

                    System.out.print("\nСредняя длина пищевода у мужчин с вероятностью "+dF2.format(0.95) +
                            " находится в пределах от "+
                            dF.format(x_sr + ((t2 * sigma_zv) / Math.sqrt(row.size())) )  +" до "+
                            dF.format(x_sr - ((t2 * sigma_zv) / Math.sqrt(row.size())) ) + " ,среднее отклонение от средней длины пищевода - в пределах от " +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r2))+ " до " +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l2)));


                    System.out.print("\nСредняя длина пищевода у мужчин с вероятностью "+dF2.format(0.99) +
                            " находится в пределах от "+
                            dF.format(x_sr + ((t3 * sigma_zv) / Math.sqrt(row.size())) )  +" до "+
                            dF.format(x_sr - ((t3 * sigma_zv) / Math.sqrt(row.size())) ) + " ,среднее отклонение от средней длины пищевода - в пределах от " +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_r3))+ " до " +
                            dF.format(sigma_zv*Math.sqrt(row.size()/chi_l3)));

                }
                case (0) -> {
                    in.close();
                    in.close();
                    System.exit(0);
                }
                default -> System.out.println(System.lineSeparator() + "Это недопустимый вариант меню! Пожалуйста, выберите другой");
            }
//                }
//                else{
//                    System.out.print(" Введите целое число. Повторите ввод.");
//                }
        }
    }
}


