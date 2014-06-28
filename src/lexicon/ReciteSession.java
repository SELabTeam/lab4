package com.wordmaster.recitemodel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.wordmaster.lexicon.Word;
import com.wordmaster.lexicon.XMLLexicon;

public class ReciteSession {

	private String lexicon;             //所选的词典
	private Word startWord;             //背诵的起始单词
	private int reciteLen;              //背诵的长度
	
	private int offset;                 //正在背的单词的位置                
	private Word currentWord;           //正在背诵的单词
	
	private int recitedNum;             //已经背诵过的单词数
	private int correctNum;             //背诵正确的单词数
	
	private String lastWord = "";
	private String currentStatus = "";
	
	private ActionListener listener;
	
	public ReciteSession(String lexicon, String startWorld, int reciteLen,
			ActionListener actionListener){
		
		this.lexicon = lexicon;
		this.startWord = XMLLexicon.getInstance().getNext(lexicon, startWorld, 0);           
		this.reciteLen = reciteLen;
		this.listener = actionListener;
		
		this.offset = 0;
		this.currentWord = this.startWord;        
		this.recitedNum = 0;
		this.correctNum = 0;
	}
	
	private void reciteCorrect(String word){
		File dir = new File("UserInfo");
		if(!dir.exists())
			dir.mkdirs();
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(new File("UserInfo/userDataAll"+lexicon+".txt"), true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(word.equals(this.currentWord.getWord())){
			this.correctNum += 1;
			this.currentStatus = "正确";
			
			writer.print("\n"+this.currentWord.getWord()+" "+0);
		}
		else{
			this.currentStatus = "错误，应是："+this.currentWord.getWord();
			writer.print("\n"+this.currentWord.getWord()+" "+1);
		}
		
		writer.close();
		
		this.recitedNum += 1;
		this.lastWord = word;
		//this.currentStatus = "拼写错误";
		
	}
	
	public void nextWord(String word){
		
		this.reciteCorrect(word);
		
		this.offset += 1;
		
		if(this.offset == this.reciteLen){
			this.throwEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "finish"));
			return;
		}
			
		this.currentWord = XMLLexicon.getInstance().getNext(lexicon, this.startWord.getWord(), this.offset);   
		
		this.throwEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "update"));
	}
	
	public Word getCurrentWord() {
		return currentWord;
	}

	public void setCurrentWord(Word currentWord) {
		this.currentWord = currentWord;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public String getProcessInfo(){
		String info = "已经记忆 "+this.offset+" 个单词，还有 " + (this.reciteLen-this.offset)
				+ " 个单词要记忆。";
		
		return info;
	}

	public String getStatistics(){
		
		double accuracy;
		if(this.recitedNum==0) accuracy = 0;
		else
			accuracy = 1.0 * this.recitedNum / this.correctNum;
		
		String statistics = "\n\n这次单词记忆从 "+this.startWord.getWord()+" 开始；\n"
				+ "共记忆了 " + this.recitedNum + " 个单词，" + "\n记忆正确的单词共有 "
				+ this.correctNum + "个，\n错误单词共有 " + (this.recitedNum-this.correctNum)
				+ "个，" + "\n正确率为 " + accuracy + "。";
		
		return statistics;
	}
	
	public void saveSession(){
		
		File dir = new File("UserInfo");
		if(!dir.exists())
			dir.mkdirs();
		
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(new File("UserInfo/userData"+lexicon+".txt"), true));
			
			writer.print("\n"+this.currentWord.getWord()+" ");
			writer.print(this.startWord.getWord()+" ");
			writer.print(this.recitedNum+" ");
			writer.print(this.correctNum+" ");
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void throwEvent(ActionEvent actionEvent) {
		this.listener.actionPerformed(actionEvent);
	}

	public int getReciteLen() {
		return reciteLen;
	}

	public void setReciteLen(int reciteLen) {
		this.reciteLen = reciteLen;
	}

	public int getRecitedNum() {
		return recitedNum;
	}

	public void setRecitedNum(int recitedNum) {
		this.recitedNum = recitedNum;
	}

	public String getLastWord() {
		return lastWord;
	}

	public void setLastWord(String lastWord) {
		this.lastWord = lastWord;
	}
	
	
	
}
