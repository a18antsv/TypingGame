package game;

import game.entity.Entity;
import game.entity.EntityCategory;
import game.entity.Player;
import game.level.Level;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WorldContext implements Paintable, Updateable {

    public static final int BORDER_THICKNESS = 40;
    private Map<EntityCategory, List<Entity>> entities;
    private Player player;
    private Level level;
    private Color backgroundColor;

    public WorldContext() {
        this.entities = new HashMap<>();
        this.player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2, 20, this);
        this.addEntity(EntityCategory.PLAYER, this.player);
        this.backgroundColor = new Color(16, 16, 16);
    }

    public void addEntity(EntityCategory category, Entity entity) {
        this.entities.computeIfAbsent(category, key -> new LinkedList<>());
        this.entities.get(category).add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.forEach((category, list) -> list.remove(entity));
    }

    public List<Entity> getEntitiesByCategory(EntityCategory category) {
        return this.entities.get(category);
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

    @Override
    public void paint(Graphics g) {
        g.setColor(this.backgroundColor);
        g.fillRect(BORDER_THICKNESS, BORDER_THICKNESS, Game.WIDTH - BORDER_THICKNESS * 2, Game.HEIGHT - BORDER_THICKNESS * 2);
        entities.forEach(((category, entities) -> entities.forEach(entity -> entity.paint(g))));
    }

    @Override
    public void step() {
        entities.forEach(((category, entities) -> entities.forEach(Entity::step)));
    }
}
