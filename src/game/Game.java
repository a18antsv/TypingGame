package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import menu.MainMenu;

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
     * Varje cycel består av ett anrop på render() och sker i princip while(true) - så ofta som
     * möjligt, men tar hänsyn till att den även behöver anropa step() 60 per sekund.
     *
     * Genom att varje cykel räkna ut hur mycket tid som passerat sedan förra cykeln avgörs om
     * step() bör anropas eller kan skippas. När variablen stepsThisCykel är >= 1 "tar spelet ett
     * steg". Om spelet körs på en långsammare enhet kan det behövas ta två eller flera steg denna
     * cykel (mellan varje rendering) för att uppfylla 60 steg per sekund och därmed används
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
