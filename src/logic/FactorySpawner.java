package logic;

import java.util.ArrayList;
import java.util.Random;

import gameobject.enemy.Dummy;
import gameobject.enemy.Enemy;
import gameobject.enemy.EnemyPlane;
import gameobject.enemy.EyeMonster;
import gameobject.enemy.SlimeBoss;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import ui.InGameUI;

public class FactorySpawner {
	
	private ArrayList<String> enemyTypes;  
	private ArrayList<String> bossTypes;
	
	private Random randomizer = new Random();
	private int monsterTypeLimit = 1;
	private int indexBoss = 0;
	private boolean bossAlive = false;
	private boolean spawnDone = false;
	
	private Enemy currentBoss;
	
	private Thread spawnMosterThread;
	
	public FactorySpawner() {
		enemyTypes = new ArrayList<String>();
		bossTypes = new ArrayList<String>();
		enemyTypes.add("Dummy");
		enemyTypes.add("EyeMonster");
		enemyTypes.add("EnemyPlane");
		bossTypes.add("EyeMonster");
		bossTypes.add("SlimeBoss");
		spawnMosterThread = new Thread(() -> {
			int monsterSpawnCount = 0;
			while (GameLogic.getInstance().getGameStatus() == GAME_STATUS.RUNNING && !spawnDone) {
				if (currentBoss != null && !currentBoss.isAlive) {
					Platform.runLater(() -> {
						InGameUI.getInstance().getGameLog().addData("Boss Defeated!!");
					});
					currentBoss = null;
					bossAlive = false;
					indexBoss++;
					if (indexBoss == bossTypes.size()) {
						spawnDone = true;
					}
					monsterTypeLimit += 2;
				}
				if (!bossAlive && !spawnDone) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (monsterSpawnCount < 20) {
						randomSpawn();
						monsterSpawnCount++;
					}
					else {
						currentBoss = spawnBoss();
						Platform.runLater(() -> {
							InGameUI.getInstance().getGameLog().addData(currentBoss + " has spawned!!");
						});
						monsterSpawnCount = 0;
						bossAlive = true;
					}
				}
			}
		});
	}
	
	private Enemy spawnBoss() {
		String bossPicked = bossTypes.get(indexBoss);
		Point2D spawnPoint = randomPointOutOfMap();
		if (bossPicked.equals("EyeMonster")) {
			Enemy enemy = new EyeMonster(spawnPoint.getX(), spawnPoint.getY(), 0);
			GameLogic.getInstance().addNewObject(enemy);
			return enemy;
		}
		if (bossPicked.equals("SlimeBoss")) {
			Enemy enemy = new SlimeBoss(spawnPoint.getX(), spawnPoint.getY(), 0);
			GameLogic.getInstance().addNewObject(enemy);
			return enemy;
		}
		return null;
	}
	
	private void randomSpawn() {
		String monsterPicked = enemyTypes.get(randomizer.nextInt(monsterTypeLimit));
		Point2D spawnPoint = randomPointOutOfMap();
		if (monsterPicked.equals("Dummy")) {
			GameLogic.getInstance().addNewObject(new Dummy(spawnPoint.getX(), spawnPoint.getY(), 0));
		}
		if (monsterPicked.equals("EnemyPlane")) {
			GameLogic.getInstance().addNewObject(new EnemyPlane(spawnPoint.getX(), spawnPoint.getY(), 0));
		}
		if (monsterPicked.equals("EyeMonster")) {
			GameLogic.getInstance().addNewObject(new EyeMonster(spawnPoint.getX(), spawnPoint.getY(), 0));
		}
	}
	
	private Point2D randomPointOutOfMap() {
		int rangeX = (int) (GameLogic.getInstance().getField().getArenaBottomRight().getX() - 
				GameLogic.getInstance().getField().getArenaTopLeft().getX() + 800);
		int rangeY = (int) (GameLogic.getInstance().getField().getArenaBottomRight().getY() - 
				GameLogic.getInstance().getField().getArenaTopLeft().getY() + 800);
		int x = randomizer.nextInt(rangeX) - 400;
		int y;
		if (x > 0 && x < rangeX - 400) {
			y = randomizer.nextInt(800) - 400;
			if (y > 0) {
				y += rangeY - 400;
			}
		}
		else {
			y = randomizer.nextInt(rangeY) - 400;
		}
		return new Point2D(x, y);
	}

	public Thread getSpawnMosterThread() {
		return spawnMosterThread;
	}

	public ArrayList<String> getEnemyTypes() {
		return enemyTypes;
	}

	public ArrayList<String> getBossTypes() {
		return bossTypes;
	}
	
}
