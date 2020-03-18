package game.entity;

import game.Game;
import game.WorldContext;

import java.awt.*;

/**
 * A class representing an unpredictable enemy that changes direction in an interval.
 */
public class EnemyUnpredictable extends Enemy {

    private int counter;

    public EnemyUnpredictable(int x, int y, float vx, float vy, int thickness, WorldContext worldContext) {
        super(x, y, thickness, Color.MAGENTA, worldContext);
        this.setVelocity(vx, vy);
        this.counter = 0;
    }

    @Override
    public void step() {
        this.counter++;

        //Sets random velocity every 100 steps
        if(this.counter % 100 == 0) {
            this.setVelocity(Game.random(-4, 4), Game.random(-4, 4));
        }
        if (this.getTransform().getTranslateX() <= WorldContext.BORDER_THICKNESS || this.getTransform().getTranslateX() >= Game.WIDTH - WorldContext.BORDER_THICKNESS - this.getThickness()) {
            this.setVelocity(-this.getVelocityX(), this.getVelocityY());
        }
        if (this.getTransform().getTranslateY() <= WorldContext.BORDER_THICKNESS || this.getTransform().getTranslateY() >= Game.HEIGHT - WorldContext.BORDER_THICKNESS - this.getThickness()) {
            this.setVelocity(this.getVelocityX(), -this.getVelocityY());
        }
        super.step();
    }
}
