package controller;

import model.Region;
import model.RegionDAOImpl;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Time extends View{
    TimeSeriesCollection dataset;
    private static Time instance;
    private Time(){
        super("Time Chart");
        createChart();
    }

    public static synchronized View getInstance(){
        if(instance == null){
            instance = new Time();
        }
        return instance;
    }
    public void addDataset(RegionData data) {

        //set series

        dataset = new TimeSeriesCollection();
        ArrayList<TimeSeries> allSeries = new ArrayList<>();

        //set series
        HashMap<String,List<Region>> regions= data.getSortedData();
        Set<String> keys = regions.keySet();
        SimpleDateFormat standardDateFormat = new SimpleDateFormat("yyyy-MM");
        int i = 0;
        for (String name:keys){
            allSeries.add(new TimeSeries(name));
            ArrayList<Region> sameRegion = (ArrayList<Region>) regions.get(name);
            for(Region r : sameRegion){
                String date = r.getPeriod();
                try {
                    Date myDate = standardDateFormat.parse(r.getPeriod());
                    System.out.println("string: "+date.substring(0,4) +" "+date.substring(5) + " "+r.getNHPI());
                    Double time = Double.parseDouble(date.substring(0,4)) + Double.parseDouble(date.substring(5))/100;
                    System.out.println("time: "+time);
                    allSeries.get(i).add(new Month(myDate), r.getNHPI());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            dataset.addSeries(allSeries.get(i));
            i++;

        }

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



}
