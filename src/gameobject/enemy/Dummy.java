package gameobject.enemy;

import gameobject.effect.BombEffect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import render.RenderHolder;

public class Dummy extends Chaser {

	private static Image img = RenderHolder.imageCollection.get("Dummy");

	public Dummy(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 3;
		rotateSpeed = 3;
		hp = maxHP = 5;
		atk = 3;
		def = 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		basicDrawImage(gc, img, location, angle);
	}
	
	public Rectangle getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - img.getWidth()/2, 
				location.getY() - img.getHeight()/2, img.getWidth(), img.getHeight());
		boundary.setRotate(angle);
		return boundary;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		RenderHolder.getInstance().addNewObject(new BombEffect(location.getX(), location.getY(), 1));
	}

}
