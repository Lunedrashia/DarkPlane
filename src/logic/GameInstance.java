package logic;

public class GameInstance {

	public static final GameInstance instance = new GameInstance();
	private String playerName;
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		if (playerName.equals("")) {
			playerName = "Unknown";
		}
		this.playerName = playerName;
	}
	
}
