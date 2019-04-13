package logic;

public class GameLogic {

	private static final GameLogic instance = new GameLogic();
	private String playerName;
	private int score;
	
	public GameLogic() {
		gameReset();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		if (playerName.equals("")) {
			playerName = "Unknown";
		}
		this.playerName = playerName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score < 0 ? 0 : score;
	}
	
	public void gameReset() {
		score = 0;
	}

	public static GameLogic getInstance() {
		return instance;
	}
	
}
