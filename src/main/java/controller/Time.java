package controller;

import model.Region;
import model.RegionDAOImpl;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Time extends View{
    TimeSeriesCollection dataset;
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

        TimeSeries series3 = new TimeSeries("city3");
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
        XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
        XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, splinerenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        //plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart chart = new JFreeChart("Time series: New housing price index (NHPI) ",
                new Font("Serif", Font.BOLD, 18), plot, true);
        return chart;
    }
//    public void createTimeSeries(JPanel west) {
//        addDataset(new RegionData(new RegionDAOImpl()));
//        JFreeChart chart = plotView();

//        XYPlot plot = new XYPlot();
//        XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
//        XYSplineRenderer splinerenderer2 = new XYSplineRenderer();
//
//        plot.setDataset(0, dataset);
//        plot.setRenderer(0, splinerenderer1);
//        DateAxis domainAxis = new DateAxis("Year");
//        plot.setDomainAxis(domainAxis);
//        plot.setRangeAxis(new NumberAxis(""));

//        plot.setDataset(1, dataset);
//        plot.setRenderer(1, splinerenderer2);
//        plot.setRangeAxis(1, new NumberAxis("US$"));

//        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
//        //plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
//
//        JFreeChart chart = new JFreeChart("Time series: New housing price index (NHPI) ",
//                new Font("Serif", Font.BOLD, 18), plot, true);

//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new Dimension(400, 300));
//        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//        chartPanel.setBackground(Color.white);
//        west.add(chartPanel);

//    }


}
