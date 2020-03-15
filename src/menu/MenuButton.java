package menu;

import java.awt.*;

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
