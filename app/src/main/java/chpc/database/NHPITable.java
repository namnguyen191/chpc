package chpc.database;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import chpc.dataLoader.NHPIRecord;

public class NHPITable extends JPanel {
  public NHPITable(List<NHPIRecord> records, String title) {
    JTable table = new JTable();

    var tableModel = new DefaultTableModel();

    String[] columnNames = { "Geo", "Date", "Value" };
    tableModel.setColumnIdentifiers(columnNames);

    var df = new DecimalFormat("0.00");
    for (NHPIRecord record : records) {
      String value = record.getValue() == -1 ? "No data" : df.format(record.getValue());
      String[] row = { record.getGeo(), record.getRefDate(), value };
      tableModel.addRow(row);
    }

    table.setModel(tableModel);

    this.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createTitledBorder(title)));
    this.add(new JScrollPane(table));
  }
}
