package com.wordmaster.recitecontrollor;

import com.wordmaster.recitemodel.ReciteSession;

public class ReciteControllor {

	private ReciteSession model;
	
	public ReciteControllor(ReciteSession model){
		this.model = model;
	}
	
	public String getLastWord(){
		return this.model.getLastWord();
	}
	
	public String getCurrentStatus(){
		return this.model.getCurrentStatus();
	}
	
	public void getNextWord(String currentWorld){
		this.model.nextWord(currentWorld);
	}
	
	public String getCurrentParaphase(){
		return this.model.getCurrentWord().getParaphrase();
	}
	
	public String getCurrentWord(){
		return model.getCurrentWord().getWord();
	}
	
	public String getStatInfo(){
		return model.getStatistics();
	}
	
	public void saveCurrent(){
		this.model.saveSession();
	}
}
