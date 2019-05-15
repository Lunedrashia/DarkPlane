package render;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Field implements Renderable {

	protected Point2D location;
	protected Point2D playerSpawnLocation;
	protected Point2D arenaTopLeft, arenaBottomRight;
	
	public Field(double x, double y, double playerSpawnX, double playerSpawnY) {
		location = new Point2D(x, y);
		playerSpawnLocation = new Point2D(playerSpawnX, playerSpawnY);
	}
	
	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return -999;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return true;
	}

	public abstract void draw(GraphicsContext gc);

	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public Point2D getArenaTopLeft() {
		return arenaTopLeft;
	}

	public void setArenaTopLeft(Point2D arenaTopLeft) {
		this.arenaTopLeft = arenaTopLeft;
	}

	public Point2D getArenaBottomRight() {
		return arenaBottomRight;
	}

	public void setArenaBottomRight(Point2D arenaBottomRight) {
		this.arenaBottomRight = arenaBottomRight;
	}

	public Point2D getPlayerSpawnLocation() {
		return playerSpawnLocation;
	}

	public void setPlayerSpawnLocation(Point2D playerSpawnLocation) {
		this.playerSpawnLocation = playerSpawnLocation;
	}
	
}
