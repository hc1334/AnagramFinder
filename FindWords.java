package project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * FindWords class parses command line argument, reading input file and user input
 * it creates Dictionary and LetterBag objects depending on input file and input
 * @author Helen Chang
 * @version Oct 10, 2015 
 *
 */
public class FindWords {

	public static void main(String[]args) throws FileNotFoundException{
		//instantiates FindWords and Dictionary object to work with 
		FindWords program = new FindWords();
		Dictionary allWords = new Dictionary();
		
		//checks file can be opened
		program.openFile(args);
		
		//creates new scanner object linking to file to open
		File fileName = new File(args[0]);
		Scanner scn = new Scanner(fileName);
		
		//stores all the words in dictionary in arrayList in dictionary object
		while (scn.hasNextLine()){
			allWords.addWord(scn.nextLine());
		}
		
		//creates new LetterBag object using validated user input word
		String input = program.getUserInput();
		LetterBag letterBag = new LetterBag(input);
		
		//Get arraylist of all possible anagrams
		ArrayList<String> toPrint = letterBag.getAllWords(allWords);

		
		if (toPrint.isEmpty()){
			System.out.println("Sorry, there are no anagrams that matches with the current dictionary. ");
		}
		else{
			System.out.printf("Found %d words that can be made of the letters entered: %n", toPrint.size());
			for (String x : toPrint){
				System.out.println(x);
			}
		}
	
		scn.close();
	}
	
	/**
	 * Method that makes sure the file can be opened and read
	 * @param String[] args is filename of file to be opened entered in commandline
	 */
	public void openFile(String[]args){

		//checks there is an argument. if no, program exits
		if (args.length < 1) {
			System.err.println("File name missing");
			System.exit(0);
		}
		
		//stores argument as a new File object
		File fileName = new File(args[0]);
		
		//checks file can be read. if no, program exits
		if (!fileName.canRead()) {
			System.err.printf("Cannot read from file %s\n", fileName.getName());
			System.exit(0);
		}
	}
	
	/**
	 * Get user's input and checks that it is only alphabet
	 * @return lowercase string of user's input after validated
	 */
	public String getUserInput(){
		Scanner getInput = new Scanner(System.in);
		System.out.println("Please input the word to search: ");
		String inputLine = getInput.nextLine();
		
		//make sure user inputs something
		if (inputLine.trim().length()==0){
			System.err.print("You entered no characters. I will now terminate!");
			System.exit(0);
		}
		
		//check individual characters to make sure they're all letters
		for (int i = 0; i<inputLine.length(); i++){
			if (!Character.isLetter(inputLine.charAt(i))){
				System.err.print("You entered something that wasn't a letter. I will now terminate!");
				System.exit(0);
			}
		}
		
		getInput.close();
		return inputLine.toLowerCase();
		
	}//getUserInput
}

