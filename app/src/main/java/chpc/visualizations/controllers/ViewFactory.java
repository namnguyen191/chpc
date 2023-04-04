package chpc.visualizations.controllers;

import chpc.visualizations.views.*;
/**
 * an interface can create instance of View to show different kinds of chart
 */
public interface ViewFactory {

  /**
   * @param viewType it specifies the type of visualization
   * @param dataGroup it specifies the which group of data is used to display on chart
   * @param predicted it specifies if the chart is used to show predicated datq
   * @return a selected visualization
   */
  public static View createView(String viewType, String dataGroup, boolean predicted) {
    View view = null;

    switch (viewType) {
      case "Bar":
        view = new Bar(dataGroup, predicted);
        break;
      case "Line":
        view = new Line(dataGroup, predicted);
        break;
      case "Scatter":
        view = new Scatter(dataGroup, predicted);
        break;
      case "Time":
        view = new Time(dataGroup, predicted);
        break;
    }

    return view;
  }
}
