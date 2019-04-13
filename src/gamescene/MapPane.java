package gamescene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MapPane extends Canvas{

	private GraphicsContext gc;
	
	public MapPane(double width, double height) {
		super(width, height);
		gc = this.getGraphicsContext2D();
		this.setVisible(true);
	}
	
}
