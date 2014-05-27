package lexicon;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class InitiationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetWords() throws IOException {
		Initiation init = new Initiation();

		assertEquals("abandon", init.getNext("a", null, 0).getWord());
		assertEquals("v.抛弃，放弃", init.getNext("a", null, 0).getParaphrase());

		assertNull(init.getNext("s", null, 907));

		assertEquals("zoology", init.getNext("z", "zoo", 1).getWord());
		assertEquals("n.动物学", init.getNext("z", "zoo", 1).getParaphrase());
		assertNull(init.getNext("z", "zoo", 2));

		assertEquals(907, init.leftCount("s", "sack"));
		assertEquals(1, init.leftCount("a", "axle"));
		assertEquals(2, init.leftCount("x", "test"));
	}

}
