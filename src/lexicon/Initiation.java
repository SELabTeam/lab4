package lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Initiation {
	private static String dict_name = "dictionary.txt";
	private static HashMap<Character, ArrayList<Word>> wordMap = new HashMap<Character, ArrayList<Word>>();

	public Initiation() throws IOException {
		initLexicon();
	}

	public void setDict(String dict_name) {
		Initiation.dict_name = dict_name;
	}

	public HashMap<Character, ArrayList<Word>> getWordMap() {
		return Initiation.wordMap;
	}

	/*
	 * letter: a,b,...,z(case insensitive). If start is null, then start from
	 * the first word. If the offset is too large, then return null.
	 */
	public Word getNext(String letter, String start, int offset)
			throws IOException {
		if (letter == null) {
			JOptionPane.showMessageDialog(null,
					"Initiation.getNext error: Letter must be not null!");
			return null;
		}

		char letter_c = letter.toLowerCase().charAt(0);
		if (letter_c < 'a' || letter_c > 'z') {
			JOptionPane.showMessageDialog(null,
					"Initiation.getNext error: Illegal letter!");
			return null;
		}

		ArrayList<Word> al = wordMap.get(letter_c);
		int position = 0;
		int index;

		if (start == null && offset == 0) {
			index = 0;
		} else {
			if (start == null) {
				index = offset;
			} else {
				for (int i = 0; i < al.size(); i++) {/*
													 * Get the index of the
													 * start.
													 */
					if (al.get(i).getWord().equals(start)) {
						position = i;
						break;
					}
				}
				index = position + offset;
			}

			if (index >= al.size()) {/* Too large! */
				return null;
			}
		}

		return al.get(index);
	}

	/*
	 * Should be invoked after construct the Initiation instance. Split the
	 * dictionary to 26 lexicons. The dictionary should be put at
	 * \src\lexicon\dictionary\ e.g:
	 * E:\\WordMaster\src\lexicon\dictionary\dictionary.txt
	 */
	private void initLexicon() throws IOException {
		String directory = System.getProperty("user.dir")
				+ "/src/lexicon/dictionary/";

		/* Read the dictionary. */
		FileInputStream fis = new FileInputStream(directory + dict_name);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = br.readLine();
		if (line == null) {
			JOptionPane.showMessageDialog(null,
					"Initiation.initLexicon error: Illegal dictionary!");
			return;
		}
		String[] l = line.split(" +");
		Word w = new Word(l[0], l[1]);
		char c = line.toLowerCase().charAt(0);
		ArrayList<Word> wordList = new ArrayList<Word>();
		wordList.add(w);

		/* Write to the splited file. */
		FileOutputStream fos = new FileOutputStream(directory + c + ".txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(line);
		bw.newLine();
		bw.flush();

		char cur_c = c;
		while (br.ready()) {
			line = br.readLine();
			if (line == null) {
				JOptionPane.showMessageDialog(null,
						"Initiation.initLexicon error: Illegal dictionary!");
				return;
			}
			c = line.toLowerCase().charAt(0);
			l = line.split(" +");
			w = new Word(l[0], l[1]);

			if (c == cur_c) {
				wordList.add(w);
				bw.write(line);
				bw.newLine();
				bw.flush();
			} else {
				wordMap.put(cur_c, wordList);
				wordList = new ArrayList<Word>();
				wordList.add(w);
				cur_c = c;
				fos = new FileOutputStream(directory + c + ".txt");
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		}
		wordMap.put(c, wordList);

		fis.close();
		br.close();
		// fos.close();
		bw.close();
	}

	/*
	 * Return the left words count in lexicon "letter" from the word.
	 */
	public int leftCount(String letter, String word) {
		if (letter == null) {
			JOptionPane.showMessageDialog(null,
					"Initiation.leftCount error: Letter must be not null!");
			return 0;
		}

		char letter_c = letter.toLowerCase().charAt(0);
		if (letter_c < 'a' || letter_c > 'z') {
			JOptionPane.showMessageDialog(null,
					"Initiation.leftCount error: Illegal letter!");
			return 0;
		}

		ArrayList<Word> al = wordMap.get(letter_c);

		int index = 0;
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i).getWord().equals(word)) {
				index = i;
				break;
			}
		}

		return (al.size() - index);
	}
}
