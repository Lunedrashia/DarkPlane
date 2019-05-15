package gameobject;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.GameEntity;
import logic.Input;
import logic.Skill;
import render.RenderHolder;
import skill.BuckShot;
import skill.IncreaseSpeed1;
import skill.ShootBasicBullet;

public class Player extends GameEntity {

	private static final Image NORMAL_PLANE_IMAGE = RenderHolder.imageCollection.get("Plane");
	private static final Image FADE_PLANE_IMAGE = RenderHolder.imageCollection.get("FadePlane");
	
	private Image currentImage;
	private Image trailImage;
	private Skill skill1 = new ShootBasicBullet();
	private Skill skill2 = new BuckShot();
	private Skill skill3 = new IncreaseSpeed1();
	private ArrayList<Point2D> trailLocations;
	private ArrayList<Integer> trailAngles;
	private boolean fading = false;
	
	public Player(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 5;
		rotateSpeed = 3;
		hp = maxHP = 1;
		atk = 99;
		currentImage = NORMAL_PLANE_IMAGE;
		trailImage = FADE_PLANE_IMAGE;
		trailLocations = new ArrayList<Point2D>();
		trailAngles = new ArrayList<Integer>();
	}

	@Override
	public void draw(GraphicsContext gc) {
		for (int i = 0; i < trailLocations.size(); i++) {
			basicDrawImage(gc, trailImage, trailLocations.get(i), trailAngles.get(i));
		}
		basicDrawImage(gc, currentImage, location, angle);
	}

	@Override
	public void update() {
		if (fading) {
			trailLocations.add(location);
			trailAngles.add(angle);
			if (trailLocations.size() > 5) {
				trailLocations.remove(0);
				trailAngles.remove(0);
			}
		}
		else {
			if (trailLocations.size() > 0) {
				trailLocations.remove(0);
				trailAngles.remove(0);
			}
		}
		
		if (Input.checkKeyPressed(KeyCode.W)) {
			this.forward();
		}
		if (Input.checkKeyPressed(KeyCode.S) || speed > maxSpeed) {
			this.backward();
		}
		if (Input.checkKeyPressed(KeyCode.A)) {
			this.rotateLeft();
		}
		if (Input.checkKeyPressed(KeyCode.D)) {
			this.rotateRight();
		}
		this.move();
		if (Input.skill1Used) {
			this.useSkill(skill1, angle, currentImage.getWidth()/2 + currentImage.getHeight()/2 - 2);
		}
		else if (Input.skill2Used) {
			this.useSkill(skill2, angle, currentImage.getWidth()/2 + currentImage.getHeight()/2 - 2);
		}
		else if (Input.skill3Used) {
			this.useSkill(skill3, angle, 0);
		}
	}
	
	public Rectangle getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - currentImage.getWidth()/2,
				location.getY() - currentImage.getHeight()/2, currentImage.getWidth(), currentImage.getHeight());
		boundary.setRotate(angle);
		return boundary;
	}
	
	public void switchFadeEffect() {
		fading = !fading;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		RenderHolder.getInstance().addNewObject(new BombEffect(location.getX(), location.getY(), 1));
	}
	
}
