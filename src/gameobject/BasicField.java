package gameobject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import render.Field;
import render.RenderHolder;

public class BasicField extends Field {

	public static Image img = RenderHolder.imageCollection.get("GameMap");
	
	public BasicField(double x, double y) {
		super(x, y, 1088, 903);
		arenaTopLeft = new Point2D(20, 20);
		arenaBottomRight = new Point2D(img.getWidth()-20, img.getHeight()-20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(img, location.getX(), location.getY());
	}

}
