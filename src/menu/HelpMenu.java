package menu;

import game.Game;

public class HelpMenu extends Menu {
	
	private int BUTTON_WIDTH = 240;
	private int BUTTON_HEIGHT = 80;
	
	private MenuButton backButton;

	public HelpMenu(Game game) {
		super(game);
		this.backButton = new MenuButton("Back", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT * 2 + 20 * 2, BUTTON_WIDTH, BUTTON_HEIGHT, this::back);
		this.addComponent(backButton);
	}
	
	private void back() {
		System.out.println("Back");
	}

}
