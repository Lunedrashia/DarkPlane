package main;

import gamescene.MainMenuScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) {
		// TODO Setup the menu
		MainMenuScreen mainMenu = new MainMenuScreen();
		setQuitButton(primaryStage, mainMenu.getQuitButton());
		
		primaryStage.setScene(new Scene(mainMenu, 800, 600));
		primaryStage.setTitle("Game Name");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		// launch the game
		launch(args);
	}
	
	private void setQuitButton(Stage primaryStage, Button q) {
		q.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.close();
			}
		});
	}
	
	private void setLoadScreenButton(Stage primaryStage, Scene s, Button b) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.setScene(s);
			}
		});
	}

}
