package gameobject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.GameEntity;
import logic.Input;
import logic.Skill;
import skill.BuckShot;
import skill.ShootBasicBullet;

public class Player extends GameEntity {

	private static final Image NORMAL_PLANE_IMAGE = new Image(ClassLoader.getSystemResourceAsStream("img/Plane.png"));
	@SuppressWarnings("unused")
	private static final Image FADE_PLANE_IMAGE = new Image(ClassLoader.getSystemResourceAsStream("img/FadePlane.png"));
	
	private Image currentImage;
	private Image trailImage;
	private Skill skill1 = new ShootBasicBullet();
	private Skill skill2 = new BuckShot();
	private Point2D previousLocation;
	private int previousAngle;
	
	public Player(double x, double y, int angle) {
		super(x, y, angle);
		maxSpeed = 5;
		rotateSpeed = 3;
		hp = maxHP = 1;
		atk = 0;
		currentImage = NORMAL_PLANE_IMAGE; 
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (trailImage != null) {
			basicDrawImage(gc, trailImage, previousLocation, previousAngle);
		}
		basicDrawImage(gc, currentImage, location, angle);
	}

	@Override
	public void update() {
		previousLocation = location;
		previousAngle = this.angle;
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
			this.useSkill(skill1, location, angle, currentImage.getWidth()/2 + currentImage.getHeight()/2 - 2);
		}
		else if (Input.skill2Used) {
			this.useSkill(skill2, location, angle, currentImage.getWidth()/2 + currentImage.getHeight()/2 - 2);
		}
	}
	
	public Rectangle getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - currentImage.getWidth()/2,
				location.getY() - currentImage.getHeight()/2, currentImage.getWidth(), currentImage.getHeight());
		boundary.setRotate(angle);
		return boundary;
	}
	
}
