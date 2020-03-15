package game;

import java.io.DataInputStream;
import java.lang.Character;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class WordSelector {
	private static WordSelector instance;
	private String[] words;
	private Random random;
	
	public static WordSelector getInstance() {
		if(instance == null) {
			URL url = WordSelector.class.getResource("/words.txt");
			instance = fromFile(url);
		}
		return instance;
	}
	
	public WordSelector(String... words) {
		this.words = words;
		this.random = new Random();
	}
	
	public String getWord() {
		return words[random.nextInt(words.length)];
	}
	
	public static WordSelector fromFile(URL url) {
		String fileContent = "";
		ArrayList<String> retval = new ArrayList<>();
		try {
			DataInputStream dis = new DataInputStream(url.openStream());
			byte[] buffer = new byte[dis.available()];
			dis.read(buffer);
			fileContent = new String(buffer);
			dis.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		StringBuilder word = new StringBuilder();
		for(int i = 0; i < fileContent.length(); i++) {
			if(Character.isAlphabetic(fileContent.charAt(i))) {
				word.append(fileContent.charAt(i));
			} else {
				if(word.length() > 1) {
					retval.add(word.toString().toLowerCase());
				}
				word = new StringBuilder();
			}
		}
		if(retval.size() == 0) {
			return new WordSelector("add", "words");
		} else {
			return new WordSelector(retval.toArray(new String[retval.size()]));
		}
	}
}
