package gamescene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainMenuScene extends VBox {
	
	private Button playButton;
	private Button quitButton;
	
	public MainMenuScene() {
		// TODO Initialize objects in MenuScreen
		super();
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(25);
		this.setPadding(new Insets(10));
		
		Label gameTitle = new Label("Game Name");
		gameTitle.setFont(new Font(80));
		gameTitle.setPadding(new Insets(50, 10, 10, 10));
		
		playButton = generateButton("PLAY", 600, 50, 24);
		quitButton = generateButton("QUIT", 600, 50, 24);
		
		this.getChildren().addAll(gameTitle, playButton, quitButton);
	}
	
	public Button getPlayButton() {
		return playButton;
	}

	public Button getQuitButton() {
		return quitButton;
	}
	
	private Button generateButton(String name, double w, double h, double f) {
		Button button = new Button(name);
		button.setAlignment(Pos.CENTER);
		button.setPrefSize(w, h);
		button.setFont(new Font(f));
		return button;
	}
	
}
