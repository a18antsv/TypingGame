package game.entity;

import game.Text;
import game.WorldContext;

import java.awt.*;

public abstract class Enemy extends EntityMoving {

	private Text text;

	public Enemy(int x, int y, int thickness, WorldContext worldContext) {
		super(x, y, thickness, worldContext);
	}

	@Override
	protected void paintTransformed(Graphics2D g2d) {
		super.paintTransformed(g2d);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(0, 0, thickness, thickness);
	}
}
