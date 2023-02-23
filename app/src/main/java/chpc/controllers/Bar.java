package chpc.controllers;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import chpc.models.NHPIRecord;

import java.awt.*;
import java.util.Set;

public class Bar extends View {
  DefaultCategoryDataset dataset;

  public Bar() {
    setTitle("Bar Chart");
    chartType = "Bar";
    createChart();
  }

  public void addDataset() {

    dataset = new DefaultCategoryDataset();
    Set<NHPIRecord> regions = dataStore.getLoadedData();
    System.out.println("bar view: " + regions);
    for (NHPIRecord r : regions) {
      dataset.setValue(r.getValue(), r.getGeo(), r.getRefDate());
    }

  }

  public JFreeChart plotView() {
    CategoryPlot plot = new CategoryPlot();

    BarRenderer barrenderer1 = new BarRenderer();
    BarRenderer barrenderer2 = new BarRenderer();

    plot.setDataset(0, dataset);
    plot.setRenderer(0, barrenderer1);
    CategoryAxis domainAxis = new CategoryAxis("Year-Month");
    plot.setDomainAxis(domainAxis);
    plot.setRangeAxis(new NumberAxis("NHPI"));

    // plot.setDataset(1, dataset2);
    plot.setRenderer(1, barrenderer2);
    // plot.setRangeAxis(1, new NumberAxis("US$"));

    plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
    // plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

    JFreeChart barChart = new JFreeChart("Bar: New housing price index (NHPI) ",
        new Font("Serif", Font.BOLD, 18), plot, true);

    return barChart;
  }

}
