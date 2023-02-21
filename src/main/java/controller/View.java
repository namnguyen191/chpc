package controller;

import model.Region;
import model.RegionDAOImpl;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import static com.sun.java.accessibility.util.SwingEventMonitor.addInternalFrameListener;


public abstract class View extends JFrame{
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

//				System.out.println("confi");
//				JDesktopPane desktopPane = new JDesktopPane();
//				JInternalFrame internalFrame = createInternalFrame(mainPanel);
//				//internalFrame.updateUI();
//				//internalFrame.setLayer();
//				desktopPane.add(internalFrame);
//				setContentPane(desktopPane);
//				try {
//
//					internalFrame.setSelected(true);
//				} catch (PropertyVetoException ex) {
//					ex.printStackTrace();
//				}
////						desktopPane.add(internalFrame);
//				//setVisible(false);
//
//				setVisible(true);
				confiButton();

			}
		});

		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(chartPanel,BorderLayout.CENTER);

		mainPanel.add(confiButton,BorderLayout.SOUTH);
		setContentPane(mainPanel);
		setVisible(true);
	}

//	private JInternalFrame createInternalFrame(JPanel mainPanel ) {
//		// inner frame
//		JInternalFrame internalFrame = new JInternalFrame(
//				"configure window",  // title
//				true,       // resizable
//				true,       // closable
//				true,       // maximizable
//				true        // iconifiable
//		);
//
//
//		internalFrame.setSize(500, 400);
//		internalFrame.setLocation(50, 50);
//
//		JPanel panel = new JPanel();
//
//		JButton button1 = new JButton("Cancel");
//		JButton button2 = new JButton("Load Data");
//		button1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// handle button 1 click
//				//mainPanel.updateUI();
//				dispose();
//			}
//		});
//		button2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// handle button 2 click
//			}
//		});
//		panel.add(button1);
//		panel.add(button2);
//
//		internalFrame.setContentPane(panel);
//
//		/*
//		 * another way to set up panel
//		 *     internalFrame.setLayout(new FlowLayout());
//		 *     internalFrame.add(new JLabel("Label001"));
//		 *     internalFrame.add(new JButton("JButton001"));
//		 */
//
//		internalFrame.setVisible(true);
//
//		addInternalFrameListener(new InternalFrameAdapter() {
//			public void internalFrameClosing(InternalFrameEvent e) {
//				//parent.repaint();
//				//mainPanel.updateUI();
//				//View.super.repaint();
//				dispose();
//
//			}
//		});
//
//		return internalFrame;
//	}

	public void refreshWindow() {
		// refresh the content of the main frame
		// e.g. update a table, reload data from database, etc.
	}

	public void confiButton(){
		System.out.println("confi");
		JDesktopPane desktopPane = new JDesktopPane();
		JInternalFrame internalFrame = new InnerWindow(this).getInnerWindow();
		//internalFrame.updateUI();
		//internalFrame.setLayer();
		desktopPane.add(internalFrame);
		setContentPane(desktopPane);
//		try {
//
//			internalFrame.setSelected(true);
//		} catch (PropertyVetoException ex) {
//			ex.printStackTrace();
//		}
//						desktopPane.add(internalFrame);
		//setVisible(false);

		setVisible(true);
	}
}

