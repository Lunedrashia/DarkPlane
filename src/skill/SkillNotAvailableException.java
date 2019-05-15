package skill;

@SuppressWarnings("serial")
public class SkillNotAvailableException extends Exception {

	public String message;

	public SkillNotAvailableException(String message) {
		super();
		this.message = message;
	}
	
}
