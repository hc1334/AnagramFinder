package project2;
import java.util.ArrayList;

/**
 * Dictionary class contains all words considered "real" words depending on dictionary user chooses
 * It provides search methods tnat go through the dictionary faster (using binary search)
 * @author Helen Chang
 * @version Oct 10, 2015 
 *
 */

public class Dictionary implements DictionaryInterface {
	
	//variables
	//holds all words in dictionary as individual strings in arraylist
	private ArrayList<String> words = new ArrayList<String>();	
	
	/**
	 * Adds a single string (word) into Arraylist of words considered valid for dictionary
	 * replacement for setter
	 * @param word as String
	 */
	public void addWord (String word){
		words.add(word);
	}
	
	/**
	 * Getter method for AL of string objects considered valid
	 * @return ArrayList of Strings containing all valid words
	 */
	public ArrayList<String> getWords(){
		return words;
	}
	
	
	/**
	* This method determines if a given word is in this Dictionary.
	* implemented from interface
	* @param word the word to be checked
	* @return true of the word is in this Dictionary, * false otherwise
	*/
	@Override
	public boolean findWord(String word) {
		boolean result = findWord (word, 0, words.size()-1);//calls method recursively
		
		return result;
		
	}
	/**
	 * Overloaded method of findWord that should only be called as a helper method of findWord(String word) method
	 * @param word the word to be checked
	 * @param minIndex where to start checking
	 * @param maxIndex where to end checking
	 * @return true if the word is in the Dictionary, false otherwise
	 */
	public boolean findWord(String word, int minIndex, int maxIndex){
		
		//base case: searched through entire list, did not find word, return false 
		if (minIndex > maxIndex){
			return false; 
		}
		
		//middle index of ArrayList
		int mid = (maxIndex - minIndex)/2 + minIndex;
		
		//if word I'm searching for should go before middle term, look in first half
		if (this.words.get(mid).compareTo(word)>0){
			return findWord(word, 0, mid-1);
		}
		//if word I'm searching for should go after middle term, look in second half
		else if (this.words.get(mid).compareTo(word)<0){
			return findWord(word, mid+1, maxIndex);
		}
		
		//if word I'm searching for is middle term, you've found the word! look no further and return true. 
		else if (this.words.get(mid).compareTo(word)==0){
			return true;
		}
		else return false;
	
		//else return true;
	}
	
	
	
	/**
	* This method determines if a given
	* prefix is a prefix of a word that exists in this Dictionary.
	* @param prefix the prefix to be checked
	* @return true if the prefix is in this Dictionary, false otherwise
	*/
	@Override
	public boolean findPrefix(String prefix) {
		int length = this.words.size();
		boolean result = findPrefix(prefix, 0, length-1);
		return result;
	}
	/**
	 * OVerload method of findPrefix only called as helper method of findPrefix(String prefix)
	 * checks to see if prefix being entered could be a prefix of a word in the dictionary
	 * @param prefix the prefix to be checked
	 * @param minIndex of where to start the search
	 * @param maxIndex of where to end the search
	 * @return true if the prefix is in this dictionary, false otherwise. 
	 */
	private boolean findPrefix(String prefix, int minIndex, int maxIndex){
		int prefixSize = prefix.length();
		int mid = (maxIndex-minIndex)/2 + minIndex;
		
		//base case: searched through entire list, did not find.
		if (minIndex > maxIndex){
			return false;
		}
		

		try{
			if (this.words.get(mid).substring(0,prefixSize).compareTo(prefix) > 0 ){
				return findPrefix(prefix, minIndex, mid-1);
			}
			if (this.words.get(mid).substring(0, prefixSize).compareTo(prefix) < 0){
				return findPrefix(prefix, mid+1, maxIndex);
			}
			if (this.words.get(mid).substring(0, prefixSize).compareTo(prefix) == 0){
				return true;
			}
			else return false;
		}
		//if prefix is longer than word in dictionary
		catch (StringIndexOutOfBoundsException e){
			if (this.words.get(mid).substring(0,this.words.get(mid).length()-1).compareTo(prefix) > 0 ){
				return findPrefix(prefix, minIndex, mid-1);
			}
			if (this.words.get(mid).substring(0,this.words.get(mid).length()-1).compareTo(prefix) < 0){
				return findPrefix(prefix, mid+1, maxIndex);
			}
			else return false;
		}
	}
	


}
