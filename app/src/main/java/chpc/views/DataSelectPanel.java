package chpc.views;

import javax.swing.*;

import chpc.models.DataStore;
import chpc.controllers.ViewFactory;
import chpc.models.Db;
import chpc.models.NHPIRecord;
import chpc.models.NHPIRecordDAO;
import chpc.models.NHPIRecordDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DataSelectPanel {
  static Box selectData;
  private View parent;
  private NHPIRecordDAO recordDAO;

  public DataSelectPanel(View parent) {
    this.recordDAO = new NHPIRecordDAOImpl(Db.getConnection());
    selectData = Box.createVerticalBox();
    this.parent = parent;
    setBox();
  }

  public void setBox() {
    JPanel selectRegion = new JPanel();
    JTextField region = new JTextField(10);
    // textField.getText()
    // Set top bar
    JLabel chooseCountryLabel = new JLabel("Choose a region: ");
    selectRegion.add(chooseCountryLabel);
    selectRegion.add(region);

    JPanel selectPeriod = new JPanel();
    JLabel fromYear = new JLabel("From");
    JLabel toYear = new JLabel("To");
    Vector<Integer> years = new Vector<>();
    for (int i = 2022; i >= 1981; i--) {
      years.add(i);
    }
    JComboBox<Integer> fromList = new JComboBox<>(years);
    JComboBox<Integer> toList = new JComboBox<>(years);
    // add month
    JLabel fromMonth = new JLabel();
    JLabel toMonth = new JLabel();
    Vector<Integer> monthes = new Vector<>();
    for (int i = 12; i >= 1; i--) {
      monthes.add(i);
    }
    JComboBox<Integer> fromListMonthes = new JComboBox<>(monthes);
    JComboBox<Integer> toListMonthes = new JComboBox<>(monthes);
    selectPeriod.add(fromYear);
    selectPeriod.add(fromList);
    selectPeriod.add(fromMonth);
    selectPeriod.add(fromListMonthes);
    selectPeriod.add(toYear);
    selectPeriod.add(toList);
    selectPeriod.add(toMonth);
    selectPeriod.add(toListMonthes);

    JButton loadData = new JButton("Load Data");
    loadData.addActionListener(e -> {
      String geo = region.getText();
      int from_month = (int) fromListMonthes.getSelectedItem();
      int to_month = (int) toListMonthes.getSelectedItem();
      int from_year = (int) fromList.getSelectedItem();
      int to_year = (int) toList.getSelectedItem();
      try {
        List<NHPIRecord> records = this.recordDAO.getRecordsByGeoAndDateRange(geo, from_year, from_month, to_year,
            to_month);
        DataStore.getInstance().loadData(records);
      } catch (SQLException e1) {
        System.out.println("Something went wrong fetching new data");
      } finally {
        if (parent != null) {
          ViewFactory.createView(parent.chartType);
          parent.dispose();
        }
      }
    });

    selectData.add(selectRegion);
    selectData.add(selectPeriod);
    selectData.add(loadData);

    selectData.add(loadData);
  }

  public Box getSelectPanel() {
    return selectData;
  }

}
