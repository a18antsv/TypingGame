package menu;

import game.Game;
import game.GameActivity;

public class MainMenu extends Menu {
	
	private int BUTTON_WIDTH = 240;
	private int BUTTON_HEIGHT = 80;

	private MenuText title;
	private MenuButton newGameButton;
	private MenuButton helpButton;
	private MenuButton quitButton;
	
	public MainMenu(Game game) {
		super(game);
		this.title = new MenuText("TypoShooter", Game.WIDTH / 2, 150, 108);
		this.newGameButton = new MenuButton("New Game", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT), BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::newGame);
		this.helpButton = new MenuButton("Help", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT + 20, BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::help);
		this.quitButton = new MenuButton("Quit", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT * 2 + 20 * 2, BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::quit);
		this.addComponent(title);
		this.addComponent(newGameButton);
		this.addComponent(helpButton);
		this.addComponent(quitButton);
	}
	
	private void newGame() {
		this.getGame().setActivity(new GameActivity(this.getGame()));
	}
	
	private void help() {
		this.getGame().setActivity(new HelpMenu(this.getGame()));
	}
	
	private void quit() {
		System.exit(0);
	}
}
