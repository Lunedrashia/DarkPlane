package ui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import logic.GAME_STATUS;

public class GameEndScreen extends StackPane {
	
	private Label gameStatusText;
	private Label timerText;
	private int secondLeft;
	
	private Thread countdown;
	
	public GameEndScreen(int second) {
		super();
		setMaxSize(800, 600);
		setAlignment(Pos.TOP_LEFT);
		
		this.secondLeft = second;
		
		Rectangle greenBox = new Rectangle(800, 200);
		greenBox.setFill(Color.GREENYELLOW);
		greenBox.setOpacity(0.75);
		setAlignment(greenBox, Pos.CENTER);
		
		gameStatusText = new Label();
		gameStatusText.setFont(new Font(32));
		gameStatusText.setStyle("-fx-font-weight: bold");
		
		timerText = new Label("Returning to main menu in " + this.secondLeft + " second(s)..");
		timerText.setFont(new Font(18));
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(gameStatusText, timerText);
		
		getChildren().addAll(greenBox, vbox);
		
		countdown = new Thread(() -> {
			try {
				while (this.secondLeft > 0) {
					Thread.sleep(1000);
					this.secondLeft--;
					Platform.runLater(() -> {
						timerText.setText("Returning to main menu in " + this.secondLeft + " second(s)..");
					});
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void startCountdown() {
		this.setVisible(true);
		countdown.start();
	}
	
	public void setGameStatusText(GAME_STATUS status) {
		if (status == GAME_STATUS.LOSE) {
			gameStatusText.setText("You lose!!");
		}
		else {
			gameStatusText.setText("You win!!");
		}
	}
	
	public Thread getCountdown() {
		return countdown;
	}

}
