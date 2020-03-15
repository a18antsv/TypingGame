package game;

import game.level.LevelHandler;

import java.awt.*;

public class GameActivity extends Activity {

	private Controller controller;
	private WorldContext worldContext;

	public GameActivity(Game game) {
		super(game);
		this.worldContext = new WorldContext();
		this.addComponent(this.worldContext);
		this.addComponent(new LevelHandler(this.worldContext));
		this.controller = new Controller(this.worldContext.getPlayer(), this);
		game.addKeyListener(this.controller);
	}

	@Override
	public void step() {
		super.step();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		super.paint(g);
	}
}
