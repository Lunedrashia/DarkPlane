package render;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AnimationEffect implements Renderable {

	protected Point2D location;
	protected int angle;
	protected double size;
	protected boolean isAlive = true, isVisible = true;
	
	public AnimationEffect(double x, double y, int angle, double size) {
		this.location = new Point2D(x, y);
		this.angle = angle;
		this.size = size;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return isAlive;
	}

	public abstract void draw(GraphicsContext gc);
	
	protected void basicDrawImage(GraphicsContext gc, Image image) {
		gc.save();
		gc.translate(location.getX(), location.getY());
		gc.rotate(angle);
		gc.drawImage(image, -image.getWidth()*size/2, -image.getHeight()*size/2, image.getWidth()*size, image.getHeight()*size);
		gc.restore();
	}

}
