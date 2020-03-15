package game;

import game.entity.Snake;

import java.awt.*;

public class GameActivity extends Activity {
	
	private final int GRID_SIZE = 20;
	private final int BORDER_THICKNESS = 40;
	//private final int OFFSET_X = (Game.WIDTH % GRID_SIZE) / 2;
	//private final int OFFSET_Y = (Game.HEIGHT % GRID_SIZE) / 2;
	private Snake snake;
	private Controller controller;

	public GameActivity(Game game) {
		super(game);
		this.snake = new Snake(2, 2, GRID_SIZE);
		this.addComponent(this.snake);
		this.controller = new Controller(this.snake, this);
		game.addKeyListener(this.controller);
	}

	@Override
	public void step() {
		super.step();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		for(int x = BORDER_THICKNESS; x < Game.WIDTH - BORDER_THICKNESS ; x += GRID_SIZE) {
			g.drawLine(x, BORDER_THICKNESS, x, Game.HEIGHT - BORDER_THICKNESS);
		}
		for(int y = BORDER_THICKNESS; y < Game.HEIGHT - BORDER_THICKNESS; y += GRID_SIZE) {
			g.drawLine(BORDER_THICKNESS, y, Game.WIDTH - BORDER_THICKNESS, y);
		}
		super.paint(g);
	}
}
