package game.entity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class Snake extends Unit {
	
	private LinkedList<TailPart> tail;
	private int thickness;
	private float velocityX;
	private float velocityY;
	
	public Snake(int x, int y, int thickness) {
		super(new AffineTransform());
		this.translate(x, y);
		this.tail = new LinkedList<>();
		this.thickness = thickness;
		this.setVelocity(0, 0);
		
		/*for(int i = 0; i < 3; i++) {
			tail.add(new TailPart(x + i * width, y, width, height));
		}*/
		
	}

	@Override
	public void step() {
		this.getTransform().translate(this.velocityX, this.velocityY);
	}

	@Override
	public void paintTransformed(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(0, 0, thickness, thickness);
		this.tail.forEach(tailPart -> tailPart.paint(g2d));
	}
	
	public void setVelocity(float velocityX, float velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
}
