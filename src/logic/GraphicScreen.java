package logic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import render.RenderHolder;
import render.Renderable;

public class GraphicScreen extends Canvas {
	
	public GraphicScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
		addListener();
	}
	
	private void addListener() {
		this.setOnKeyPressed((KeyEvent e) -> {
			Input.setKeyPressed(e.getCode());
		});
		this.setOnKeyReleased((KeyEvent e) -> {
			if (e.getCode() == Input.skill3Key) {
				Input.skill3Used = true;
			}
			Input.releaseKey(e.getCode());
		});
	}
	
	public void draw() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		for (int i = 0; i < RenderHolder.getInstance().getAllRender().size(); i++) {
			Renderable currentRender = RenderHolder.getInstance().getAllRender().get(i);
			if (currentRender.isAlive() && currentRender.isVisible()) {
				currentRender.draw(gc);
			}
		}
		
	}
	
}
