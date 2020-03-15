package menu;

import game.Game;

public class HelpMenu extends Menu {
	
	private int BUTTON_WIDTH = 240;
	private int BUTTON_HEIGHT = 80;

	private MenuText title;
	private MenuText information;
	private MenuButton backButton;

	public HelpMenu(Game game) {
		super(game);
		this.title = new MenuText("Help", Game.WIDTH / 2, 150, 108);
		this.information = new MenuText("This is information about the game", Game.WIDTH / 2, 250, 32);
		this.backButton = new MenuButton("Back", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT * 2 + 20 * 2, BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::back);
		this.addComponent(title);
		this.addComponent(information);
		this.addComponent(backButton);
	}
	
	private void back() {
		this.getGame().setActivity(new MainMenu(this.getGame()));
	}

}
