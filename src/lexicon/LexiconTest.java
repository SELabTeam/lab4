package com.wordmaster.lexicon;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class LexiconTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetWords() throws IOException {
		XMLLexicon init = XMLLexicon.getInstance();
		
		assertEquals("abandon", init.getNext("v", null, 0).getWord());
		assertEquals("抛弃，放弃", init.getNext("v", null, 0).getParaphrase());

		assertNull(init.getNext("int", null, 907));

		assertEquals("abbreviation", init.getNext("n", "abandonment", 1).getWord());
		assertNull(init.getNext("v,,n", "groan", 2));

		assertEquals(1, init.leftCount("v,,n", "groan"));
		assertEquals(1, init.leftCount("v,,n", "null"));
		
	}

}
