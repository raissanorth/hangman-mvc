package logic;

import java.util.Random;
import java.util.Scanner;

import visuals.UI;

public class Hangman {

	int limit = 10;
	int guessesTaken = 0;
	int remainingGuesses = limit - guessesTaken;
	String target;
	String win = "";
	String gameOver = "";
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
		usedChars = createUsedChars();
	}

	public void playGame() {// game logic
		while (guessesTaken < limit && !checkWin()) { // or remainingGuesses > 0 && !checkWin()

			String temp = (UI.userGuess).toLowerCase();// gets user guess
			guessChar = temp.charAt(0);// stores user guess (character only)
			usedChars[guessesTaken] = guessChar;
			checkGuess();
			guessesTaken++;
			updateDisplay(); // returns hint

			if (checkWin() == true) {
				win = "Yippie, you've won! You only took " + guessesTaken + " guesses.";
			}

			if (checkWin() != true) {
				gameOver = ("Game Over! Unfortunately, you ran out of guesses. \n"
						+ "The word we were looking for was: " + target);
			}
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

	private char[] createDisplay() {
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

	public String updateDisplay() { // TODO could be smoother!
		char[] array = new char[display.length];

		for (int i = 0; i < display.length; i++) {
			array[i] = (display[i]);
		}
		String hint = new String(array);
		return hint;
	}

	/*
	 * public void displayHint() { for (int i = 0; i < display.length; i++) {
	 * System.out.print(display[i]); } System.out.println(); System.out.print(
	 * "Characters used so far: ");
	 * 
	 * for (int i = 0; i < usedChars.length; i++) {
	 * System.out.print(usedChars[i] + " "); } System.out.println();
	 * 
	 * }
	 */

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
}