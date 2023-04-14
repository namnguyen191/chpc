package chpc.visualizations.views;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import chpc.dataLoader.NHPIRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.*;

/**
 * A class can create a window containing a Time chart
 */
public class Time extends View {
  TimeSeriesCollection dataset;
  public static final String CHART_TYPE = "Time";

  /**
   * constructor to create a window containing a time chart
   * 
   * @param dataGroup it specifies the which group of data is used to display on
   *                  chart
   * @param predicted it specifies if the chart is used to show predicated datq
   */
  public Time(String dataGroup, boolean predicted) {
    super(dataGroup, predicted, "TimeSeries Chart for " + dataGroup);
  }

  /**
   * access selected data
   */
  public void addDataset() {
    dataset = new TimeSeriesCollection();
    ArrayList<TimeSeries> allSeries = new ArrayList<>();
    List<NHPIRecord> records =setSeries();
    SimpleDateFormat standardDateFormat = new SimpleDateFormat("yyyy-MM");
    int i = 0;
    allSeries.add(new TimeSeries(this.dataGroup));
    for (var r : records) {
      String date = r.getRefDate();
      try {
        Date myDate = standardDateFormat.parse(date);
        allSeries.get(i).add(new Month(myDate), r.getValue());
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    dataset.addSeries(allSeries.get(i));
  }
  /**
   *  it helps to set series
   * @return the list contain series
   */
  List<NHPIRecord> setSeries(){
    if (this.predicted) {
      List<NHPIRecord> records = dataStore.getPredictedData(this.dataGroup);
      return records;
    } else {
      List<NHPIRecord> records = dataStore.getLoadedDataForGroup(this.dataGroup);
      return records;
    }
  }
  /**
   *
   * @return a plotted a time chart
   */
  public JFreeChart plotView() {
    XYPlot plot = new XYPlot();
    XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
    // XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

    plot.setDataset(0, dataset);
    plot.setRenderer(0, splinerenderer1);
    DateAxis domainAxis = new DateAxis("Year");
    plot.setDomainAxis(domainAxis);
    plot.setRangeAxis(new NumberAxis("NHPI"));

    plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
    // plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

    JFreeChart chart = new JFreeChart("Time series: New housing price index (NHPI) ",
        new Font("Serif", Font.BOLD, 18), plot, true);
    return chart;
  }

}
