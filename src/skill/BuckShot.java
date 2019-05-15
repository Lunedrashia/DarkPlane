package skill;

import gameobject.bullet.PlayerBasicBullet;
import logic.GameEntity;
import logic.GameLogic;
import logic.Skill;
import logic.SkillNotAvailableException;

public class BuckShot extends Skill {

	private static final int COOLDOWN = 2000;
	private static final int DIFF_ANGLE = 7;
	
	public BuckShot() {
		super("Buck shot", "Shotgun is fun!!");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(GameEntity user, int angle, double radius) throws SkillNotAvailableException {
		// TODO Auto-generated method stub
		if (!available) {
			throw new SkillNotAvailableException("Skill on cooldown");
		}
		for (int i = -3; i <= 3; i++) {
			GameLogic.getInstance().addNewObject(
				new PlayerBasicBullet(user.getLocation().getX() + Math.cos(Math.toRadians(angle + DIFF_ANGLE*i)) * radius,
						user.getLocation().getY() + Math.sin(Math.toRadians(angle + DIFF_ANGLE*i)) * radius,
						angle + DIFF_ANGLE*i));
		}
		startCooldown(COOLDOWN);
	}

}

