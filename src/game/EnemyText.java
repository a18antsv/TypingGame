package game;

/**
 * A class for text that will be used as to kill enemies or destroy shields of food.
 */
public class EnemyText extends Text {

    public EnemyText(String text, float x, float y, Runnable onCompletion) {
        super(WordSelector.getInstance().getWord(), x, y, Orientation.DEFAULT, Size.SMALL, onCompletion);
    }
}
