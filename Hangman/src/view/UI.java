package view;

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
import model.Hangman;

public class UI{

	public String userGuess;
	Label charTriedLabel;
	public Label[] charsTried;
//	public Hangman hm;
	public FlowPane fpchars;
	int limit;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Label[] createCharsTried() {
		Label[] array = new Label[limit];
		fpchars.getChildren().add(charTriedLabel);
		return array;
	}


	ToolBar top;
	Text title;
	public TextField input;
	Button bEnter;
	public Button getbEnter() {
		return bEnter;
	}

	public void setbEnter(Button bEnter) {
		this.bEnter = bEnter;
	}
	public Text hint;
	public Text won;
	public Text gm;
	public GridPane grid;
	public Text guesses;
	public Text remainder;
	public VBox vBox;
	public BorderPane root;
	public Scene scene;
	String hintText;
	public String getHintText() {
		return hintText;
	}

	public void setHintText(String hintText) {
		this.hintText = hintText;
	}

	public void init(Stage primaryStage) {

		// ====Top====================================================
		top = new ToolBar();

		// ====Left===================================================
		fpchars = new FlowPane();
		fpchars.setPrefWrapLength(100);
		
		charTriedLabel = new Label("Characters tried:");
		// create Label [] with same length as char [] in Hangman class
		charsTried = createCharsTried();
		
		// ====Center==================================================
		title = new Text();
		title.setText("Hangman");
		title.setFont(Font.font("Arial", 30));

		input = new TextField();
		input.setPromptText("Enter a character.");
		input.setFocusTraversable(false);

		bEnter = new Button();
		bEnter.setText("Enter");

		hint = new Text();
		hint.setText(hintText);

		grid = new GridPane();
		// (child, columnIndex, rowIndex, colspan, rowspan)
		grid.add(title, 0, 0, 2, 1);
		grid.add(input, 0, 1);
		grid.add(bEnter, 1, 1);
		grid.add(hint, 0, 2, 2, 1);

		won = new Text();
		gm = new Text();

		// ====Right====================================================
		guesses = new Text();
		guesses.setText("Guesses taken: 0");

		remainder = new Text();
		remainder.setText("Guesses left: " + limit);
		vBox = new VBox();
		vBox.setPadding(new Insets(15, 12, 15, 12));
		vBox.setSpacing(10);
		vBox.getChildren().add(guesses);
		vBox.getChildren().add(remainder);

		// ====BorderPane================================================
		root = new BorderPane();
		root.setPadding(new Insets(10, 10, 10, 10));

		root.setTop(top);
		root.setCenter(grid);
		root.setRight(vBox);
		root.setLeft(fpchars);

		// ====Scene====================================================

		scene = new Scene(root, 500, 500);
		scene.getStylesheets().add("view/style.css");
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
