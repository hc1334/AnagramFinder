package project2;

import java.util.ArrayList;

/**
 * LetterBag class represents a series of letters that come from a word (entered by user).
 * Different sequences of these letters can be generated to form other words
 * 
 * @author Helen Chang
 * @version Oct 10, 2015 
 *
 */
public class LetterBag implements LetterBagInterface{
	
	//variables
	private String wordToSearch; //string of word being searched
	private ArrayList<Character> letters = new ArrayList<Character>(); //AL of all the characters we can use
	private ArrayList<String> wordsToPrint = new ArrayList<String>(); //words generated from letters that exist in dictionary

	//CONSTRUCTOR
	public LetterBag(String wordToSearch){
		setWordToSearch(wordToSearch);
		this.setLetters(wordToSearch);//creates AL of characters of letters we can use to build words with
	}
	
	/**
	 * Sorts AL of words in alphabetical order
	 * @param wordsToPrint AL of words to sort
	 */
	public static void sort(ArrayList<String> wordsToPrint){
		String tempHolder;
		//for each word, compare with the one after
		//checks up to second last word in AL, which will compare with the last
		for (int i = 0; i < wordsToPrint.size()-1; i++){
			if (wordsToPrint.get(i).compareTo(wordsToPrint.get(i+1))>0){//if the second word occurs earlier in alphabetical order
				tempHolder = wordsToPrint.get(i);
				wordsToPrint.set(i, wordsToPrint.get(i+1));
				wordsToPrint.set(i+1, tempHolder);
				if (i>=1){
					i-=2;
				}
			}
		}
	}
	
	/**
	 * Removes duplicates of words in Al of words (String objects)
	 * 
	 * @param AL of strings to look through, must be sorted in alphabetical order
	 */
	public static void removeDuplicates(ArrayList<String> wordsToPrint){
		for (int i = 0; i < wordsToPrint.size()-1; i++){//for each word in array up to second last...
			//...compare with the one after
			if (wordsToPrint.get(i).equals(wordsToPrint.get(i+1))){
				wordsToPrint.remove(i+1);//remove duplicate word
				i--;//if same found, use the same first string for further comparison 
			}
		}
	}

	
	
	/**
	* This method determines the list of words that can be created 
	* from a given LetterBag object that are present in the
	* provided Dictionary object dict.
	* @param dict the Dictionary object to be used
	* @return a list of valid words in alphabetical order */
	@Override
	public ArrayList<String> getAllWords(Dictionary dict) {
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		for (Character c : this.letters){
			//all possible anagrams starting with each letter in original letters
			temp = getAllWords(dict,c.toString(), this.letters);
			
			//add to collection of all possible anagrams 
			for (String x : temp){
				result.add(x);
			}
		}
		
		sort(result);
		removeDuplicates(result);
		return result;
	}
	
	/**
	 * Overloads getAllWords method, used for recursive calls
	 * determines the list of words that can be created 
	* from a given LetterBag object that are present in the
	* provided Dictionary object dict.
	 * @param dict Dictionary object containing words that exist
	 * @param currentString the sequence I have generated from the letter thus far
	 * @param currentLetters AL of letters i can still use
	 * @return AL of all the anagrams that are considered words
	 */
	public ArrayList<String> getAllWords(Dictionary dict, String currentString, ArrayList<Character> currentLetters){
		//if current string is word in dictionary, add to list
		if (dict.findWord(currentString) && currentString.length()==letters.size()){
			wordsToPrint.add(currentString);
		}
		
		//if prefix exists, keep generating sequences
		else if (dict.findPrefix(currentString)){
			//creates duplicate array of letters
			ArrayList<Character> tempArray = new ArrayList<Character>(); 
			for (int i = 0; i < currentLetters.size(); i++){
				tempArray.add(currentLetters.get(i));
			}
			
			//removes the last letter added on the string from list of letters we're trying add on
			char lastLetter = currentString.charAt(currentString.length()-1);
			tempArray.remove(tempArray.lastIndexOf(lastLetter));
			
			for (Character c:tempArray){
				//for every character thats still open for use, 
				//create separate branches for each character, continue building sequence
				String tempCurrentString = currentString +c;
				getAllWords(dict, tempCurrentString, tempArray);
			}
		}
		
		return wordsToPrint;
	}
	

	
	
	//generated getters and setters
	/**
	 * getter method for wordToSearch
	 * @return the wordToSearch as String
	 */
	public String getWordToSearch() {
		return wordToSearch;
	}

	/**
	 * setter method for wordToSearch
	 * @param wordToSearch the word to set
	 */
	public void setWordToSearch(String wordToSearch) {
		this.wordToSearch = wordToSearch;
	}


	public ArrayList<Character> getLetters() {
		return letters;
	}

	/**
	 * setter for letters
	 * @param AL of characters of letters that can be used in this LetterBag object
	 */
	public void setLetters(ArrayList<Character> letters) {
		this.letters = letters;
	}
	/**
	 * Overloaded setter for letters
	 * @param wordToSearch as String, divides up string and stores individual characters in AL
	 */
	public void setLetters(String wordToSearch){
		for (int i = 0; i < wordToSearch.length(); i++){
			this.letters.add(i, wordToSearch.charAt(i));
		}
	}

	/**
	 * getter method for wordsToPrint AL of possible anagrams
	 * @return the wordsToPrint as AL of strings
	 */
	public ArrayList<String> getWordsToPrint() {
		return wordsToPrint;
	}

	/**
	 * setter method for wordsToPrint AL of possible anagrams
	 * @param wordsToPrint the AL of strings to set
	 */
	public void setWordsToPrint(ArrayList<String> wordsToPrint) {
		this.wordsToPrint = wordsToPrint;
	}
}
