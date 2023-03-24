package chpc.visualizations;

public interface ViewFactory {

  /**
   *
   * @param viewType it specifys the type of  visualization
   * @return a selected visualization
   */
  public static View createView(String viewType) {
    View view = null;

    switch (viewType) {
      case "Bar":
        view = new Bar();
        break;
      case "Line":
        view = new Line();
        break;
      case "Scatter":
        view = new Scatter();
        break;
      case "Time":
        view = new Time();
        break;
    }

    return view;
  }
}
