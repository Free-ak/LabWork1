package Lab2.continuousDistributions;

import jfree__.simpleGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class continuousDistributions {
    protected List<Integer> x = new ArrayList<>();
    protected List<Double> fx = new ArrayList<>();
    protected List<Double> Fx = new ArrayList<>();
    protected double a;
    protected double b;
    protected double h;

    public abstract interfaceFunction func();

    public abstract interface interfaceFunction{
        double function(double t);
        double function(double t,double k);
    }
    public continuousDistributions(double d1,double d2,double d3){
        this.a=d1;
        this.b=d2;
        this.h=d3;
    }
    public continuousDistributions(){
    }
    public void print (String s) {
        JFrame frame = simpleGui.frameDefault(s, 370, 400);
        DefaultTableModel TModel1 = new DefaultTableModel(new String[]{"x","f(x)","F(x)"}, 0);
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000");
        for (int i = 0; i < x.size(); i++) {
            Object[] addingRow = {x.get(i), decimalFormat.format(fx.get(i)), decimalFormat.format(Fx.get(i))};
            TModel1.addRow(addingRow);
        }
        simpleGui.scrollDefault(new JTable(TModel1), frame, 330, 380);
    }
}
