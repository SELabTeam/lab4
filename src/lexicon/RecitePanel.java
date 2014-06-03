package com.wordmaster.reciteview;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.wordmaster.recitecontrollor.ReciteControllor;

public class RecitePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6001248092648991258L;
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	private JLabel preAnswerLabel = new JLabel();
	private JLabel isCorrectLabel = new JLabel();
	private JLabel ChineseJtx = new JLabel();
	private JTextField EnglishJtx = new JTextField(20);
    private JTextArea statInfo = new JTextArea();
    
    private JButton okButton = new JButton("继续");
	private JButton returnButton = new JButton("返回");
	
	private ReciteControllor controllor;
	
	public RecitePanel(CardLayout parentLayout, JPanel parentPanel){
		
		this.cardLayout = parentLayout;
		this.cardPanel = parentPanel;
		
		BorderLayout bLayout = new BorderLayout();
		this.setLayout(bLayout);

		JPanel answerPanel = new JPanel();
		JPanel operationPanel = new JPanel();
		this.add(answerPanel, BorderLayout.NORTH);
		this.add(operationPanel, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane(statInfo);
		this.add(scrollPane, BorderLayout.CENTER);
		this.statInfo.setEditable(false);
		
		GridLayout gLayout = new GridLayout(5, 2);
		answerPanel.setLayout(gLayout);

		answerPanel.add(new JLabel("前词答案:"));
		answerPanel.add(preAnswerLabel);
		answerPanel.add(new JLabel("回答情况:"));
		answerPanel.add(isCorrectLabel);
		answerPanel.add(new JLabel());
		answerPanel.add(new JLabel());
		answerPanel.add(new JLabel("中文释义:"));
		answerPanel.add(ChineseJtx);
		answerPanel.add(new JLabel("英文拼写:"));
		answerPanel.add(EnglishJtx);

		GridLayout gLayout5 = new GridLayout(1, 2);
		operationPanel.setLayout(gLayout5);
		operationPanel.add(okButton);
		operationPanel.add(returnButton);

		//this.paintInfo();
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String inputWord = EnglishJtx.getText().trim();
				
				controllor.getNextWord(inputWord);
				//cardLayout.show(cardPanel, "recitation");

			}

		});

		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否保存当前背诵情况?",
						"标题", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					controllor.saveCurrent();
				} else {

				}
				cardLayout.show(cardPanel, "begin");

			}

		});
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String signal = arg0.getActionCommand();
		if(signal.equals("update"))
			paintInfo();
		else if(signal.equals("finish")){
			this.statInfo.setText(controllor.getStatInfo());
			this.okButton.setEnabled(false);
		}
		

	}
	
	public void paintInfo(){
		this.preAnswerLabel.setText(this.controllor.getLastWord());
		this.isCorrectLabel.setText(this.controllor.getCurrentStatus());
		
		this.ChineseJtx.setText(this.controllor.getCurrentParaphase());
		this.EnglishJtx.setText("");
	}
	
	public void refresh(){
		this.okButton.setEnabled(true);
		this.statInfo.setText("");
		this.preAnswerLabel.setText("");
		this.isCorrectLabel.setText("");
		this.ChineseJtx.setText("");
		this.EnglishJtx.setText("");
	}

	public void setControllor(ReciteControllor controllor){
		this.controllor = controllor;
	}
}
