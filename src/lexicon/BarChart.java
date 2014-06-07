package com.wordmaster.reciteview;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {

	public static CategoryDataset createDataset(ArrayList<String> nameList,
			ArrayList<Double> valueList) // 创建柱状图数据集
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Iterator<String> itorN = nameList.iterator();
		Iterator<Double> itorV = valueList.iterator();
		int color = 97;

		while (itorV.hasNext()) {
			dataset.setValue(itorV.next(), String.valueOf((char) (color++)),
					itorN.next());
		}

		return dataset;
	}

	public static JFreeChart createChart(CategoryDataset dataset, String title,
			String hOrdinate, String vOrdinate) // 用数据集创建一个图表
	{
		JFreeChart chart = ChartFactory
				.createBarChart("hi", hOrdinate, vOrdinate, dataset,
						PlotOrientation.VERTICAL, true, true, false); // 创建一个JFreeChart

		chart.setTitle(new TextTitle(title, new Font("宋体", Font.BOLD
				+ Font.ITALIC, 20)));// 可以重新设置标题，替换“hi”标题
		CategoryPlot plot = (CategoryPlot) chart.getPlot();// 获得图标中间部分，即plot
		CategoryAxis categoryAxis = plot.getDomainAxis();// 获得横坐标
		categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));// 设置横坐标字体
		categoryAxis.setTickLabelFont(new Font("微软雅黑", Font.BOLD, 12));// X轴坐标上数值字体
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		ValueAxis valueAxis = plot.getRangeAxis(); // 座標上的文字
		valueAxis.setTickLabelFont(new Font("微软雅黑", Font.BOLD, 12)); // 標題文字
		valueAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));

		BarRenderer barrenderer = new BarRenderer();
		// barrenderer.setMaximumBarWidth(0.7);
		// barrenderer.setMinimumBarLength(1);
		barrenderer.setItemMargin(0.01);
		plot.setRenderer(barrenderer);

		categoryAxis.setLowerMargin(0.1);
		// 设置距离图片右端距离
		categoryAxis.setUpperMargin(0.1);

		return chart;
	}

	public static JPanel genBarChart(ArrayList<String> nameList,
			ArrayList<Double> valueList, String title, String hOrdinate,
			String vOrdinate) {
		CategoryDataset dataSet = createDataset(nameList, valueList);
		JFreeChart chart = createChart(dataSet, title, hOrdinate, vOrdinate);
		return new ChartPanel(chart); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
	}

	public static void main(String[] args) {
		/*
		 * JPanel panel=createPanel(); JFrame frame = new JFrame();
		 * frame.add(panel);
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * //frame.setSize(400, 360); frame.pack();
		 * frame.setLocationRelativeTo(null); frame.setVisible(true);
		 */
	}
}
