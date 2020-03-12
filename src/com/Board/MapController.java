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

	public void selectCountry(Country c) {
		switch (phase) {
		case DRAFT:
			selectedCountry1 = c;
			if (board.currentPlayer.getBonusTroops() > 0) {
				interactivePane.draftPopup(board.currentPlayer.getBonusTroops());
			} else {
				board.nextPhase();
			}
		default:
			break;
		}
	}

	public void setSelectedCountry2(Country c) {
		if (maxSelected == 2 & !this.selectedCountry1.equals(c)) {
			this.selectedCountry2 = c;
			selected2 = true;
		} else {
			selected2 = false;
		}
	}

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
	
	private void clear1() {
		selectedCountry1 = empty;
		selected1 = false;
	}
	
	private void clear2() {
		selectedCountry2 = empty;
		selected2 = false;
	}
	
}
