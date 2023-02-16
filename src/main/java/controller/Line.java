package controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Line {


    public void createLine(JPanel west) {
        XYSeries series1 = new XYSeries("city1");
        series1.add(2018, 5.6);
        series1.add(2017, 5.7);
        series1.add(2016, 5.8);
        series1.add(2015, 5.8);
        series1.add(2014, 5.9);
        series1.add(2013, 6.0);
        series1.add(2012, 6.1);
        series1.add(2011, 6.2);
        series1.add(2010, 6.4);

        XYSeries series2 = new XYSeries("city2");
        series2.add(2018, 10);
        series2.add(2017, 19);
        series2.add(2016, 20);
        series2.add(2015, 31);
        series2.add(2014, 22);
        series2.add(2013, 25);
        series2.add(2012, 39);
        series2.add(2011, 30);
        series2.add(2010, 30);

        XYSeries series3 = new XYSeries("city3");
        series3.add(2018, 2.92);
        series3.add(2017, 2.87);
        series3.add(2016, 2.77);
        series3.add(2015, 2.8);
        series3.add(2014, 2.83);
        series3.add(2013, 2.89);
        series3.add(2012, 2.93);
        series3.add(2011, 2.97);
        series3.add(2010, 3.05);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        JFreeChart chart = ChartFactory.createXYLineChart("New housing price index (NHPI)", "Year", "", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(
                new TextTitle("Line: New housing price index (NHPI)", new Font("Serif", Font.BOLD, 18)));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        west.add(chartPanel);

    }


}
