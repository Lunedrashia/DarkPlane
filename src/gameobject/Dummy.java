package gameobject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.Enemy;
import logic.GameLogic;

public class Dummy extends Enemy {

	private static Image img = new Image(ClassLoader.getSystemResourceAsStream("img/DummyAgain.png"));

	public Dummy(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 3;
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
		//follow the player
		if (GameLogic.getInstance().getPlayer() == null) {
			return;
		}
		Point2D playerLocation = GameLogic.getInstance().getPlayer().getLocation();
		double dx = playerLocation.getX() - location.getX();
		double dy = playerLocation.getY() - location.getY();
		int targetAngle = (int) Math.toDegrees(Math.atan2(dy, dx));
		System.out.println(targetAngle);
		if (targetAngle > angle) {
			rotateRight();
		}
		else if (targetAngle < angle) {
			rotateLeft();
		}
		this.forward();
		if (Math.abs(targetAngle - angle) < 30) {
			this.move();
		}
	}
	
	public Rectangle getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - img.getWidth()/2, 
				location.getY() - img.getHeight()/2, img.getWidth(), img.getHeight());
		boundary.setRotate(angle);
		return boundary;
	}

}
