package render;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {

	int getLayer();
	void draw(GraphicsContext gc);
	boolean isDestroyed();
	boolean isVisible();
	
}
