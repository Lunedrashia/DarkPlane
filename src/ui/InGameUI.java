package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.Input;

public class InGameUI extends StackPane {

	private static InGameUI instance;
	
	private GameLog gameLog;
	private LifeUI lifeUI;
	
	public InGameUI(int lifeAmount) {
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
		
		lifeUI = new LifeUI(lifeAmount);
		setAlignment(lifeUI, Pos.TOP_LEFT);
		
		getChildren().add(border);
		getChildren().add(gameLog);
		getChildren().add(lifeUI);
		
		addListener();
		
	}
	
	private void addListener() {
		this.setOnMouseClicked((MouseEvent e) -> {
			MouseButton button = e.getButton();
			if (button == MouseButton.PRIMARY) {
				Input.skill1Used = true;
				System.out.println("LeftClick");
			}
			if (button == MouseButton.SECONDARY) {
				Input.skill2Used = true;
			}
		});
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
