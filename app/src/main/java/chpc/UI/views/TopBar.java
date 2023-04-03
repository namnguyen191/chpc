package chpc.UI.views;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.showMessageDialog;

import chpc.dataLoader.NHPITable;
import chpc.UI.controllers.AddPanelCommand;
import chpc.dataLoader.DataStore;
import chpc.database.PostgresDb;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.Pair;

import chpc.dataLoader.NHPIRecord;
import chpc.prediction.PopUpWindow;
import chpc.visualizations.controllers.ViewFactory;
import chpc.visualizations.views.View;
import chpc.prediction.RecordProcessor;
import chpc.prediction.WekaModule;
import chpc.dataLoader.NHPIRecordDAO;
import chpc.dataLoader.NHPIRecordDAOImpl;
import chpc.dataLoader.GeoFromTo;

/**
 * A class used to create panel used to choose the region and time series
 */
public class TopBar extends JPanel {
  private NHPIRecordDAO recordDAO;
  private MainUI mainUI;
  private JComboBox<String> countriesComboBox;
  private JComboBox<Integer> fromYearComboBox;
  private JComboBox<Integer> fromMonthComboBox;
  private JComboBox<Integer> toYearComboBox;
  private JComboBox<Integer> toMonthComboBox;
  private DataStore dataStore;
  private View currentView;
  private int maxYear;

  /**
   *
   * @param mainUI it is the window where the TopBar want to be added to
   * @param minYear it is the minimum year that can be selected for time series
   * @param maxYear it is the maximum year that can be selected for time series
   * @param availableGeos is the set of regions  that can be selected
   */
  public TopBar(MainUI mainUI, int minYear, int maxYear, Vector<String> availableGeos) {
    this.maxYear = maxYear;
    this.recordDAO = new NHPIRecordDAOImpl(PostgresDb.getInstance().getConnection());
    this.dataStore = DataStore.getInstance();
    this.mainUI = mainUI;

    // Countries ComboBox
    JLabel chooseCountryLabel = new JLabel("Choose a region: ");
    this.countriesComboBox = new JComboBox<String>(availableGeos);
    this.add(chooseCountryLabel);
    this.add(this.countriesComboBox);

    var years = new Vector<Integer>();
    for (int i = maxYear; i >= minYear; i--) {
      years.add(i);
    }

    var months = new Vector<Integer>();
    for (int i = 1; i <= 12; i++) {
      months.add(i);
    }

    // From ComboBoxes
    JLabel fromLabel = new JLabel("From");
    this.fromYearComboBox = new JComboBox<Integer>(years);
    this.fromYearComboBox.addActionListener(e -> this.setToDateBound());
    this.fromMonthComboBox = new JComboBox<Integer>(months);
    this.fromMonthComboBox.addActionListener(e -> this.setToDateBound());
    this.add(fromLabel);
    this.add(this.fromYearComboBox);
    this.add(this.fromMonthComboBox);

    // To ComboBoxes
    JLabel toLabel = new JLabel("To");
    this.toYearComboBox = new JComboBox<Integer>(years);
    this.toMonthComboBox = new JComboBox<Integer>(months);
    this.add(toLabel);
    this.add(this.toYearComboBox);
    this.add(this.toMonthComboBox);
    this.setToDateBound();

    // Load Data button
    JButton loadDataBtn = new JButton("Load Data");
    loadDataBtn.addActionListener(e -> this.onLoadDataClick());
    this.add(loadDataBtn);

    JButton statsBtn = new JButton("Statistics");
    statsBtn.addActionListener(e -> this.onStatsClick());
    this.add(statsBtn);

    JButton predictModelBtn = new JButton("Predict Model");
    predictModelBtn.addActionListener(e -> this.onPredictModelClick());
    this.add(predictModelBtn);

    JButton predictChartBtn = new JButton("Predict Graph");
    predictChartBtn.addActionListener(e -> this.onPredictChartClick());
    this.add(predictChartBtn);

    // Set background to gray
    this.setBackground(Color.GRAY);
  }

  /**
   * it specifies the event after clicking the button. This event is loading data based on the choice
   */
  private void onLoadDataClick() {
    String geo = (String) this.countriesComboBox.getSelectedItem();
    int fromYear = (int) this.fromYearComboBox.getSelectedItem();
    int fromMonth = (int) this.fromMonthComboBox.getSelectedItem();
    int toYear = (int) this.toYearComboBox.getSelectedItem();
    int toMonth = (int) this.toMonthComboBox.getSelectedItem();
    try {
      GeoFromTo geoFromTo = new GeoFromTo(geo, fromYear + " " + fromMonth, toYear + " " + toMonth);
      if (dataStore.checkGroupAlreadyLoaded(geoFromTo.toString())) {
        showMessageDialog(null, "This data has already been loaded");
        return;
      }
      var records = this.recordDAO.getRecordsByGeoAndDateRange(geo, fromYear, fromMonth, toYear, toMonth);

      var table = new NHPITable(records, geoFromTo.toString());
      new AddPanelCommand(mainUI, table).execute();

      this.dataStore.loadGeoFromTos(geoFromTo);
      RecordProcessor.sortRecords(records);
      this.dataStore.loadData(geoFromTo.toString(), records);
    } catch (SQLException e1) {
      System.out.println("Something went wrong fetching data: " + e1.getMessage());
    }
  }

  /**
   * it is used to restrict the user from selecting a "TO" that is smaller than "FROM"
   */
  private void setToDateBound() {
    int fromYear = (int) this.fromYearComboBox.getSelectedItem();
    int fromMonth = (int) this.fromMonthComboBox.getSelectedItem();
    int toYear = (int) this.toYearComboBox.getSelectedItem();
    int toMonth = (int) this.toMonthComboBox.getSelectedItem();

    var yearsBound = new Vector<Integer>();
    for (int i = maxYear; i >= fromYear; i--) {
      yearsBound.add(i);
    }
    this.toYearComboBox.setModel(new DefaultComboBoxModel<>(yearsBound));

    var monthsBound = new Vector<Integer>();
    for (int i = fromMonth; i <= 12; i++) {
      monthsBound.add(i);
    }
    this.toMonthComboBox.setModel(new DefaultComboBoxModel<>(monthsBound));

    if (toYear < fromYear) {
      this.toYearComboBox.setSelectedItem(fromYear);

      if (toMonth < fromMonth) {
        this.toMonthComboBox.setSelectedItem(fromMonth);
      } else {
        this.toMonthComboBox.setSelectedItem(toMonth);
      }
    } else {
      this.toYearComboBox.setSelectedItem(toYear);
      this.toMonthComboBox.setSelectedItem(toMonth);
    }
  }

  /**
   * it specifies the event after clicking the button. This event is predicting future house price
   */
  private void onPredictModelClick() {
    Set<String> loadedGeos = this.dataStore.getAllLoadedDataGroups();
    if (loadedGeos.isEmpty()) {
      PopUpWindow.showResult("No regions loaded!");
    } else {
      String paramSet = PopUpWindow.getChoiceWindow(dataStore.getAllLoadedDataGroups());
      List<NHPIRecord> regions = new ArrayList<NHPIRecord>(dataStore.getLoadedDataForGroup(paramSet));
      if (regions.size() <= 3) {
        PopUpWindow.showResult("Not enough entries chosen!");
      } else {
        String[] predictionMethods = { "Linear Regression" };
        Pair<String, Integer> predictionParams = PopUpWindow.getPredictionChoice(predictionMethods);

        String title = PopUpWindow.getTitleWindow();

        List<NHPIRecord> records = regions;
        WekaModule mlModule = new WekaModule();
        var result = mlModule.MLFunc(records, predictionParams.getSecond());
        RecordProcessor.addPredictedValues(records, records.get(0).getGeo(), result);
        this.dataStore.loadPrediction(title, records);

        String tableTitle = title;
        var table = new NHPITable(records, tableTitle);
        new AddPanelCommand(mainUI, table).execute();
      }
    }
  }

  /**
   * it specifies the event after clicking the button. This event is provided for the statistical comparison
   */
  private void onStatsClick() {
    Set<String> options = dataStore.getAllLoadedDataGroups();
    if (options.size() < 2) {
      PopUpWindow.showResult("Please load at least 2 regions' data sets to compare their means: ");
    } else {
      String paramSet1 = PopUpWindow.getChoiceWindow(dataStore.getAllLoadedDataGroups());
      List<NHPIRecord> regions1 = dataStore.getLoadedDataForGroup(paramSet1);

      options.remove(paramSet1);

      String paramSet2 = PopUpWindow.getChoiceWindow(options);
      List<NHPIRecord> regions2 = dataStore.getLoadedDataForGroup(paramSet2);
      SummaryStatistics statsObject1 = new SummaryStatistics();
      SummaryStatistics statsObject2 = new SummaryStatistics();
      int i = 0;
      for (NHPIRecord record : regions1) {
        statsObject1.addValue(record.getValue());
        i++;
      }

      i = 0;
      for (NHPIRecord record : regions2) {
        statsObject2.addValue(record.getValue());
        i++;
      }

      double mean1 = statsObject1.getMean();
      double mean2 = statsObject2.getMean();
      String result;
      if (mean1 != mean2) {
        result = "T-Test: the mean is not the same.\n Null Hypothesis is false.";
      } else {
        result = "T-Test: the mean is the same.\n Null Hypothesis is true.";
      }
      PopUpWindow.showResult(result);
    }
  }

  /**
   * it specifies the event after clicking the button. This event is provided for showing the predicted data by a chart
   */

  public void onPredictChartClick() {
    String predictionTitle = PopUpWindow.getChoiceWindow(dataStore.getPredictionTitles());
    String chartType = PopUpWindow.getViewChoiceWindow();
    if (this.currentView != null) {
      this.currentView.dispose();
    }
    this.currentView = ViewFactory.createView(chartType, predictionTitle, true);
  }
}
