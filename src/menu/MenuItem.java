package menu;

import game.Paintable;

public abstract class MenuItem implements Paintable {

    protected String text;
    protected int x;
    protected int y;
    protected int fontSize;

    public MenuItem(String text, int x, int y, int fontSize) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
    }
}
