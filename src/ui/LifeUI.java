package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import render.RenderHolder;

public class LifeUI extends HBox {

	private static Image lifeImage = RenderHolder.imageCollection.get("Life");
	private Label lifeText;
	private int lifeLeft;
	
	public LifeUI(int life) {
		super();
		
		lifeLeft = life;
		lifeText = new Label("Life Left : ");
		lifeText.setFont(new Font(36));
		lifeText.setStyle("-fx-font-weight: bold");
		lifeText.setAlignment(Pos.CENTER);
		getChildren().add(lifeText);
		
		setPadding(new Insets(15));
		
		for (int i = 0; i < lifeLeft; i++) {
			getChildren().add(new ImageView(lifeImage));
		}
	}
	
	public void lifeDecrease() {
		getChildren().remove(lifeLeft);
		lifeLeft--;
	}
	
}
