package skill;

import gameobject.enemy.Dummy;
import javafx.application.Platform;
import logic.GameEntity;
import logic.GameLogic;

public class SpawnDummy extends Skill {

private final int COOLDOWN = 10000;
	
	public SpawnDummy() {
		super("Spawn Dummy (Rusher)", "Spawn Dummy monster to attack enemy");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(GameEntity user, int angle, double radius) throws SkillNotAvailableException {
		// TODO Auto-generated method stub
		if (!available) {
			throw new SkillNotAvailableException(name + " is on cooldown");
		}
		Platform.runLater(() -> {
			GameLogic.getInstance().addNewObject(
				new Dummy(user.getLocation().getX() + Math.cos(Math.toRadians(angle)) * radius 
						, user.getLocation().getY() + Math.sin(Math.toRadians(angle)) * radius, angle));
		});
		
		startCooldown(COOLDOWN);
	}
	
}
