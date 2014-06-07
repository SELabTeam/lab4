package com.wordmaster.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class StatisticManager {

	@SuppressWarnings("resource")
	public static ArrayList<String> readCurrentStat(String lexicon){
		File file=new File("UserInfo/UserDataAll"+lexicon+".txt");
		Scanner scan=null;
		if(file.exists()){
			try {
				scan=new Scanner(file);
			} catch (FileNotFoundException e) {
				System.out.println("UserDataAllX not found"
						+ "\tshouldn't see this msg");
			}
		}else{
			return null;//file doesn't exist
		}
		
		ArrayList<String> wordList=new ArrayList<String>();
		
		scan.nextLine();//there a blank line at the begining
		while(scan.hasNextLine()){
			String line=scan.nextLine().trim();
			wordList.add(line);
		}
		return wordList;
	}
	
	
	
	public static TreeMap<String,String> getCurrentStat(ArrayList<String> wordList){
		TreeMap<String,String> map=new TreeMap<String,String>();
		
		Iterator<String> itor=wordList.iterator();
		
		while(itor.hasNext()){
			String wordRaw=itor.next();
			String []wordSplited=wordRaw.split(" ");
			String word=wordSplited[0];
			String answer=wordSplited[1];
			
			if(map.get(word)==null){
				map.put(word, answer);
			}else{
				if(answer.equals("0"))
					map.put(word, answer);
			}
		}
		
		    return map;
	 }
	
	public static Accuracy countAccuracy(TreeMap<String,String> map){
		Accuracy accy=new Accuracy();
		int rightCount=0;
		int wrongCount=0;
		
		Set<Entry<String, String>> set=map.entrySet();
		Iterator<Entry<String, String>> itor=set.iterator();
		
		while(itor.hasNext()){
			Entry<String, String> entry=itor.next();
			if(entry.getValue().equals("0"))
				rightCount++;
			if(entry.getValue().equals("1"))
				wrongCount++;
		}
		accy.setRightCount(rightCount);
		accy.setWrongCount(wrongCount);
		
		return accy;
	}
	
	public static Accuracy getCurrentAccuracy(String lexicon){
		ArrayList<String> list=readCurrentStat(lexicon);
		if(list==null)
			return null;
		
		TreeMap<String,String> map=getCurrentStat(list);
		return countAccuracy(map);
	}
	
	
	/*public class Accuracy{
		int rightCount;
		int wrongCount;
		
		public int getRightCount() {
			return rightCount;
		}
		public int getWrongCount() {
			return wrongCount;
		}
		private void setRightCount(int rightCount) {
			this.rightCount = rightCount;
		}
		private void setWrongCount(int wrongCount) {
			this.wrongCount = wrongCount;
		}
		
	}*/
	
	/*public static void main(String[] args) {
		
		  
		Lexicon lex=Lexicon.getInstance();
		Accuracy accy=lex.getCurrentAccuracy("b");
		System.out.println(accy.getRightCount());
		System.out.println(accy.getWrongCount());
		System.out.println(lex.leftCount("B",null));
	}*/
	
}
