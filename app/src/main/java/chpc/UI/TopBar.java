package chpc.UI;

import java.awt.Color;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.showMessageDialog;

import chpc.dataLoader.DataStore;
import chpc.database.PostgresDb;
import chpc.visualizations.NHPITable;
import chpc.dataLoader.NHPIRecordDAO;
import chpc.dataLoader.NHPIRecordDAOImpl;

public class TopBar extends JPanel {
  private NHPIRecordDAO recordDAO;
  private MainUI mainUI;
  private JComboBox<String> countriesComboBox;
  private JComboBox<Integer> fromYearComboBox;
  private JComboBox<Integer> fromMonthComboBox;
  private JComboBox<Integer> toYearComboBox;
  private JComboBox<Integer> toMonthComboBox;
  private DataStore dataStore;
  private int maxYear;

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
      String groupName = geo + " from " + fromYear + "-" + fromMonth + " to " + toYear + "-" + toMonth;
      if (dataStore.checkGroupAlreadyLoaded(groupName)) {
        showMessageDialog(null, "This data has already been loaded");
        return;
      }
      var records = this.recordDAO.getRecordsByGeoAndDateRange(geo, fromYear, fromMonth, toYear, toMonth);
      this.dataStore.loadData(groupName, records);

      var table = new NHPITable(records, groupName);
      new AddPanelCommand(mainUI, table).execute();
    } catch (SQLException e1) {
      System.out.println("Something went wrong fetching data: " + e1.getMessage());
    }
  }

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
}
