package com.Board;

import java.util.ArrayList;

import com.Board.Map.Country;
import com.Gui.Panes.InteractivePane;

public class MapController {

	private Board board;
	private Phase phase;
	private int maxSelected;
	private Country selectedCountry1;
	private Country selectedCountry2;
	private Country empty;
	private int count;
	private boolean selected1;
	private boolean selected2;
	private InteractivePane interactivePane;

	/**
	 * Acts as an intermediary between the Board and the Countries.
	 * Stores selected Countries.
	 * @param board
	 */
	public MapController(Board board) {
		this.board = board;
		interactivePane = (InteractivePane) board.getPanes().get(0);
		phase = board.getPhase();
		maxSelected = 1;
		count = 0;
		selected1 = false;
		selected2 = false;
		this.empty = new Country("Empty");
		this.selectedCountry1 = empty;
		this.selectedCountry2 = empty;
	}

	
	/** Whenever a Country is selected, this methods checks what phase the game is in and
	 * goes through the appropriate actions.
	 * @param c the selected Country.
	 */
	public void selectCountry(Country c) {
		switch (phase) {
		case DRAFT:
			selectedCountry1 = c;
			if (board.currentPlayer.getBonusTroops() > 0) {
				interactivePane.draftPopup(board.currentPlayer.getBonusTroops());
			}
		default:
			break;
		}
	}

	/**
	 * Selects a second country and stores it. Used for attack and fortify methods.
	 * @param c the country selected consecutively after the first country selected.
	 */
	public void setSelectedCountry2(Country c) {
		if (maxSelected == 2 & !this.selectedCountry1.equals(c)) {
			this.selectedCountry2 = c;
			selected2 = true;
		} else {
			selected2 = false;
		}
	}

	
	/** Sets the phase/state of the MapController.
	 * The maximum number of countries selected at any moment changes based on the phase.
	 * @param p
	 */
	public void setPhase(Phase p) {
		this.phase = p;
		switch (phase) {
		case DRAFT:
			this.maxSelected = 1;
			break;
		case ATTACK:
			this.maxSelected = 2;
			break;
		case FORTIFY:
			this.maxSelected = 2;
			break;
		case START:
			this.maxSelected = 0;
			break;
		}
	}

	public Country getSelectedCountry1() {
		return this.selectedCountry1;
	}

	public Country getSelectedCountry2() {
		return this.selectedCountry2;
	}
	
	public void unSelect(Country c) {
		if (selectedCountry1.equals(c)) {
			clear1();
		} else if (selectedCountry2.equals(c)) {
			clear2();
		}
	}
	
	
	public void clear() {
		clear1();
		clear2();
	}
	
	private void clear1() {
		selectedCountry1 = empty;
		selected1 = false;
	}
	
	private void clear2() {
		selectedCountry2 = empty;
		selected2 = false;
	}
	
}
