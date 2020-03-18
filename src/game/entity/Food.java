package game.entity;

import game.EnemyText;
import game.WordSelector;
import game.WorldContext;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A class representing Food that can have shield or not. To remove the shield there is a word to type.
 */
public class Food extends Entity {

    private boolean isProtected;
    private EnemyText text;

    public Food(int x, int y, int thickness, boolean isProtected, WorldContext worldContext) {
        super(new AffineTransform(), thickness, Color.ORANGE, worldContext);
        this.translate(x, y);
        this.text = new EnemyText(WordSelector.getInstance().getWord(), this.getThickness() / 2f, -10, this::removeProtection);
        this.isProtected = isProtected;
    }

    /**
     * Removes the shield from the food and is called when the text is correctly written.
     */
    private void removeProtection() {
        this.isProtected = false;
    }

    public boolean isProtected() {
        return this.isProtected;
    }

    public void pressed(char c) {
        this.text.pressed(c);
    }

    /**
     * Draws a circle around the food with text over if the food should have protection
     * Draws the food.
     * @param g Graphics context
     */
    @Override
    public void paintTransformed(Graphics g) {
        if(this.isProtected) {
            g.setColor(Color.WHITE);
            g.drawArc(-this.getThickness() / 2, -this.getThickness() / 2, this.getThickness() * 2, this.getThickness() * 2, 0, 360);
            this.text.paint(g);
        }
        g.setColor(this.getColor());
        g.fillRect(0, 0, this.getThickness(), this.getThickness());
    }

    /**
     * Could be used for animations in the future but as of now Food is not moving.
     */
    @Override
    public void step() {}
}
