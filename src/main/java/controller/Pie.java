package controller;

import model.Region;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//似乎用不到
public class Pie implements View {
	private RegionData subject;

	public Pie() {
	}

	public Pie(RegionData subject) {
		this.subject = subject;
		subject.attach(this);
	}

	@Override
	public void update(Subject updatedSubject) {
		// TODO Auto-generated method stub
		if(updatedSubject.equals(subject)){
			System.out.println("here is pie "+subject);
		}

	}

	public void createPie(JPanel west) {
		// Different way to create pie chart
		/*
		 * var dataset = new DefaultPieDataset(); dataset.setValue("Unemployed", 3.837);
		 * dataset.setValue("Employed", 96.163);
		 *
		 * JFreeChart pieChart = ChartFactory.createPieChart("Women's Unemployment",
		 * dataset, true, true, false);
		 */
		RegionData data = new RegionData();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Region> regions = data.getData();
		Region r = regions.get(0);
		dataset.setValue(r.getNHPI(),r.getRegionName(),r.getPeriod());
//		dataset.addValue(3.946, "Unemployed", "Men");
//		dataset.addValue(96.054, "Employed", "Men");
//		dataset.addValue(3.837, "Unemployed", "Women");
//		dataset.addValue(96.163, "Employed", "Women");

		String title = "New housing price index (NHPI): " + "Toronto vs North York";
		JFreeChart pieChart = ChartFactory.createMultiplePieChart(title, dataset,
				TableOrder.BY_COLUMN, true, true, false);

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}


}
