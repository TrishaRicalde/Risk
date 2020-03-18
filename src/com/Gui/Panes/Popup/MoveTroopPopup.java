package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.StageStyle;

public class MoveTroopPopup extends TroopPopup {

	public MoveTroopPopup(int numTroops, Board board) {
		super(numTroops, board);
		this.setTitle("Move Troops");
		this.initStyle(StageStyle.UNDECORATED);
		this.setWidth(200);
		this.setHeight(30);
		getTroopBox().setInitialSelected(numTroops);
		
		board.getInteractivePane().setMouseTransparent(true);
		
	}

	@Override
	public void onButtonClick() {
		getTroopBox().getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				board.mapController.getSelectedCountry1().subractTroops(getNumOfTroops());
				board.mapController.getSelectedCountry2().addTroops(getNumOfTroops());	
				board.mapController.getSelectedCountry2().setOccupantID(board.mapController.getSelectedCountry1().getPlayerOccupantOfCountry());
				board.mapController.clear();
				board.resetMap();
				board.mapController.setDeployableCountries();
				//Allows the interactive pane to receive mouse events.
				board.getInteractivePane().setMouseTransparent(false);			
				close();				
				// Activate transition here.			
			}

		});
		
	}

}
