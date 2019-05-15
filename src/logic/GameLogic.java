package logic;

import java.util.ArrayList;

import gameobject.Player;
import gameobject.PlayerSpawnTower;
import gameobject.bullet.Bullet;
import gameobject.enemy.Dummy;
import gameobject.enemy.Enemy;
import gameobject.enemy.EyeMonster;
import gameobject.field.BasicField;
import gameobject.field.Field;
import render.RenderHolder;
import ui.InGameUI;

public class GameLogic {

	private static GameLogic instance;

	private int lifeLeft;

	private ArrayList<GameEntity> gameEntities;

	private Thread spawnMonster;

	private PlayerSpawnTower playerSpawnTower;
	private Player player;
	private Field field;
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
		for (int i = bullets.size() - 1; i >= 0; i--) {
			if (!bullets.get(i).isAlive()) {
				gameEntities.remove(bullets.get(i));
				bullets.remove(i);
			}
		}
		for (int i = enemies.size() - 1; i >= 0; i--) {
			if (!enemies.get(i).isAlive()) {
				gameEntities.remove(enemies.get(i));
				enemies.remove(i);
			}
		}
		if ((player == null || !player.isAlive()) && !isSpawning && lifeLeft > 0) {
			lifeLeft--;
			InGameUI.getInstance().getLifeUI().lifeDecrease();
			gameEntities.remove(player);
			player = null;
			InGameUI.getInstance().getGameLog().addData("Life left : " + lifeLeft);
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
		for (Enemy enemy : enemies) {
			enemy.update();
		}
		for (Bullet bullet : bullets) {
			bullet.update();
		}
		for (int i = 0; i < gameEntities.size(); i++) {
			for (int j = i + 1; j < gameEntities.size(); j++) {
				if (gameEntities.get(i).collideWith(gameEntities.get(j))) {
					gameEntities.get(i).dealDamage(gameEntities.get(j));
					gameEntities.get(j).dealDamage(gameEntities.get(i));
					// InGameUI.getInstance().getGameLog().addData(gameEntities.get(i) + " deal
					// damage to " + gameEntities.get(j));
					// InGameUI.getInstance().getGameLog().addData(gameEntities.get(j) + " deal
					// damage to " + gameEntities.get(i));
				}
			}
		}
	}

	private void sceneSetUp(String sceneName) {
		if (sceneName.equals("TestScene")) {
			lifeLeft = 3;
			field = new BasicField(0, 0);
			playerSpawnTower = new PlayerSpawnTower(field.getPlayerSpawnLocation().getX(), field.getPlayerSpawnLocation().getY());
			player = playerSpawnTower.spawnPlayer();
			
			RenderHolder.getInstance().addNewObject(field);
			this.addNewObject(playerSpawnTower);
			this.addNewObject(player);

			spawnMonster = new Thread(() -> {
				try {
					Thread.sleep(5000);
					this.addNewObject(new Dummy(300, 500, 0));
					Thread.sleep(5000);
					this.addNewObject(new EyeMonster(3000, 2500, 0));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}

	public int getLifeLeft() {
		return lifeLeft;
	}

	public static GameLogic getInstance() {
		return instance;
	}

	public GAME_STATUS getGameStatus() {
		if (lifeLeft == 0) {
			return GAME_STATUS.LOSE;
		}
		if (spawnMonster.getState() == Thread.State.TERMINATED && enemies.size() == 0) {
			return GAME_STATUS.WIN;
		}
		return GAME_STATUS.RUNNING;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<GameEntity> getGameEntities() {
		return gameEntities;
	}

	public void setGameEntities(ArrayList<GameEntity> gameEntities) {
		this.gameEntities = gameEntities;
	}

	public PlayerSpawnTower getPlayerSpawnTower() {
		return playerSpawnTower;
	}

	public void setPlayerSpawnTower(PlayerSpawnTower playerSpawnTower) {
		this.playerSpawnTower = playerSpawnTower;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public boolean isSpawning() {
		return isSpawning;
	}

	public void setSpawning(boolean isSpawning) {
		this.isSpawning = isSpawning;
	}

	public static void setInstance(GameLogic instance) {
		GameLogic.instance = instance;
	}

	public void setLifeLeft(int lifeLeft) {
		this.lifeLeft = lifeLeft;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Thread getSpawnMonster() {
		return spawnMonster;
	}

	public void setSpawnMonster(Thread spawnMonster) {
		this.spawnMonster = spawnMonster;
	}

}
