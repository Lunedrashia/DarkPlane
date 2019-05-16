package render;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderHolder {

	private static final RenderHolder instance = new RenderHolder();
	
	private ArrayList<Renderable> allRender;
	private Comparator<Renderable> comparator;
	
	public static HashMap<String, Image> imageCollection;
	public static HashMap<String, AudioClip> soundCollection;
	private static boolean haveLoaded = false;
	private static boolean menuResourceLoaded = false;
	
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
		soundCollection = new HashMap<String, AudioClip>();
	}
	
	private static void loadResource() {
		// TODO Auto-generated method stub
		imageCollection.put("GameIcon", new Image(ClassLoader.getSystemResourceAsStream("img/GameIcon.png")));
		imageCollection.put("MainMenuBG", new Image(ClassLoader.getSystemResourceAsStream("img/MainMenuBackground.png")));
		soundCollection.put("ClickSound", new AudioClip(ClassLoader.getSystemResource("sound/HeavyButtonClick.wav").toString()));
		soundCollection.put("MenuBGM", new AudioClip(ClassLoader.getSystemResource("sound/Game-Menu_Looping.wav").toString()));
		soundCollection.put("GameBGM", new AudioClip(ClassLoader.getSystemResource("sound/Light-Years_V001_Looping.wav").toString()));
		menuResourceLoaded = true;
		
		soundCollection.put("LaserSFX", new AudioClip(ClassLoader.getSystemResource("sound/Laser-Shot.wav").toString()));
		soundCollection.put("ExplosionSFX", new AudioClip(ClassLoader.getSystemResource("sound/Explosion.wav").toString()));
		imageCollection.put("BombEffect", new Image(ClassLoader.getSystemResourceAsStream("img/BombEff-Sheet.png")));
		imageCollection.put("Dummy", new Image(ClassLoader.getSystemResourceAsStream("img/DummyAgain.png")));
		imageCollection.put("EyeMonster", new Image(ClassLoader.getSystemResourceAsStream("img/EyeMonster.png")));
		imageCollection.put("FadePlane", new Image(ClassLoader.getSystemResourceAsStream("img/FadePlane.png")));
		imageCollection.put("GameMap", new Image(ClassLoader.getSystemResourceAsStream("img/GameMap2.png")));
		imageCollection.put("Life", new Image(ClassLoader.getSystemResourceAsStream("img/Life.png")));
		imageCollection.put("Plane", new Image(ClassLoader.getSystemResourceAsStream("img/Plane.png")));
		imageCollection.put("PlayerSpawnTower", new Image(ClassLoader.getSystemResourceAsStream("img/PlayerSpawnTower.png")));
		imageCollection.put("EnemyPlane", new Image(ClassLoader.getSystemResourceAsStream("img/EnemyPlane2.png")));
		imageCollection.put("Slime1", new Image(ClassLoader.getSystemResourceAsStream("img/SlimeBossTopView-1.png")));
		imageCollection.put("Slime2", new Image(ClassLoader.getSystemResourceAsStream("img/SlimeBossTopView-2.png")));
		haveLoaded = true;
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
	
	public static boolean checkHaveLoaded() {
		return haveLoaded;
	}
	
	public static boolean checkHaveMenuResourceLoaded() {
		return menuResourceLoaded;
	}
	
}
