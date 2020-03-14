package game.entity;

import game.Paintable;
import game.Updateable;

public abstract class Unit implements Paintable, Updateable {
	
	private float x;
	private float y;
	private int width;
	private int height;
	private float velocityX;
	private float velocityY;
	
	public Unit(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocityX = 0;
		this.velocityY = 0;
	}
	
	public Unit(float x, float y, int width, int height, float velocityX, float velocityY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	
	@Override
	public void step() {
		this.x += this.velocityX;
		this.y += this.velocityY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}
}
