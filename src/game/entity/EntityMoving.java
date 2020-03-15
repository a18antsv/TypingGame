package game.entity;

import game.WorldContext;

import java.awt.geom.AffineTransform;

public abstract class EntityMoving extends Entity {

    protected float velocityX;
    protected float velocityY;

    public EntityMoving(int x, int y, int thickness, WorldContext worldContext) {
        super(new AffineTransform(), thickness, worldContext);
        this.translate(x, y);
        this.setVelocity(0, 0);
    }

    @Override
    public void step() {
        this.getTransform().translate(this.velocityX, this.velocityY);
        //if(this.getTransform().getTranslateX() <= G)
    }

    public void setVelocity(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
}
