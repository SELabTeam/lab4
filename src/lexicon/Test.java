package lexicon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
	public static void main(String[] args) throws IOException {
		Initiation init = new Initiation();
		Word w = init.getNext("b", null, 0);

		System.out.println(w.getWord() + "   " + w.getParaphrase());
	}
}
