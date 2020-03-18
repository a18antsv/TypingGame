package game.entity;

import game.EnemyText;
import game.WordSelector;
import game.WorldContext;

import java.awt.*;

/**
 * A class holding all functionality each type of enemy needs.
 * All enemies have a text the player needs to write to kill the enemy.
 */
public abstract class Enemy extends EntityMoving {

	private EnemyText text;

	public Enemy(int x, int y, int thickness, Color color, WorldContext worldContext) {
		super(x, y, thickness, color, worldContext);
		this.text = new EnemyText(WordSelector.getInstance().getWord(), this.getThickness() / 2f, -10, this::kill);
	}

	/**
	 * Removes the enemy from the worldContext and increments the number of killed enemies by one.
	 * Method is referenced in the Runnable which executes when text is completed
	 */
	private void kill() {
		WorldContext worldContext = this.getWorldContext();
		worldContext.removeEntity(this);
		worldContext.setNumberOfKilledEnemies(worldContext.getNumberOfKilledEnemies() + 1);
	}

	public void pressed(char c) {
		this.text.pressed(c);
	}

	/**
	 * Paints the enemy and text after transformation.
	 * @param g Graphics context
	 */
	@Override
	protected void paintTransformed(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(0, 0, this.getThickness(), this.getThickness());
		this.text.paint(g);
	}
}
