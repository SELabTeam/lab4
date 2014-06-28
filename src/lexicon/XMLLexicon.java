package com.wordmaster.lexicon;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLLexicon {
	
	public String xmlPath = "dictionary.xml";
	
	public HashMap<String, ArrayList<Word>> mainLexicon = new HashMap<String, ArrayList<Word>> ();
	
	private static XMLLexicon lexicon = null;
	
	public static XMLLexicon getInstance(){
		if(lexicon==null)
			lexicon = new XMLLexicon();
		
		return lexicon;
	}
	
	public int getSubNumber(){
		return mainLexicon.size();
	}
	
	public Set<String> getTypes(){
		return mainLexicon.keySet();
	}
	
	public Word getNext(String type, String start, int offset){
		ArrayList<Word> subLexicon = mainLexicon.get(type);
		
		int startIndex = parseIndex(subLexicon, start);
		int totalSize = subLexicon.size();
		
		int currLoc = startIndex + offset;
		if(currLoc>=totalSize)
			return null;
		
		return subLexicon.get(currLoc);
		
	}
	
	public int leftCount(String type, String word) {
		ArrayList<Word> subLexicon = mainLexicon.get(type);
		
		if(word==null)
			return subLexicon.size();
		
		boolean isExist = parseExist(subLexicon, word);
		if(isExist==false)
			return subLexicon.size();
		
		int index = parseIndex(subLexicon, word);
		
		return (subLexicon.size()-index);
	}
	
	private boolean parseExist(ArrayList<Word> subLexicon, String word) {
		int length = subLexicon.size();
		
		for(int i=0; i<length; i++){
			if(subLexicon.get(i).getWord().equals(word))
				return true;
		}
		
		return false;
	}

	private int parseIndex(ArrayList<Word> subLexicon, String start) {
		int length = subLexicon.size();
		
		for(int i=0; i<length; i++){
			if(subLexicon.get(i).getWord().equals(start))
				return i;
		}
		
		return 0;
	}

	private XMLLexicon(){
		initMainLexicon();
	}

	private void initMainLexicon() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		try{
			File xmlFile = new File(xmlPath);
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			
			NodeList engList = document.getElementsByTagName("english");
			NodeList chaList = document.getElementsByTagName("chinese");
			
			int size = engList.getLength();
			for(int i=0; i<size; i++){
				String english = engList.item(i).getFirstChild().getNodeValue();
				String chaDes = chaList.item(i).getFirstChild().getNodeValue();
				
				String type = parseType(chaDes);
				String chinese = parsePara(chaDes);
				
				Word word = new Word(english, chinese);
				
				if(mainLexicon.containsKey(type)){
					ArrayList<Word> subLexicon = mainLexicon.get(type);
					subLexicon.add(word);
				}
				else{
					ArrayList<Word> subLexicon = new ArrayList<Word> ();
					subLexicon.add(word);
					
					mainLexicon.put(type, subLexicon);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String parsePara(String chaDes) {
		int index = chaDes.indexOf(".");
		if(index<0)
			return chaDes;
		else
			return chaDes.substring(index+1, chaDes.length()); 
		
	}

	private String parseType(String chaDes) {
		int index = chaDes.indexOf(".");
		if(index<=0)
			return "n";
		else
			return chaDes.substring(0, index);
	}
	
	
}
