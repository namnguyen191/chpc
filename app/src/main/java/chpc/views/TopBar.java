package chpc.views;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import chpc.controllers.AddPanelCommand;
import chpc.models.DataStore;
import chpc.controllers.NHPITable;
import chpc.models.Db;
import chpc.models.NHPIRecord;
import chpc.models.NHPIRecordDAO;
import chpc.models.NHPIRecordDAOImpl;
import chpc.weka.InstancesFactory;
import chpc.weka.ModelEvaluator;
import chpc.weka.ModelTrainer;
import weka.core.Instances;

public class TopBar extends JPanel {
  private NHPIRecordDAO recordDAO;
  private MainUI mainUI;
  private JComboBox<String> countriesComboBox;
  private JComboBox<Integer> fromYearComboBox;
  private JComboBox<Integer> fromMonthComboBox;
  private JComboBox<Integer> toYearComboBox;
  private JComboBox<Integer> toMonthComboBox;
  private DataStore dataStore;

  public TopBar(MainUI mainUI, int minYear, int maxYear, Vector<String> availableGeos) {
    this.recordDAO = new NHPIRecordDAOImpl(Db.getConnection());
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
    this.fromMonthComboBox = new JComboBox<Integer>(months);
    this.add(fromLabel);
    this.add(this.fromYearComboBox);
    this.add(this.fromMonthComboBox);

    // To ComboBoxes
    JLabel toLabel = new JLabel("To");
    this.toYearComboBox = new JComboBox<Integer>(years);
    this.toMonthComboBox = new JComboBox<Integer>(months);
    this.add(toLabel);
    this.add(toYearComboBox);
    this.add(this.toMonthComboBox);

    // Load Data button
    JButton loadDataBtn = new JButton("Load Data");
    loadDataBtn.addActionListener(e -> this.onLoadDataClick());
    this.add(loadDataBtn);

    JButton predictModelBtn = new JButton("Predict Model");
    predictModelBtn.addActionListener(e -> this.onPredictModelClick());
    this.add(predictModelBtn);

    // Set background to gray
    this.setBackground(Color.GRAY);
  }

  private void onLoadDataClick() {
    String geo = (String) this.countriesComboBox.getSelectedItem();
    int fromYear = (int) this.fromYearComboBox.getSelectedItem();
    int fromMonth = (int) this.fromMonthComboBox.getSelectedItem();
    int toYear = (int) this.toYearComboBox.getSelectedItem();
    int toMonth = (int) this.toMonthComboBox.getSelectedItem();
    try {
      var records = this.recordDAO.getRecordsByGeoAndDateRange(geo, fromYear, fromMonth, toYear, toMonth);
      this.dataStore.loadData(records);
      String tableTitle = "Data for " + geo + " from " + fromYear + "-" + fromMonth + " to " + toYear + "-" + toMonth;

      var table = new NHPITable(records, tableTitle);
      new AddPanelCommand(mainUI, table).execute();
    } catch (SQLException e1) {
      System.out.println("Something went wrong fetching data: " + e1.getMessage());
    }
  }

  private void onPredictModelClick(){
    String geo = (String) this.countriesComboBox.getSelectedItem();
    int fromYear = (int) this.fromYearComboBox.getSelectedItem();
    int fromMonth = (int) this.fromMonthComboBox.getSelectedItem();
    int toYear = (int) this.toYearComboBox.getSelectedItem();
    int toMonth = (int) this.toMonthComboBox.getSelectedItem();
    try {
      List<NHPIRecord> records = this.recordDAO.getRecordsByGeoAndDateRange(geo, fromYear, fromMonth, toYear, toMonth);
      InstancesFactory instancesFactory = new InstancesFactory();
      Instances data = instancesFactory.createInstances(records);
      ModelTrainer mTrainer = new ModelTrainer();
      mTrainer.buildModel(data);
      //ModelEvaluator mEvaluator = new ModelEvaluator();
      //mEvaluator.evaluate(mTrainer.getModel(), data);
      mTrainer.getNext(data);
    } catch(SQLException e1){
      System.out.println("Something went wrong fetching data: " + e1.getMessage());
    }
  }
}
