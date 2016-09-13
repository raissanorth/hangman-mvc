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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Hangman;

public class UI extends Application {

	public static String userGuess;
	Label charTriedLabel;
	public Label[] charsTried;
	public Hangman hm;
	public FlowPane fpchars;

	@Override
	public void start(Stage primaryStage) throws Exception {
		hm = new Hangman();

		// ====Top====================================================
		ToolBar top = new ToolBar();

		// ====Left===================================================
		fpchars = new FlowPane();
		fpchars.setPrefWrapLength(100);
		
		charTriedLabel = new Label("Characters tried:");
		// create Label [] with same length as char [] in Hangman class
		charsTried = createCharsTried();
		
		// ====Center==================================================
		final Text title = new Text();
		title.setText("Hangman");
		title.setFont(Font.font("Arial", 30));

		TextField input = new TextField();
		input.setPromptText("Enter a character.");
		input.setFocusTraversable(false);

		Button bEnter = new Button();
		bEnter.setText("Enter");

		Text hint = new Text();
		hint.setText(hm.getHint());

		GridPane grid = new GridPane();
		// (child, columnIndex, rowIndex, colspan, rowspan)
		grid.add(title, 0, 0, 2, 1);
		grid.add(input, 0, 1);
		grid.add(bEnter, 1, 1);
		grid.add(hint, 0, 2, 2, 1);

		Text won = new Text();
		Text gm = new Text();

		// ====Right====================================================
		Text guesses = new Text();
		guesses.setText("Guesses taken: " + Integer.toString(hm.getGuessesTaken()));

		Text remainder = new Text();
		remainder.setText("Guesses left: " + Integer.toString(hm.getRemainingGuesses()));
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(15, 12, 15, 12));
		vBox.setSpacing(10);
		vBox.getChildren().add(guesses);
		vBox.getChildren().add(remainder);

		// ====BorderPane================================================
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10, 10, 10, 10));

		root.setTop(top);
		root.setCenter(grid);
		root.setRight(vBox);
		root.setLeft(fpchars);

		// ====Scene====================================================

		Scene scene = new Scene(root, 500, 500);
		scene.getStylesheets().add("visuals/style.css");
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// ====Events====================================================
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
					root.setBottom(won);
				}
				if (hm.remainingGuesses <= 0 && !hm.checkWin()) {
					gm.setText(hm.gameOver);
					root.setBottom(gm);
				}
			}
		});
	}
	
	// ====Main====================================================

	public static void main(String[] args) {
		Application.launch(args);
	}

	public Label[] createCharsTried() {
		Label[] array = new Label[hm.getUsedChars().length];
		fpchars.getChildren().add(charTriedLabel);
		return array;
	}

	/** Change text of labels to used chars and add label to VBox */
	public void updateCharsTried() {
		// TODO clear array or only add latest later rather than every single
		// one AGAIN

		for (int i = charsTried.length - 1; i >= 0; i--) {
			// make sure that value != default value
			if (hm.getUsedChars()[i] != '\u0000') {
				// charsTried at index i to hold uppercase char from
				// getUsedChars at position i
				charsTried[i] = new Label((Character.toString(hm.getUsedChars()[i])).toUpperCase());
				fpchars.getChildren().add(charsTried[i]);
				break;
			}
		}
	}
}
