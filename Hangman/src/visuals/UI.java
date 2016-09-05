package visuals;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Hangman;

public class UI extends Application {

	public static String userGuess;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Hangman hm = new Hangman();
		// TODO Auto-generated method stub

		GridPane root = new GridPane();

		final Text text = new Text();
		text.setText("Hangman");
		text.setFont(Font.font("Arial", 30));

		Text hint = new Text();
		text.setText(hm.updateDisplay());

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

		bEnter.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				userGuess = input.getText();
				input.clear();
				hm.playGame();
			}
		});
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
