
public class Hangman {
	
	int hangmanNum = 6;
	
	WordList w = new WordList();
	String word = w.getRandomWord();
	
	String[] revealedLetters = new String[word.length()];
	
	public void rL() {
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == ' ')
				revealedLetters[i] = " ";
			else
				revealedLetters[i] = "_ ";
		}
	}
	
	public boolean check(char ch) {
		boolean found = false;
		for(int i = 0; i < word.length(); i++) {
			if(ch == word.charAt(i)) {
				revealedLetters[i] = ch + " ";
				found = true;
			}
		}
		if(found == false) {
			incorrectLetter(ch);
		}
		return found;
		//print();
	}
	
	public void incorrectLetter(char ch) {
		hangmanNum--;
	}
	
	public boolean wordCompleted() {
		boolean completed = false;
		for(int i = 0; i < revealedLetters.length; i++) {
			if(revealedLetters[i].equals("_ "))
				return completed;
		}
		completed = true;
		
		
		return completed;
	}
	
	public boolean deadMan() {
		if(hangmanNum == 0)
			return true;
		else
			return false;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getCurrentGuess() {
		String guessedWord = "";
		for(int i = 0; i < word.length(); i++)
			guessedWord = guessedWord + revealedLetters[i];
		return guessedWord;
			
	}
	
	public int getHangmanNum() {
		return hangmanNum;
	}

}//end Hangman
