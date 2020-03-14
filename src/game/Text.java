package game;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Text implements Paintable {
	
	private Character[] characters;
	private int index;
	
	public Text(String text) {
		this.characters = new Character[text.length()];
		for(int i = 0; i < this.characters.length; i++) {
			//this.characters[i] = new Character(i, i, i, 0, null);
		}
		this.characters = text.toCharArray();
		this.index = 0;
	}
	
	protected abstract void textCompleted();
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		for(int i = 0; i < this.characters.length; i++) {
			
		}
	}
	
	public void pressed(char c) {
		if(this.characters[this.index] == c) {
			this.completeChar();
		}
	}
	
	private void completeChar() {
		if(this.index < this.characters.length - 1) {
			this.index++;
		} else {
			this.completeText();
		}
	}
	
	private void completeText() {
		this.textCompleted();
	}
}
