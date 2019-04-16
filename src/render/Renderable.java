package render;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {

	int getLayer();
	boolean isVisible();
	boolean isAlive();
	void draw(GraphicsContext gc);
	
}
