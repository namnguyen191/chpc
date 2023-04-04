package chpc.visualizations.views;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import chpc.dataLoader.NHPIRecord;

import java.awt.*;
import java.util.List;
/**
 * A class can create a window containing a bar chart
 */
public class Bar extends View {
  DefaultCategoryDataset dataset;
  String dataGroup;
  boolean predicted;
  /**
   * constructor to  create a window containing a bar chart
   * @param dataGroup it specifies the which group of data is used to display on chart
   * @param predicted it specifies if the chart is used to show predicated datq
   */
  public Bar(String dataGroup, boolean predicted) {
    this.predicted = predicted;
    this.dataGroup = dataGroup;
    setTitle("Bar Chart for " + dataGroup);
    chartType = "Bar";
    createChart();
  }
  /**
   * access selected data
   */
  public void addDataset() {

    dataset = new DefaultCategoryDataset();
    List<NHPIRecord> regions;
    if (this.predicted) {
      regions = dataStore.getPredictedData(this.dataGroup);
    } else {
      regions = dataStore.getLoadedDataForGroup(this.dataGroup);
    }
    System.out.println("bar view: " + regions);
    for (NHPIRecord r : regions) {
      dataset.setValue(r.getValue(), r.getGeo(), r.getRefDate());
    }

  }

//  public void addDataset(List<NHPIRecord> inputList) {
//
//    dataset = new DefaultCategoryDataset();
//    for (NHPIRecord r : inputList) {
//      dataset.setValue(r.getValue(), r.getGeo(), r.getRefDate());
//    }
//
//  }

  /**
   *
   * @return a plotted a bar chart
   */
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
