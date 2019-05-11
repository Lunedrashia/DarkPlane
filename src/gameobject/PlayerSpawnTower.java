package gameobject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
import logic.GameEntity;

public class PlayerSpawnTower extends GameEntity {

	private static Image img = new Image(ClassLoader.getSystemResourceAsStream("img/PlayerSpawnTower.png"));
	
	public PlayerSpawnTower(double x, double y) {
		super(x, y, 0);
		layer = 500;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		basicDrawImage(gc, img, location, angle);
	}

	@Override
	public void update() {
		
	}

	public Player spawnPlayer() {
		return new Player(location.getX(), location.getY(), angle);
	}

	@Override
	public Shape getBoundary() {
		return null;
	}
	
	
	
}
