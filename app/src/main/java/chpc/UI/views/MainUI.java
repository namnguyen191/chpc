package chpc.UI.views;

import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

import chpc.database.PostgresDb;
import chpc.dataLoader.NHPIRecordDAO;
import chpc.dataLoader.NHPIRecordDAOImpl;

public class MainUI extends JFrame {
  private static MainUI instance;
  private NHPIRecordDAO nhpiDAO;
  public JPanel mainContentPanel;

  public static MainUI getInstance() {
    if (instance == null)
      instance = new MainUI();

    return instance;
  }

  private MainUI() {
    // Set window title
    super("House Price Statistics");

    this.nhpiDAO = new NHPIRecordDAOImpl(PostgresDb.getInstance().getConnection());

    // Set top bar
    Vector<String> availableGeos;
    try {
      availableGeos = new Vector<String>(this.nhpiDAO.getAllGeos());
      availableGeos.sort(null);
      TopBar topBar = new TopBar(this, 1981, 2022, availableGeos);
      this.add(topBar, BorderLayout.NORTH);
    } catch (SQLException e) {
      System.out.println("Could not build Top Bar since query for all available geos failed");
    }

    // Set main content panel
    this.mainContentPanel = new JPanel();
    var scrlpane = new JScrollPane(this.mainContentPanel);
    this.mainContentPanel.setLayout(new GridLayout(2, 0));
    this.add(scrlpane, BorderLayout.CENTER);

    // Set Menu
    this.setJMenuBar(new Menu());

    // Set size, visibility and terminate app on close
    this.setSize(1600, 900);
    this.setVisible(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}