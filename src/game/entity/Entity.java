package game.entity;

import game.Paintable;
import game.Updateable;
import game.WorldContext;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Entity implements Paintable, Updateable {
	
	private AffineTransform transform;
	private WorldContext worldContext;
	protected int thickness;
	
	public Entity(AffineTransform transform, int thickness, WorldContext worldContext) {
		this.transform = transform;
		this.thickness = thickness;
		this.worldContext = worldContext;
	}

	public void rotate(double radians) {
		this.transform.rotate(radians);
	}
	
	public void translate(float x, float y) {
		this.transform.translate(x, y);
	}
	
	public AffineTransform getTransform() {
		return this.transform;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		g2d.transform(this.transform);
		this.paintTransformed(g2d);
		g2d.setTransform(transform);
	}
	
	protected void paintTransformed(Graphics2D g2d) {
		
	}
}
