package jfree__;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;

public class scatterChart {
    private static final String CHART_TITLE = "";
    private static final String X_AXIS_TITLE = "";
    private static final String Y_AXIS_TITLE = "";

    public scatterChart(List<Float> dataPoints) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Data");
        for (int i = 0; i < dataPoints.size(); i++) {
            series.add(i + 1, dataPoints.get(i));
        }
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createScatterPlot(CHART_TITLE, X_AXIS_TITLE, Y_AXIS_TITLE,
                dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartFrame frame = new ChartFrame("ScatterChart", chart);
        UIUtils.centerFrameOnScreen( frame );
        frame.setPreferredSize( new java.awt.Dimension( 500 , 500 ));
        frame.setVisible(true);
    }
}
