package game;

import java.awt.*;
import java.util.LinkedList;

public abstract class Activity implements Paintable, Updateable {
	
	private final Game game;
	private final LinkedList<Paintable> paintables;
	private final LinkedList<Updateable> updateables;
	
	public Activity(Game game) {
		this.game = game;
		this.paintables = new LinkedList<>();
		this.updateables = new LinkedList<>();
	}

	@Override
	public void step() {
		updateables.forEach(Updateable::step);
	}

	@Override
	public void paint(Graphics g) {
		paintables.forEach(paintable -> paintable.paint(g));
	}
	
	public void addComponent(GameComponent component) {
		if(component instanceof Paintable) {
			this.paintables.add((Paintable) component);
		} 
		if(component instanceof Updateable) {
			this.updateables.add((Updateable) component);
		}
	}
	
	public void removeComponent(GameComponent component) {
		if(component instanceof Paintable) {
			this.paintables.remove(component);
		} 
		if(component instanceof Updateable) {
			this.updateables.remove(component);
		}
	}

	public Game getGame() {
		return game;
	}
}