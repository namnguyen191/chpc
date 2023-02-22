package controller;

import model.RegionDAO;
import model.RegionDAOImplement;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import view.InnerWindow;
import view.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public abstract class View extends JFrame{
	public String chartType;

	static RegionDAO data;


	public View() {
		//go back to mainUI when close the view
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				JFrame frame = MainUI.getInstance();
				frame.setSize(900, 600);
				frame.pack();
				frame.setVisible(true);
				System.out.println("closing");
			}
		});
		data = RegionDAOImplement.getInstance();

	}

	//step1 access data
	public abstract void addDataset();

	//step2 plot view
	public abstract JFreeChart plotView();

	//step3
	public void createChart( ) {
		//1.get data
		addDataset();

		//2.plot the view and add it to ChartPanel
		JFreeChart chart = plotView();
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);




		//3.add config button to main panel
		JButton confiButton = new JButton("Configure");
		confiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confiButton();
			}
		});


		//4.create the main panel for store all component
		JPanel mainPanel = new JPanel( );
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(chartPanel,BorderLayout.CENTER);
		mainPanel.add(confiButton,BorderLayout.SOUTH);


		//5. add main panel to View frame
		setSize(900,700);
		setContentPane(mainPanel);
		setVisible(true);
	}


	public void confiButton(){
		JDesktopPane desktopPane = new JDesktopPane();
		JInternalFrame internalFrame = new InnerWindow(this).getInnerWindow();
		desktopPane.add(internalFrame);
		setContentPane(desktopPane);

		setVisible(true);
	}



}

