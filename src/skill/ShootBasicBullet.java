package skill;

import gameobject.PlayerBasicBullet;
import logic.GameEntity;
import logic.GameLogic;
import logic.Skill;
import logic.SkillNotAvailableException;

public class ShootBasicBullet extends Skill {

	private static final int COOLDOWN = 500;
	
	public ShootBasicBullet() {
		super("Normal shot", "Shoot a normal bullet");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(GameEntity user, int angle, double radius) throws SkillNotAvailableException {
		// TODO Auto-generated method stub
		if (!available) {
			throw new SkillNotAvailableException("Skill on cooldown");
		}
		GameLogic.getInstance().addNewObject(
				new PlayerBasicBullet(user.getLocation().getX() + Math.cos(Math.toRadians(angle)) * radius 
						, user.getLocation().getY() + Math.sin(Math.toRadians(angle)) * radius, angle));
		startCooldown(COOLDOWN);
	}

}
