package chpc.visualizations;

public interface ViewFactory {

  /**
   *
   * @param viewType it specifys the type of visualization
   * @return a selected visualization
   */
  public static View createView(String viewType, String dataGroup) {
    View view = null;

    switch (viewType) {
      case "Bar":
        view = new Bar(dataGroup);
        break;
      case "Line":
        view = new Line(dataGroup);
        break;
      case "Scatter":
        view = new Scatter(dataGroup);
        break;
      case "Time":
        view = new Time(dataGroup);
        break;
    }

    return view;
  }
}
