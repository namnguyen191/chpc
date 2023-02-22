package controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import model.Region;
import model.RegionDAOImpl;
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
import java.util.ArrayList;
import java.util.List;

public class Bar extends View{
    DefaultCategoryDataset dataset;
    private static Bar instance;
    private Bar(){
        super("Bar Chart");
        type = "Bar";
        createChart();
    }

    public static synchronized View getInstance(){
        if(instance == null){
            instance = new Bar();
        }
        return instance;
    }



    public void addDataset(RegionData data) {
        //RegionData data = new RegionData(new RegionDAOImpl());
        dataset = new DefaultCategoryDataset();
        List<Region> regions = data.getData();
        for(Region r : regions){
            dataset.setValue(r.getNHPI(),r.getRegionName(),r.getPeriod());
        }

    }

    public JFreeChart plotView(){
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

        return barChart;
    }



}
