package chpc.visualizations;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import chpc.dataLoader.NHPIRecord;

public class NHPITable extends JPanel {
  private boolean isRawMode = false;
  private List<NHPIRecord> records;
  private JTable table;
  private JButton loadDataBtn;

  public NHPITable(List<NHPIRecord> records, String title) {
    this.records = records;

    this.table = new JTable();
    this.add(new JScrollPane(this.table));

    this.loadDataBtn = new JButton();
    loadDataBtn.addActionListener(e -> this.toggleButtonClick());
    this.add(loadDataBtn);

    this.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createTitledBorder(title)));

    updateTableModel();
    updateButtonText();
  }

  private void toggleButtonClick() {
    this.isRawMode = !this.isRawMode;
    this.updateTableModel();
    updateButtonText();
  }

  private void updateTableModel() {
    if (this.isRawMode) {
      this.table.setModel(createRawTableModel());
    } else {
      this.table.setModel(createSummaryTableModel());
    }
  }

  private TableModel createRawTableModel() {
    var rawTableModel = new DefaultTableModel();

    String[] columnNames = { "Geo", "Date", "Value" };
    rawTableModel.setColumnIdentifiers(columnNames);

    var df = new DecimalFormat("0.00");
    for (NHPIRecord record : records) {
      String value = record.getValue() == -1 ? "No data" : df.format(record.getValue());
      String[] row = { record.getGeo(), record.getRefDate(), value };
      rawTableModel.addRow(row);
    }
    return rawTableModel;
  }

  private TableModel createSummaryTableModel() {
    var summaryTableModel = new DefaultTableModel();

    String[] columnNames = { "Average", "Standard Deviations", "Min", "Max" };
    summaryTableModel.setColumnIdentifiers(columnNames);

    double[] values = this.records.stream().mapToDouble(r -> r.getValue() == -1 ? 0 : r.getValue()).toArray();
    var df = new DecimalFormat("0.00");

    StandardDeviation sd = new StandardDeviation();

    String avgValue = df.format(StatUtils.mean(values));
    String sdValue = df.format(sd.evaluate(values));
    String minValue = df.format(StatUtils.min(values));
    String maxValue = df.format(StatUtils.max(values));
    String[] row = { avgValue, sdValue, minValue, maxValue };

    summaryTableModel.addRow(row);

    return summaryTableModel;
  }

  private void updateButtonText() {
    if (isRawMode) {
      this.loadDataBtn.setText("View Summary");
    } else {
      this.loadDataBtn.setText("View Raw");
    }
  }
}
