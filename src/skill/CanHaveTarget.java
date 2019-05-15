package skill;

import logic.GameEntity;

public interface CanHaveTarget {

	void activate(GameEntity user, int angle, double radius, GameEntity target) throws SkillNotAvailableException;
	
}