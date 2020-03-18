package menu;

import game.GameComponent;

import java.awt.*;

/**
 * Components that can be clicked at implements this interface
 */
public interface Clickable extends GameComponent {
	Rectangle getHitbox();
	Runnable getAction();
}
