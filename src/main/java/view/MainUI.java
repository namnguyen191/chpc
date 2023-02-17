/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.*;

import controller.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		// Set window title
		super("House Price Statistics");

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a region: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("Canada");
		countriesNames.add("Atlantic Region");
		countriesNames.add("Quebec");
		countriesNames.add("Ontario");
		countriesNames.add("Prairie Region");
		countriesNames.add("British Columbia");
		countriesNames.sort(null);
		JComboBox<String> countriesList = new JComboBox<>(countriesNames);

		JLabel fromYear = new JLabel("From");
		JLabel toYear = new JLabel("To");
		Vector<String> years = new Vector<>();
		for (int i = 2022; i >= 1981; i--) {
			years.add("" + i);
		}
		JComboBox<String> fromList = new JComboBox<>(years);
		JComboBox<String> toList = new JComboBox<>(years);
		//add month
		JLabel fromMonth = new JLabel();
		JLabel toMonth = new JLabel();
		Vector<String> monthes = new Vector<>();
		for (int i = 12; i >= 1; i--) {
			monthes.add("" + i);
		}
		JComboBox<String> fromListMonthes = new JComboBox<String>(monthes);
		JComboBox<String> toListMonthes = new JComboBox<String>(monthes);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(fromYear);
		north.add(fromList);
		//add month to label
		north.add(fromMonth);
		north.add(fromListMonthes);
		north.add(toYear);
		north.add(toList);
		
		north.add(toMonth);
		north.add(toListMonthes);



		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(800,600));
		west.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		west.setBackground(Color.gray);
		//west.setLayout(new GridLayout(2, 0));
		//create views and add to main panel
		//createCharts(west);

		//choose from a set of predefined visualizations
		JLabel viewsLabel = new JLabel("Available Views: ");
		Vector<String> viewsNames = new Vector<>();
		//viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Time Series Chart");
		//viewsNames.add("Report");
		JComboBox<String> viewsList = new JComboBox<>(viewsNames);
		JButton selectView = new JButton("select");
		//JButton removeView = new JButton("-");
		selectView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if the button is clicked, the following code will be executed
				//getContentPane().remove(MainUI.this);
				west.removeAll();
				west.updateUI();
				String view = (String) viewsList.getSelectedItem();
				System.out.println(view);
				if(view.equals("Line Chart")){
					View line = new Line();
					line.createChart(west);
				}else if(view.equals("Bar Chart")){
					View bar = new Bar();
					bar.createChart(west);
				}else if(view.equals("Time Series Chart")){
					View t = new Time();
					t.createChart(west);
				}else if(view.equals("Scatter Chart")){
					View s = new Scatter();
					s.createChart(west);
				}

				//getContentPane().add(west, BorderLayout.WEST);
				getContentPane().repaint();
			}
		});

		JButton recalculate = new JButton("Recalculate");
		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Mortality");
		methodsNames.add("Mortality vs Expenses");
		methodsNames.add("Mortality vs Expenses & Hospital Beds");
		methodsNames.add("Mortality vs GDP");
		methodsNames.add("Unemployment vs GDP");
		methodsNames.add("Unemployment");

		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(selectView);
		//south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		//JPanel east = new JPanel();



		getContentPane().add(north, BorderLayout.NORTH);
		//getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.CENTER);
	}

//	private void createCharts(JPanel west) {
//		createLine(west);
//		createTimeSeries(west);
//		createBar(west);
//		//createPie(west);
//		createScatter(west);
//		createReport(west);
//
//	}

	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage, reportMessage2;

		reportMessage = "Mortality vs Expenses & Hospital Beds\n" + "==============================\n" + "Year 2018:\n"
				+ "\tMortality/1000 births => 5.6\n" + "\tHealth Expenditure per Capita => 10624\n"
				+ "\tHospital Beds/1000 people => 2.92\n" + "\n" + "Year 2017:\n" + "\tMortality/1000 births => 5.7\n"
				+ "\tHealth Expenditure per Capita => 10209\n" + "\tHospital Beds/1000 people => 2.87\n" + "\n"
				+ "Year 2016:\n" + "\tMortality/1000 births => 5.8\n" + "\tHealth Expenditure per Capita => 9877\n"
				+ "\tHospital Beds/1000 people => 2.77\n";

		reportMessage2 = "Unemployment: Mev vs Women\n" + "==========================\n" + "Men=>\n"
				+ "\tEmployed: 96.054%\n" + "\tUnemployed: 3.946%\n" + "\n" + "Women=>\n" + "\tEmployed: 96.163%\n"
				+ "\tUnemployed: 3.837%\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

//	private void createScatter(JPanel west) {
//
//		Scatter s = new Scatter();
//		s.createChart(west);
//
//	}
//
//	private void createPie(JPanel west) {
//
//		Pie p = new Pie();
//		p.createPie(west);
//
//
//
//	}
//
//	private void createBar(JPanel west) {
//		Bar bar = new Bar();
//
//		bar.createChart(west);
//
//	}
//
//	private void createLine(JPanel west) {
//		Line l = new Line();
//		l.createChart(west);
//
//	}
//
//	private void createTimeSeries(JPanel west) {
//		Time t = new Time();
//		t.createChart(west);
//
//	}



}