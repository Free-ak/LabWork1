package Lab2.oneDimensionalDistributions;

import jfree__.simpleGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class oneDimensionalDistributions {
    protected List<Double> Px = new ArrayList<>();
    protected List<Integer> x = new ArrayList<>();
    protected List<Double> fx = new ArrayList<>();
    public static BigDecimal getFactorial(double f) {
        if (f <= 1) {
            return BigDecimal.valueOf(1);
        } else {
            return BigDecimal.valueOf(f).multiply(getFactorial(f - 1));
        }
    }
    public void print (String s) {
        JFrame frame = simpleGui.frameDefault(s, 370, 400);
        DefaultTableModel TModel1 = new DefaultTableModel(new String[]{"x", "P(x)", "f(x)"}, 0);
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000");
        for (int i = 0; i < x.size(); i++) {
            Object[] addingRow = {x.get(i), decimalFormat.format(Px.get(i)), decimalFormat.format(fx.get(i))};
            TModel1.addRow(addingRow);
        }
        simpleGui.scrollDefault(new JTable(TModel1), frame, 330, 380);
    }
}
