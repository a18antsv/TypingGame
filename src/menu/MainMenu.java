package menu;

import game.Game;
import game.GameActivity;

/**
 * A class representing the main menu activity.
 */
public class MainMenu extends Menu {

	private int BUTTON_WIDTH = 240;
	private int BUTTON_HEIGHT = 80;

	/**
	 * GameComponents are added to different lists depending on what the component implements which makes it possible to easily draw, update and click on them.
	 * @param game The instance of game
	 */
	public MainMenu(Game game) {
		super(game);
		this.addComponent(new MenuText("Typing Game", Game.WIDTH / 2, 150, 108));
		this.addComponent(new MenuButton("New Game", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT), BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::newGame));
		this.addComponent(new MenuButton("Help", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT + 20, BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::help));
		this.addComponent(new MenuButton("Quit", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT * 2 + 20 * 2, BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::quit));
	}

	/**
	 * The action to take when the user clicks on the "New Game" button.
	 * The game should start. Activity is set to GameActivity.
	 */
	private void newGame() {
		this.getGame().setActivity(new GameActivity(this.getGame()));
	}

	/**
	 * The action to take when the user clicks on the "Help" button. Activity is set to HelpMenu.
	 */
	private void help() {
		this.getGame().setActivity(new HelpMenu(this.getGame()));
	}

	/**
	 * The action to take when the user clicks on the "Quit" button. The program is killed.
	 */
	private void quit() {
		System.exit(0);
	}
}
