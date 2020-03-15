package game.level;

import game.Updateable;
import game.WorldContext;

public class LevelHandler implements Updateable {

    private WorldContext worldContext;
    private int counter;

    public LevelHandler(WorldContext worldContext) {
        this.worldContext = worldContext;
        this.worldContext.setLevel(new LevelOne());
        this.worldContext.getLevel().init(this.worldContext);
        this.counter = 1;
    }

    @Override
    public void step() {
        if(this.counter % 1000 == 0) {
            this.worldContext.setLevel(this.worldContext.getLevel().getNext());
            this.worldContext.getLevel().init(this.worldContext);
        }
        this.counter++;
    }
}


