package gameobject.bullet;

import logic.GameEntity;

public abstract class Bullet extends GameEntity {

	protected Bullet(double x, double y, int angle) {
		super(x, y, angle);
	}
	
	public void startLifetime(int ms) {
		Thread killItself = new Thread(() -> {
			try {
				Thread.sleep(ms);
			}
			catch (InterruptedException e) {}
			this.isAlive = false;
		});
		killItself.start();
	}
	
}
