package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

public class DraftPopup extends ActionPopup {

	public DraftPopup(int numTroops, Board board) {
		super(numTroops, board);
		this.setTitle("Draft");
		
		setOnCloseRequest(event -> {
			board.mapController.clear();
			board.getPanes().get(0).setMouseTransparent(false);
		});
		
	}

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
				//Allows the interactive pane to recieeve mouse events.
				board.getPanes().get(0).setMouseTransparent(false);
				close();				
				// Transition pane here				
			}

		});
		
	}
}
