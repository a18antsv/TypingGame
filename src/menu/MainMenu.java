package menu;

import game.Game;
import game.GameActivity;

public class MainMenu extends Menu {
	
	private int BUTTON_WIDTH = 240;
	private int BUTTON_HEIGHT = 80;

	private MenuButton newGameButton;
	private MenuButton helpButton;
	private MenuButton quitButton;
	
	public MainMenu(Game game) {
		super(game);
		this.newGameButton = new MenuButton("New Game", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT), BUTTON_WIDTH, BUTTON_HEIGHT, this::newGame);
		this.helpButton = new MenuButton("Help", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT + 20, BUTTON_WIDTH, BUTTON_HEIGHT, this::help);
		this.quitButton = new MenuButton("Quit", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT * 2 + 20 * 2, BUTTON_WIDTH, BUTTON_HEIGHT, this::quit);
		this.addComponent(newGameButton);
		this.addComponent(helpButton);
		this.addComponent(quitButton);
	}
	
	private void newGame() {
		System.out.println("New Game");
		this.getGame().setActivity(new GameActivity(this.getGame()));
	}
	
	private void help() {
		System.out.println("Help");
	}
	
	private void quit() {
		System.out.println("Quit");
		System.exit(0);
	}
}
