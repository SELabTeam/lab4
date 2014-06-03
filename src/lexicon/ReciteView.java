package com.wordmaster.reciteview;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.wordmaster.recitecontrollor.ReciteControllor;
import com.wordmaster.recitemodel.ReciteFactory;
import com.wordmaster.recitemodel.ReciteSession;

public class ReciteView extends JFrame {

	private static final long serialVersionUID = -6144329855342711416L;

	private CardLayout cardLayout = new CardLayout(10, 10);
	private JPanel cardPanel = new JPanel(cardLayout);

	private JPanel lexiconPanel = new JPanel();
	private JPanel readyPanel = new JPanel();
	private JPanel beginPanel = new JPanel();
	private RecitePanel recitationPanel = new RecitePanel(cardLayout, cardPanel);
	private JPanel statPanel = new JPanel();
	
	private ReciteSession model = null;
	private ReciteControllor controllor = null;
	
	private String lexicon;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				constructUI();
			}
		});

	}

	private static void constructUI() {

		ReciteView frame = new ReciteView();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public ReciteView() {
		drawCardPanel();
		this.add(cardPanel);
	}

	private void drawCardPanel() {
		drawFiveSubPanel();

		cardPanel.add(lexiconPanel, "lexicon");
		cardPanel.add(readyPanel, "ready");
		cardPanel.add(beginPanel, "begin");
		cardPanel.add(recitationPanel, "recitation");
		cardPanel.add(statPanel, "stat");
	}

	private void drawFiveSubPanel() {
		drawLexiconPanel();
		drawReadyPanel();
		drawBeginPanel();
		//drawRecitationPanel();
		drawStatPanel();
	}

	private void drawLexiconPanel() {
		GridLayout gLayout = new GridLayout(6, 5);
		lexiconPanel.setLayout(gLayout);
		// JButton[] lexiconButtons=new JButton[30];
		List<JButton> buttonList = new ArrayList<JButton>();
		for (int i = 65; i < 91; i++) {
			buttonList.add(new JButton(String.valueOf((char) (i))));
		}

		Iterator<JButton> itor = buttonList.iterator();
		while (itor.hasNext()) {
			JButton button = itor.next();
			lexiconPanel.add(button);

			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String currentLexicon = ((JButton) e.getSource()).getText();
					((JButton) readyPanel.getComponent(0)).setText("当前词库:  "
							+ currentLexicon);

					lexicon = currentLexicon;
					// pass currentLexicon to backstage to do something
					cardLayout.show(cardPanel, "ready");

				}

			});

		}
	}

	private void drawReadyPanel() {
		// BorderLayout bLayout=new BorderLayout(70,60);
		GridLayout gLayout = new GridLayout(3, 2, 5, 5);
		readyPanel.setLayout(gLayout);

		JButton lexicon = new JButton();
		lexicon.setEnabled(false);
		
		JButton beginButton = new JButton("开始背诵");
		JButton statButton = new JButton("统计信息");
		JButton returnButton = new JButton("返回");

		readyPanel.add(lexicon);
		readyPanel.add(beginButton);
		readyPanel.add(new JLabel());
		readyPanel.add(new JLabel());
		readyPanel.add(statButton);
		readyPanel.add(returnButton);

		beginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "begin");

			}

		});

		statButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "stat");

			}

		});

		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "lexicon");

			}

		});

	}

	private void drawBeginPanel() {
		BorderLayout bLayout = new BorderLayout();
		beginPanel.setLayout(bLayout);

		JPanel recitationStylePanel = new JPanel();
		JPanel operationPanel = new JPanel();
		beginPanel.add(recitationStylePanel, BorderLayout.NORTH);
		beginPanel.add(operationPanel, BorderLayout.SOUTH);

		/* recitationStylePanel */
		final JRadioButton newJrb = new JRadioButton("新的背诵");
		final JRadioButton oldJrb = new JRadioButton("从上次开始");
		ButtonGroup bg = new ButtonGroup();
		bg.add(newJrb);
		bg.add(oldJrb);
		newJrb.setSelected(true);

		GridLayout gLayout = new GridLayout(5, 2);
		recitationStylePanel.setLayout(gLayout);
		recitationStylePanel.add(newJrb);
		recitationStylePanel.add(new JLabel());
		String space = "            ";
		recitationStylePanel.add(new JLabel(space + "起始单词:"));

		final JTextField beginJtx = new JTextField(20);
		final JTextField lengthJtx = new JTextField(20);

		recitationStylePanel.add(beginJtx);
		recitationStylePanel.add(new JLabel(space + "背诵长度:"));
		recitationStylePanel.add(lengthJtx);
		recitationStylePanel.add(new JLabel());
		recitationStylePanel.add(new JLabel());
		recitationStylePanel.add(oldJrb);
		recitationStylePanel.add(new JLabel());

		/* operationPanel */
		JButton okButton = new JButton("确定");
		JButton returnButton = new JButton("返回");

		GridLayout gLayout5 = new GridLayout(1, 2);
		operationPanel.setLayout(gLayout5);
		operationPanel.add(okButton);
		operationPanel.add(returnButton);

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//cardLayout.show(cardPanel, "recitation");
				boolean checked = newJrb.isSelected();
				if(checked){
					String startStr = beginJtx.getText().trim();
					int length = Integer.parseInt(lengthJtx.getText().trim());
					
					model = ReciteFactory.createReciteSession(lexicon, startStr, length, recitationPanel);
					controllor = new ReciteControllor(model);
					recitationPanel.setControllor(controllor);
					recitationPanel.refresh();
					recitationPanel.paintInfo();
					
					cardLayout.show(cardPanel, "recitation");
				}
				else{
					model = ReciteFactory.createReciteSession(lexicon, recitationPanel);
					controllor = new ReciteControllor(model);
					recitationPanel.refresh();
					recitationPanel.setControllor(controllor);
					recitationPanel.paintInfo();
					
					cardLayout.show(cardPanel, "recitation");
				}

			}

		});

		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "ready");

			}

		});

	}

	private void drawStatPanel() {
		BorderLayout bLayout = new BorderLayout();
		statPanel.setLayout(bLayout);

		JPanel statInfoPanel = new JPanel();
		JPanel operationPanel = new JPanel();
		statPanel.add(statInfoPanel, BorderLayout.NORTH);
		statPanel.add(operationPanel, BorderLayout.SOUTH);

		JLabel statInfoLabel = new JLabel("xxx");
		statInfoPanel.add(statInfoLabel);

		JButton returnButton = new JButton("返回");
		operationPanel.add(returnButton);

		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "ready");

			}

		});
	}

}
