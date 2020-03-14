package menu;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import game.Activity;
import game.Game;
import game.GameComponent;

public abstract class Menu extends Activity implements MouseListener {
	
	private LinkedList<Clickable> clickables;
		
	public Menu(Game game) {
		super(game);
		this.clickables = new LinkedList<>();
		game.addMouseListener(this);
	}
	
	@Override
	public void addComponent(GameComponent component) {
		super.addComponent(component);
		if(component instanceof Clickable) {
			clickables.add((Clickable) component);
		}
	}
	
	@Override
	public void removeComponent(GameComponent component) {
		super.removeComponent(component);
		if(component instanceof Clickable) {
			clickables.remove(component);
		}
	}

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
