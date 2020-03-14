package menu;

import java.awt.Rectangle;

import game.GameComponent;

public interface Clickable extends GameComponent {
	Rectangle getHitbox();
	Runnable getAction();
}
