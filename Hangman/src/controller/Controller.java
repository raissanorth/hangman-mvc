package controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Hangman;
import view.UI;

public class Controller extends Application {

	UI ui;
	public Hangman hm;



	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		ui = new UI(); // view
		hm = new Hangman(ui); // model
		ui.setLimit(hm.getLimit()); // Hack
		ui.setHintText(hm.getHint()); // Hack
		ui.init(primaryStage); // start the view
		
		
		
		Button enterButton = ui.getbEnter();
		TextField guess = ui.input;

		guess.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	takeGuess();
	            }
	        }
	    });
		
		enterButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				takeGuess();
			}
		});
		
	}
	
	public void takeGuess(){
		ui.userGuess = ui.input.getText();
		ui.input.clear();
		hm.playGame();
		ui.hint.setText(hm.getHint());
		ui.guesses.setText("Guesses taken: " + Integer.toString(hm.getGuessesTaken()));
		ui.remainder.setText("Guesses left: " + Integer.toString(hm.getRemainingGuesses()));
		updateCharsTried();
		if (hm.checkWin()) {
			ui.won.setText(hm.win);
			ui.root.setBottom(ui.won);
		}
		if (hm.remainingGuesses <= 0 && !hm.checkWin()) {
			ui.gm.setText(hm.gameOver);
			ui.root.setBottom(ui.gm);
		}
	}
	
	/** Change text of labels to used chars and add label to VBox */
	public void updateCharsTried() {
		
		for (int i = ui.charsTried.length - 1; i >= 0; i--) {
			
			// make sure that value != default value
			if (hm.getUsedChars()[i] != '\u0000') {
				
				// charsTried at index i to hold upper case char from
				// getUsedChars at position i
				ui.charsTried[i] = new Label((Character.toString(hm.getUsedChars()[i])).toUpperCase());
				ui.fpchars.getChildren().add(ui.charsTried[i]);
				break;
			}
		}
	}

}
