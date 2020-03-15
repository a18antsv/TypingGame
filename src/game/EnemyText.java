package game;

public class EnemyText extends Text {

    public EnemyText(String text, float x, float y, Orientation orientation, Size size, Runnable onCompletion) {
        super(WordSelector.getInstance().getWord(), x, y, orientation, size, onCompletion);
    }

    @Override
    protected void completeText() {
        super.completeText();

    }
}
