package visuals;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Hangman;

public class UI extends Application {

	public static String userGuess;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Hangman hm = new Hangman();

		GridPane root = new GridPane();

		final Text text = new Text();
		text.setText("Hangman");
		text.setFont(Font.font("Arial", 30));

		Text hint = new Text();
		hint.setText(hm.getHint());
		
		Text guesses = new Text();
		guesses.setText("Guesses taken: " + Integer.toString(hm.getGuessesTaken()));
		
		Text remainder = new Text();
		remainder.setText("Guesses left: " + Integer.toString(hm.getRemainingGuesses()));
				
		TextField input = new TextField();
		input.setPromptText("Enter a character.");
		input.setFocusTraversable(false);
		
	
		Button bEnter = new Button();
		bEnter.setText("Enter");
		
		//layout - position elements on grid
		//(child, columnIndex, rowIndex, colspan, rowspan)
		root.add(text, 3, 1, 9, 3);
		root.add(input, 3, 5, 5, 2);
		root.add(bEnter,9, 5, 4, 2);
		root.add(hint, 3, 8, 9, 2);
		root.add(guesses, 14, 6, 2, 1);
		root.add(remainder, 14, 5, 2, 1);
		
		bEnter.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				userGuess = input.getText();
				input.clear();
				hm.playGame();
				hint.setText(hm.getHint());
				guesses.setText("Guesses taken: " + Integer.toString(hm.getGuessesTaken()));
				remainder.setText("Guesses left: " + Integer.toString(hm.getRemainingGuesses()));
				
				//System.out.println("Action handled" + hm.getHint());
			}
		});
		
		Scene scene = new Scene(root, 500, 500);
		//scene.getStylesheets().add(UI.class.getResource("style.css").toExternalForm()); //add css stylesheet
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
