package chpc.visualizations.views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import chpc.dataLoader.NHPIRecord;

import java.awt.*;
import java.util.List;
/**
 * A class can create a window containing a line chart
 */
public class Line extends View {
  DefaultCategoryDataset dataset;
  String dataGroup;
  boolean predicted;

  /**
   * constructor to create  a window containing a line chart
   * @param dataGroup it specifies the which group of data is used to display on chart
   * @param predicted it specifies if the chart is used to show predicated datq
   */
  public Line(String dataGroup, boolean predicted) {
    this.predicted = predicted;
    this.dataGroup = dataGroup;
    setTitle("Line Chart for " + dataGroup);
    chartType = "Line";
    createChart();

  }

  /**
   * access selected data
   */
  @Override
  public void addDataset() {
    dataset = new DefaultCategoryDataset();
    List<NHPIRecord> nhpiRecords = dataStore.getLoadedDataForGroup(this.dataGroup);
    if (this.predicted) {
      nhpiRecords = dataStore.getPredictedData(this.dataGroup);
    } else {
      nhpiRecords = dataStore.getLoadedDataForGroup(this.dataGroup);
    }
    for (NHPIRecord r : nhpiRecords) {
      System.out.println(r.getValue() + " " + r.getGeo() + " " + r.getRefDate());
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
  @Override
  public JFreeChart plotView() {

    JFreeChart chart = ChartFactory.createLineChart("Line: New housing price index (NHPI)",
        "Year-Month",
        "NHPI",
        dataset,
        PlotOrientation.VERTICAL,
        true,
        true,
        false);

    CategoryPlot plot = chart.getCategoryPlot();
    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

    renderer.setSeriesPaint(0, Color.YELLOW);
    renderer.setSeriesStroke(0, new BasicStroke(2.0f));

    plot.setRenderer(renderer);
    plot.setBackgroundPaint(Color.white);

    plot.setRangeGridlinesVisible(true);
    plot.setRangeGridlinePaint(Color.BLACK);

    plot.setDomainGridlinesVisible(true);
    plot.setDomainGridlinePaint(Color.BLACK);

    chart.getLegend().setFrame(BlockBorder.NONE);

    return chart;
  }

}
