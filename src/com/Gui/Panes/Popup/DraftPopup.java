package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

/**
 * A Pop-up with which a player can select the number of troops
 * to draft to the selected Country.
 *
 */
public class DraftPopup extends TroopPopup {

	/** 
	 * Creates a stage which shows a popup for drafting troops.
	 * @param numTroops the number of bonus troops available
	 * @param board the game board.
	 */
	public DraftPopup(int numTroops, Board board) {
		super(numTroops, board);
		this.setTitle("Draft");
		
		//When the popup closes, this method clears the selected objects and allows
		//the interactive pane to receive mouse events.
		setOnCloseRequest(event -> {
			board.mapController.clear();
			board.getInteractivePane().removeMapBlocker();
			//board.getPanes().get(0).setMouseTransparent(false);
		});
		
	}

	/**
	 * This method is called whenever the confirm button is clicked.
	 * Adds troops to the selected Country.
	 */
	@Override
	public void onButtonClick() {
		getTroopBox().getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// switch to next phase if there are no troops left
				if (getNumOfTroops() == getBonusTroops()) {
					setDisableNextButton(false);
					Country selectedCountry = getSelectedCountry();
					board.draftBonusTroops(selectedCountry, getNumOfTroops());
					board.nextPhase();
				} else {
					Country selectedCountry = board.mapController.getSelectedCountry1();
					board.draftBonusTroops(selectedCountry, getNumOfTroops());
					board.mapController.clear();
				}
				//Allows the interactive pane to receive mouse events.
				board.getInteractivePane().removeMapBlocker();
				//board.getPanes().get(0).setMouseTransparent(false);
				close();				
				// Activate transition here.			
			}

		});
		
	}
}
