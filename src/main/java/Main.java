import Lab1.Lab1;
import Lab2.Lab2;
import Lab3.Task3_1;
import Lab4.Task4_1;
import Lab5.Task5_1;
import Lab6.Task6;
import Lab7.Task7;
import inputData.inputData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print(" Введите номер лаборатотной работы: ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case(1) ->{
                inputData input=new inputData();
                Lab1 lab1=new Lab1(input.input(),false);
            }
            case(2)->{
                Lab2 lab2 = new Lab2();
            }
            case(3)->{
               Task3_1 task3_1 =new Task3_1();
               task3_1.Task3();
            }
            case(4)->{
            Task4_1 t4=new Task4_1();
            t4.Task4();
            }
            case(5)-> {
                Task5_1 t5 = new Task5_1();
                t5.Task5();
            }case(6)-> {
                Task6 t6 = new Task6();
                t6.Task6();
            }
            case(7)-> {
                new Task7();
            }
        }
    }
}