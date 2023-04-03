package chpc.UI.views;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import chpc.dataLoader.DataStore;
import chpc.visualizations.views.View;
import chpc.visualizations.controllers.ViewFactory;
import com.google.common.collect.ImmutableMap;

public class Menu extends JMenuBar {
  private View currentView;
  private final ImmutableMap<String, String> CHARTS = ImmutableMap.<String, String>builder()
      .put("Line Chart", "Line")
      .put("Bar Chart", "Bar")
      .put("Scatter Chart", "Scatter")
      .put("Time Series Chart", "Time")
      .build();

  Menu() {
    JMenu viewMenu = new JMenu("View");

    ButtonGroup btnGroup = new ButtonGroup();
    for (var entry : this.CHARTS.entrySet()) {
      String name = entry.getKey();
      String chartType = entry.getValue();
      var menuItem = createMenuItem(name, chartType);
      btnGroup.add(menuItem);
      viewMenu.add(menuItem);
    }

    this.add(viewMenu);
  }

  private JRadioButtonMenuItem createMenuItem(String name, String chartName) {
    JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);

    item.addActionListener(e -> {
      String selectedGroup = VisualizationSelectionWindow
          .getLoadedGeoChoice(DataStore.getInstance().getAllLoadedDataGroups());
      if (this.currentView != null) {
        this.currentView.dispose();
      }
      this.currentView = ViewFactory.createView(chartName, selectedGroup, false);
    });

    return item;
  }
}
