package logic;

import javafx.geometry.Point2D;

public abstract class Skill {

	private String name, description;
	
	public Skill(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract void activate(Point2D location, int angle, double radius) throws SkillNotAvailableException;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
