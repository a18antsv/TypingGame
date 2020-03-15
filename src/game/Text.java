package game;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Text implements Paintable {
	
	private AffineTransform transform;
	private Character[] characters;
	private int index;
	private Size size;
	private Runnable onCompletion;

	public enum Orientation {
		COUNTER_CLOCKWISE(-Math.PI / 2),
		CLOCKWISE(Math.PI / 2),
		DEFAULT(0);
		
		private double angle;
		
		Orientation(double angle) {
			this.angle = angle;
		}
	}
	
	public enum Size {
		SMALL(12),
		MEDIUM(16),
		LARGE(24);
		
		private int fontHeight;
		
		Size(int fontHeight) {
			this.fontHeight = fontHeight;
		}

		public int getFontHeight() {
			return fontHeight;
		}
	}
	
	public Text(String text, float x, float y, Orientation orientation, Size size, Runnable onCompletion) {
		this.transform = new AffineTransform();
		this.transform.translate(x, y);
		this.transform.rotate(orientation.angle);
		this.characters = new Character[text.length()];
		this.size = size;
		this.setWord(text);
		this.onCompletion = onCompletion;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		g2d.transform(this.transform);
		for (Character character : this.characters) {
			character.paint(g);
		}
		g2d.setTransform(transform);
	}
	
	public void pressed(char c) {
		if(this.characters[this.index].getChar() == c) {
			this.completeChar();
		}
	}
	
	protected void completeChar() {
		this.characters[this.index].setCorrect();
		if(this.index < this.characters.length - 1) {
			this.index++;
		} else {
			this.completeText();
		}
	}
	
	protected void completeText() {
		this.onCompletion.run();
		this.index = 0;
	}

	protected void setWord(String text) {
		this.characters = new Character[text.length()];
		float charWidth = this.size.fontHeight * 0.7f;
		for(int i = 0; i < text.length(); i++) {
			this.characters[i] = new Character(-text.length() / 2f * charWidth + i * charWidth, 0, text.charAt(i), size.fontHeight);
		}
		this.reset();
	}

	public void reset() {
		this.index = 0;
		for (Character character : this.characters) {
			character.reset();
		}
	}
}