package menu;

import game.Game;
import game.HighScore;

/**
 * A class representing the game over menu activity.
 */
public class GameOverMenu extends Menu {

    private int BUTTON_WIDTH = 240;
    private int BUTTON_HEIGHT = 80;

    /**
     * The game over menu is showing information about how much food was eaten and how many enemies were killed in the previously played session.
     * The all time high score is also shown and updated if the new score was better than the high score.
     * A button is also created that takes the player back to the main menu.
     * @param game The instance of Game
     * @param score The HighScore object with the score of the previously played session.
     */
    public GameOverMenu(Game game, HighScore score) {
        super(game);
        HighScore.newScore(score);
        HighScore highScore = HighScore.deserialize();
        this.addComponent(new MenuText("GameOver", Game.WIDTH / 2, 150, 108));
        this.addComponent(new MenuText("This round:", 250, 300, 32));
        this.addComponent(new MenuText("Food eaten: " + score.getMaxEatenFood(), 250, 340, 32));
        this.addComponent(new MenuText("Killed enemies: " + score.getMaxKilledEnemies(), 250, 380, 32));
        this.addComponent(new MenuText("HighScore:", Game.WIDTH - 250, 300, 32));
        this.addComponent(new MenuText("Food eaten: " + highScore.getMaxEatenFood(), Game.WIDTH - 250, 340, 32));
        this.addComponent(new MenuText("Killed enemies: " + highScore.getMaxKilledEnemies(), Game.WIDTH  - 250, 380, 32));
        this.addComponent(new MenuButton("MainMenu", Game.getCenterX(BUTTON_WIDTH), Game.getCenterY(BUTTON_HEIGHT) + BUTTON_HEIGHT * 2 + 20 * 2, BUTTON_WIDTH, BUTTON_HEIGHT, 42, this::toMainMenu));
    }

    /**
     * The action to take when the user clicks on the "MainMenu" button. Activity is set to MainMenu.
     */
    private void toMainMenu() {
        this.getGame().setActivity(new MainMenu(this.getGame()));
    }

}
