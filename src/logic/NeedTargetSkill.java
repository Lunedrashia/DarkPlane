package logic;

public interface NeedTargetSkill {

	void activate(GameEntity user, int angle, double radius, GameEntity target) throws SkillNotAvailableException;
	
}
