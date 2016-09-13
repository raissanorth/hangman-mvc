package visuals;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Hangman;

public class UI extends Application {

	public static String userGuess;
	public Label[] charsTried;
	public Hangman hm;
	public VBox vbchars;
	Label charTriedLabel;

	@Override
	public void start(Stage primaryStage) throws Exception {
		hm = new Hangman();

		GridPane root = new GridPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setVgap(10);
		root.setHgap(10);
		// for debugging uncomment below line (default value is false)
		// root.setGridLinesVisible(true);

		ToolBar top = new ToolBar();

		final Text text = new Text();
		text.setText("Hangman");
		text.setFont(Font.font("Arial", 30));

		vbchars = new VBox();

		charTriedLabel = new Label("Characters tried:");
		// create Label [] with same length as char [] in Hangman class
		charsTried = createCharsTried();
		System.out.println("charsTried length: " + charsTried.length); // TODO
																		// delete
																		// me

		Text won = new Text();
		Text gm = new Text();

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

		// layout - position elements on grid
		// (child, columnIndex, rowIndex, colspan, rowspan)
		root.add(text, 3, 1, 9, 3);
		root.add(input, 3, 5, 5, 2);
		root.add(bEnter, 9, 5, 4, 2);
		root.add(hint, 3, 8, 9, 2);
		root.add(guesses, 14, 6, 2, 1);
		root.add(remainder, 14, 5, 2, 1);
		root.add(vbchars, 0, 0, 3, 3);

		bEnter.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				userGuess = input.getText();
				input.clear();
				hm.playGame();
				hint.setText(hm.getHint());
				guesses.setText("Guesses taken: " + Integer.toString(hm.getGuessesTaken()));
				remainder.setText("Guesses left: " + Integer.toString(hm.getRemainingGuesses()));
				updateCharsTried();
				if (hm.checkWin()) {
					won.setText(hm.win);
					root.add(won, 3, 11);
				}
				if (hm.remainingGuesses <= 0 && !hm.checkWin()) {
					gm.setText(hm.gameOver);
					root.add(gm, 3, 11);
				}

				// System.out.println("Action handled" + hm.getHint());
			}
		});

		Scene scene = new Scene(root, 500, 500);
		// scene.getStylesheets().add(UI.class.getResource("style.css").toExternalForm());
		// //add css stylesheet
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public Label[] createCharsTried() {
		Label[] array = new Label[hm.getUsedChars().length];
		vbchars.getChildren().add(charTriedLabel);
		return array;
	}

	/* change text of labels to used chars and add label to VBox */
	public void updateCharsTried() {
		// TODO clear array or only add latest later rather than every single
		// one AGAIN

		for (int i = charsTried.length -1; i >= 0; i--) {
			// make sure that value != default value
			if (hm.getUsedChars()[i] != '\u0000') {
				// charsTried at index i to hold uppercase char from getUsedChars at position i
				charsTried[i] = new Label((Character.toString(hm.getUsedChars()[i])).toUpperCase());
				vbchars.getChildren().add(charsTried[i]);
				break;
			}
		}
	}
}
