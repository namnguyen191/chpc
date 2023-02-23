package chpc.controllers;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import chpc.models.NHPIRecord;

public class NHPITable extends JTable {
  public NHPITable(List<NHPIRecord> records) {
    var tableModel = new DefaultTableModel();

    String[] columnNames = { "Geo", "Date", "Value" };
    tableModel.setColumnIdentifiers(columnNames);

    var df = new DecimalFormat("0.00");
    for (NHPIRecord record : records) {
      String value = record.getValue() == -1 ? "No data" : df.format(record.getValue());
      String[] row = { record.getGeo(), record.getRefDate(), value };
      tableModel.addRow(row);
    }

    this.setModel(tableModel);
  }
}
