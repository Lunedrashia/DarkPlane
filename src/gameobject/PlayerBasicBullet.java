package gameobject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import logic.Bullet;

public class PlayerBasicBullet extends Bullet {

	private final static int LIFETIME = 3000;
	private final static double WIDTH = 20;
	private final static double HEIGHT = 2;
	
	public PlayerBasicBullet(double x, double y, int angle) {
		super(x, y, angle);
		speed = maxSpeed = 7;
		hp = maxHP = 1;
		atk = 1;
		location.add(Math.cos(Math.toRadians(angle)) * WIDTH / 2, Math.sin(Math.toRadians(angle)) * HEIGHT / 2);
	
		Thread killItself = new Thread(() -> {
			try {
				Thread.sleep(LIFETIME);
			}
			catch (InterruptedException e) {}
			this.alive = false;
		});
		killItself.start();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.save();
		gc.translate(location.getX(), location.getY());
		gc.rotate(angle);
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, 20, 2);
		gc.restore();
	}

	@Override
	public Shape getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - 10, location.getY() - 1, 20, 2);
		boundary.setRotate(angle);
		return boundary;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.move();
	}

}
