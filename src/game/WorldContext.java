package game;

import game.entity.Entity;
import game.entity.EntityCategory;
import game.entity.Player;
import game.level.Level;
import menu.GameOverMenu;

import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A class representing the context of which Entities exists in.
 */
public class WorldContext implements Paintable, Updateable {

    public static final int BORDER_THICKNESS = 40;
    private Map<EntityCategory, Collection<Entity>> entities; //
    private Player player;
    private Level level;
    private int numberOfKilledEnemies;
    private int numberOfEatenFood;
    private Color backgroundColor;
    private Font font;
    private Game game;

    public WorldContext(Game game) {
        this.game = game;

        //Multi-value map which stores a collection of entities for each key.
        //Is concurrent so threads cannot both remove something from the map and then try to draw it at the same time for example.
        this.entities = new ConcurrentHashMap<>();

        this.player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2, 20, this);
        this.addEntity(EntityCategory.PLAYER, this.player);
        this.numberOfKilledEnemies = 0;
        this.numberOfEatenFood = 0;
        this.backgroundColor = new Color(16, 16, 16);
        this.font = new Font("Monospaced", Font.PLAIN, 12);
    }

    /**
     * Creates a new ConcurrentLinkedQueue to store for the key (EntityCategory enum) if there is nothing mapped to the key already.
     * Adds the entity to the ConcurrentLinkedQueue which is mapped to the key in the ConcurrentHashMap.
     * @param category Enum type for the entity category
     * @param entity Entity to store
     */
    public void addEntity(EntityCategory category, Entity entity) {
        this.entities.computeIfAbsent(category, key -> new ConcurrentLinkedQueue<>());
        this.entities.get(category).add(entity);
    }

    /**
     * Removes the passed entity from theConcurrentHashMap of entities
     * @param entity Entity to remove
     */
    public void removeEntity(Entity entity) {
        this.entities.forEach((category, list) -> list.remove(entity));
    }

    /**
     * Clears the ConcurrentHashMap of entities
     */
    public void removeAllEntities() {
        this.entities.clear();
    }

    /**
     * Extracts everything from a specific category from the ConcurrentHashMap of entities as a ConcurrentLinkedQueue.
     * @param category Enum type for the entity category
     * @return ConcurrentLinkedQueue with all entities that is mapped to passed category in the ConcurrentHashMap of entities.
     */
    public Collection<Entity> getEntitiesByCategory(EntityCategory category) {
        if(this.entities.get(category) == null) {
            return new ConcurrentLinkedQueue<>();
        }
        return this.entities.get(category);
    }

    /**
     * Removes all entities from the map and sets the game's activity to the game over menu.
     * Passes the just finished game round score as a HighScore object to the Game Over menu.
     */
    public void gameOver() {
        this.removeAllEntities();
        this.game.setActivity(new GameOverMenu(this.game, new HighScore(this.numberOfEatenFood, this.numberOfKilledEnemies)));
    }

    /**
     * Paints background, health bar, information text and all entities.
     * @param g Graphics context
     */
    @Override
    public void paint(Graphics g) {
        //Paint background for game area to preserve borders from the previous background where text for controller should be.
        g.setColor(this.backgroundColor);
        g.fillRect(BORDER_THICKNESS, BORDER_THICKNESS, Game.WIDTH - BORDER_THICKNESS * 2, Game.HEIGHT - BORDER_THICKNESS * 2);

        //Draw health bar
        g.setColor(Color.GRAY);
        g.fillRect(Game.WIDTH - BORDER_THICKNESS - 100 * 2, 8, 100 * 2, BORDER_THICKNESS - 16);
        g.setColor(Color.getHSBColor((1f * this.player.getHealth()) / 360, 1f, 1f));
        g.fillRect(Game.WIDTH - BORDER_THICKNESS - this.player.getHealth() * 2, 8, this.player.getHealth() * 2, BORDER_THICKNESS - 16);
        g.setColor(Color.WHITE);
        g.drawRect(Game.WIDTH - BORDER_THICKNESS - 100 * 2, 8, 100 * 2, BORDER_THICKNESS - 16);

        //Draw information text about level and score
        g.setFont(this.font);
        g.drawString("Killed: " + this.numberOfKilledEnemies, BORDER_THICKNESS, 16);
        g.drawString("Eaten: " + this.numberOfEatenFood, BORDER_THICKNESS, 32);
        g.drawString(this.level.getClass().getSimpleName(), BORDER_THICKNESS, Game.HEIGHT - BORDER_THICKNESS / 2);

        //Paint every entity in map
        entities.forEach(((category, entities) -> entities.forEach(entity -> entity.paint(g))));
    }

    /**
     * Steps all entities in the Map.
     */
    @Override
    public void step() {
        entities.forEach(((category, entities) -> entities.forEach(Entity::step)));
    }

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getNumberOfKilledEnemies() {
        return numberOfKilledEnemies;
    }

    public void setNumberOfKilledEnemies(int numberOfKilledEnemies) {
        this.numberOfKilledEnemies = numberOfKilledEnemies;
    }

    public int getNumberOfEatenFood() {
        return numberOfEatenFood;
    }

    public void setNumberOfEatenFood(int numberOfEatenFood) {
        this.numberOfEatenFood = numberOfEatenFood;
    }
}
