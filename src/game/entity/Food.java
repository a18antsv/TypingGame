package game.entity;

import game.WorldContext;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Food extends Entity {

    public Food(int x, int y, int thickness, WorldContext worldContext) {
        super(new AffineTransform(), thickness, worldContext);
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void step() {

    }
}
