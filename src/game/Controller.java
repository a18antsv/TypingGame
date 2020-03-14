package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.entity.Snake;

public class Controller implements KeyListener, Paintable {
	
	private final Snake snake;
	private boolean isUpPressed = false;
	private boolean isDownPressed = false;
	private boolean isLeftPressed = false;
	private boolean isRightPressed = false;
	
	public Controller(Snake snake) {
		this.snake = snake;
	}
	
	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			isUpPressed = true;
			snake.setVelocityY(-5);
		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			isDownPressed = true;
			snake.setVelocityY(5);
		}
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			isLeftPressed = true;
			snake.setVelocityX(-5);
		}
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			isRightPressed = true;
			snake.setVelocityX(5);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			isUpPressed = false;
		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			isDownPressed = false;
		}
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			isLeftPressed = false;
		}
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			isRightPressed = false;
		}
		
		if(!isUpPressed && !isDownPressed) {
			snake.setVelocityY(0);
		}
		if(!isLeftPressed && !isRightPressed) {
			snake.setVelocityX(0);					
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
