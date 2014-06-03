package com.wordmaster.recitemodel;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReciteFactoryTestCase2 {

	//用户都过历史信息开始背诵，且历史信息存在
	@Test
	public void testCreateReciteSessionStringActionListener_one() {
		
		ReciteFactory.createReciteSession("f", null);
		assertEquals(ReciteFactory.wordTest, "false");
		assertEquals(ReciteFactory.lengthTest, 350);
		
		//fail("Not yet implemented");
	}

	//用户根据历史信息背诵，但历史信息不存在
	@Test
	public void testCreateReciteSessionStringActionListener_two() {
		
		ReciteFactory.createReciteSession("a", null);
		assertEquals(ReciteFactory.wordTest, "abandon");
		assertEquals(ReciteFactory.lengthTest, 562);
		
		//fail("Not yet implemented");
	}
}
