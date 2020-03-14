package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Character implements Paintable {
	
	private int x;
	private int y;
	private double angle;
	private char character;
	private Color color;
	
	public Character(int x, int y, double angle, char character, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.character = character;
		this.color = color;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		g.setColor(this.color);
		g2d.rotate(this.angle);
		g.drawString(String.valueOf(this.character), this.x, this.y);
		g2d.setTransform(transform);
	}
	
	public char getChar() {
		return this.character;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
