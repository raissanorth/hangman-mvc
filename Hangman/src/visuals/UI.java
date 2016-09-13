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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

		FlowPane root = new FlowPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		

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
		root.getChildren().add(text);
		root.getChildren().add(input);
		root.getChildren().add(bEnter);
		root.getChildren().add(hint);
		root.getChildren().add(guesses);
		root.getChildren().add(remainder);
		root.getChildren().add(vbchars);

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
					root.getChildren().add(won);
				}
				if (hm.remainingGuesses <= 0 && !hm.checkWin()) {
					gm.setText(hm.gameOver);
					root.getChildren().add(gm);
				}

				// System.out.println("Action handled" + hm.getHint());
			}
		});

		Scene scene = new Scene(root, 500, 500);
		scene.getStylesheets().add("visuals/style.css");
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
