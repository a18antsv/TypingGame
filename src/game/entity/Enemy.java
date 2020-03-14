package game.entity;
import java.awt.Graphics;

import game.Text;

public abstract class Enemy extends Unit {
	
	private Text text;
	
	public Enemy(Text text, float x, float y, int width, int height, float velocityX, float velocityY) {
		super(x, y, width, height, velocityX, velocityY);
		this.text = text;
	}

	public Enemy(Text text, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.text = text;
	}

	@Override
	public void step() {

	}

	@Override
	public void paint(Graphics g) {

	}

}
