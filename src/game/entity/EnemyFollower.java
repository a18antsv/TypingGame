package game.entity;

import game.WorldContext;

import java.awt.*;

/**
 * A class representing an enemy that follows the player.
 */
public class EnemyFollower extends Enemy {

    private Player player;

    public EnemyFollower(int x, int y, int thickness, WorldContext worldContext) {
        super(x, y, thickness, Color.YELLOW, worldContext);
        this.player = worldContext.getPlayer();
    }

    /**
     * Calculates the difference in x and y from the enemy and the player and the distance between them on each frame.
     * The values calculated can be used to set the velocity of the enemy to go after the player.
     */
    @Override
    public void step() {
        float diffX = (float) (this.getTransform().getTranslateX() - this.player.getTransform().getTranslateX());
        float diffY = (float) (this.getTransform().getTranslateY() - this.player.getTransform().getTranslateY());
        float distance = (float) Math.sqrt(diffX * diffX + diffY * diffY);

        float velocityX = -1.2f * diffX / distance;
        float velocityY = -1.2f * diffY / distance;

        this.setVelocity(velocityX, velocityY);

        super.step();
    }
}
