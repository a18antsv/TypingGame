package game.entity;

import game.WorldContext;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A class extending the normal entity which all entities that can move should extend.
 */
public abstract class EntityMoving extends Entity {

    private float velocityX;
    private float velocityY;

    public EntityMoving(int x, int y, int thickness, Color color, WorldContext worldContext) {
        super(new AffineTransform(), thickness, color, worldContext);
        this.translate(x, y);
        this.setVelocity(0, 0);
    }

    /**
     * A moving entity should be translated with the velocityX and velocityY in each step.
     */
    @Override
    public void step() {
        this.getTransform().translate(this.velocityX, this.velocityY);
    }

    public float getVelocityX() {
        return this.velocityX;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public void setVelocity(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
}
