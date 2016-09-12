package logic;

import java.util.Random;
import java.util.Scanner;

import visuals.UI;

public class Hangman {

	int limit = 10;
	private int guessesTaken = 0;
	public int remainingGuesses = limit - guessesTaken;
	String target;
	public String win = "";
	public String gameOver = "";
	String hint;
	char guessChar;
	char[] charArray;
	char[] display;
	char[] usedChars;
	Scanner sc;
	String[] targetCollection = new String[] { "Haus", "Elephant", "Summer", "Collection", "Internet", "Photography",
			"Rose", "Germany", "Soul", "Music", "Telephone", "Dog", "Cat", "Bread", "Marmelade", "World", "Mouse" };

	public Hangman() {
		// setup for each game
		target = createTarget(); // returns target
		charArray = createCharArray();
		display = createDisplay();
		hint = updateHint();
		usedChars = createUsedChars();
	}

	public void playGame() {// game logic
		if (remainingGuesses > 0 && !checkWin()){

			String temp = (UI.userGuess).toLowerCase();// gets user guess
			guessChar = temp.charAt(0);// stores user guess (character only)
			usedChars[guessesTaken] = guessChar;
			display = checkGuess(); // checks guess and returns display array
			hint = updateHint();// returns String
			guessesTaken++;
			remainingGuesses = limit - guessesTaken;
			System.out.println("Guesses taken " + guessesTaken);
			System.out.println("Guesses remaining " + remainingGuesses);

			
			if (checkWin() == true) {
				win = "Yippie, you've won!";
			}
		}
		if (remainingGuesses <= 0) {
			gameOver = ("Game Over! Unfortunately, you ran out of guesses. \n" + "The word we were looking for was: "
					+ target);
		}
	}

	private String createTarget() {
		Random r = new Random();
		target = targetCollection[r.nextInt(targetCollection.length)];
		return target;
	}

	private char[] createCharArray() {
		charArray = target.toCharArray();
		return charArray;
	}

	public char[] createDisplay() {
		display = new char[charArray.length];
		for (int i = 0; i < display.length; i++) {
			display[i] = '_';
		}
		return display;
	}

	private char[] createUsedChars() {
		usedChars = new char[limit];
		return usedChars;
	}

	public char[] checkGuess() {
		for (int i = 0; i < charArray.length; i++) {
			if (guessChar == Character.toLowerCase(charArray[i])) {
				display[i] = charArray[i];
			}
		}
		return display;
	}

	public String updateHint() {
		hint = (Character.toString(display[0]));
		for (int i = 1; i < display.length; i++) {
			hint = hint + " " + display[i];
		}
		return hint;
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

	public String getHint() {
		return hint;
	}

	public int getGuessesTaken() {
		return guessesTaken;
	}
	public int getRemainingGuesses(){
		return remainingGuesses;
	}
	
	public char[] getUsedChars(){
		return usedChars;
	}
}	
	
	/* Design ideas:
	 * 
	 * Add button to allow users to chose limit of guesses.
	 * Implement high score
	 * Add hangman visuals
	 */
	 
