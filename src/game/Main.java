package game;
import java.awt.EventQueue;

/**
 * Normal enemy following player
 * Shield around enemy like a circle and you have to shoot to break it before shooting the enemy
 * Nest spawning enemies
 * 
 * 
 * @author Anton Svensson - a18antsv
 *
 */

public class Main {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	Window window = new Window("TypoShooter", WIDTH, HEIGHT);
        	Game game = new Game();
        
        	window.add(game);
        	window.setVisible(true);
        	game.start();
        });
	}
}
