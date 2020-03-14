package game;
import java.awt.Dimension;

import javax.swing.JFrame;

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