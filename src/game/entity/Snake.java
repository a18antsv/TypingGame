package game.entity;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import game.Game;

public class Snake extends Unit {
	
	private LinkedList<TailPart> tail;

	public Snake(int x, int y, int gridSize) {
		super(x * gridSize, y * gridSize, gridSize, gridSize);
		this.tail = new LinkedList<>();
		/*for(int i = 0; i < 3; i++) {
			tail.add(new TailPart(x + i * width, y, width, height));
		}*/
		
	}

	@Override
	public void step() {
		super.step();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
		this.tail.forEach(tailPart -> tailPart.paint(g));
	}

}
