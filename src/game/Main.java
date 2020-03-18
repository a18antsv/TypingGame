package game;

import java.awt.*;

/**
 * Root class containing the main method which launches the application
 * The singleton instance of Game is added to the JFrame as Game extends JPanel and is a component.
 * Game thread is started.
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
