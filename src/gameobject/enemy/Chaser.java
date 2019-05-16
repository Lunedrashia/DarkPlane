package gameobject.enemy;

import javafx.geometry.Point2D;
import logic.GameLogic;

public abstract class Chaser extends Enemy {

	protected Chaser(double x, double y, int angle) {
		super(x, y, angle);
		// TODO Auto-generated constructor stub
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
		
		int rotationDifference = angle - targetAngle;

		if(Math.abs(rotationDifference) > 180){
		    rotationDifference += rotationDifference > 0 ? -360 : 360; 
		}

		if(rotationDifference < 0){
		    rotateRight();
		}
		else if(rotationDifference > 0){
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

}
