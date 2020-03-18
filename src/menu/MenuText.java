package menu;

import java.awt.*;

/**
 * Class representing text drawn in the Menu
 */
public class MenuText extends MenuItem {

    private Font font;

    public MenuText(String text, int x, int y, int fontSize) {
        super(text, x, y, fontSize);
        this.font = new Font("Monospaced", Font.PLAIN, fontSize);
    }

    /**
     * Paints text to the Graphics context. Takes the width of the string and the height of the font into consideration
     * when positioning the text to make it possible to provide an x-value and y value and that be the middle of the text.
     * @param g The Graphics context
     */
    @Override
    public void paint(Graphics g) {
        FontMetrics metrics = g.getFontMetrics(this.font);

        int x = this.x - metrics.stringWidth(this.text) / 2;
        int y = this.y - metrics.getHeight() / 2 + metrics.getAscent();

        g.setColor(Color.WHITE);
        g.setFont(this.font);

        g.drawString(this.text, x, y);
    }
}
