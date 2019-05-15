package logic;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
import render.Renderable;

public abstract class GameEntity implements Renderable {

	protected int hp, maxHP;
	protected int atk, def;
	protected int angle;
	protected Point2D location;
	protected int speed;
	protected int maxSpeed;
	protected int rotateSpeed;
	protected int layer;
	protected boolean isVisible, isAlive;
	protected boolean isInvincible = false;
	
	protected GameEntity(double x, double y, int angle) {
		isVisible = true;
		isAlive = true;
		location = new Point2D(x, y);
		this.angle = angle;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return layer;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return isAlive;
	}
	
	public void rotateRight() {
		angle += rotateSpeed;
		if (angle > 180) {
			angle -= 360;
		}
	}
	
	public void rotateLeft() {
		angle -= rotateSpeed;
		if (angle < -180) {
			angle += 360;
		}
	}
	
	public void forward() {
		if (speed < maxSpeed) {
			speed++;
		}
	}
	
	public void backward() {
		if (maxSpeed == 0) {
			return;
		}
		if (speed > 0) {
			speed--;
		}
	}
	
	public void move() {
		double dx = Math.cos(Math.toRadians(angle)) * speed;
		double dy = Math.sin(Math.toRadians(angle)) * speed;
		Point2D newLocation = location.add(dx, dy);
		Point2D arenaTopLeft = GameLogic.getInstance().getField().getArenaTopLeft();
		Point2D arenaBottomRight = GameLogic.getInstance().getField().getArenaBottomRight();
		if (location.getX() < arenaTopLeft.getX() || location.getY() < arenaTopLeft.getY()
				|| location.getX() > arenaBottomRight.getX() || location.getY() > arenaBottomRight.getY()) {
		}
		else if (newLocation.getX() < arenaTopLeft.getX() || newLocation.getY() < arenaTopLeft.getY()
				|| newLocation.getX() > arenaBottomRight.getX() || newLocation.getY() > arenaBottomRight.getY()) {
			if (this instanceof Bullet) {
				this.isAlive = false;
			}
			return;
		}
		location = newLocation;
	}
	
	public abstract Shape getBoundary();
	public abstract void update();
	
	public void onDestroy() {
		isAlive = false;
	}
	
	public boolean collideWith(GameEntity other) {
		if (other.getBoundary() == null || this.getBoundary() == null) {
			return false;
		}
		Shape collision = Shape.intersect(this.getBoundary(), other.getBoundary());
		return collision.getBoundsInLocal().getWidth() != -1;
	}
	
	public void dealDamage(GameEntity other) {
		if (other.isInvincible) {
			return;
		}
		int damage = this.atk - other.def < 0 ? 0 : this.atk - other.def;
		other.hp -= damage;
		if (other.hp <= 0) {
			other.onDestroy();
		}
	}
	
	protected void basicDrawImage(GraphicsContext gc, Image image, Point2D location, int angle) {
		gc.save();
		gc.translate(location.getX(), location.getY());
		gc.rotate(angle);
		gc.drawImage(image, -image.getWidth()/2, -image.getHeight()/2);
		gc.restore();
	}
	
	protected void useSkill(Skill skill, int angle, double radius) {
		try {
			skill.activate(this, angle, radius);
		} catch (SkillNotAvailableException e) {
			if (this == GameLogic.getInstance().getPlayer()) {
				System.out.println(e.message);
			}
		}
	}
	
	protected void useSkill(CanHaveTarget skill, int angle, double radius, GameEntity target) {
		try {
			skill.activate(this, angle, radius, target);
		} catch (SkillNotAvailableException e) {
			if (this == GameLogic.getInstance().getPlayer()) {
				System.out.println(e.message);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public Point2D getLocation() {
		return location;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		if (hp > maxHP) {
			hp = maxHP;
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getRotateSpeed() {
		return rotateSpeed;
	}

	public void setRotateSpeed(int rotateSpeed) {
		this.rotateSpeed = rotateSpeed;
	}

	public int getAngle() {
		return angle;
	}

	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public boolean isInvincible() {
		return isInvincible;
	}

	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

}
