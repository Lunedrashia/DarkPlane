package gamescene;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import logic.GameLogic;
import logic.Input;
import render.GraphicScreen;
import render.RenderHolder;

public class TestScene extends Scene {

	private static StackPane root = new StackPane();
	
	public TestScene() {
		super(root);
		GraphicScreen graphic = new GraphicScreen(800, 600);
		GameLogic logic = new GameLogic(this.getClass().getSimpleName());
		root.getChildren().add(graphic);
		graphic.requestFocus();
		
		AnimationTimer animation = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				graphic.draw();
				logic.update();
				RenderHolder.getInstance().update();
				Input.update();
				if (!logic.getGameStatus()) {
					graphic.draw();
					this.stop();
				}
			}
		};
		
		animation.start();
	}
	
}
