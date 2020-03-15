package game.entity;

import game.Text;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Enemy extends Unit {
	
	private Text text;
	
	public Enemy(AffineTransform transform) {
		super(transform);
	}




	@Override
	public void step() {

	}

	@Override
	public void paint(Graphics g) {

	}

}
