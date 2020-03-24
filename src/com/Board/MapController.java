package com.Board;

import java.util.ArrayList;

import com.Board.Map.Country;
import com.Gui.Panes.InteractivePane;
import com.Gui.Panes.Popup.AttackPopup;

public class MapController {

	private Board board;
	private Phase phase;
	private int maxSelected;
	private int countSelected;
	private Country selectedCountry1;
	private Country selectedCountry2;
	private Country empty;
	private boolean selected1;
	private boolean selected2;
	private InteractivePane interactivePane;

	/**
	 * Acts as an intermediary between the Board and the Countries. Stores
	 * selected Countries.
	 * 
	 * @param board
	 */
	public MapController(Board board) {
		this.board = board;
		interactivePane = (InteractivePane) board.getPanes().get(0);
		phase = board.getPhase();
		maxSelected = 1;
		countSelected = 0;
		selected1 = false;
		selected2 = false;
		this.empty = new Country("Empty");
		this.selectedCountry1 = empty;
		this.selectedCountry2 = empty;
	}

	/**
	 * This method should be called IF AND ONLY IF the country is selected. When
	 * the country is unselected this method should NOT be called.
	 * 
	 * This method checks what phase the game is in and goes through the
	 * appropriate actions.
	 * 
	 * @param c
	 *            the selected Country.
	 */
	public void selectCountry(Country c) {
		countSelected++;
		switch (phase) {
		case DRAFT:
			selectedCountry1 = c;
			if (board.currentPlayer.getBonusTroops() > 0) {
				interactivePane.draftPopup(board.currentPlayer.getBonusTroops());
			}
			break;
		case ATTACK:
			attack(c);
			break;
		case FORTIFY:
			fortify(c);
		default:

		}
	}

	/**
	 * This method is called whenever a country is clicked. Based on the current
	 * phase, this method will return true if the country is able to be
	 * selected, and false otherwise.
	 * 
	 * @param c
	 *            the country clicked.
	 * @return boolean value of whether or not the country is selectable.
	 */
	public boolean checkSelectable(Country c) {
		if (countSelected < maxSelected) {
			return true;
		} else if (selectedCountry1.equals(c) || selectedCountry2.equals(c)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the phase/state of the MapController. The maximum number of
	 * countries selected at any moment changes based on the phase.
	 * 
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
			setDeployableCountries();
			break;
		case FORTIFY:
			this.maxSelected = 2;
			setDeployableCountries();
			break;
		case START:
			this.maxSelected = 0;
			break;
		}
	}

	public void attack(Country c) {
		if (selectedCountry1.equals(empty)) {
			selectedCountry1 = c;
			singlePlayerOwnedClickable(c);
			setBorderingEnemies(c);
		} else if (selectedCountry2.equals(empty)) {
			selectedCountry2 = c;
			interactivePane.attackPopup();
			board.resetMap();
			board.mapController.setDeployableCountries();
		} else {
			clear();
			board.resetMap();
			board.mapController.setDeployableCountries();
		}
	}

	public void fortify(Country c) {
		if (selectedCountry1.equals(empty)) {
			selectedCountry1 = c;
			singlePlayerOwnedClickable(c);
			setFortifiable();
		} else if (selectedCountry2.equals(empty)) {
			selectedCountry2 = c;
			interactivePane.fortifyPopup(selectedCountry1.getNumTroops() - 1);
			board.resetMap();
			board.mapController.setDeployableCountries();
		} else {
			clear();
			board.resetMap();
			board.mapController.setDeployableCountries();
		}
	}

	public void setFortifiable() {
		for (Country c : selectedCountry1.getBorders()) {
			if (c.isAllied(selectedCountry1)) {
				c.setClickable(true);
			}
		}
	}

	/**
	 * Goes through each of the current Player's countries, if the number of
	 * troops on that country is greater than 1, the country is set to
	 * clickable.
	 */
	public void setDeployableCountries() {
		switch (phase) {
		case ATTACK:
			for (Country c : board.getCurrentPlayerOwnedCountries()) {
				boolean deployable = false;
				if (c.getNumTroops() > 1) {
					for (Country border : c.getBorders()) {
						if (!border.isAllied(c)) {
							deployable = true;
							break;
						}
					}
				}
				if (deployable) {
					c.setClickable(true);
				}
			}
			break;
		case FORTIFY:
			for (Country c : board.getCurrentPlayerOwnedCountries()) {
				boolean deployable = false;
				if (c.getNumTroops() > 1) {
					for (Country border : c.getBorders()) {
						if (border.isAllied(c))
							deployable = true;
					}
				}
				if (deployable) {
					c.setClickable(true);
				}
			}
			break;
		default:
			break;
		}

	}

	/**
	 * Out of the player's countries, only the current selected country will be
	 * clickable.
	 * 
	 * @param c
	 *            Country to be set to clickable.
	 */
	private void singlePlayerOwnedClickable(Country c) {
		for (Country playerOwned : board.getCurrentPlayerOwnedCountries()) {
			playerOwned.setClickable(false);
		}
		c.setClickable(true);
	}

	/**
	 * Sets the boolean clickable of the bordering enemy countries to true.
	 * 
	 * @param c
	 */
	private void setBorderingEnemies(Country c) {
		for (Country border : c.getBorders()) {
			if (!border.isAllied(c))
				border.setClickable(true);
		}
	}

	/**
	 * Selects a second country and stores it. Used for attack and fortify
	 * methods.
	 * 
	 * @param c
	 *            the country selected consecutively after the first country
	 *            selected.
	 */
	public void setSelectedCountry2(Country c) {
		if (maxSelected == 2 & !this.selectedCountry1.equals(c)) {
			this.selectedCountry2 = c;
			selected2 = true;
		} else {
			selected2 = false;
		}
	}

	public Country getSelectedCountry1() {
		return this.selectedCountry1;
	}

	public Country getSelectedCountry2() {
		return this.selectedCountry2;
	}

	public void clear() {
		clear1();
		clear2();
	}

	private void clear1() {
		if (!selectedCountry1.equals(empty)) {
			selectedCountry1.unSelect();
			countSelected--;
			selectedCountry1 = empty;
			selected1 = false;
		}

	}

	private void clear2() {
		if (!selectedCountry2.equals(empty)) {
			selectedCountry2.unSelect();
			countSelected--;
			selectedCountry2 = empty;
			selected2 = false;
		}
	}

	public void unSelected() {
		switch (phase) {
		case ATTACK:
			board.resetMap();
			setDeployableCountries();
			break;
		case FORTIFY:
			board.resetMap();
			setDeployableCountries();
		default:
			break;

		}
	}

}
