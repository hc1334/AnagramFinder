package project2;
/**
 * Dictionary interface requires methods that determine if a word is found and if a prefix is found is Dictionary objects
 * 
 * @author Helen Chang, Joanna Klukowska
 * @version Oct 10, 2015 
 *
 */
public interface DictionaryInterface { /**
	* This method determines if a given word
	* is in this Dictionary.
	* @param word the word to be checked
	* @return true of the word is in this Dictionary, * false otherwise
	*/
	boolean findWord ( String word );
	/**
	* This method determines if a given
	* prefix is a prefix of a word that exists in * this Dictionary.
	* @param prefix the prefix to be checked
	* @return true if the prefix is in this Dictionary, * false otherwise
	*/
	boolean findPrefix ( String prefix ); 
}