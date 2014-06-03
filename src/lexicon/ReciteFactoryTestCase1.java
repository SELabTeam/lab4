package com.wordmaster.recitemodel;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReciteFactoryTestCase1 {
		
	//测试用户输入单词不存在的情况
	@Test
	public void testCreateReciteSessionStringStringIntActionListener_one() {
		
		ReciteFactory.createReciteSession("f", "fxy", 10, null);
		
		assertEquals(ReciteFactory.wordTest, "fable");
		assertEquals(ReciteFactory.lengthTest, 10);
		//fail("Not yet implemented");
	}

	//测试用户输入单词存在，但剩余长度不足的情况
	@Test
	public void testCreateReciteSessionStringStringIntActionListener_two() {
		
		ReciteFactory.createReciteSession("f", "fussy", 10, null);
		
		assertEquals(ReciteFactory.wordTest, "fussy");
		assertEquals(ReciteFactory.lengthTest, 3);
		//fail("Not yet implemented");
	}
	
	//测试用户输入单词不存在，且剩余单词数量不足
	@Test
	public void testCreateReciteSessionStringStringIntActionListener_three() {
		
		ReciteFactory.createReciteSession("f", "fxyz", 400, null);
		
		assertEquals(ReciteFactory.wordTest, "fable");
		assertEquals(ReciteFactory.lengthTest, 376);
		//fail("Not yet implemented");
	}
	
	//测试用户输入单词不存在的情况
	@Test
	public void testCreateReciteSessionStringStringIntActionListener_four() {
				
		ReciteFactory.createReciteSession("f", "fake", 3, null);
				
		assertEquals(ReciteFactory.wordTest, "fake");
		assertEquals(ReciteFactory.lengthTest, 3);
		//fail("Not yet implemented");
	}

}
