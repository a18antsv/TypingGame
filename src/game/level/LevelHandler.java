package game.level;

import game.Updateable;
import game.WorldContext;
import game.entity.EntityCategory;

/**
 * A class handling what level should be played and when the level should switch.
 */
public class LevelHandler implements Updateable {

    private WorldContext worldContext;
    private int counter;

    public LevelHandler(WorldContext worldContext) {
        this.worldContext = worldContext;
        this.worldContext.setLevel(new LevelOne());
        this.worldContext.getLevel().init(this.worldContext);
        this.counter = 1;
    }

    /**
     * Step method running 60 times per second with a counter incrementing making it possible to spawn enemies in a specific interval over the initial spawn.
     * When no enemies and food remain in the worldContext the Level will be set to the next level.
     */
    @Override
    public void step() {
        this.counter++;
        if(this.worldContext.getEntitiesByCategory(EntityCategory.ENEMY).size() <= 0 && this.worldContext.getEntitiesByCategory(EntityCategory.FOOD).size() <= 0) {
            this.worldContext.setLevel(this.worldContext.getLevel().getNext());
            this.worldContext.getLevel().init(this.worldContext);
            this.worldContext.getPlayer().setHealth(100);
        }
        this.worldContext.getLevel().interval(this.worldContext, this.counter);
    }
}


