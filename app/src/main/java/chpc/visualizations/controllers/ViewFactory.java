package chpc.visualizations.controllers;

import java.util.Arrays;
import java.util.HashSet;

import chpc.visualizations.views.*;

/**
 * an interface can create instance of View to show different kinds of chart
 */
public interface ViewFactory {
  public static final HashSet<String> AVAILABLE_CHARTS = new HashSet<String>(Arrays.asList(
      Time.CHART_TYPE,
      Scatter.CHART_TYPE,
      Bar.CHART_TYPE,
      Line.CHART_TYPE));

  /**
   * @param viewType  it specifies the type of visualization
   * @param dataGroup it specifies the which group of data is used to display on
   *                  chart
   * @param predicted it specifies if the chart is used to show predicated datq
   * @return a selected visualization
   */
  public static View createView(String viewType, String dataGroup, boolean predicted) {
    View view = null;
    switch (viewType) {
      case Bar.CHART_TYPE:
        view = new Bar(dataGroup, predicted);
        break;
      case Line.CHART_TYPE:
        view = new Line(dataGroup, predicted);
        break;
      case Scatter.CHART_TYPE:
        view = new Scatter(dataGroup, predicted);
        break;
      case Time.CHART_TYPE:
        view = new Time(dataGroup, predicted);
        break;
    }
    return view;
  }
}
