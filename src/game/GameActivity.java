package game;

import game.level.LevelHandler;

import java.awt.*;

/**
 * A class representing the game activity that is active when the actual gameplay is happening.
 */
public class GameActivity extends Activity {

	private Controller controller;
	private WorldContext worldContext;
	private LevelHandler levelHandler;

	public GameActivity(Game game) {
		super(game);

		//When a new GameActivity is started the word list will be reset to the default normal one.
		WordSelector.getInstance().loadFile(WordSelector.class.getResource("/wordsNormal.txt"));
		this.worldContext = new WorldContext(game);
		this.levelHandler = new LevelHandler(this.worldContext);
		this.controller = new Controller(this.worldContext.getPlayer(), this, this.worldContext);
		this.addComponent(this.worldContext);
		this.addComponent(this.levelHandler);
		game.addKeyListener(this.controller);
	}

	/**
	 * Draws a background to draw everything else on top of and then calls the super paint method to draw Paintables in the Activity class.
	 * @param g Graphics context
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		super.paint(g);
	}
}
