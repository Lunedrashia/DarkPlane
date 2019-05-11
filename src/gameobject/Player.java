package gameobject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.GameEntity;
import logic.GameLogic;
import logic.Input;

public class Player extends GameEntity {

	private static final Image NORMAL_PLANE_IMAGE = new Image(ClassLoader.getSystemResourceAsStream("img/Plane.png"));
	private static final Image FADE_PLANE_IMAGE = new Image(ClassLoader.getSystemResourceAsStream("img/FadePlane.png"));
	
	private Image currentImage;
	private Image trailImage;
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
		if (Input.shotFired) {
			GameLogic.getInstance().addNewObject(
					new PlayerBasicBullet(location.getX() + Math.cos(Math.toRadians(angle)) * currentImage.getWidth(), 
							location.getY() + Math.sin(Math.toRadians(angle)) * currentImage.getHeight(), angle));
		}
	}
	
	public Rectangle getBoundary() {
		Rectangle boundary = new Rectangle(location.getX() - currentImage.getWidth()/2,
				location.getY() - currentImage.getHeight()/2, currentImage.getWidth(), currentImage.getHeight());
		boundary.setRotate(angle);
		return boundary;
	}
	
}