package menu;

import game.Game;

/**
 * A class representing the help menu activity.
 */
public class HelpMenu extends Menu {

	private int BUTTON_WIDTH = 240;
	private int BUTTON_HEIGHT = 80;

	/**
	 * Adds GameComponents presenting information about how to play the game to lists.
	 * @param game The instance of Game
	 */
	public HelpMenu(Game game) {
		super(game);
		this.addComponent(new MenuText("Help", Game.WIDTH / 2, 150, 108));
		this.addComponent(new MenuText("This game is all about typing. You are the green rectangle.", Game.WIDTH / 2, 230, 16));
		this.addComponent(new MenuText("To control the green rectangle you have to type the word currently located in the direction you want to go.", Game.WIDTH / 2, 258, 16));
		this.addComponent(new MenuText("Eat orange rectangles by collision. Food can have a shield, destroy it first, otherwise you take damage.", Game.WIDTH / 2, 286, 16));
		this.addComponent(new MenuText("Cyan, magenta and yellow rectangles are enemies of different types. Yellow will follow you!", Game.WIDTH / 2, 314, 16));
		this.addComponent(new MenuText("Type the word over an enemy to kill it.", Game.WIDTH / 2, 342, 16));
		this.addComponent(new MenuText("Once you killed all enemies and ate all food on the screen, a new level will start.", Game.WIDTH / 2, 370, 16));
		this.addComponent(new MenuText("The wordlist can change to contain much harder or easier words.", Game.WIDTH / 2, 398, 16));
		this.addComponent(new MenuText("If you beat all levels there will be a level called LevelInfinite which will play over and over until you die.", Game.WIDTH / 2, 426, 16));
		this.addComponent(new MenuText("Highscore will be saved and shown after you die based on how many enemies killed and how much food eaten.", Game.WIDTH / 2, 454, 16));
		this.addComponent(new MenuButton("Back", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT * 2 + 20 * 2, BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::back));
	}

	/**
	 * The action to take when the user clicks on the "Back button". Activity is set to MainMenu.
	 */
	private void back() {
		this.getGame().setActivity(new MainMenu(this.getGame()));
	}

}
