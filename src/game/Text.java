package game;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Abstract class representing Text that the player should write to do actions in the game.
 */
public abstract class Text implements Paintable {
	
	private AffineTransform transform; // 3x3 matrix of transforms made to the text
	private Character[] characters; //Array of custom Character objects making up the Text object
	private int index; //Current index of completed characters in the text
	private Size size; //Enum type for size of text
	private Runnable onCompletion; //Function to execute when text is correctly written

	/**
	 * Enumeration of orientations in radians
	 */
	public enum Orientation {
		COUNTER_CLOCKWISE(-Math.PI / 2),
		CLOCKWISE(Math.PI / 2),
		DEFAULT(0);
		
		private double angle;
		
		Orientation(double angle) {
			this.angle = angle;
		}
	}

	/**
	 * Enumeration of font sizes
	 */
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
		this.transform.translate(x, y); //Translate whole text to provided x and y coordinates
		this.transform.rotate(orientation.angle); //Rotate text according to provided orientation
		this.characters = new Character[text.length()];
		this.size = size;
		this.setWord(text);
		this.onCompletion = onCompletion;
	}

	/**
	 * Paints each char in the text to the graphics context. Gets the transform of the graphics context,
	 * transforms the graphics context with the transform matrix and resets the graphics context transform again.
	 * @param g Graphics context
	 */
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

	/**
	 * Check if the char at the current index is equal to the pressed char. If so char is completed, else reset text.
	 * @param c Char
	 */
	public void pressed(char c) {
		if(this.characters[this.index].getChar() == c) {
			this.completeChar();
		} else {
			this.reset();
		}
	}

	/**
	 * Sets the current index char to being correct. The index should be incremented if index is less than length of text.
	 * If index is equal to the length of text the text is completed.
	 */
	protected void completeChar() {
		this.characters[this.index].setCorrect();
		if(this.index < this.characters.length - 1) {
			this.index++;
		} else {
			this.completeText();
		}
	}

	/**
	 * When text is completed the Runnable method passed as a reference to the object should be executed and index reset.
	 */
	protected void completeText() {
		this.onCompletion.run();
		this.index = 0;
	}

	/**
	 * Sets the array of Character objects to have as many positions as the text length.
	 * Fills the array with Character objects, one for each character in the text.
	 * The x-coordinate of the current iteration's char is calculated to position right after the previous char and
	 * so the overal text in the end appears in the middle of the object it is over.
	 * @param text String to make into Character objects in the Character array
	 */
	protected void setWord(String text) {
		this.characters = new Character[text.length()];
		float charWidth = this.size.fontHeight * 0.7f;
		for(int i = 0; i < text.length(); i++) {
			this.characters[i] = new Character(-text.length() / 2f * charWidth + i * charWidth, 0, text.charAt(i), size.fontHeight);
		}
		this.reset();
	}

	/**
	 * Resets the index of correctly written characters in the Text and also resets each instance of Character in the texts.
	 */
	public void reset() {
		this.index = 0;
		for (Character character : this.characters) {
			character.reset();
		}
	}
}