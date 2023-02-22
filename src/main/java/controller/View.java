package controller;

import model.RegionDAOImpl;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import view.InnerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class View extends JFrame{
	public String type;
	JPanel mainPanel;

	JButton confButton;



	public View(){
	};

	public View(String s) {
		super(s);
	}
	//static View instance;

	//need to pass in Subject
	//public void update(Subject s);

	//void addDataset(RegionData data);
	public abstract void addDataset(RegionData data);
	/**
	 * @return a  chart
	 */
	public abstract  JFreeChart plotView();

	/**
	 * return a new frame
	 */
	public void createChart( ) {
		//1.create a new frame
		setVisible(true);
		setSize(900,700);

		//2.plot the view and add it to ChartPanel
		addDataset(new RegionData(new RegionDAOImpl()));
		JFreeChart chart = plotView();

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);



		//4.create panel for store all component
		mainPanel = new JPanel( );
		mainPanel.setLayout(new BorderLayout());

		//3.set config button
		JButton confiButton = new JButton("Configure");
		confiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				confiButton();

			}
		});

		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(chartPanel,BorderLayout.CENTER);

		mainPanel.add(confiButton,BorderLayout.SOUTH);
		setContentPane(mainPanel);
		setVisible(true);
	}



//	public void refreshWindow() {
//		// refresh the content of the main frame
//		// e.g. update a table, reload data from database, etc.
//	}

	public void confiButton(){
		JDesktopPane desktopPane = new JDesktopPane();
		JInternalFrame internalFrame = new InnerWindow(this).getInnerWindow();
		desktopPane.add(internalFrame);
		setContentPane(desktopPane);

		setVisible(true);
	}
}

