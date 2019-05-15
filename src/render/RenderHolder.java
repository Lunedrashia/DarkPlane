package render;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import javafx.scene.image.Image;

public class RenderHolder {

	private static final RenderHolder instance = new RenderHolder();
	
	private ArrayList<Renderable> allRender;
	private Comparator<Renderable> comparator;
	
	public static HashMap<String, Image> imageCollection;
	
	static {
		loadResource();
	}
	
	public RenderHolder() {
		allRender = new ArrayList<Renderable>();
		comparator = new Comparator<Renderable>() {
			@Override
			public int compare(Renderable arg0, Renderable arg1) {
				// TODO Auto-generated method stub
				if (arg0.getLayer() < arg1.getLayer()) {
					return -1;
				}
				return 0;
			}
		};
		imageCollection = new HashMap<String, Image>();
	}
	
	private static void loadResource() {
		// TODO Auto-generated method stub
		imageCollection.put("BombEffect", new Image(ClassLoader.getSystemResourceAsStream("img/BombEff-Sheet.png")));
		imageCollection.put("Dummy", new Image(ClassLoader.getSystemResourceAsStream("img/DummyAgain.png")));
		imageCollection.put("EyeMonster", new Image(ClassLoader.getSystemResourceAsStream("img/EyeMonster.png")));
		imageCollection.put("FadePlane", new Image(ClassLoader.getSystemResourceAsStream("img/FadePlane.png")));
		imageCollection.put("GameMap", new Image(ClassLoader.getSystemResourceAsStream("img/GameMap2.png")));
		imageCollection.put("Life", new Image(ClassLoader.getSystemResourceAsStream("img/Life.png")));
		imageCollection.put("Plane", new Image(ClassLoader.getSystemResourceAsStream("img/Plane.png")));
		imageCollection.put("PlayerSpawnTower", new Image(ClassLoader.getSystemResourceAsStream("img/PlayerSpawnTower.png")));
		imageCollection.put("EnemyPlane", new Image(ClassLoader.getSystemResourceAsStream("img/EnemyPlane.png")));
	}

	public void addNewObject(Renderable i) {
		allRender.add(i);
		allRender.sort(comparator);
	}
	
	public static RenderHolder getInstance() {
		return instance;
	}
	
	public void update() {
		for (int i = allRender.size()-1; i >= 0; i--) {
			if (!allRender.get(i).isAlive()) {
				allRender.remove(i);
			}
		}
	}
	
	public ArrayList<Renderable> getHolder() {
		return allRender;
	}
	
	public void reset() {
		allRender.clear();
	}
	
}
