package main;

import java.lang.Thread.State;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.GAME_STATUS;
import logic.GameLogic;
import logic.GraphicScreen;
import logic.Input;
import render.RenderHolder;
import ui.GameEnd;
import ui.InGameUI;
import ui.Loading;
import ui.MainMenuScene;

public class Main extends Application {
	
	private static Scene mainMenu;
	
	@Override
	public void start(Stage primaryStage) {
		// TODO Setup the menu
		
		MainMenuScene mainMenuScene = new MainMenuScene();
		mainMenuScene.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.close();
			}
		});
		mainMenuScene.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				loadScene(primaryStage, "TestScene");
			}
		});
		
		mainMenu = new Scene(mainMenuScene, 800, 600);
		primaryStage.setScene(mainMenu);
		primaryStage.setTitle("Game Name");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		// launch the game
		launch(args);
	}
	
	@SuppressWarnings("static-access")
	public static void loadScene(Stage primaryStage, String stageName) {
		StackPane root = new StackPane();
		Scene scene = new Scene(new BorderPane(root), 800, 600);
		
		RenderHolder.getInstance().reset();
		Input.reset();
		GraphicScreen graphic = new GraphicScreen(2000, 2000);
		GameLogic logic = new GameLogic(stageName);
		InGameUI inGameUI = new InGameUI(logic.getLifeLeft());
		root.setAlignment(inGameUI, Pos.TOP_LEFT);
		Loading loading = new Loading();
		root.setAlignment(loading, Pos.TOP_LEFT);
		GameEnd gameEnd = new GameEnd(10);
		root.setAlignment(gameEnd, Pos.TOP_LEFT);
		gameEnd.setVisible(false);
		
		root.getChildren().add(graphic);
		root.getChildren().add(inGameUI);
		root.getChildren().add(loading);
		root.getChildren().add(gameEnd);
		
		Rectangle camera = new Rectangle();
		camera.widthProperty().bind(scene.widthProperty());
        camera.heightProperty().bind(scene.heightProperty());
        root.setClip(camera);
        root.translateXProperty().bind(camera.xProperty().multiply(-1));
        root.translateYProperty().bind(camera.yProperty().multiply(-1));
		
		graphic.requestFocus();

		primaryStage.setScene(scene);
		
		loading.startCountdown(10000);
		AnimationTimer animation = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if (loading.getCountThread().getState() != Thread.State.TERMINATED) {
					return;
				}
				if (logic.getSpawnMonster().getState() == State.NEW) {
					logic.getSpawnMonster().start();
				}
				graphic.draw();
				logic.update();
				if (logic.getPlayer() != null) {
					camera.setX(clampRange(logic.getPlayer().getLocation().getX() - scene.getWidth()/2, 0, graphic.getWidth() - scene.getWidth()));
					camera.setY(clampRange(logic.getPlayer().getLocation().getY() - scene.getHeight()/2, 0, graphic.getHeight() - scene.getHeight()));
					inGameUI.setTranslateX(camera.getX());
					inGameUI.setTranslateY(camera.getY());
				}  
				RenderHolder.getInstance().update();
				Input.update();
				if (logic.getGameStatus() != GAME_STATUS.RUNNING) {	
					gameEnd.setTranslateX(camera.getX());
					gameEnd.setTranslateY(camera.getY());
					if (gameEnd.getCountdown().getState() == State.NEW) {
						gameEnd.setGameStatusText(logic.getGameStatus());
						gameEnd.startCountdown();
					}
					Thread changeBackToMainMenu = new Thread(() -> {
						try {
							gameEnd.getCountdown().join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Platform.runLater(() -> {
							primaryStage.setScene(mainMenu);
							this.stop();
						});
					});
					changeBackToMainMenu.start();
				}
			}
		};
		animation.start();
	}
	
	private static double clampRange(double value, double min, double max) {
        if (value < min) return min ;
        if (value > max) return max ;
        return value ;
    }

}
