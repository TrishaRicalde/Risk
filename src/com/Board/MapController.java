package com.Board;

import java.util.ArrayList;

import com.Board.Map.Country;
import com.Gui.Panes.InteractivePane;
import com.Gui.Panes.Popup.AiReportPopup;
import com.Gui.Panes.Popup.AttackPopup;
import com.Gui.Panes.Popup.BattleReportPopup;
import com.Gui.Panes.Popup.AttackPopup;


/**
 * The Class MapController.
 */
public class MapController {

	/** The board. */
	private Board board;
	
	/** The phase. */
	private Phase phase;
	
	/** The max of selected countries. */
	private int maxSelected;
	
	/** The count of selected countries. */
	private int countSelected;
	
	/** The first selected country. */
	private Country selectedCountry1;
	
	/** The second selected country */
	private Country selectedCountry2;
	
	/** an empty country. */
	private Country empty;
	
	/** a bool to check if the first country is selected */
	private boolean selected1;
	
	/** a bool to check if the second country is selected  */
	private boolean selected2;
	
	/** The interactive pane. */
	private InteractivePane interactivePane;
	
	/** The battle report. */
	private BattleReport report;
	
	/** an arrayList of events. */
	private ArrayList<String> events = new ArrayList<String>();
	//private ArrayList<>

	/**
	 * Acts as an intermediary between the Board and the Countries. Stores
	 * selected Countries.
	 *
	 * @param board the board
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
	 * Draft Ai.
	 */
	public void draftAi() {
		int distributeNum = board.currentPlayer.getBonusTroops();
		selectedCountry1 = board.currentPlayer.getCountryToAddTroops(this.board, board.getCurrentPlayerOwnedCountries());	
		for(int i = 0; i < distributeNum; i++) {
			board.draftBonusTroops(selectedCountry1, 1);
			events.add(board.getCurrentPlayerName().toString() + " has Drafted " + 1 + " troop to " + selectedCountry1.getName() + "\n");
		}
		board.nextPhase();
	}
	
	/**
	 * Attack ai.
	 */
	public void attackAi() {
		for (Country c : board.getCurrentPlayerOwnedCountries()) {
			for (Country border : c.getBorders()) {
				if (border.isAllied(c) == false && c.getNumTroops() > 1) {
					if(c.getNumTroops() > border.getNumTroops() + border.getNumTroops() * 0.25) {
						selectedCountry1 = c;
						selectedCountry2 = border;
						report = board.battle(selectedCountry1, selectedCountry2);
						//System.out.println(selectedCountry1.getName() + " has attacked " + selectedCountry2.getName());
						if (report.isVictorious()) {
							board.mapController.getSelectedCountry1().subractTroops(report.getAttackingTroopsLost());
							board.mapController.getSelectedCountry2().subractTroops(report.getDefendingTroopsLost());
							board.mapController.getSelectedCountry2().setOccupantID(board.mapController.getSelectedCountry1().getPlayerOccupantOfCountry());
							board.mapController.getSelectedCountry2().addTroops(1);
							board.mapController.getSelectedCountry1().subractTroops(1);
							//System.out.println(board.getCurrentPlayerName() + " has been Won at the cost of " + report.getAttackingTroopsLost() + " troops.");
							events.add(board.getCurrentPlayerName().toString() + " has Captured " + selectedCountry2.getName() + " at the cost of " + report.getAttackingTroopsLost() + " troops." + "\n");
						} else {
							//System.out.println(board.getCurrentPlayerName() + " has been Lost, further losing " + report.getAttackingTroopsLost() + " troops.");
							events.add(board.getCurrentPlayerName().toString() + " has Lost " + report.getAttackingTroopsLost() + " troops trying to attack " + selectedCountry2.getName() + "\n");
						}
					}
				}
			}
		}
		board.nextPhase();
	}
	
	/**
	 * Fortify ai.
	 */
	public void fortifyAi() {
		for (Country c : board.getCurrentPlayerOwnedCountries()) {
			for (Country border : c.getBorders()) {
				if (border.isAllied(c) == true) {
					if(c.getNumTroops() > border.getNumTroops() + border.getNumTroops() * 0.1) {
						selectedCountry1 = c;
						selectedCountry2 = border;
							board.mapController.getSelectedCountry2().addTroops(1);
							board.mapController.getSelectedCountry1().subractTroops(1);
							events.add(selectedCountry1.getName() + " has Fortified 1 troop to " + selectedCountry2.getName() + "\n");
						}
					}
				}
			}
		if(board.currentPlayer.getIsAI() == true) {
			interactivePane.aiReportPopup(events);
			//interactivePane.removeMapBlocker();
		}
	}
		
	/**
	 * This method should be called IF AND ONLY IF the country is selected. When
	 * the country is unselected this method should NOT be called.
	 * 
	 * This method checks what phase the game is in and goes through the
	 * appropriate actions.
	 */
	public void selectCountry(Country c) {
		countSelected++;
		switch (phase) {
		case DRAFT:
			if(board.currentPlayer.getIsAI() == false) {
				selectedCountry1 = c;
				if (board.currentPlayer.getBonusTroops() > 0) {
					interactivePane.draftPopup(board.currentPlayer.getBonusTroops());
				}
			}
			break;
		case ATTACK:
			if(board.currentPlayer.getIsAI() == false) {
				attack(c);
			} else {
				attackAi();
			}
			break;
		case FORTIFY:
			if(board.currentPlayer.getIsAI() == false) {
				fortify(c);
			} else {
				fortifyAi();
			}
			
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
	 * @param p the new phase
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

	/**
	 * Attack.
	 *
	 * @param c - the country about to be attacked
	 */
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

	/**
	 * Fortify.
	 *
	 * @param c - the country about to be fortified
	 */
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

	/**
	 * Sets the a country to fortifiable.
	 */
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
	 * @param c - the new bordering enemies
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

	/**
	 * Gets the first selected country 
	 *
	 * @return the first selected country
	 */
	public Country getSelectedCountry1() {
		return this.selectedCountry1;
	}
	
	/**
	 * Gets the second selected country.
	 *
	 * @return the second selected country
	 */
	public Country getSelectedCountry2() {
		return this.selectedCountry2;
	}

	/**
	 * Calls both clear methods
	 */
	public void clear() {
		clear1();
		clear2();
		unSelected();
	}

	/**
	 * Clears the first selected country 
	 */
	private void clear1() {
		if (!selectedCountry1.equals(empty)) {
			selectedCountry1.unSelect();
			countSelected--;
			selectedCountry1 = empty;
			selected1 = false;
		}

	}

	/**
	 * Clears the second selected country
	 */
	private void clear2() {
		if (!selectedCountry2.equals(empty)) {
			selectedCountry2.unSelect();
			countSelected--;
			selectedCountry2 = empty;
			selected2 = false;
		}
	}

	/**
	 * resets the map
	 */
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
