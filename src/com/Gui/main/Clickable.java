package com.Gui.main;

/**
 * Class for objects which are clickable.
 * @author skusj
 *
 */
public abstract class Clickable {
	private boolean selected = false;
	private boolean clickable = true;
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public boolean isClickable() {
		return clickable;
	}
	
	public void onClick() {
		selected = !selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	
	public void unSelect() {
		selected = false;
	}
	
	
}
