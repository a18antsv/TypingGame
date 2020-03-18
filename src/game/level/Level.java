package game.level;

import game.WorldContext;

/**
 * Interface describing behaviour of a level class.
 */
public interface Level {

    /**
     * Executes when the level is instantiated to spawn entities to the worldContext.
     * @param worldContext WorldContext instance
     */
    void init(WorldContext worldContext);

    /**
     * An interval function called every step making it possible to spawn enemies during the level in an interval.
     * @param worldContext WorldContext instance
     * @param counter step count
     */
    void interval(WorldContext worldContext, int counter);

    /**
     * Returns the level coming after the current.
     * @return Level object
     */
    Level getNext();
}