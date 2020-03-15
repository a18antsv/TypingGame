package game;

import menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Game extends JPanel {
	
	private static final long serialVersionUID = -4780536924710960575L;
	
	public static final int WIDTH = Main.WIDTH - 16; //Component takes up 16 pixels less than width given to JFrame
	public static final int HEIGHT = Main.HEIGHT - 39; //Component takes up 39 pixels less than height given to JFrame

	private Thread thread;
	private boolean isRunning = false;
	private Activity activity;
		
	public Game() {
		this.thread = new Thread(this::run);
		this.setActivity(new MainMenu(this));
		this.setFocusable(true);
	}
	
	public void start() {
		this.thread.start();
		this.isRunning = true;
	}
	
	public void stop() {
		this.isRunning = false;
	}
	
	/*
     * Spelets gameloop:
     * Varje cycel best�r av ett anrop p� render() och sker i princip while(true) - s� ofta som
     * m�jligt, men tar h�nsyn till att den �ven beh�ver anropa step() 60 per sekund.
     *
     * Genom att varje cykel r�kna ut hur mycket tid som passerat sedan f�rra cykeln avg�rs om
     * step() b�r anropas eller kan skippas. N�r variablen stepsThisCykel �r >= 1 "tar spelet ett
     * steg". Om spelet k�rs p� en l�ngsammare enhet kan det beh�vas ta tv� eller flera steg denna
     * cykel (mellan varje rendering) f�r att uppfylla 60 steg per sekund och d�rmed anv�nds
     * while(stepsThisCykle >=1)
     */
    public void run() {

        long lastTime = System.nanoTime();
        final double nsPerStep = 1_000_000_000.0 / 60.0;
        double stepsThisCycle = 0;

        // For debugging
        long timer = System.currentTimeMillis();
        int frames = 0;
        int steps = 0;

        while(isRunning) {
            long now = System.nanoTime();
            stepsThisCycle += (now-lastTime) / nsPerStep;
            lastTime = now;

            while(stepsThisCycle >= 1) {
                step();
                stepsThisCycle--;
                steps++;
            }
            repaint();
            frames++;

            // For debugging
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames + " fps - " + steps + " sps");
                frames = 0;
                steps = 0;
            }
        }
    }
	
	public void step() {
		this.activity.step();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		this.activity.paint(g);
	}
	
	public void setActivity(Activity activity) {
		if(this.activity instanceof MouseListener) {
			this.removeMouseListener((MouseListener) this.activity);
		}
		this.activity = activity;
	}

	/**
	 * Calculates the x-coordinate an item should be placed at to appear to be  in the middle of the frame
	 * @param itemWidth Width of item
	 * @return Integer value representing the x-coordinate to position the item at
	 */
	public static int getCenterX(int itemWidth) {
		return WIDTH / 2 - itemWidth / 2;
	}
	
	/**
	 * Calculates the y-coordinate an item should be placed at to appear to be  in the middle of the frame
	 * @param itemHeight Height of item to position
	 * @return Integer value representing the y-coordinate to position the item at
	 */
	public static int getCenterY(int itemHeight) {
		return HEIGHT / 2 - itemHeight / 2;
	}
}
