package menu;

import java.awt.*;

/**
 * Class representing a button in the menu.
 * A button is Clickable, hence the Clickable interface is implemented.
 * Each button has a Runnable function so when creating a new button a reference to a method to execute when the button
 * is clicked can be provided.
 */
public class MenuButton extends MenuItem implements Clickable {

	private int width;
	private int height;
	private Runnable action;

	public MenuButton(String text, int x, int y, int width, int height, int fontSize, Runnable action) {
		super(text, x, y, fontSize);
		this.width = width;
		this.height = height;
		this.action = action;
	}

	/**
	 * Draws a rectangle at the x and y coordinates with the text centered in the middle of the rectangle.
	 * @param g The Graphics context
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Monospaced", Font.BOLD, this.fontSize);
		FontMetrics metrics = g.getFontMetrics(font);

		int x = this.x + (this.width - metrics.stringWidth(this.text)) / 2;
		int y = this.y + ((this.height - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(4));
		g.drawRect(this.x, this.y, this.width, this.height);
		g.setColor(new Color(255, 255, 255, 127));
		g.fillRect(this.x, this.y, this.width, this.height);
		
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(this.text, x, y);
	}
	
	@Override
	public Rectangle getHitbox() {
		return new Rectangle(this.x, this.y, this.width, this.height);	
	}
	
	@Override
	public Runnable getAction() {
		return action;
	}
}
