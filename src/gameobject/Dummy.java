package gameobject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.Enemy;

public class Dummy extends Enemy {

	private static Image img = new Image(ClassLoader.getSystemResourceAsStream("img/DummyAgain.png"));

	public Dummy(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 5;
		rotateSpeed = 3;
		hp = maxHP = 5;
		atk = 3;
		def = 9;
	}

	@Override
	public void draw(GraphicsContext gc) {
		basicDrawImage(gc, img, location, angle);
	}

	@Override
	public void update() {

	}
	
	public Rectangle getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - img.getWidth()/2, 
				location.getY() - img.getHeight()/2, img.getWidth(), img.getHeight());
		boundary.setRotate(angle);
		return boundary;
	}

}
