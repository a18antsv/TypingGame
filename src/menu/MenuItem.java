package menu;

import game.Paintable;

import java.awt.*;

/**
 * Abstract class holding all common attributes for different menu items, like buttons and text.
 */
public abstract class MenuItem implements Paintable {

    protected String text;
    protected int x;
    protected int y;
    protected int fontSize;
    protected Font font;

    public MenuItem(String text, int x, int y, int fontSize) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.font = new Font("Monospaced", Font.PLAIN, fontSize);
    }
}
