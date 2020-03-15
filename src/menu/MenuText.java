package menu;

import java.awt.*;

public class MenuText extends MenuItem {

    public MenuText(String text, int x, int y, int fontSize) {
        super(text, x, y, fontSize);
    }

    @Override
    public void paint(Graphics g) {
        Font font = new Font("Monospaced", Font.PLAIN, this.fontSize);
        FontMetrics metrics = g.getFontMetrics(font);

        int x = this.x - metrics.stringWidth(this.text) / 2;
        int y = this.y - metrics.getHeight() / 2 + metrics.getAscent();

        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString(this.text, x, y);
    }
}
