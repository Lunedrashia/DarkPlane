package skill;

import gameobject.PlayerBasicBullet;
import javafx.geometry.Point2D;
import logic.GameLogic;
import logic.Skill;
import logic.SkillNotAvailableException;

public class ShootBasicBullet extends Skill {

	private static final int COOLDOWN = 500;
	private boolean available = true;
	
	public ShootBasicBullet() {
		super("Normal shot", "Shoot a normal bullet");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(Point2D location, int angle, double radius) throws SkillNotAvailableException {
		// TODO Auto-generated method stub
		if (!available) {
			throw new SkillNotAvailableException("Skill on cooldown");
		}
		GameLogic.getInstance().addNewObject(
				new PlayerBasicBullet(location.getX() + Math.cos(Math.toRadians(angle)) * radius 
						, location.getY() + Math.sin(Math.toRadians(angle)) * radius, angle));
		available = false;
		Thread cooldown = new Thread(() -> {
			try {
				Thread.sleep(COOLDOWN);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			available = true;
		});
		cooldown.start();
	}

}
