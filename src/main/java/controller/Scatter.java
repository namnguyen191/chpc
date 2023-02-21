package controller;

import model.Region;
import model.RegionDAOImpl;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Scatter extends View{
    private static Scatter instance;
    TimeSeriesCollection dataset;

    private Scatter(){
        createChart();
    }

    public static synchronized View getInstance(){
        if(instance == null){
            instance = new Scatter();
        }
        return instance;
    }
    public void addDataset(RegionData data) {

        //set series

        TimeSeries series1 = new TimeSeries("Toronto");
        TimeSeries series2 = new TimeSeries("North York");
        List<Region> regions = data.getData();
        for (Region r : regions) {
            if (r.getRegionName().equals("Toronto")) {
                series1.add(new Year(Integer.parseInt(r.getPeriod())), r.getNHPI());
            } else {
                series2.add(new Year(Integer.parseInt(r.getPeriod())), r.getNHPI());
            }
        }

        dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        TimeSeries series3 = new TimeSeries("area3");
        series3.add(new Year(2018), 2.92);
        series3.add(new Year(2017), 2.87);
        series3.add(new Year(2016), 2.77);
        series3.add(new Year(2015), 2.8);
        series3.add(new Year(2014), 2.83);
        series3.add(new Year(2013), 2.89);
        series3.add(new Year(2012), 2.93);
        series3.add(new Year(2011), 2.97);
        series3.add(new Year(2010), 3.05);


        dataset.addSeries(series3);
    }



    public JFreeChart plotView() {
        XYPlot plot = new XYPlot();
        XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
        XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

        plot.setDataset(0, dataset);
        plot.setRenderer(0, itemrenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

//        plot.setDataset(1, dataset2);
//        plot.setRenderer(1, itemrenderer2);
//        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        //plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        String title = "Scatter: New housing price index (NHPI)";
        JFreeChart scatterChart = new JFreeChart(title,
                new Font("Serif", Font.BOLD, 18), plot, true);
        return scatterChart;
    }

}
