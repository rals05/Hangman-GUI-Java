import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class Main extends Application{

	TextField guess;
	TextField result;
	TextField word;
	Button[] alphabet;
	
	Circle head;
	Line torso, rightArm, leftArm, rightLeg, leftLeg;
	Shape[] bodyParts;
	int totalBodyParts = 6;
	Line base, vertical, top, rope;
	
	Hangman hangman = new Hangman();
	
	public static void main(String[] args) {
		launch(args);
	}//end main
	
	public void start(Stage stage) {
		hangman.rL();
		
		//------------- TITLE LABEL -------------
		Label title = new Label("Hangman - Marvel Edition");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		title.setAlignment(Pos.CENTER);
		
		//------------- TEXT FIELDS -------------
		Label gs = new Label("Current Guess");
		gs.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		gs.setAlignment(Pos.CENTER);
		guess = new TextField(hangman.getCurrentGuess().trim());
		guess.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		guess.setAlignment(Pos.CENTER);
		guess.setStyle("-fx-alignment: center;");
		guess.setEditable(false);
		
		Label res = new Label("Result");
		res.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		res.setAlignment(Pos.CENTER);
		result = new TextField();
		result.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		result.setAlignment(Pos.CENTER);
		result.setStyle("-fx-alignment: center;");
		result.setEditable(false);
		
		Label cw = new Label("Correct Word");
		cw.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		cw.setAlignment(Pos.CENTER);
		word = new TextField();
		word.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		word.setAlignment(Pos.CENTER);
		word.setStyle("-fx-alignment: center;");
		word.setEditable(false);
		
		
		//------------- ALPHABET + AGAIN BUTTONS -------------
		alphabet = new Button[26];
		int i = 0;
		for(char c = 'A'; c <= 'Z'; c++) {
			Button b = new Button(String.valueOf(c));
			b.setPrefSize(50, 50);
			b.setFocusTraversable(false);
			alphabet[i] = b;
			final char ch = c;
			b.setOnAction(e -> {
				checkLetter(Character.toLowerCase(ch));
				((Button)e.getSource()).setDisable(true);
			});
			i++;
		}
		
		Button again = new Button("Play Again");
		again.setPrefSize(100, 50);
		again.setOnAction(e -> {playAgain();});
		
		
		//------------- GRID FOR BUTTONS -------------
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		int n = 0;
		for(int j = 0; j <= 3; j++) {
			if(j < 3) {
				for(int k = 0; k < 7; k++) {
					grid.add(alphabet[n], k, j);
					n++;
				}
			}
			if(j == 3) {
				for(int k = 0; k < 5; k++) {
					grid.add(alphabet[n], k, j);
					n++;
				}
			}
		}
		
		
		//------------- HANGMAN DRAWING -------------
		Pane hangmanPane = new Pane();
		hangmanPane.setPrefSize(200, 200);
		hangmanPane.setTranslateX(180);
		hangmanPane.setTranslateY(-8);
		double w = hangmanPane.getPrefWidth();
		double h = hangmanPane.getPrefHeight();
		double y = h * 0.95;
		
		head = new Circle(w*0.55, h*0.2, h*0.08);
		head.setFill(Color.WHITE);
		head.setStroke(Color.BLACK);
		head.setVisible(false);
		
		torso = new Line(w*0.55, h*0.28, w*0.55, h*0.55);
		torso.setStroke(Color.BLACK);
		torso.setVisible(false);
		
		rightArm = new Line(w*0.55, h*0.32, w*0.45, h*0.45);
		rightArm.setStroke(Color.BLACK);
		rightArm.setVisible(false);
		
		leftArm = new Line(w*0.55, h*0.32, w*0.65, h*0.45);
		leftArm.setStroke(Color.BLACK);
		leftArm.setVisible(false);
		
		rightLeg = new Line(w*0.55, h*0.55, w*0.48, h*0.85);
		rightLeg.setStroke(Color.BLACK);
		rightLeg.setVisible(false);
		
		leftLeg = new Line(w*0.55, h*0.55, w*0.62, h*0.85);
		leftLeg.setStroke(Color.BLACK);
		leftLeg.setVisible(false);
		
		base = new Line(w*0.15, y, w*0.85, y);
		base.setStroke(Color.BLACK);
		base.setVisible(true);
		
		vertical = new Line(w*0.25, y, w*0.25, h*0.05);
		vertical.setStroke(Color.BLACK);
		vertical.setVisible(true);
		
		top = new Line(w*0.25, h*0.05, w*0.55, h*0.05);
		top.setStroke(Color.BLACK);
		top.setVisible(true);
		
		rope = new Line(w*0.55, h*0.05, w*0.55, h*0.12);
		rope.setStroke(Color.BLACK);
		rope.setVisible(true);
		
		hangmanPane.getChildren().addAll(base, vertical, top, rope, head, torso, rightArm, leftArm, rightLeg, leftLeg);
		bodyParts = new Shape[]{head, torso, rightArm, leftArm, rightLeg, leftLeg};

		
		//------------- ROOT LAYOUT -------------
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.TOP_CENTER);
		root.getChildren().addAll(title, hangmanPane, gs, guess, grid, res, result, cw, word, again);
		root.setSpacing(20);
		
		Scene scene = new Scene(root, 600, 800);
		stage.setTitle("Hangman - Marvel Edition");
		stage.setScene(scene);
		stage.show();

	}//end start
	
	
	//------------- HELPER METHODS -------------
	
	private void checkLetter(char ch) {
		hangman.check(ch);
		updateResultField();
		int indexToShow = totalBodyParts - hangman.getHangmanNum() - 1;
		if(indexToShow >= 0 && indexToShow < totalBodyParts)
			bodyParts[indexToShow].setVisible(true);
		updateRevealedLetters();
		
	}
	
	private void updateRevealedLetters() {
		String word = hangman.getCurrentGuess();
		guess.setText(word);
	}
	
	private void updateResultField() {
		if(hangman.deadMan() == false && hangman.wordCompleted() == true) {
			result.setText("You Win!");
			word.setText(hangman.getWord());
		}
		else {
			if(hangman.deadMan() == true) {
				result.setText("You Lose!");
				word.setText(hangman.getWord());
			}
			else {
				result.setText("Keep guessing...");
			}
		}
	}
	
	private void playAgain() {
		hangman = new Hangman();
		hangman.rL();
		guess.setText(hangman.getCurrentGuess());
		result.setText("");
		
		for(int i = 0; i < totalBodyParts; i++) {
			bodyParts[i].setVisible(false);
		}
		
		for(int i = 0; i < alphabet.length; i++) {
			alphabet[i].setDisable(false);
		}
	}
	

}//class main
