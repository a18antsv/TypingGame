package game;

import java.awt.*;

/**
 * Components that can be painted to the game implements this interface
 */
public interface Paintable extends GameComponent {
	void paint(Graphics g);
}
