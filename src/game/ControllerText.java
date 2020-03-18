package game;

/**
 * A class for text that will be used as a controller of the player. Writing the text will take the player into a direction.
 */
public class ControllerText extends Text {

	private int health;

	public ControllerText(float x, float y, Orientation orientation, Size size, Runnable onCompletion) {
		super(WordSelector.getInstance().getWord(), x, y, orientation, size, onCompletion);
		this.health = 2;
	}

	/**
	 * Executes the super function and extends functionality to also reduce the health of the controller when a word is
	 * completed. When the health reaches 0 the controller will pull a new word from the word list and reset the health.
	 */
	@Override
	protected void completeText() {
		super.completeText();
		if(this.health > 1) {
			this.health--;
		} else {
			this.setWord(WordSelector.getInstance().getWord());
			this.health = 2;
		}
	}
}
