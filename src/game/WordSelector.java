package game;

import java.io.DataInputStream;
import java.lang.Character;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A singleton class that loads a word list in .txt format and stores it as an ArrayList.
 * Makes it possible to get random words from a huge word list.
 */
public class WordSelector {

	private static WordSelector instance;
	private ArrayList<String> words;
	private int index;

	/**
	 * Reference to the singleton instance. The word list "wordsNormal.txt" is chosen as default.
	 * @return The singleton instance
	 */
	public static WordSelector getInstance() {
		if(instance == null) {
			instance = new WordSelector(WordSelector.class.getResource("/wordsNormal.txt"));
		}
		return instance;
	}

	/**
	 * Private constructor restricted to this class making the class a singleton
	 */
	private WordSelector(URL url) {
		this.loadFile(url);
	}

	/**
	 * An index is used to prevent the same word to appear more than once until the huge list is gone through.
	 * The word at the current index in the ArrayList is returned and incremented.
	 * If the index is more than the number of words in the word list the word list will be shuffled and index set to 0.
	 * @return A word
	 */
	public String getWord() {
		if(this.index > this.words.size() - 1) {
			this.index = 0;
			this.shuffle();
		}
		return this.words.get(this.index++);
	}

	/**
	 * A .txt file is loaded into an ArrayList which is shuffled and the index is set to 0 to start selection from the beginning.
	 * @param url The url to the .txt file.
	 */
	public void loadFile(URL url) {
		this.words = this.fromFile(url);
		this.shuffle();
		this.index = 0;
	}

	/**
	 * Shuffles the ArrayList of words.
	 */
	private void shuffle() {
		Collections.shuffle(this.words);
	}

	/**
	 * Reads a .txt file and returns an ArrayList with the words in the text file (words in text file should be on separate rows).
	 * @param url The url to the .txt file.
	 * @return ArrayList of words
	 */
	public ArrayList<String> fromFile(URL url) {
		String fileContent = "";
		ArrayList<String> words = new ArrayList<>();

		//Read file
		try {
			DataInputStream dis = new DataInputStream(url.openStream());
			byte[] buffer = new byte[dis.available()];
			dis.read(buffer);
			fileContent = new String(buffer);
			dis.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/*As words in .txt file are separated by new lines it is possible to catch all words by looping through the string
		  and checking if current char is alphabetic or not. If it is alphabetic the char can be appended to a StringBuilder object.
		  If current char is not alphabetic and there is actually chars in the StringBuilder, the word can be added to the ArrayList
		  and the StringBuilder can be reset to process the next word.
		*/
		StringBuilder word = new StringBuilder();
		for(int i = 0; i < fileContent.length(); i++) {
			if(Character.isAlphabetic(fileContent.charAt(i))) {
				word.append(fileContent.charAt(i));
			} else {
				if(word.length() > 1) {
					words.add(word.toString().toLowerCase());
				}
				word = new StringBuilder();
			}
		}
		if(words.size() == 0) {
			return new ArrayList<>();
		} else {
			return words;
		}
	}
}
