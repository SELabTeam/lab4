package com.wordmaster.reciteview;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.*;

public class PieChart {

	public static void main(String[] args) {
		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
		dpd.setValue("管理人员", 25); // 输入数据
		dpd.setValue("市场人员", 25);
		dpd.setValue("开发人员", 45);
		dpd.setValue("其他人员", 10);

		JFreeChart chart = ChartFactory.createPieChart("某公司人员组织数据图", dpd, true,
				true, false);
		// 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL

		JPanel panel = new ChartPanel(chart);
		// ChartFrame chartFrame=new ChartFrame("某公司人员组织数据图",chart);
		// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
		// chartFrame.pack(); //以合适的大小展现图形
		// chartFrame.setVisible(true);//图形是否可见

		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 360);
		// frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static JPanel genBarChart(ArrayList<String> nameList,
			ArrayList<Double> valueList, String title) {
		if (nameList.size() != valueList.size())
			return null;

		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
		Iterator<String> itorN = nameList.iterator();
		Iterator<Double> itorV = valueList.iterator();

		while (itorV.hasNext()) {
			dpd.setValue(itorN.next(), itorV.next());
		}

		JFreeChart chart = ChartFactory.createPieChart(title, dpd, true, true,
				false);
		JPanel panel = new ChartPanel(chart);

		return panel;
	}
}
