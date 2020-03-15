package game.entity;

import game.WorldContext;

import java.awt.*;

public class Player extends EntityMoving {

	public Player(int x, int y, int thickness, WorldContext worldContext) {
		super(x, y, thickness, worldContext);
	}

	@Override
	public void paintTransformed(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(0, 0, thickness, thickness);
	}
}
