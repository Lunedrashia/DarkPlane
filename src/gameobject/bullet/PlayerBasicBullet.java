package gameobject.bullet;

import gameobject.effect.BombEffect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import logic.Bullet;
import render.RenderHolder;

public class PlayerBasicBullet extends Bullet {

	private final static int LIFETIME = 3000;
	private final static double LENGTH = 30;
	private final static double WIDTH = 5;
	
	public PlayerBasicBullet(double x, double y, int angle) {
		super(x, y, angle);
		speed = maxSpeed = 20;
		hp = maxHP = 1;
		atk = 1;
		location.add(Math.cos(Math.toRadians(angle)) * LENGTH / 2, Math.sin(Math.toRadians(angle)) * WIDTH / 2);
		startLifetime(LIFETIME);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.save();
		gc.translate(location.getX(), location.getY());
		gc.rotate(angle);
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, LENGTH, WIDTH);
		gc.restore();
	}

	@Override
	public Shape getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - LENGTH/2, location.getY() - WIDTH/2, 20, 2);
		boundary.setRotate(angle);
		return boundary;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.move();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		RenderHolder.getInstance().addNewObject(new BombEffect(location.getX(), location.getY(), 1));
	}

}
