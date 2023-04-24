package Lab1;

import Lab1.Task1.Task1_1;
import Lab1.Task2.Task1_2;
import Lab1.Task3.Task1_3;

import java.util.List;

public class Lab1 {
   public Lab1(List<Float> row,boolean bool){
       new Task1_1(row);
       Task1_2 task_2=new Task1_2(row);
       task_2.Task2(bool);
       Task1_3 task_3=new Task1_3(row);
       task_3.Task3(bool);
    }

}
