package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A Pop-up for the player to choose an action for the chosen 
 * number of troops.
 * @author skusj
 *
 */
public abstract class TroopPopup extends Stage {
	private TroopBox hBox;
	protected Board board;
	
	public TroopPopup(int numTroops, Board board) {
		this.board = board;
		hBox = new TroopBox(numTroops);
		Scene dialogScene = new Scene(hBox, 180, 25);
		this.setScene(dialogScene);
		this.setOpacity(0.9);
		this.setResizable(false);
		this.setAlwaysOnTop(true);	
		onButtonClick();
	}
	
	public TroopBox getTroopBox() {
		return hBox;
	}
	
	public int getNumOfTroops() {
		return hBox.getNumTroops();
	}
	
	public int getBonusTroops() {
		return board.currentPlayer.getBonusTroops();
	}
		
	public Country getSelectedCountry() {
		return board.mapController.getSelectedCountry1();
	}
	
	public void setDisableNextButton(boolean value) {
		board.getInteractivePane().setDisableNextButton(value);
	}
	
	public abstract void onButtonClick();
}
