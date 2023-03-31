package chpc.visualizations;

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
import java.util.*;

public class Scatter extends View {
  TimeSeriesCollection dataset;

  public Scatter() {
    setTitle("Scatter Chart");
    chartType = "Scatter";
    createChart();
  }

  public void addDataset() {

    // set series
    dataset = new TimeSeriesCollection();
    ArrayList<TimeSeries> allSeries = new ArrayList<>();

    // set series
    var groupedRecords = dataStore.getGroupedLoadedData();
    SimpleDateFormat standardDateFormat = new SimpleDateFormat("yyyy-MM");
    int i = 0;
    for (var entry : groupedRecords.entrySet()) {
      String geo = entry.getKey();
      Set<NHPIRecord> records = entry.getValue();
      allSeries.add(new TimeSeries(geo));
      for (NHPIRecord r : records) {
        String date = r.getRefDate();
        try {
          Date myDate = standardDateFormat.parse(date);
          System.out.println("string: " + date.substring(0, 4) + " " + date.substring(5) + " " + r.getValue());
          double time = Double.parseDouble(date.substring(0, 4)) +
              Double.parseDouble(date.substring(5)) / 100;
          System.out.println("time: " + time);
          allSeries.get(i).addOrUpdate(new Month(myDate), r.getValue());

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
