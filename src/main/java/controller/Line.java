package controller;

import model.Region;
import model.RegionDAOImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Line extends View{
    XYSeriesCollection dataset;
    private static Line instance;

    private Line(){
        createChart();
    }

    public static synchronized View getInstance(){
        if(instance == null){
            instance = new Line();
        }
        return instance;
    }

    @Override
    public void addDataset(RegionData data) {

        //set series
        XYSeries series1 = new XYSeries("Toronto");
        XYSeries series2 = new XYSeries("North York");
        List<Region> regions = data.getData();
        for(Region r : regions){
            if(r.getRegionName().equals("Toronto")){
                series1.add(Double.parseDouble(r.getPeriod()),r.getNHPI());
            }else {
                series2.add(Double.parseDouble(r.getPeriod()),r.getNHPI());

            }
        }

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

        dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
    }



    @Override
    public JFreeChart plotView() {
        JFreeChart chart = ChartFactory.createXYLineChart("New housing price index (NHPI)", "Year", "", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        //not sure how many series we have
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

//        renderer.setSeriesPaint(1, Color.GREEN);
//        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
//
//        renderer.setSeriesPaint(2, Color.blue);
//        renderer.setSeriesStroke(2, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(
                new TextTitle("Line: New housing price index (NHPI)", new Font("Serif", Font.BOLD, 18)));

        return chart;
    }



}
