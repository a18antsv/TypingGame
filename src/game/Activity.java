package game;

import java.awt.*;
import java.util.LinkedList;

/**
 * An abstract class that cannot be instantiated, holding things that are common for all Activities
 * An activity needs the step and paint methods, hence Paintable and Updateable is implemented
 */
public abstract class Activity implements Paintable, Updateable {
	
	private final Game game;
	private final LinkedList<Paintable> paintables;
	private final LinkedList<Updateable> updateables;
	
	public Activity(Game game) {
		this.game = game;
		this.paintables = new LinkedList<>();
		this.updateables = new LinkedList<>();
	}

	/**
	 * Steps all components that implements the Updateable interface and is in the updateables list
	 */
	@Override
	public void step() {
		updateables.forEach(Updateable::step);
	}

	/**
	 * Steps all components that implements the Paintable interface and is in the paintables list
	 */
	@Override
	public void paint(Graphics g) {
		paintables.forEach(paintable -> paintable.paint(g));
	}

	/**
	 * Adds a GameComponent to the right list by checking which interface the GameComponent implements.
	 * @param component The GameComponent to add
	 */
	public void addComponent(GameComponent component) {
		if(component instanceof Paintable) {
			this.paintables.add((Paintable) component);
		} 
		if(component instanceof Updateable) {
			this.updateables.add((Updateable) component);
		}
	}

	/**
	 * Removes a GameComponent from the right list by checking which interface the GameComponent implements.
	 * @param component The GameComponent to remove
	 */
	public void removeComponent(GameComponent component) {
		if(component instanceof Paintable) {
			this.paintables.remove(component);
		} 
		if(component instanceof Updateable) {
			this.updateables.remove(component);
		}
	}

	/**
	 * @return The instance of Game
	 */
	public Game getGame() {
		return game;
	}
}