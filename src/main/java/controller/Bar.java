package controller;

import model.Region;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Bar implements View{


    @Override
    public void update(Subject s) {

    }

    public static void createBar(JPanel west) {
        //the chart can be plotted by 1 or more datasets
        RegionData data = new RegionData();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Region> regions = data.getData();
        for(Region r : regions){
            dataset.setValue(r.getNHPI(),r.getRegionName(),r.getPeriod());
        }

//        dataset.setValue(5.6, "Mortality/1000 births", "2018");
//        dataset.setValue(5.7, "Mortality/1000 births", "2017");
//        dataset.setValue(5.8, "Mortality/1000 births", "2016");
//        dataset.setValue(5.8, "Mortality/1000 births", "2015");
//        dataset.setValue(5.9, "Mortality/1000 births", "2014");
//        dataset.setValue(6, "Mortality/1000 births", "2013");
//        dataset.setValue(6.1, "Mortality/1000 births", "2012");
//        dataset.setValue(6.2, "Mortality/1000 births", "2011");
//        dataset.setValue(6.4, "Mortality/1000 births", "2010");
//
//        dataset.setValue(2.92, "Hospital beds/1000 people", "2018");
//        dataset.setValue(2.87, "Hospital beds/1000 people", "2017");
//        dataset.setValue(2.77, "Hospital beds/1000 people", "2016");
//        dataset.setValue(2.8, "Hospital beds/1000 people", "2015");
//        dataset.setValue(2.83, "Hospital beds/1000 people", "2014");
//        dataset.setValue(2.89, "Hospital beds/1000 people", "2013");
//        dataset.setValue(2.93, "Hospital beds/1000 people", "2012");
//        dataset.setValue(2.97, "Hospital beds/1000 people", "2011");
//        dataset.setValue(3.05, "Hospital beds/1000 people", "2010");
//
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

//        dataset2.setValue(106, "North York", "2018");
//        dataset2.setValue(102, "North York", "2017");
//        dataset2.setValue(97, "North York", "2016");
//        dataset2.setValue(94, "North York", "2015");
//        dataset2.setValue(90, "North York", "2014");
//        dataset2.setValue(85, "North York", "2013");
//        dataset2.setValue(89, "North York", "2012");
//        dataset2.setValue(81, "North York", "2011");
//        dataset2.setValue(79, "North York", "2010");


        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer1 = new BarRenderer();
        BarRenderer barrenderer2 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer1);
        CategoryAxis domainAxis = new CategoryAxis("Year-Month");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        //plot.setDataset(1, dataset2);
        plot.setRenderer(1, barrenderer2);
        //plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        //plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart barChart = new JFreeChart("New housing price index (NHPI) ",
                new Font("Serif", Font.BOLD, 18), plot, true);

        // Different way to create bar chart
        /*
         * dataset = new DefaultCategoryDataset();
         *
         * dataset.addValue(3.946, "Unemployed", "Men"); dataset.addValue(96.054,
         * "Employed", "Men"); dataset.addValue(3.837, "Unemployed", "Women");
         * dataset.addValue(96.163, "Employed", "Women"); barChart =
         * ChartFactory.createBarChart("Unemployment: Men vs Women", "Gender",
         * "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
         */
//        String title = "Bar: New housing price index (NHPI): " + "Toronto vs North York";
//        JFreeChart barChart = ChartFactory.createBarChart(title, "Year-Month", "NHPI", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        west.add(chartPanel);
    }


}
