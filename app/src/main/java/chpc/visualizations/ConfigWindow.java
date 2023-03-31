package chpc.visualizations;

import chpc.dataLoader.DataStore;
import chpc.dataLoader.NHPIRecord;
import chpc.dataLoader.NHPIRecordDAO;
import chpc.dataLoader.NHPIRecordDAOImpl;
import chpc.database.Db;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ConfigWindow {

  private static JInternalFrame innerWindow;
  private  View parent;
  private  NHPIRecordDAO recordDAO;



  /**\
   *
   * @param parent it specifys the parent window of the inner window
   */
  public ConfigWindow(View parent) {
    this.recordDAO = new NHPIRecordDAOImpl(Db.getConnection());
    this.parent = parent;
    innerWindow = createInternalFrame();
    innerWindow.addInternalFrameListener(new InternalFrameAdapter() {
      public void internalFrameClosing(InternalFrameEvent e) {
        ViewFactory.createView(parent.chartType);
        // when refresh view frame, the inner window will disappear automatically
      }
    });

  }

  public JInternalFrame getInnerWindow() {
    return innerWindow;
  }

  private  JInternalFrame createInternalFrame() {
    // 创建一个内部窗口
    JInternalFrame internalFrame = new JInternalFrame(
            "configure window", // title
            true, // resizable
            true, // closable
            true, // maximizable
            true // iconifiable
    );

    internalFrame.setSize(600, 400);
    internalFrame.setLocation(50, 50);

    //set the content of configuration window
    Box selectData = Box.createVerticalBox();

    JPanel selectRegion = new JPanel();
    Vector<String> availableGeos;
    try {
      //set all available regions
      availableGeos = new Vector<String>(recordDAO.getAllGeos());
      availableGeos.sort(null);
      JComboBox<String> allRegions = new JComboBox<>(availableGeos);
      JLabel chooseCountryLabel = new JLabel("Choose a region: ");
      selectRegion.add(chooseCountryLabel);
      selectRegion.add(allRegions);

      //set all available time period
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
      for (int i = 1; i <= 12; i++) {
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
        String geo = (String) allRegions.getSelectedItem();
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

      //add all components to Box
      selectData.add(selectRegion);
      selectData.add(selectPeriod);
      selectData.add(loadData);

    } catch (SQLException e) {
      System.out.println("Could select region since query for all available geos failed");
    }

    internalFrame.setContentPane(selectData);
    internalFrame.setVisible(true);

    return internalFrame;
  }


}
