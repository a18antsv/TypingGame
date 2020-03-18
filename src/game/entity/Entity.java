package game.entity;

import game.Paintable;
import game.Updateable;
import game.WorldContext;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Abstract class holding attributes and functionality that all entities have in common.
 */
public abstract class Entity implements Paintable, Updateable {
	
	private AffineTransform transform; // 3x3 matrix of transforms made to the text
	private WorldContext worldContext;
	private Color color;
	private int thickness;
	
	public Entity(AffineTransform transform, int thickness, Color color, WorldContext worldContext) {
		this.transform = transform;
		this.thickness = thickness;
		this.color = color;
		this.worldContext = worldContext;
	}

	/**
	 * Rotates the matrix of transforms.
	 * @param radians angle to rotate in Radians
	 */
	public void rotate(double radians) {
		this.transform.rotate(radians);
	}

	/**
	 * Translates the matrix of transforms.
	 * @param x the distance by which coordinates are translated in the X-axis direction
	 * @param y the distance by which coordinates are translated in the Y-axis direction
	 */
	public void translate(float x, float y) {
		this.transform.translate(x, y);
	}

	/**
	 * Gets the transform of the graphics context, transforms the graphics context with the transform matrix,
	 * paints the transformed entity to the graphics context and resets the graphics context transform again.
	 * @param g Graphics context
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		g2d.transform(this.transform);
		this.paintTransformed(g2d);
		g2d.setTransform(transform);
	}

	protected void paintTransformed(Graphics g) {

	}
	
	public AffineTransform getTransform() {
		return this.transform;
	}

	public WorldContext getWorldContext() {
		return worldContext;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	/**
	 * Creates a rectangle working as the hitbox for the entity as it is easy to work with collisions with rectangles.
	 * @return Rectangle the hitbox of the entity
	 */
	public Rectangle getHitbox() {
		return new Rectangle((int) this.getTransform().getTranslateX(), (int) this.getTransform().getTranslateY(), this.thickness, this.thickness);
	}

	/**
	 * Checks if one entity collides with another
	 * @param rectangle Rectangle to check for collision with
	 * @return boolean
	 */
	public boolean isIntersecting(Rectangle rectangle) {
		return this.getHitbox().intersects(rectangle);
	}
}
