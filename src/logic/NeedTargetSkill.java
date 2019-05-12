package logic;

import javafx.geometry.Point2D;

public interface NeedTargetSkill {

	void activate(Point2D location, int angle, double radius, GameEntity target) throws SkillNotAvailableException;
	
}
