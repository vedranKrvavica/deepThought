package supercomputerDeepThought;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DeepThought {
	
	// A little strange but our answers come in arrays so the answer to everything does too.
	public static final String[] answerToEverything = {"the answer to life, universe and everything is 42"};
	private static Map<String, String[]> questionAnswerPair = new HashMap<String, String[]>();
	// This is our super amazing clever exit strategy.
	public static String[] exitArray = {"quit", "exit", "mischief managed"};

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String input = "";
		initializeExitStrategy();
		
		System.out.println("I am Deep Thought, please ask me a question and I'll give it a thought and give you the answer.");
		
		while(checkout(input)) {
			
			if(input != null && !input.isBlank() && !input.isBlank()) {
				processInput(input);
			}
			
			// Added a colon just to make it more readable.
			System.out.printf(":");
			input = scan.nextLine();
		}
		
		scan.close();
		System.out.println("I hope you liked my answers. Have a nice day.");
	}
	
	private static void addQuestion(String questionString, String[] answers) {
		questionAnswerPair.put(questionString, answers);
	}

	public static void initializeExitStrategy() {
		addQuestion("How can I leave?", exitArray);
		addQuestion("How do I quit?", exitArray);
	}
	
	private static boolean checkout(String input) {
		return !Arrays.asList(exitArray).contains(input.toLowerCase());
	}
	
	private static void processInput(String input) {
		
		if(!input.contains("?")) {
			System.out.println("You dont seem to have asked a question please try again. Remember questions usually end with a \"?\"");
			return;
		}
		
		String[] inputArray = input.split("\\?", 2);
		if(inputArray.length == 0 || inputArray[0].isBlank()) {
			System.out.println("Questions usually require words, try writting some next time.");
			return;
		}
		
		String questionString = inputArray[0]+"?";
		questionString = checkQuestion(questionString);
		
		if(questionString == null) {
			printInstructions();
			return;
		}
		
		String answerString = inputArray[1];
		if(answerString.isEmpty()) {
			printAnswers(getAnswers(questionString));
			return;
		}
		
		String[] answers = checkAnswers(answerString);
		
		if(answers == null) {
			printInstructions();
			return;
		}
		
		addQuestion(questionString, answers);
	}

	private static void printInstructions() {
		System.out.println("The question you asked seemed to be invalid. Please follow one of the two following formats and remember that answers and questions have a max of 255 characters!");
		System.out.println("  <question>?");
		System.out.println("  <question>? \"<answer1>\" \"<answer2>\" \"<answer3>\" ...");
	}
	
	public static String[] getAnswers(String questionString) {
		return questionAnswerPair.containsKey(questionString) ? questionAnswerPair.get(questionString) : answerToEverything;
	}
	
	private static void printAnswers(String[] answers) {
		for(String answer : answers) {
			System.out.println("  - " + answer);
		}
	}

	private static boolean checkLength(String str) {
		return str.length()<=255;
	}

	public static String checkQuestion(String question) {
		// A blank question will never reach here, but for testing I left it in.
		return checkLength(question) && !question.replace("?", "").isBlank() ? question : null;
	}
	
	public static String[] checkAnswers(String answer) {

		if(!answer.contains("\"")) {
			return null;
		}
		
		Set<String> answers = new LinkedHashSet<String>();
		String[] answerArray = answer.split("(?<!\\\\)\"");
		
		if(answerArray.length == 1) {
			return null;
		}
		
		for(int i = 0 ; i < answerArray.length ; i++ ) {
			
			if((i%2 == 0 && !answerArray[i].isBlank()) || (i%2 == 0 && answerArray[i].length() != 1 && answerArray[i].isBlank()) || !checkLength(answerArray[i]) || (i%2 == 0 && i == answerArray.length-1 && answerArray[i].isBlank())) {
				return null;
			}
			
			// If the provided answer is not blank then save it, we want actual answers.
			if(!answerArray[i].isBlank()) {
				answers.add(answerArray[i]);
			}
		}
		
		// If this is reached it means the answers provided were all blanks which I consider an error.
		if(answers.isEmpty()) {
			return null;
		}
		
		String[] returnArray = new String[answers.size()];
		returnArray = answers.toArray(returnArray);
		return returnArray;
	}
}