package com.wordmaster.reciteview;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BarChartPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6667465345391011799L;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JPanel operationPanel = new JPanel();

	private String lexicon;
	private int total;
	private int right;
	private int wrong;

	public BarChartPanel(CardLayout parentLayout, JPanel parentPanel) {
		this.cardLayout = parentLayout;
		this.cardPanel = parentPanel;

		this.setLayout(new BorderLayout());

		this.add(operationPanel, BorderLayout.SOUTH);
		JButton jbt_return = new JButton("返回");
		operationPanel.add(jbt_return);

		jbt_return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "stat");
			}

		});

	}

	public void setBarData(String lexicon, int total, int right, int wrong) {
		this.total = total;
		this.right = right;
		this.wrong = wrong;
		this.lexicon = lexicon;

	}

	public void paintBarChart() {
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("单词总数");
		nameList.add("已背数目");
		nameList.add("背诵正确");
		nameList.add("背诵错误");

		ArrayList<Double> valueList = new ArrayList<Double>();
		valueList.add((double) total);
		valueList.add((double) (right + wrong));
		valueList.add((double) right);
		valueList.add((double) wrong);

		String hOrdinate = "背诵情况分述";
		String vOrdinate = "单词（个）";

		String title = "词库" + lexicon + "总体背诵情况柱状图";

		if (this.getComponentCount() == 2)
			this.remove(1);

		JPanel chartPanel = BarChart.genBarChart(nameList, valueList, title,
				hOrdinate, vOrdinate);

		// 貌似只显示第一个 add无法后一个覆盖前一个
		this.add(chartPanel, BorderLayout.CENTER);

	}
}
