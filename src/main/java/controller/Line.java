package controller;

import model.Region;
import model.RegionDAOImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Line extends View{
    DefaultCategoryDataset dataset;
    //XYSeriesCollection dataset;
    private static Line instance;

    private Line(){
        super("Line Chart");
        type="Line";
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
//        dataset = new XYSeriesCollection();
//        ArrayList<XYSeries> allSeries = new ArrayList<>();
//
//        //set series
//        HashMap<String,List<Region>> regions= data.getSortedData();
//        Set<String> keys = regions.keySet();
//        int i = 0;
//        for (String name:keys){
//            allSeries.add(new XYSeries(name));
//            ArrayList<Region> sameRegion = (ArrayList<Region>) regions.get(name);
//            for(Region r : sameRegion){
//                String date = r.getPeriod();
////                System.out.println("string: "+date.substring(0,4) +" "+date.substring(5) + " "+r.getNHPI());
////                System.out.println("year "+Double.parseDouble(date.substring(0,4)));
////                System.out.println("month "+Double.parseDouble(date.substring(5))/100);
//                Double time = Double.parseDouble(date.substring(0,4))*100 + Double.parseDouble(date.substring(5));
//                System.out.println("time: "+time);
//                //allSeries.get(i).add(time, r.getNHPI());
//                allSeries.get(i).add(time,r.getNHPI());
//            }
//            dataset.addSeries(allSeries.get(i));
//            i++;
//        }
//        System.out.println(allSeries);
        dataset = new DefaultCategoryDataset();
        List<Region> regions = data.getData();
        for(Region r : regions){
            dataset.setValue(r.getNHPI(),r.getRegionName(),r.getPeriod());
        }




    }



    @Override
    public JFreeChart plotView() {
//        JFreeChart chart = ChartFactory.createXYLineChart("New housing price index (NHPI)", "Year-Month", "NHPI", dataset,
//                PlotOrientation.VERTICAL, true, true, false);
        JFreeChart chart = ChartFactory.createLineChart("New housing price index (NHPI)",
                "Year-Month", // 目录轴的显示标签
                "NHPI", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                true, // 是否生成工具
                false // 是否生成URL链接
        );


        //XYPlot plot = chart.getXYPlot();
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        //XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
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
