package com.wordmaster.recitemodel;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.wordmaster.lexicon.XMLLexicon;

public class ReciteFactory {
   
	//user for test
	public static int status = 0;
	public static String wordTest;
	public static int lengthTest;
	
	//通过用户输入选择
	public static ReciteSession createReciteSession(String lexicon, 
			String word, int length, ActionListener listener){
		
		String verifyWord = XMLLexicon.getInstance().getNext(lexicon, word, 0).getWord();
		
		if(!verifyWord.equals(word)){
			JOptionPane.showMessageDialog(null, "词库"+lexicon+"中不存在单词 "+ word
					+"，将从该词库第一个单词开始。", "无法找到单词", JOptionPane.INFORMATION_MESSAGE);
			
			word = verifyWord;
			
			//user for test
			status = 1;       //没有找到输入的单词
		}
		
		//user for test
		wordTest = word;
		
		int remain = XMLLexicon.getInstance().leftCount(lexicon, word);    
		
		if(length==-1){
			length = remain;
		}
		else if(remain<length){
			JOptionPane.showMessageDialog(null, "词库"+lexicon+"在"+word+"之后的单词数不足"+length
					+"，将把之后所有单词都加入这次记忆中。", "单词数不足", JOptionPane.INFORMATION_MESSAGE);
			
			length = remain;
			
			//user for test
			status = 2;        //剩余的单词数不足
		}
		
		lengthTest = length;
		
		return new ReciteSession(lexicon, word, length, listener);
	}
	
	//从上次背诵开始
	public static ReciteSession createReciteSession(String lexicon, ActionListener listener){
		try {
			Scanner scanner = new Scanner(new File("UserInfo/userData"+lexicon+".txt"));
			
			String startWord = new String();
			
			while(scanner.hasNext()){
				startWord = scanner.next();
				scanner.next();
				scanner.nextInt();
				scanner.nextInt();
			}
			
			int remain = XMLLexicon.getInstance().leftCount(lexicon, startWord);                    //调用接口
			scanner.close();
			
			//user for test
			wordTest = startWord;
			lengthTest = remain;
			
			return new ReciteSession(lexicon, startWord, remain, listener);
			
		} catch (FileNotFoundException e) {
			
			//没有历史信息，从第一个单词背起
			String startWord = XMLLexicon.getInstance().getNext(lexicon, null, 0).getWord();      
			int length = XMLLexicon.getInstance().leftCount(lexicon, startWord);                    //调用接口
			
			//user for test
			wordTest = startWord;
			lengthTest = length;
			
			return new ReciteSession(lexicon, startWord, length, listener);
		}
	}
}
