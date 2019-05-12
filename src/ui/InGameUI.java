package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InGameUI extends StackPane {

	private static InGameUI instance;
	
	private GameLog gameLog;
	private LifeUI lifeUI;
	
	public InGameUI() {
		super();
		if (instance != this) {
			instance = this;
		}
		setMaxSize(800, 600);
		setPadding(new Insets(10));
		
		Rectangle border = new Rectangle(780, 580);
		border.setX(10);
		border.setY(10);
		border.setStroke(Color.WHITE);
		border.setStrokeWidth(2);
		border.setFill(Color.TRANSPARENT);
		setAlignment(border, Pos.CENTER);
		
		gameLog = new GameLog();
		setAlignment(gameLog, Pos.BOTTOM_RIGHT);
		
		lifeUI = new LifeUI(3);
		setAlignment(lifeUI, Pos.TOP_LEFT);
		
		getChildren().add(border);
		getChildren().add(gameLog);
		getChildren().add(lifeUI);
	}
	
	public static InGameUI getInstance() {
		return instance;
	}

	
	public LifeUI getLifeUI() {
		return lifeUI;
	}
	
	public GameLog getGameLog() {
		return gameLog;
	}
	
}
