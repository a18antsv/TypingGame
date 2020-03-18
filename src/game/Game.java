package game;

import menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Extends JPanel to draw on and is where the game is actually running from.
 */
public class Game extends JPanel {
	
	private static final long serialVersionUID = -4780536924710960575L;
	
	public static final int WIDTH = Main.WIDTH - 16; //Component takes up 16 pixels less than width given to JFrame
	public static final int HEIGHT = Main.HEIGHT - 39; //Component takes up 39 pixels less than height given to JFrame

	private Thread thread;
	private boolean isRunning = false;
	private Activity activity;
	private static Random random;

	public Game() {
		this.thread = new Thread(this::run);
		random = new Random();
		this.setActivity(new MainMenu(this)); //The game should start in the main menu
		this.setFocusable(true); //Sets focus to the JPanel
	}

	/**
	 * Starts the Game thread and the gameloop.
	 */
	public void start() {
		this.thread.start();
		this.isRunning = true;
	}

	/**
	 * Stops the gameloop so step() and render() will not be called anymore
	 */
	public void stop() {
		this.isRunning = false;
	}
	
	/**
     * The gameloop:
     * Each cycle consists of a call to render() and is baically done while(true) - as often as possible,
     * but takes into account that it also need sto call step() 60 times per second.
	 * By calculating how much time has passed since the previous cycle it is possible to determine if step()
	 * should be called or skipped. When the variable stepsThisCycle is >= 1 the game "takes a step".
	 * If the game is running on a slower device it may need to take two or more steps this cycle
	 * (between each render) to meet 60 steps per second and thus is while(stepsThisCycle >= used).
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


	/**
	 * Takes a step for all components in the active Activity
	 */
	public void step() {
		this.activity.step();
	}

	/**
	 * The paintComponent runs when the JPanel is created and when repaint is called on the JPanel.
	 * repaint() is called from the gameloop to repaint everything after every entity has updated.
	 * A background rectangle is first drawn and then the current activity's components are painted.
	 * @param g Graphics context
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		this.activity.paint(g);
	}

	/**
	 * Sets the activity the game should currently do.
	 * One Activity implements MouseListener to register mouse events but as others do not
	 * MouseListener is removed from the Game if the Activity that implemented it was active before to prevent bad
	 * registration of mouse clicks.
	 * @param activity The activity that should be active
	 */
	public void setActivity(Activity activity) {
		if(this.activity instanceof MouseListener) {
			this.removeMouseListener((MouseListener) this.activity);
		}
		this.activity = activity;
	}

	/**
	 * Gets random Integer value representing the x-coordinate an entity spawn on within the game area.
	 * Extra margin added for an entity to never spawn little outside game area and get stuck.
	 * @return random X-coordinate
	 */
	public static int getRandomX() {
    	return random(WorldContext.BORDER_THICKNESS + 20, Game.WIDTH - WorldContext.BORDER_THICKNESS - 40);
	}

	/**
	 * Gets random Integer value representing the y-coordinate an entity spawn on within the game area.
	 * Extra margin added for an entity to never spawn little outside game area and get stuck.
	 * @return random Y-coordinate
	 */
	public static int getRandomY() {
		return random(WorldContext.BORDER_THICKNESS + 20, Game.HEIGHT - WorldContext.BORDER_THICKNESS - 40);
	}

	/**
	 * Returning a number in the range between the two parameter integer values
	 * @param min Integer value for start of range
	 * @param max Integer value for end of range
	 * @return Random Integer value in range
	 */
	public static int random(int min, int max) {
		int number = min + random.nextInt(max - min + 1);
		if(number == 0) {
			number = random(min, max);
		}
		return number;
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
