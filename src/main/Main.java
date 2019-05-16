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
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.GAME_STATUS;
import logic.GameLogic;
import logic.GraphicScreen;
import logic.Input;
import render.RenderHolder;
import ui.GameEndScreen;
import ui.InGameUI;
import ui.LoadingScreen;
import ui.MainMenuScreen;

public class Main extends Application {

	private static Scene mainMenu;
	private static boolean isOnMainMenu = true;
	private static boolean isInGame = false;
	
	private Thread bgmLoop;
	private AudioClip menuBGM;
	private AudioClip gameBGM;

	@Override
	public void start(Stage primaryStage) {
		// TODO Setup the menu

		LoadingScreen loading = new LoadingScreen();
		loading.startCountdown(99999);
		Thread t = new Thread(() -> {
			while (!RenderHolder.checkHaveMenuResourceLoaded()) {
			}
			
			menuBGM = RenderHolder.soundCollection.get("MenuBGM");
			gameBGM = RenderHolder.soundCollection.get("GameBGM");
			MainMenuScreen mainMenuScene = new MainMenuScreen();
			setupBGMLoopThread();

			mainMenuScene.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					AudioClip sound = RenderHolder.soundCollection.get("ClickSound");
					sound.play(0.2);
					isOnMainMenu = false;
					loadScene(primaryStage, "TestScene");
				}
			});
			mainMenu = new Scene(mainMenuScene, 800, 600);
			Platform.runLater(() -> {
				primaryStage.getIcons().add(RenderHolder.imageCollection.get("GameIcon"));
				primaryStage.setScene(mainMenu);
			});

		});
		t.start();

		primaryStage.setScene(new Scene(loading, 800, 600));
		primaryStage.setTitle("DarkPlane");
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
		LoadingScreen loading = new LoadingScreen();
		root.setAlignment(loading, Pos.TOP_LEFT);
		GameEndScreen gameEnd = new GameEndScreen(10);
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

		if (RenderHolder.getInstance().checkHaveLoaded()) {
			loading.startCountdown(9000);
		} else {
			loading.startCountdown(15000);
		}

		AnimationTimer animation = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if (loading.getCountThread().getState() != Thread.State.TERMINATED) {
					return;
				}
				isInGame = true;
				graphic.draw();
				logic.update();
				if (logic.getPlayer() != null) {
					camera.setX(clampRange(logic.getPlayer().getLocation().getX() - scene.getWidth() / 2, 0,
							graphic.getWidth() - scene.getWidth()));
					camera.setY(clampRange(logic.getPlayer().getLocation().getY() - scene.getHeight() / 2, 0,
							graphic.getHeight() - scene.getHeight()));
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
							isOnMainMenu = true;
							isInGame = false;
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
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}
	
	public void setupBGMLoopThread() {
		bgmLoop = new Thread(() -> {
			while (true) {
				if (isOnMainMenu) {
					if (!menuBGM.isPlaying()) {
						menuBGM.play(0.2);
					}
				}
				else {
					menuBGM.stop();
				}
				if (isInGame) {
					if (!gameBGM.isPlaying()) {
						gameBGM.play(0.2);
					}
				}
				else {
					gameBGM.stop();
				}
			}
		});
		bgmLoop.start();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		Platform.exit();
		System.exit(0);
	}

}
