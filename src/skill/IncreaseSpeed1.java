package skill;

import gameobject.Player;
import logic.GameEntity;

public class IncreaseSpeed1 extends Skill {

	private final int COOLDOWN = 13000;
	private final int DURATION = 5000;
	
	public IncreaseSpeed1() {
		super("Speed Boost LV.1", "Increase user's speed for 5 seconds");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(GameEntity user, int angle, double radius) throws SkillNotAvailableException {
		// TODO Auto-generated method stub
		if (!available) {
			throw new SkillNotAvailableException("Skill on cooldown");
		}
		int originalMaxSpeed = user.getMaxSpeed();
		user.setMaxSpeed(originalMaxSpeed*2);
		user.setSpeed(originalMaxSpeed*2);
		if (user instanceof Player) {
			((Player) user).switchFadeEffect();
		}
		
		Thread effectDuration = new Thread(() -> {
			try {
				Thread.sleep(DURATION);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setMaxSpeed(originalMaxSpeed);
			if (user instanceof Player) {
				((Player) user).switchFadeEffect();
			}
		});
		effectDuration.start();
		
		startCooldown(COOLDOWN);
	}

}
