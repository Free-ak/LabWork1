package Lab1.Task1;

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

public class Task1_1  {
    private final List<Float> row_sort = new ArrayList<>();

    public Map<Float, Long> rowStat;
    private final JFrame frame = simpleGui.frameDefault("Задание №1", 600, 900);

    private void Table1() {
        String[] columnname1 = {"Вариационный ряд"};
        DefaultTableModel TModel1 = new DefaultTableModel(columnname1, 0);
        for (Float aFloat : row_sort) {
            Object[] addingRow = {aFloat};
            TModel1.addRow(addingRow);
        }
        simpleGui.scrollDefault(new JTable(TModel1), frame, 140, 880);
    }

    private void Table2() {
        String[] columnname2 = {"Элемент", "Частота"};
        DefaultTableModel TModel2 = new DefaultTableModel(columnname2, 0);
        Map<Float, Long> map = row_sort.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //Очень плохой вариант map.entrySet().stream().sorted(Map.Entry.<Float, Long>comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        rowStat = new TreeMap<>(map);
        for (var i : rowStat.entrySet()) {
            Object[] addingRow = {i.getKey(), i.getValue()};
            TModel2.addRow(addingRow);
        }
        simpleGui.scrollDefault(new JTable(TModel2), frame, 140, 880);
    }

    private void Table3() {
        String[] columnname3 = {"Элемент", "Частота", "Процент"};
        DefaultTableModel TModel3 = new DefaultTableModel(columnname3, 0);
        for (var i : rowStat.entrySet()) {
            Object[] addingRow = {new DecimalFormat("#0.0000").format(i.getKey()), new DecimalFormat("#0.0000").format((double) i.getValue()), new DecimalFormat("#0.0000").format((double) i.getValue() / row_sort.size())};
            TModel3.addRow(addingRow);
        }
        simpleGui.scrollDefault(new JTable(TModel3), frame, 200, 880);
    }


    public Task1_1(List<Float> row) {
        row_sort.addAll(row);
        Table1();
        Table2();
        Table3();
    }
}

