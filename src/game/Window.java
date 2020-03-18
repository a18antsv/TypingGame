package game;

import javax.swing.*;
import java.awt.*;

/**
 * Extends JFrame behaviour, places a Window in the middle of the screen with the title, width and height passed to the constructor
 */
public class Window extends JFrame {
		
	private static final long serialVersionUID = 1L;

	public Window(String title, int width, int height) {
		this.setTitle(title);
		this.setPreferredSize(new Dimension(width, height));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
