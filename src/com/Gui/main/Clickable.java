/*
 * 
 */
package com.Gui.main;


/**
 * Class for objects which are clickable.
 *
 */
public abstract class Clickable {
	
	/** a bool repping selected. */
	private boolean selected = false;
	
	/** a bool repping clickable. */
	private boolean clickable = true;
	
	/**
	 * Sets the clickable.
	 *
	 * @param clickable - the new clickable
	 */
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	/**
	 * Checks if is clickable.
	 *
	 * @return true, if is clickable
	 */
	public boolean isClickable() {
		return clickable;
	}
	
	/**
	 * On click.
	 */
	public void onClick() {
		selected = !selected;
	}
	
	/**
	 * Checks if is selected.
	 *
	 * @return true, if is selected
	 */
	public boolean isSelected() {
		return selected;
	}
	
	
	/**
	 * Un select.
	 */
	public void unSelect() {
		selected = false;
	}
	
	
}
