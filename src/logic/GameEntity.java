package logic;

import render.Renderable;

public abstract class GameEntity implements Renderable {

	protected double x, y;
	protected int layer;
	protected boolean visible, alive; 
	
	protected GameEntity(double x, double y) {
		visible = true;
		alive = true;
		this.x = x;
		this.y = y;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return layer;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}

}
