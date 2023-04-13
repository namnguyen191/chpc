package chpc.visualizations.views;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import chpc.dataLoader.NHPIRecord;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class can create a window containing a Scatter chart
 */
public class Scatter extends View {
  public static final String CHART_TYPE = "Scatter";

  TimeSeriesCollection dataset;

  /**
   * constructor to create a window containing a Scatter chart
   * 
   * @param dataGroup it specifies the which group of data is used to display on
   *                  chart
   * @param predicted it specifies if the chart is used to show predicated datq
   */
  public Scatter(String dataGroup, boolean predicted) {
    super(dataGroup, predicted, "Scatter Chart for " + dataGroup);
  }

  /**
   * access selected data
   */
  public void addDataset() {

    // set series
    dataset = new TimeSeriesCollection();
    ArrayList<TimeSeries> allSeries = new ArrayList<>();

    // set series
    List<NHPIRecord> records = dataStore.getLoadedDataForGroup(this.dataGroup);
    if (this.predicted) {
      records = dataStore.getPredictedData(this.dataGroup);
    } else {
      records = dataStore.getLoadedDataForGroup(this.dataGroup);
    }
    SimpleDateFormat standardDateFormat = new SimpleDateFormat("yyyy-MM");
    int i = 0;
    allSeries.add(new TimeSeries(this.dataGroup));
    for (NHPIRecord r : records) {
      String date = r.getRefDate();
      try {
        Date myDate = standardDateFormat.parse(date);
        System.out.println("string: " + date.substring(0, 4) + " " + date.substring(5) + " " + r.getValue());
        double time = Double.parseDouble(date.substring(0, 4)) +
            Double.parseDouble(date.substring(5)) / 100;
        System.out.println("time: " + time);
        allSeries.get(i).add(new Month(myDate), r.getValue());

      } catch (ParseException e) {
        e.printStackTrace();
      }

    }
    dataset.addSeries(allSeries.get(i));

  }

  /**
   *
   * @return a plotted a scatter chart
   */
  public JFreeChart plotView() {
    XYPlot plot = new XYPlot();
    XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
    // XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

    plot.setDataset(0, dataset);
    plot.setRenderer(0, itemrenderer1);
    DateAxis domainAxis = new DateAxis("Year");
    plot.setDomainAxis(domainAxis);
    plot.setRangeAxis(new NumberAxis("NHPI"));

    // plot.setDataset(1, dataset2);
    // plot.setRenderer(1, itemrenderer2);
    // plot.setRangeAxis(1, new NumberAxis("US$"));

    plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
    // plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

    JFreeChart scatterChart = new JFreeChart("Scatter: New housing price index (NHPI)",
        new Font("Serif", Font.BOLD, 18), plot, true);

    return scatterChart;
  }

}
