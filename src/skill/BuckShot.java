package skill;

import gameobject.PlayerBasicBullet;
import javafx.geometry.Point2D;
import logic.GameLogic;
import logic.Skill;
import logic.SkillNotAvailableException;

public class BuckShot extends Skill {

	private static final int COOLDOWN = 2000;
	private static final int DIFF_ANGLE = 7;
	private boolean available = true;
	
	public BuckShot() {
		super("Buck shot", "Shotgun is fun!!");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(Point2D location, int angle, double radius) throws SkillNotAvailableException {
		// TODO Auto-generated method stub
		if (!available) {
			throw new SkillNotAvailableException("Skill on cooldown");
		}
		for (int i = -3; i <= 3; i++) {
			GameLogic.getInstance().addNewObject(
				new PlayerBasicBullet(location.getX() + Math.cos(Math.toRadians(angle + DIFF_ANGLE*i)) * radius,
						location.getY() + Math.sin(Math.toRadians(angle + DIFF_ANGLE*i)) * radius,
						angle + DIFF_ANGLE*i));
		}
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

