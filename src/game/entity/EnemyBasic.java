package game.entity;

import game.Game;
import game.WorldContext;

import java.awt.*;

/**
 * A class representing a basic enemy that collides with walls.
 */
public class EnemyBasic extends Enemy {

    public EnemyBasic(int x, int y, float vx, float vy,  int thickness, WorldContext worldContext) {
        super(x, y, thickness, Color.CYAN, worldContext);
        this.setVelocity(vx, vy);
    }

    @Override
    public void step() {
        if (this.getTransform().getTranslateX() <= WorldContext.BORDER_THICKNESS || this.getTransform().getTranslateX() >= Game.WIDTH - WorldContext.BORDER_THICKNESS - this.getThickness()) {
            this.setVelocity(-this.getVelocityX(), this.getVelocityY());
        }
        if (this.getTransform().getTranslateY() <= WorldContext.BORDER_THICKNESS || this.getTransform().getTranslateY() >= Game.HEIGHT - WorldContext.BORDER_THICKNESS - this.getThickness()) {
            this.setVelocity(this.getVelocityX(), -this.getVelocityY());
        }
        super.step();
    }
}
