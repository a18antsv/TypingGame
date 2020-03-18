package menu;

import game.Activity;
import game.Game;
import game.GameComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * A class that holds everything in common for menus.
 * A menu is an activity and each instance of different menus can be set as the game activity to show that menu.
 * Menu implements MouseListener for registering mouse events.
 */
public abstract class Menu extends Activity implements MouseListener {
	
	private LinkedList<Clickable> clickables;
		
	public Menu(Game game) {
		super(game);
		this.clickables = new LinkedList<>();
		game.addMouseListener(this);
	}

	/**
	 * Adds a GameComponent to the right list by check which interface the GameComponent implements.
	 * Functionality is added here compared to the Acitivty class which only holds Updateable and Paintable GameComponents.
	 * Menus also holds Clickable components, hence a check for Clickables. Clickables are added to a list.
	 * @param component The GameComponent to add
	 */
	@Override
	public void addComponent(GameComponent component) {
		super.addComponent(component);
		if(component instanceof Clickable) {
			clickables.add((Clickable) component);
		}
	}

	/**
	 * Removes a GameComponent from the right list by check which interface the GameComponent implements.
	 * Functionality is added here compared to the Acitivty class which only holds Updateable and Paintable GameComponents.
	 * Menus also holds Clickable components, hence a check for Clickables. Clickables are removed from a list.
	 * @param component The GameComponent to remove
	 */
	@Override
	public void removeComponent(GameComponent component) {
		super.removeComponent(component);
		if(component instanceof Clickable) {
			clickables.remove(component);
		}
	}

	/**
	 * The mousePressed function exectues automatically everytime the mouse is clicked.
	 * Iterates through every Clickable GameComponent and checks if the mouse was within the components area or not.
	 * If the mouse was within the area the Clickables Runnable (Action) function is executed.
	 * @param e The MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
	
		clickables.forEach(clickable -> {
			if(isOver(mouseX, mouseY, clickable.getHitbox())) {
				clickable.getAction().run();
			}
		});
	}

	/**
	 * Checks if a mouse click was over a specific area
	 * @param mouseX The clicked x-coordinate
	 * @param mouseY The clicked y-coordinate
	 * @param rectangle A rectangle representing the area the mouse coordinates should be within
	 * @return boolean If mouse clicked over the area
	 */
	private boolean isOver(int mouseX, int mouseY, Rectangle rectangle) {
		return (
			mouseX > rectangle.x && 
			mouseX < rectangle.x + rectangle.width &&
			mouseY > rectangle.y &&
			mouseY < rectangle.y + rectangle.height
		);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}	
}
