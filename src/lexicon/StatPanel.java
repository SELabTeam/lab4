package com.wordmaster.reciteview;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wordmaster.lexicon.Lexicon;
import com.wordmaster.utils.Accuracy;
import com.wordmaster.utils.StatisticManager;

public class StatPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3235604515482282790L;
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private PieChartPanel piePanel;
	private BarChartPanel barPanel;
	
	private JLabel jlbl_total=new JLabel();
	private JLabel jlbl_completed = new JLabel();
	private JLabel jlbl_right = new JLabel();
	private JLabel jlbl_wrong = new JLabel();
	
	private JButton jbt_pie=new JButton("查看饼图");
	private JButton jbt_bar=new JButton("查看柱状图");
	
	private JButton jbt_return = new JButton("返回");
	
	private String lexicon;
	private int total;
	private int completed;
	private int right;
	private int wrong;
	
	
	public StatPanel(CardLayout parentLayout, JPanel parentPanel,PieChartPanel piePanel_Param,BarChartPanel barPanel_Param){
		this.cardLayout = parentLayout;
		this.cardPanel = parentPanel;
		this.piePanel = piePanel_Param;
		this.barPanel = barPanel_Param;
		
		BorderLayout bLayout = new BorderLayout();
		this.setLayout(bLayout);
		JPanel statInfoPanel = new JPanel(new GridLayout(6,3));
		JPanel operationPanel = new JPanel();
		this.add(statInfoPanel, BorderLayout.NORTH);
		this.add(operationPanel, BorderLayout.SOUTH);
		
		
		statInfoPanel.add(new JLabel("单词总量:"));
		statInfoPanel.add(new JLabel());
		statInfoPanel.add(jlbl_total);
		statInfoPanel.add(new JLabel("已背单词:"));
		statInfoPanel.add(new JLabel());
		statInfoPanel.add(jlbl_completed);
		statInfoPanel.add(new JLabel("正确背诵:"));
		statInfoPanel.add(new JLabel());
		statInfoPanel.add(jlbl_right);
		statInfoPanel.add(new JLabel("错误背诵:"));
		statInfoPanel.add(new JLabel());
		statInfoPanel.add(jlbl_wrong);
		statInfoPanel.add(new JLabel());
		statInfoPanel.add(new JLabel());
		statInfoPanel.add(new JLabel());
		
		statInfoPanel.add(jbt_pie);
		statInfoPanel.add(new JLabel());
		statInfoPanel.add(jbt_bar);
		
		operationPanel.add(jbt_return);

		
		
		jbt_pie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    piePanel.setPieData(lexicon,total,right,wrong);
				piePanel.paintPieChart();
				cardLayout.show(cardPanel, "pie");

			}

		});
		
		
		jbt_bar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				barPanel.setBarData(lexicon,total,right,wrong);
				barPanel.paintBarChart();
				cardLayout.show(cardPanel,"bar");

			}

		});
		
		
		jbt_return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "ready");

			}

		});
		
		
	}
	
	public void getStatInfo() {
		
		/*should be modified using controller*/
		Lexicon lex=Lexicon.getInstance();
		Accuracy accy=StatisticManager.getCurrentAccuracy(lexicon);
		if(accy!=null){
		right=accy.getRightCount();
		wrong=accy.getWrongCount();
		completed=right+wrong;
		}else{
			right=0;
			wrong=0;
			completed=0;
		}
		
		total=lex.leftCount(lexicon,null);
		
	}
	
	
	public void paintInfo(){
		this.jlbl_total.setText(String.valueOf(total));
		this.jlbl_completed.setText(String.valueOf(completed));
		this.jlbl_right.setText(String.valueOf(right));
		this.jlbl_wrong.setText(String.valueOf(wrong));
	}
	
	

	public String getLexicon() {
		return lexicon;
	}

	public void setLexicon(String lexicon) {
		//System.out.println("set lexicon "+lexicon);
		this.lexicon = lexicon;
	}
	
	
	
		
	
}
