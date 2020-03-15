package game;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Character implements Paintable {
	
	private Font font;
	private AffineTransform transform;
	private char character;
	private Color color;
	private Color colorCorrect;
	private Color colorInitial;
	
	public Character(float x, float y, char character, int fontHeight) {
		this.font = new Font("Consolas", Font.PLAIN, fontHeight);
		this.transform = new AffineTransform();
		this.transform.translate(x, y);
		this.character = character;
		this.colorInitial = Color.WHITE;
		this.colorCorrect = Color.GREEN;
		this.reset();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		g2d.transform(this.transform);
		g.setColor(this.color);
		g.setFont(this.font);
		g.drawString(String.valueOf(this.character), 0, 0);
		g2d.setTransform(transform);
	}

	public void setCorrect() {
		this.color = this.colorCorrect;
	}

	public void reset() {
		this.color = this.colorInitial;
	}
	
	public char getChar() {
		return this.character;
	}
}