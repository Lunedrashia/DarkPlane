package render;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import logic.Input;

public class GraphicScreen extends Canvas {
	
	public GraphicScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
		addListener();
	}
	
	private void addListener() {
		this.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.SPACE) {
				Input.shotFired = true;
			}
			else {
				Input.setKeyPressed(e.getCode());
			}
		});
		this.setOnKeyReleased((KeyEvent e) -> {
			if (e.getCode() != KeyCode.SPACE) {
				Input.releaseKey(e.getCode());
			}
		});
	}
	
	public void draw() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		for (int i = 0; i < RenderHolder.getInstance().getHolder().size(); i++) {
			Renderable currentRender = RenderHolder.getInstance().getHolder().get(i);
			if (currentRender.isAlive() && currentRender.isVisible()) {
				currentRender.draw(gc);
			}
		}
		
	}
	
}
