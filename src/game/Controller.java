package game;

import game.entity.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

	private final Snake snake;
	private float velocity;
	private final ControllerText[] wordControllers;
	
	public Controller(Snake snake, Activity activity) {
		this.snake = snake;
		this.velocity = 2;
		this.wordControllers = new ControllerText[] {
			new ControllerText(Game.WIDTH / 2f, Text.Size.LARGE.getFontHeight(), Text.Orientation.DEFAULT, Text.Size.LARGE, this::completeUp),
			new ControllerText(Game.WIDTH - Text.Size.LARGE.getFontHeight(), Game.HEIGHT / 2f, Text.Orientation.CLOCKWISE, Text.Size.LARGE, this::completeRight),
			new ControllerText(Game.WIDTH / 2f, Game.HEIGHT - Text.Size.LARGE.getFontHeight() * 0.6f, Text.Orientation.DEFAULT, Text.Size.LARGE, this::completeDown),
			new ControllerText(Text.Size.LARGE.getFontHeight(), Game.HEIGHT / 2f, Text.Orientation.COUNTER_CLOCKWISE, Text.Size.LARGE, this::completeLeft)
		};
		for (ControllerText wordController : wordControllers) {
			activity.addComponent(wordController);
		}
	}

	private void completeUp() {
		this.snake.setVelocity(0, -this.velocity);
		this.resetControllers();
	}
	private void completeRight() {
		this.snake.setVelocity(this.velocity, 0);
		this.resetControllers();
	}
	private void completeDown() {
		this.snake.setVelocity(0, this.velocity);
		this.resetControllers();
	}
	private void completeLeft() {
		this.snake.setVelocity(-this.velocity, 0);
		this.resetControllers();
	}

	private void resetControllers() {
		for (ControllerText wordController : wordControllers) {
			wordController.reset();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		for (ControllerText wordController : this.wordControllers) {
			wordController.pressed(c);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
}
