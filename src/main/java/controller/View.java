package controller;

import model.Region;
import model.RegionDAOImpl;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class View {


	//need to pass in Subject
	//public void update(Subject s);

	//void addDataset(RegionData data);
	public abstract void addDataset(RegionData data) ;
	public abstract  JFreeChart plotView();

	//public abstract void createChart(JPanel west);
	public void createChart(JPanel west) {
		//the chart can be plotted by 1 or more datasets
		addDataset(new RegionData(new RegionDAOImpl()));
		JFreeChart chart = plotView();


		//dispaly bar on the main panel
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}
}

