package game.level;

import game.WorldContext;
import game.entity.EnemyFollower;
import game.entity.EntityCategory;

public class LevelOne implements Level {

    @Override
    public void init(WorldContext worldContext) {
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyFollower(50, 50, 20, worldContext));
    }

    @Override
    public Level getNext() {
        return new LevelTwo();
    }
}