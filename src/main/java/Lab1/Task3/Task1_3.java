package Lab1.Task3;

import Lab1.Task2.Task1_2;
import jfree__.BarChart;
import jfree__.LineChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class Task1_3 extends Task1_2 {
    private final List<Double[]> Data  =  addValuesData(true);

    public Task1_3(List<Float> row) {
        super(row);
    }

    private DefaultCategoryDataset createDataset(int indexValueData, boolean bool) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        Double[] str = new Double[7];
        for(var row : Data) {
            int i =0;
            for(var variable : row){
                str[i] = variable;
                i++;
            }
            if(!bool){dataset.addValue( str[indexValueData] , " ", str[2] );
            }
            else {dataset.addValue( str[indexValueData] , " ", str[0] );
            }
            }
        return dataset;
    }

    private LineChart[] createPolygons(boolean bool){
        int a;
        if (!bool) a=3;
        else a=1;
        return new LineChart[]{
                new LineChart(              //frequencyPolygon
                        createDataset( a,bool),
                        "Linechart",
                        "Полигон частот",
                        "Интервалы",
                        "Кол-во чисел в интервале",
                        500, 400
                ),
                new LineChart(              //frequencyCountPolygon
                        createDataset(a+1,bool),
                        "Linechart",
                        "Полигон накопленных частот",
                        "Интервалы",
                        "Кол-во чисел",
                        500, 400
                ),
                new LineChart(              //frequencyPolygon
                        createDataset(a+2,bool),
                        "Linechart",
                        "Полигон относительных частот",
                        "Интервалы",
                        "Процент",
                        500, 400
                ),
                new LineChart(              //frequencyPolygon
                        createDataset(a+3,bool),
                        "Linechart",
                        "Относительная накопленная частота",
                        "Интервалы",
                        "Процент",
                        500, 400
                )
        };

    }

    private BarChart[] createHistogram(boolean bool){
        int a;
        if (!bool) a=3;
        else a=1;
        return new BarChart[]{
                new BarChart(              //frequencyPolygon
                        createDataset(a,bool),
                        "BarChart",
                        "Полигон частот",
                        "Интервалы",
                        "Кол-во чисел в интервале",
                        500, 400
                ),
                new BarChart(              //frequencyCountPolygon
                        createDataset(a+1,bool),
                        "BarChart",
                        "Полигон накопленных частот",
                        "Интервалы",
                        "Кол-во чисел",
                        500, 400
                ),
                new BarChart(              //frequencyPolygon
                        createDataset(a+2,bool),
                        "BarChart",
                        "Полигон относительных частот",
                        "Интервалы",
                        "Процент",
                        500, 400
                ),
                new BarChart(              //frequencyPolygon
                        createDataset(a+3,bool),
                        "BarChart",
                        "Относительная накопленная частота",
                        "Интервалы",
                        "Процент",
                        500, 400
                )
        };
    }

    public void Task3(boolean bool){
        LineChart[] Lch = createPolygons(bool);
        for(var i : Lch)
            LineChart.showChart(i);

        BarChart[] Bch = createHistogram(bool);
        for(var i : Bch)
            BarChart.showChart(i);
    }
}
