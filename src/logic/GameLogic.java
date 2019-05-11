package logic;

import java.util.ArrayList;

import gameobject.BasicBackground;
import gameobject.Dummy;
import gameobject.Player;
import gameobject.PlayerSpawnTower;
import render.Background;
import render.RenderHolder;

public class GameLogic {

	private static GameLogic instance;
	
	private int lifeLeft;

	private ArrayList<GameEntity> gameEntities;

	private PlayerSpawnTower playerSpawnTower;
	private Player player;
	private Background background;
	private ArrayList<Bullet> bullets;
	private ArrayList<Enemy> enemies;
	private boolean isSpawning = false;

	public GameLogic(String sceneName) {
		instance = this;
		gameEntities = new ArrayList<GameEntity>();
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		sceneSetUp(sceneName);
	}

	public void addNewObject(GameEntity e) {
		if (e instanceof Enemy) {
			enemies.add((Enemy) e);
		} else if (e instanceof Bullet) {
			bullets.add((Bullet) e);
		}
		gameEntities.add(e);
		RenderHolder.getInstance().addNewObject(e);
	}

	public void update() {
		for (int i = bullets.size()-1; i >= 0; i--) {
			if (!bullets.get(i).isAlive()) {
				gameEntities.remove(bullets.get(i));
				bullets.remove(i);
			}
		}
		for (int i = enemies.size()-1; i >= 0; i--) {
			if (!enemies.get(i).isAlive()) {
				gameEntities.remove(enemies.get(i));
				enemies.remove(i);
			}
		}
		if ((player == null || !player.isAlive()) && !isSpawning) {
			lifeLeft--;
			gameEntities.remove(player);
			player = null;
			System.out.println("Life left: " + lifeLeft);
			if (lifeLeft > 0) {
				isSpawning = true;
				Thread spawnPlayer = new Thread(() -> {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					player = playerSpawnTower.spawnPlayer();
					this.addNewObject(player);
					isSpawning = false;
				});
				spawnPlayer.start();
			}
		}
		if (player != null) {
			player.update();
		}
		for (Bullet bullet: bullets) {
			bullet.update();
		}
		for (int i = 0; i < gameEntities.size(); i++) {
			for(int j = i + 1; j < gameEntities.size(); j++) {
				if (gameEntities.get(i).collideWith(gameEntities.get(j))) {
					gameEntities.get(i).dealDamage(gameEntities.get(j));
					gameEntities.get(j).dealDamage(gameEntities.get(i));
					System.out.println(gameEntities.get(i) + " deal damage to " + gameEntities.get(j));
					System.out.println(gameEntities.get(j) + " deal damage to " + gameEntities.get(i));
				}
			}
		}
	}

	private void sceneSetUp(String sceneName) {
		if (sceneName.equals("TestScene")) {
			lifeLeft = 3;
			playerSpawnTower = new PlayerSpawnTower(250, 250);
			player = playerSpawnTower.spawnPlayer();
			Dummy dummy = new Dummy(300, 500, 0);
			background = new BasicBackground(0, 0);
			RenderHolder.getInstance().addNewObject(background);
			this.addNewObject(playerSpawnTower);
			this.addNewObject(player);
			this.addNewObject(dummy);
		}
	}

	public int getLifeLeft() {
		return lifeLeft;
	}

	public static GameLogic getInstance() {
		return instance;
	}
	
	public boolean getGameStatus() {
		// true if running, false if finished
		return lifeLeft > 0;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
