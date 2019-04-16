package render;

import javafx.scene.canvas.GraphicsContext;

public class Background implements Renderable {

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return -999;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
}
