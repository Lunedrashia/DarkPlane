package gameobject.enemy;

import gameobject.effect.BombEffect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import render.RenderHolder;

public class EyeMonster extends Chaser {

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
	
	public Circle getBoundary() {
		return new Circle(location.getX(), location.getY(), img.getHeight()/2);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		RenderHolder.getInstance().addNewObject(new BombEffect(location.getX(), location.getY(), 2));
		AudioClip explosionSound = RenderHolder.soundCollection.get("ExplosionSFX");
		explosionSound.play(0.4);
	}

}
