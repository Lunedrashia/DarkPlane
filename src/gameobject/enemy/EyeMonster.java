package gameobject.enemy;

import gameobject.effect.BombEffect;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import logic.Enemy;
import logic.GameLogic;
import render.RenderHolder;

public class EyeMonster extends Enemy {

	private static Image img = RenderHolder.imageCollection.get("EyeMonster");

	public EyeMonster(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 2;
		rotateSpeed = 3;
		hp = maxHP = 25;
		atk = 5;
		def = 0;
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
		//System.out.println(targetAngle);
		if (angle < targetAngle) {
			rotateRight();
		}
		else if (angle > targetAngle) {
			rotateLeft();
		}
		if (Math.abs(targetAngle - angle) < 30) {
			this.forward();
		}
		else {
			this.backward();
		}
		this.move();
	}
	
	public Circle getBoundary() {
		return new Circle(location.getX(), location.getY(), img.getHeight()/2);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		RenderHolder.getInstance().addNewObject(new BombEffect(location.getX(), location.getY(), 2));
	}

}
