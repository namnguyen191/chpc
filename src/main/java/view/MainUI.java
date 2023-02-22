/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;
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

import static javax.swing.JLayeredPane.MODAL_LAYER;

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
		//region selection
		JTextField region = new JTextField(8);
		//textField.getText()
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a region: ");
//		Vector<String> countriesNames = new Vector<>();
//		countriesNames.add("Canada");
//		countriesNames.add("Atlantic Region");
//		countriesNames.add("Quebec");
//		countriesNames.add("Ontario");
//		countriesNames.add("Prairie Region");
//		countriesNames.add("British Columbia");
//		countriesNames.sort(null);
//		JComboBox<String> countriesList = new JComboBox<>(countriesNames);

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

		JButton loadData = new JButton("Load Data");
		loadData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String area = region.getText();
				String from_month = (String) fromListMonthes.getSelectedItem();
				String to_month = (String) toListMonthes.getSelectedItem();
				String from_year = (String) fromList.getSelectedItem();
				String to_year = (String) toList.getSelectedItem();
				String from = "";
				if(from_month.length() == 1){
					from = from_year +"-0"+from_month;
				}else {
					 from = from_year +"-"+from_month;
				}
				String end;
				if(to_month.length() == 1){
					 end = to_year +"-0"+to_month;
				}else {
					 end = to_year +"-"+to_month;
				}


				System.out.printf("region: %s + from: %s to %s",area,from,end);

			}
		});
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		//north.add(countriesList);
		north.add(region);
		north.add(fromYear);
		north.add(fromList);
		//add month to label
		north.add(fromMonth);
		north.add(fromListMonthes);
		north.add(toYear);
		north.add(toList);
		north.add(toMonth);
		north.add(toListMonthes);
		north.add(loadData);


		//set view menus

		//add menu to main frame
		setJMenuBar(createMenuBar());


		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(800,600));
		west.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		west.setBackground(Color.gray);
		//west.setLayout(new GridLayout(2, 0));
		//create views and add to main panel
		//createCharts(west);




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


		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		//JPanel east = new JPanel();



		getContentPane().add(north, BorderLayout.NORTH);
		//getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.CENTER);
		setVisible(true);
	}



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


	private JInternalFrame createInternalFrame() {
		// inner frame
		JInternalFrame internalFrame = new JInternalFrame(
				"Configure Window",  // title
				true     // resizable

		);

		internalFrame.setSize(500, 400);
		internalFrame.setLocation(50, 50);

		JPanel panel = new JPanel();

		panel.add(new JLabel("Label001"));
		panel.add(new JButton("JButton001"));

		internalFrame.setContentPane(panel);

		/*
		 * another way to set up panel
		 *     internalFrame.setLayout(new FlowLayout());
		 *     internalFrame.add(new JLabel("Label001"));
		 *     internalFrame.add(new JButton("JButton001"));
		 */

		internalFrame.setVisible(true);

		return internalFrame;
	}


	public JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu viewMenu = new JMenu("View");
//		JMenuItem line = new JMenuItem("Line Chart");
//		JMenuItem bar = new JMenuItem("Bar Chart");
//		JMenuItem scatter = new JMenuItem("Scatter Chart");
//		JMenuItem time = new JMenuItem("Time Series Chart");

		JRadioButtonMenuItem line = new JRadioButtonMenuItem("Line Chart");
		JRadioButtonMenuItem bar = new JRadioButtonMenuItem("Bar Chart");
		JRadioButtonMenuItem scatter = new JRadioButtonMenuItem("Scatter Chart");
		JRadioButtonMenuItem time = new JRadioButtonMenuItem("Time Series Chart");
		//1.set radio option, then we can only choose one view at same time
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(line);
		btnGroup.add(bar);
		btnGroup.add(scatter);
		btnGroup.add(time);
		//2.add all items to view menu
		viewMenu.add(line);
		viewMenu.add(bar);
		viewMenu.add(scatter);
		viewMenu.add(time);
		//3.add menu to the bar
		menuBar.add(viewMenu);

		//4. add action for each items(views)
		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				View l = Line.getInstance();
				l.createChart();

			}
		});

		bar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				View b = Bar.getInstance();
				b.createChart( );
			}
		});

		scatter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				View s = Scatter.getInstance();
				s.createChart();
			}
		});
		time.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				View t = Time.getInstance();
				t.createChart();
			}
		});
		return menuBar;
	}
}