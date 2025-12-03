import java.util.*;
public class WordList {
	
	String[] words = {"spiderman", "ironman", "hulk", "blackwidow", "hawkeye", "thor", "captain america",
			"starlord", "groot", "rocket raccoon", "gamora", "drax", "daredevil", "jessica jones", "winter soldier", "black panther",
			"antman", "wasp", "mantis", "falcon", "luke cage", "iron fist", "punisher", "wanda", "scarlett witch", 
			"vision", "doctor strange", "wolverine", "deadpool"};
	
	public String getRandomWord() {
		int num = (int)(Math.random() * words.length);
		String word = words[num];
		return word;
	}

}
