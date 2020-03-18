package game;

/**
 * Components that can move implements this interface
 */
public interface Updateable extends GameComponent {
	void step();
}
