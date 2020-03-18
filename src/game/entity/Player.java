package game.entity;

import game.Game;
import game.WorldContext;

import java.awt.*;

/**
 * A class representing the Player.
 */
public class Player extends EntityMoving {

    private WorldContext worldContext;
    private int health;

    public Player(int x, int y, int thickness, WorldContext worldContext) {
        super(x, y, thickness, Color.GREEN, worldContext);
        this.worldContext = worldContext;
        this.health = 100;
        this.setVelocity(1, 0);
    }

    /**
     * Each step checks for:
     * collision with walls -> gameOver
     * collision with enemies -> -3 health
     * collision with food -> if protected -> -1 health else -> increment score of eaten food and remove food.
     * gameOver if health goes to 0
     * super step translates the player by the veloicty.
     */
    @Override
    public void step() {
        //Wall collision
        if (this.getTransform().getTranslateX() <= WorldContext.BORDER_THICKNESS || this.getTransform().getTranslateX() >= Game.WIDTH - WorldContext.BORDER_THICKNESS - this.getThickness()
                || this.getTransform().getTranslateY() <= WorldContext.BORDER_THICKNESS || this.getTransform().getTranslateY() >= Game.HEIGHT - WorldContext.BORDER_THICKNESS - this.getThickness()) {
            this.worldContext.gameOver();
        }

        //Gets all enemies from the ConcurrentHashMap by category and checks for intersection.
        this.worldContext.getEntitiesByCategory(EntityCategory.ENEMY).forEach(entity -> {
            if(this.isIntersecting(entity.getHitbox())) {
                this.health -= 3;
            }
        });

        //Gets all food from the ConcurrentHashMap by category and checks for intersection.
        this.worldContext.getEntitiesByCategory(EntityCategory.FOOD).forEach(entity -> {
            if(this.isIntersecting(entity.getHitbox())) {
                if(((Food) entity).isProtected()) {
                    this.health--;
                } else {
                    this.worldContext.removeEntity(entity);
                    this.worldContext.setNumberOfEatenFood(this.worldContext.getNumberOfEatenFood() + 1);
                }
            }
        });

        if(this.health <= 0) {
            this.worldContext.gameOver();
        }

        super.step(); //Will transform the player with current velocities
    }

    /**
     * What to paint when the entity has been transformed
     * As we are working with transformations x and y can be 0.
     * @param g Graphics context
     */
    @Override
    public void paintTransformed(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(0, 0, this.getThickness(), this.getThickness());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}