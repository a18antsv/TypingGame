package game;

import game.entity.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class that works as the controller and listening to all keystrokes to match against controllers, food and enemies texts.
 */
public class Controller implements KeyListener {

	private float velocity; //The standard velocity the player will move at
	private final ControllerText[] wordControllers;
	private WorldContext worldContext;
	
	public Controller(Activity activity, WorldContext worldContext) {
		this.velocity = 1.5f;

		//One ControllerText for each direction is created at the right positions and orientations.
		//Reference to method to execute when the controller's word is completed is provided.
		this.wordControllers = new ControllerText[] {
			new ControllerText(Game.WIDTH / 2f, Text.Size.LARGE.getFontHeight(), Text.Orientation.DEFAULT, Text.Size.LARGE, this::completeUp),
			new ControllerText(Game.WIDTH - Text.Size.LARGE.getFontHeight(), Game.HEIGHT / 2f, Text.Orientation.CLOCKWISE, Text.Size.LARGE, this::completeRight),
			new ControllerText(Game.WIDTH / 2f, Game.HEIGHT - Text.Size.LARGE.getFontHeight() * 0.6f, Text.Orientation.DEFAULT, Text.Size.LARGE, this::completeDown),
			new ControllerText(Text.Size.LARGE.getFontHeight(), Game.HEIGHT / 2f, Text.Orientation.COUNTER_CLOCKWISE, Text.Size.LARGE, this::completeLeft)
		};
		this.worldContext = worldContext;

		//Adds the controllers to the list of GameComponents so they will be drawn
		for (ControllerText wordController : wordControllers) {
			activity.addComponent(wordController);
		}
	}

	private void completeUp() {
		this.worldContext.getPlayer().setVelocity(0, -this.velocity);
		this.resetControllers();
	}
	private void completeRight() {
		this.worldContext.getPlayer().setVelocity(this.velocity, 0);
		this.resetControllers();
	}
	private void completeDown() {
		this.worldContext.getPlayer().setVelocity(0, this.velocity);
		this.resetControllers();
	}
	private void completeLeft() {
		this.worldContext.getPlayer().setVelocity(-this.velocity, 0);
		this.resetControllers();
	}

	/**
	 * Resets the text of all controllers
	 */
	private void resetControllers() {
		for (ControllerText wordController : wordControllers) {
			wordController.reset();
		}
	}

	/**
	 * This method is executed automatically once a key is typed.
	 * Iterates through all controllers, enemy entities and food entities and calls the method pressed on each to look
	 * for a match of typed char to the current index in the text.
	 * @param e KeyEvent to get the char from
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		for (ControllerText wordController : this.wordControllers) {
			wordController.pressed(c);
		}
		for(Entity entity : this.worldContext.getEntitiesByCategory(EntityCategory.ENEMY)) {
			Enemy enemy = (Enemy) entity;
			enemy.pressed(c);
		}
		for(Entity entity : this.worldContext.getEntitiesByCategory(EntityCategory.FOOD)) {
			Food food = (Food) entity;
			food.pressed(c);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
}
