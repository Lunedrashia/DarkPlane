package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import render.RenderHolder;

public class MainMenuScreen extends StackPane {

	private Button playButton;

	public MainMenuScreen() {
		// TODO Initialize objects in MenuScreen
		super();
		this.getStylesheets().add(ClassLoader.getSystemResource("css/PlayButtonStyle.css").toString());

		ImageView bg = new ImageView(RenderHolder.imageCollection.get("MainMenuBG"));

		playButton = generateButton("PLAY");
		playButton.setTranslateX(30);
		playButton.setTranslateY(-30);
		setAlignment(playButton, Pos.BOTTOM_LEFT);

		this.getChildren().addAll(bg, playButton);
	}

	public Button getPlayButton() {
		return playButton;
	}

	private Button generateButton(String name) {
		Button button = new Button(name);
		button.getStyleClass().add("button");
		return button;
	}

}
