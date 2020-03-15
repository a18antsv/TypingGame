package menu;

import game.GameComponent;

import java.awt.*;

public interface Clickable extends GameComponent {
	Rectangle getHitbox();
	Runnable getAction();
}
