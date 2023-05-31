package Lab1.Task2;

import jfree__.simpleGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task1_2 {
    protected List<Float> row_sort = new ArrayList<>();
    protected List<Double[]>  addValuesData(boolean bool) {

        List<Double[]> data = new ArrayList<>();
        int k = (int) Math.round(1 + 3.32 * Math.log10(row_sort.size()));
        double h = ((double) (row_sort.get(row_sort.size() - 1) - row_sort.get(0)) / k);
        //Object[][] data = new Object[k][8];
        //   DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        double minRange = row_sort.get(0);
        double frequency = 0.0;
        double cumulativeFrequency = 0.0;
        double relativeFrequency;
        double cumulativeRelativeFrequency = 0.0;
        int j = 0;
        if ( !bool) {
            for (int i = 0; i < row_sort.size(); ) {

                while (i < row_sort.size()) {
                    if (row_sort.get(i) <= minRange + h) {
                        frequency++;
                        i++;
                    } else {
                        break;
                    }
                }
//            data[j][0] = decimalFormat.format(j + 1);
//            data[j][1] = decimalFormat.format(minRange);
//            data[j][2] = decimalFormat.format(minRange + h);
//            data[j][3] = decimalFormat.format((minRange * 2 + h) / 2);
//            data[j][4] = decimalFormat.format(frequency);
                cumulativeFrequency += frequency;
//            data[j][5] = decimalFormat.format(cumulativeFrequency);
                relativeFrequency = frequency / row_sort.size();
//            data[j][6] = decimalFormat.format(relativeFrequency);
                cumulativeRelativeFrequency += relativeFrequency;
//            data[j][7] = decimalFormat.format(cumulativeRelativeFrequency);
                data.add(new Double[]{minRange, (minRange + h), (((minRange * 2) + h) / 2), frequency, cumulativeFrequency, relativeFrequency, cumulativeRelativeFrequency});
                minRange += h;
                frequency = 0.0;
            }
        }
        else {

            Map<Float, Long> map = row_sort.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            map = new TreeMap<>(map);
            for (var i : map.entrySet()) {
                frequency= i.getValue();
//            data[j][0] = decimalFormat.format(j + 1);
//            data[j][1] = decimalFormat.format(minRange);
//            data[j][2] = decimalFormat.format(minRange + h);
//            data[j][3] = decimalFormat.format((minRange * 2 + h) / 2);
//            data[j][4] = decimalFormat.format(frequency);
                cumulativeFrequency += frequency;
//            data[j][5] = decimalFormat.format(cumulativeFrequency);
                relativeFrequency = frequency / row_sort.size();
//            data[j][6] = decimalFormat.format(relativeFrequency);
                cumulativeRelativeFrequency += relativeFrequency;
//            data[j][7] = decimalFormat.format(cumulativeRelativeFrequency);
                data.add(new Double[]{Double.valueOf(i.getKey()), frequency, cumulativeFrequency, relativeFrequency, cumulativeRelativeFrequency});
            }
        }
        return data;
    }


    private void Table(boolean bool_) {
        JFrame frame = simpleGui.frameDefault("Задание 2", 1000, 200);
        DefaultTableModel TModel = null;
        if(bool_) {TModel = new DefaultTableModel(new String[]{"№", "Элемент", "Частота", "Накопленная частота", "Относительная частота", "Относительная накопленная частота"},0);}
        if(!bool_) {TModel = new DefaultTableModel(new String[]{"№", "Нижняя граница", "Верхняя граница", "Середина", "Частота", "Накопленная частота", "Относительная частота", "Относительная накопленная частота"},0);}
        //Object[][] data = addValuesData();
        List<Double[]> Data= addValuesData(bool_);
        Object[] str = new Object[8];
        int numbering = 1;
        for(var row : Data) {
           int i = 1;
            str[0] = numbering;
            for(var varaible : row){
                str[i] = new DecimalFormat("#0.0000").format(varaible);
                i++;
            }
            numbering++;
            TModel.addRow(str);
        }
        simpleGui.scrollDefault(new JTable(TModel), frame, 900, 150);
    }
    public Task1_2(List<Float> row){
       this.row_sort.addAll(row);
    }
    public void Task2(boolean bool) {
        Table(bool);
    }

}
