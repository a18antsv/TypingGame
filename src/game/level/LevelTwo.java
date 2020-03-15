package game.level;

import game.WorldContext;
import game.entity.EnemyFollower;
import game.entity.EntityCategory;

public class LevelTwo implements Level {
    @Override
    public void init(WorldContext worldContext) {
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyFollower(1000, 729, 8, worldContext));
    }

    @Override
    public Level getNext() {
        return null;
    }
}
