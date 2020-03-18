package game;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A class representing a single character in a Text
 */
public class Character implements Paintable {
	
	private Font font;
	private AffineTransform transform; // 3x3 matrix of transforms made to the text
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

	/**
	 * Paints the char to the graphics context. Gets the transform of the graphics context,
	 * transforms the graphics context with the transform matrix and resets the graphics context transform again.
	 * @param g Graphics context
	 */
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

	/**
	 * Sets to color representing a correctly typed char
	 */
	public void setCorrect() {
		this.color = this.colorCorrect;
	}

	/**
	 * Resets to initial color
	 */
	public void reset() {
		this.color = this.colorInitial;
	}
	
	public char getChar() {
		return this.character;
	}
}