package ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Loading extends StackPane {

	private Thread countdown;
	private Label loadText;
	
	public Loading() {
		super();
		setMaxSize(800, 600);
		
		Rectangle background = new Rectangle();
		background.setWidth(800);
		background.setHeight(600);
		background.setFill(Color.BLACK);
		
		loadText = new Label("LOADING");
		loadText.setFont(new Font(32));
		loadText.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
		loadText.setPadding(new Insets(20));
		
		setAlignment(loadText, Pos.BOTTOM_LEFT);
		
		getChildren().add(background);
		getChildren().add(loadText);
	}
	
	public void startCountdown(int ms) {
		countdown = new Thread(() -> {
			for (int i = 0; i <= ms/1000; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					loadText.setText(loadText.getText() + ".");
					if (loadText.getText().length() > 10) {
						loadText.setText("LOADING");
					}
				});
			}
			setVisible(false);
		});
		countdown.start();
	}
	
	public Thread getCountThread() {
		return countdown;
	}
	
}
