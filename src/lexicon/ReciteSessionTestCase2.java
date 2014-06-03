package com.wordmaster.recitemodel;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;

//用来测试背诵到最后一个单词
public class ReciteSessionTestCase2 {

	ReciteSession session;
	
	@Before
	public void setUp(){
		ActionListener listener = new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		};
		
		session = new ReciteSession("f", "fake", 1, listener);
	}
	
	@Test
	public void testNextWord() {
		
		session.nextWord("fake");
		
		assertEquals(session.getReciteLen(), session.getRecitedNum());
		
		//fail("Not yet implemented");
	}

}
