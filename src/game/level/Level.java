package game.level;

import game.WorldContext;

public interface Level {
    void init(WorldContext worldContext);
    Level getNext();
}