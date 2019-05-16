package gameobject.effect;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import render.RenderHolder;

public class BombEffect extends AnimationEffect {

	private static Image spriteSheet = RenderHolder.imageCollection.get("BombEffect");
	private static ArrayList<WritableImage> allFrame;
	private Image currentImage;
	
	private static final int FRAMECOUNT = 4;
	private static final int TIME_BETWEEN_FRAME = 125;
	
	public BombEffect(double x, double y, double size) {
		super(x, y, 0, size);
		allFrame = new ArrayList<WritableImage>();
		for (int i = 0; i < FRAMECOUNT; i++) {
			WritableImage croppedImage = new WritableImage(spriteSheet.getPixelReader(),
				i * 64, 0, 64, 64);
			allFrame.add(croppedImage);
		}
		currentImage = allFrame.get(0);
		Thread anim = new Thread(() -> {
			try {
				for (int i = 1; i < FRAMECOUNT; i++) {
					Thread.sleep(TIME_BETWEEN_FRAME);
					currentImage = allFrame.get(i);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isAlive = false;
		});
		anim.start();
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		basicDrawImage(gc, currentImage);
	}

}
