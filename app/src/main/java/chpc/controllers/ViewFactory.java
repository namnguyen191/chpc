package chpc.controllers;

import chpc.views.*;

public class ViewFactory {
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
