package lexicon;

public class Word {
	private String word; // ���ʡ�
	private String paraphrase; // �õ��ʵ����塣

	public Word(String word, String paraphrase) {
		this.word = word;
		this.paraphrase = paraphrase;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWord() {
		return this.word;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getParaphrase() {
		return this.paraphrase;
	}
}
