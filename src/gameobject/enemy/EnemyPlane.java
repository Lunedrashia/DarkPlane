package gameobject.enemy;

import gameobject.effect.BombEffect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;
import render.RenderHolder;
import skill.ShootBasicBullet;

public class EnemyPlane extends Shooter {

	private static Image img = RenderHolder.imageCollection.get("EnemyPlane");

	public EnemyPlane(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 4;
		rotateSpeed = 3;
		hp = maxHP = 10;
		atk = 5;
		def = 0;
		skillList.add(new ShootBasicBullet(1000));
	}

	@Override
	public void draw(GraphicsContext gc) {
		basicDrawImage(gc, img, location, angle);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (GameLogic.getInstance().getPlayer() != null) {
			if (GameLogic.getInstance().getPlayer().getLocation().distance(location) < 1000) {
				this.useSkill(skillList.get(0), angle, img.getHeight()/2 + img.getWidth()/2 - 25);
			}
		}
		
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
		AudioClip explosionSound = RenderHolder.soundCollection.get("ExplosionSFX");
		explosionSound.play(0.2);
	}
	
}
