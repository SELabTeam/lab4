package com.wordmaster.recitemodel;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;

//用来测试没有背诵到最后一个单词
public class ReciteSessionTestCase1 {

	ReciteSession session;
	
	@Before
	public void setUp(){
		ActionListener listener = new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		};
		
		session = new ReciteSession("f", "fake", 10, listener);
	}
	
	@Test
	public void testNextWord() {
		session.nextWord("fake");
		
		assertEquals(session.getCurrentWord().getWord(), "fall");
		
		//fail("Not yet implemented");
	}

}
