package main;

import gamescene.MainMenuScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.Input;
import render.GraphicScreen;
import render.RenderHolder;

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
	
	public static void loadScene(Stage primaryStage, String stageName) {
		StackPane root = new StackPane();
		Scene scene = new Scene(new BorderPane(root), 800, 600);
		
		RenderHolder.getInstance().reset();
		Input.reset();
		GraphicScreen graphic = new GraphicScreen(2000, 2000);
		GameLogic logic = new GameLogic(stageName);
		root.getChildren().add(graphic);
		
		Rectangle camera = new Rectangle();
		camera.widthProperty().bind(scene.widthProperty());
        camera.heightProperty().bind(scene.heightProperty());
        root.setClip(camera);
        root.translateXProperty().bind(camera.xProperty().multiply(-1));
        root.translateYProperty().bind(camera.yProperty().multiply(-1));
        
		graphic.requestFocus();

		primaryStage.setScene(scene);
		
		AnimationTimer animation = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				graphic.draw();
				logic.update();
				if (logic.getPlayer() != null) {
					camera.setX(clampRange(logic.getPlayer().getX() - scene.getWidth()/2, 0, graphic.getWidth() - scene.getWidth()));
					camera.setY(clampRange(logic.getPlayer().getY() - scene.getHeight()/2, 0, graphic.getHeight() - scene.getHeight()));
				}  
				RenderHolder.getInstance().update();
				Input.update();
				if (!logic.getGameStatus()) {				
					primaryStage.setScene(mainMenu);
					this.stop();
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
