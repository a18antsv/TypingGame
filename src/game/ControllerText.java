package game;

public class ControllerText extends Text {

	private int health;

	public ControllerText(float x, float y, Orientation orientation, Size size, Runnable onCompletion) {
		super(WordSelector.getInstance().getWord(), x, y, orientation, size, onCompletion);
		this.health = 5;
	}

	@Override
	protected void completeText() {
		super.completeText();
		if(this.health > 1) {
			this.health--;
		} else {
			this.setWord(WordSelector.getInstance().getWord());
			this.health = 5;
		}
	}
}
