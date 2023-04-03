package chpc.visualizations.controllers;

import chpc.visualizations.views.*;

public interface ViewFactory {

  /**
   *
   * @param viewType it specifys the type of visualization
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
