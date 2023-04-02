package chpc.visualizations;

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

public class Line extends View {
  DefaultCategoryDataset dataset;
  String dataGroup;

  public Line(String dataGroup) {
    this.dataGroup = dataGroup;
    setTitle("Line Chart for " + dataGroup);
    chartType = "Line";
    createChart();

  }

  @Override
  public void addDataset() {
    dataset = new DefaultCategoryDataset();
    List<NHPIRecord> nhpiRecords = dataStore.getLoadedDataForGroup(this.dataGroup);
    for (NHPIRecord r : nhpiRecords) {
      System.out.println(r.getValue() + " " + r.getGeo() + " " + r.getRefDate());
      dataset.setValue(r.getValue(), r.getGeo(), r.getRefDate());
    }
  }

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
