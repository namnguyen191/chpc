package chpc.UI;

import chpc.dataLoader.DataStore;
import chpc.dataLoader.NHPIRecord;
import chpc.visualizations.StatisticalTestCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class StatisticalTestPanel extends JPanel {
    private JComboBox<String> testTypeComboBox;
    private JComboBox<String> timeSeries1ComboBox;
    private JComboBox<String> timeSeries2ComboBox;
    private JButton addTimeSeriesButton;
    private JButton loadDataButton;

    public StatisticalTestPanel() {
        setLayout(new FlowLayout());

        String[] testTypes = {"Student's T-test", "Mann-Whitney U test"};
        testTypeComboBox = new JComboBox<>(testTypes);
        add(new JLabel("Select test type:"));
        add(testTypeComboBox);

        timeSeries1ComboBox = new JComboBox<>();
        add(new JLabel("Time-series 1:"));
        add(timeSeries1ComboBox);

        timeSeries2ComboBox = new JComboBox<>();
        add(new JLabel("Time-series 2:"));
        add(timeSeries2ComboBox);

        addTimeSeriesButton = new JButton("Add time-series");
        addTimeSeriesButton.addActionListener(new AddTimeSeriesButtonListener());
        add(addTimeSeriesButton);

        loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(new LoadDataButtonListener());
        add(loadDataButton);

    }

    private class LoadDataButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                ArrayList<String> geos = new ArrayList<>(DataStore.getInstance().getGroupedLoadedData().keySet());
                int size = geos.size();

                for (int i = 0; i < size; i++) {
                    for (int j = i + 1; j < size; j++) {
                        String geo1 = geos.get(i);
                        String geo2 = geos.get(j);

                        Set<NHPIRecord> set1 = DataStore.getInstance().getGroupedLoadedData().get(geo1);
                        Set<NHPIRecord> set2 = DataStore.getInstance().getGroupedLoadedData().get(geo2);

                        double[] timeSeries1 = set1.stream().mapToDouble(NHPIRecord::getValue).toArray();
                        double[] timeSeries2 = set2.stream().mapToDouble(NHPIRecord::getValue).toArray();


                        double pValue;
                        String selectedTest = (String) testTypeComboBox.getSelectedItem();
                        if (selectedTest.equals("Student's T-test")) {
                            pValue = StatisticalTestCalculator.performTTest(timeSeries1, timeSeries2);
                        } else if (selectedTest.equals("Mann-Whitney U test")) {
                            pValue = StatisticalTestCalculator.performMannWhitneyUTest(timeSeries1, timeSeries2);
                        } else {
                            throw new IllegalArgumentException("Invalid test type selected");
                        }

                        double significanceLevel = 0.05;
                        boolean rejectNullHypothesis = StatisticalTestCalculator.canRejectNullHypothesis(pValue, significanceLevel);

                        String resultMessage = String.format("p-value: %.5f\n", pValue);
                        if (rejectNullHypothesis) {
                            resultMessage += "We can reject the null hypothesis.";
                        } else {
                            resultMessage += "We cannot reject the null hypothesis.";
                        }

                        JOptionPane.showMessageDialog(null, resultMessage, "Statistical Test Results", JOptionPane.INFORMATION_MESSAGE);
                    }
                }}
        }

    private class AddTimeSeriesButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new JPanel for the time series line
            JPanel timeSeriesLine = new JPanel(new FlowLayout());

            // Create components for the new time series line
            JLabel timeSeriesLabel3 = new JLabel("Time-series 3");
            JLabel timeSeriesLabel4 = new JLabel("Time-series 4");
            JComboBox<String> timeSeriesComboBox3 = new JComboBox<>();
            JComboBox<String> timeSeriesComboBox4 = new JComboBox<>();

            // Populate the new JComboBox with data
            // timeSeriesComboBox.addItem(" data");

            // Add components to the time series line
            timeSeriesLine.add(timeSeriesLabel3);
            timeSeriesLine.add(timeSeriesComboBox3);

            timeSeriesLine.add(timeSeriesLabel4);
            timeSeriesLine.add(timeSeriesComboBox4);


            // Add the time series line to the panel
            StatisticalTestPanel.this.add(timeSeriesLine);

            // Refresh the panel
            StatisticalTestPanel.this.revalidate();
            StatisticalTestPanel.this.repaint();
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Statistical Test Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new StatisticalTestPanel());
        frame.setSize(600, 200);
        frame.setVisible(true);
    }

}






