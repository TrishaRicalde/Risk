package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ActionPopup extends Stage {
	private TroopBox hBox;
	protected Board board;
	
	public ActionPopup(int numTroops, Board board) {
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
