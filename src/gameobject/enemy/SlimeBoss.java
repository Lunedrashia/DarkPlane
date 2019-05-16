package gameobject.enemy;

import gameobject.effect.BombEffect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import logic.GameLogic;
import render.RenderHolder;
import skill.ShootBasicBullet;
import skill.SpawnDummy;

public class SlimeBoss extends Shooter {

	private static final Image NORMAL_IMAGE = RenderHolder.imageCollection.get("Slime1");
	private static final Image USE_SKILL_IMAGE = RenderHolder.imageCollection.get("Slime2");
	
	private Image currentImage;
	private Thread closeMouth;

	public SlimeBoss(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 6;
		rotateSpeed = 4;
		hp = maxHP = 50;
		atk = 99;
		def = 0;
		currentImage = NORMAL_IMAGE;
		skillList.add(new ShootBasicBullet(1000));
		skillList.add(new SpawnDummy());
	}

	@Override
	public void draw(GraphicsContext gc) {
		basicDrawImage(gc, currentImage, location, angle);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (GameLogic.getInstance().getPlayer() != null) {
			if (GameLogic.getInstance().getEnemies().size() < 2) {
				if (this.useSkill(skillList.get(1), angle, currentImage.getWidth() / 2 + 32)) {
					openMouth();
					return;
				}
			}
			if (GameLogic.getInstance().getPlayer().getLocation().distance(location) < 1000) {
				if (this.useSkill(skillList.get(0), angle, currentImage.getWidth() / 2 + 5)) {
					openMouth();
					return;
				}
			}
		}
	}
	
	public Circle getBoundary() {
		return new Circle(location.getX(), location.getY(), currentImage.getWidth()/2);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		RenderHolder.getInstance().addNewObject(new BombEffect(location.getX(), location.getY(), 1));
		AudioClip explosionSound = RenderHolder.soundCollection.get("ExplosionSFX");
		explosionSound.play(0.3);
	}
	
	private void openMouth() {
		currentImage = USE_SKILL_IMAGE;
		closeMouth = new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentImage = NORMAL_IMAGE;
		});
		closeMouth.start();
	}
	
}
