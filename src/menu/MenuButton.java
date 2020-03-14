package menu;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.Paintable;

public class MenuButton implements Paintable, Clickable {
	
	private String text;
	private int x;
	private int y;
	private int width;
	private int height;
	private Runnable action;
	
	public MenuButton(String text, int x, int y, int width, int height, Runnable action) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.action = action;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Monospaced", Font.BOLD, 42);
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
