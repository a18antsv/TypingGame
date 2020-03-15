package game.entity;

import game.Paintable;
import game.Updateable;

import java.awt.*;

public class TailPart implements Paintable, Updateable {
	
	private float x;
	private float y;
	private int width;
	private int height;
	
	public TailPart(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void step() {

	}

	@Override
	public void paint(Graphics g) {
		g.drawRect((int) x, (int) y, width, height);
	}
}
