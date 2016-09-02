package visuals;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Hangman;

public class UI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Hangman hm = new Hangman();
		// TODO Auto-generated method stub
		
		GridPane root = new GridPane();
		
		final Text text = new Text();
		text.setText("Hangman");
		
		Text hint = new Text();
		text.setText(hm.displayHint());
	
		
		TextField input = new TextField();
		input.setPromptText("Enter a character.");
		input.setFocusTraversable(false);
		
		
		root.add(text, 1, 3, 9, 3);
		root.add(input, 3, 9);
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main (String [] args){
		Application.launch(args);
	}

}
