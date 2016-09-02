package logic;

import java.util.Random;
import java.util.Scanner;

public class Hangman {
	
	int limit = 10;
	int guessesTaken = 0;
	String target;
	// collection of chars in target
	char guessChar;
	char[] charArray;
	char[] display;
	char[] usedChars;
	Scanner sc;
	String [] targetCollection = new String [] {"Haus", "Elephant", "Summer", "Collection", "Internet", "Photography", 
			"Rose", "Germany", "Soul", "Music", "Telephone", "Dog", "Cat", "Bread", "Marmelade", "World", "Mouse"};

	public Hangman() {
		sc = new Scanner(System.in);
		createTarget(); //TODO
		usedChars = new char[10];
		while (guessesTaken < limit && !checkWin()) {
			System.out.println("Guess a character. You have " + (limit - guessesTaken) + " guesses left.");
			String temp = sc.next();
			guessChar = temp.charAt(0);
			usedChars[guessesTaken] = guessChar;
			checkGuess();
			guessesTaken++;
			displayHint();

			if (checkWin() == true) {
				System.out.println("Yippie, you've won! You only took " + guessesTaken + " guesses.");

			}
		}
		if (checkWin() != true) {
			System.out.println("Game Over! Unfortunately, you ran out of guesses. \n"
					+ "The word we were looking for was: " + target);
		}
		sc.close();
	}

//	public void createTarget() {
//		System.out.println("Please enter the word you want users to guess.");
//		target = sc.next();
//		charArray = target.toCharArray();
	
//		display = new char[charArray.length];
//		for (int i = 0; i < display.length; i++) {
//			display[i] = '_';
//		}
	
	public String createTarget(){
		Random r = new Random();
		target = targetCollection [r.nextInt(targetCollection.length-1)];
		display = new char[charArray.length];
		for (int i = 0; i < display.length; i++) {
			display[i] = '_';
		}
		return target;
	}

	public void checkGuess() {
		for (int i = 0; i < charArray.length; i++) {
			if (guessChar == (charArray[i])) {
				display[i] = charArray[i];
			}
		}
	}

	public Boolean checkWin() {
		Boolean won = true;
		for (int i = 0; i < display.length; i++) {
			if (display[i] == ('_')) {
				won = false;
				break;
			}
		}
		return won;
	}

	/*public void displayHint() {
		System.out.print("HANGMAN: ");
		for (int i = 0; i < display.length; i++) {
			System.out.print(display[i]);
		}
		System.out.println();
		System.out.print("Characters used so far: ");

		for (int i = 0; i < usedChars.length; i++) {
			System.out.print(usedChars[i] + " ");
		}
		System.out.println();

	}*/

	public String displayHint() {
		char[] array = new char [display.length];
		
		for (int i = 0; i < display.length; i++) {
			array [i] = (display[i]);
		}
		String hint = new String(array);
		return hint;
	}

	public static void main(String[] args) {
		Hangman h = new Hangman();
	}
}
/*
 * 
 * chars in target String display chars guessed correctly by user placeholder _
 * for chars not revealed yet ? visual output for guesses taken/limit
 * 
 * x target String x int limit x int guesses x create Scanner object to read
 * user input x GameOver when guesses >= limit
 * 
 * 
 * 
 */