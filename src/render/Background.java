package render;

import javafx.scene.canvas.GraphicsContext;

public abstract class Background implements Renderable {

	protected double x, y;
	
	public Background(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
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

	public abstract void draw(GraphicsContext gc);
	
}
