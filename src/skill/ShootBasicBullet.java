package skill;

import gameobject.bullet.PlayerBasicBullet;
import logic.GameEntity;
import logic.GameLogic;

public class ShootBasicBullet extends Skill {

	private int cooldown;
	
	public ShootBasicBullet(int cooldown) {
		super("Normal shot", "Shoot a normal bullet");
		this.cooldown = cooldown;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(GameEntity user, int angle, double radius) throws SkillNotAvailableException {
		// TODO Auto-generated method stub
		if (!available) {
			throw new SkillNotAvailableException(name + " is on cooldown");
		}
		GameLogic.getInstance().addNewObject(
				new PlayerBasicBullet(user.getLocation().getX() + Math.cos(Math.toRadians(angle)) * radius 
						, user.getLocation().getY() + Math.sin(Math.toRadians(angle)) * radius, angle));
		startCooldown(cooldown);
	}

}
