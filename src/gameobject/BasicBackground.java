package gameobject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import render.Background;

public class BasicBackground extends Background {

	public static Image img = new Image(ClassLoader.getSystemResourceAsStream("img/GameMap.png"));
	
	public BasicBackground(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(img, x, y);
	}
	
}
