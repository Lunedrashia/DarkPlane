package logic;

public abstract class Skill {

	protected String name, description;
	protected boolean available = true; 
	
	public Skill(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract void activate(GameEntity user, int angle, double radius) throws SkillNotAvailableException;

	public void startCooldown(int ms) {
		available = false;
		Thread cooldown = new Thread(() -> {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			available = true;
		});
		cooldown.start();
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
