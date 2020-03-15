package game.entity;

import game.WorldContext;

public class EnemyBasic extends Enemy {

    public EnemyBasic(int x, int y, int thickness, WorldContext worldContext) {
        super(x, y, thickness, worldContext);
    }

    @Override
    public void step() {
        this.setVelocity(7, 3);
        super.step();
    }
}
