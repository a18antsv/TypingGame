package game.entity;

import game.Game;
import game.WorldContext;

public class EnemyFollower extends Enemy {

    private Player player;

    public EnemyFollower(int x, int y, int thickness, WorldContext worldContext) {
        super(x, y, thickness, worldContext);
        this.player = worldContext.getPlayer();
    }

    @Override
    public void step() {
        float diffX = (float) (this.getTransform().getTranslateX() - this.player.getTransform().getTranslateX());
        float diffY = (float) (this.getTransform().getTranslateY() - this.player.getTransform().getTranslateY());
        float distance = (float) Math.sqrt(diffX * diffX + diffY * diffY);

        float velocityX = -diffX * (distance / (Game.WIDTH));
        float velocityY = -diffY * (distance / (Game.HEIGHT));

        this.setVelocity(velocityX, velocityY);
        super.step();
    }
}
