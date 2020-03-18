package game.level;

import game.Game;
import game.WorldContext;
import game.entity.*;

public class LevelThree implements Level {

    public LevelThree() {}

    @Override
    public void init(WorldContext worldContext) {
        worldContext.addEntity(EntityCategory.FOOD, new Food(Game.getRandomX(), Game.getRandomY(), 10, true, worldContext));
        worldContext.addEntity(EntityCategory.FOOD, new Food(Game.getRandomX(), Game.getRandomY(), 10, true, worldContext));
        worldContext.addEntity(EntityCategory.FOOD, new Food(Game.getRandomX(), Game.getRandomY(), 12, true, worldContext));
        worldContext.addEntity(EntityCategory.FOOD, new Food(Game.getRandomX(), Game.getRandomY(), 12, true, worldContext));
        worldContext.addEntity(EntityCategory.FOOD, new Food(Game.getRandomX(), Game.getRandomY(), 14, true, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyBasic(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 12, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyBasic(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 12, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyBasic(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 12, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyBasic(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 12, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyUnpredictable(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 10, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyUnpredictable(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 10, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyUnpredictable(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 10, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyFollower(Game.getRandomX(), Game.getRandomY(), 12, worldContext));
        worldContext.addEntity(EntityCategory.ENEMY, new EnemyFollower(Game.getRandomX(), Game.getRandomY(), 12, worldContext));
    }

    @Override
    public void interval(WorldContext worldContext, int counter) {
        if(counter % 600 == 0) {
            worldContext.addEntity(EntityCategory.ENEMY, new EnemyBasic(Game.getRandomX(), Game.getRandomY(), Game.random(-3, 3), Game.random(-3, 3), 10, worldContext));
        }
        if(counter % 800 == 0) {
            worldContext.addEntity(EntityCategory.ENEMY, new EnemyUnpredictable(Game.getRandomX(), Game.getRandomY(), Game.random(-4, 4), Game.random(-4, 4), 10, worldContext));
        }
    }

    @Override
    public Level getNext() {
        return new LevelFour();
    }
}
