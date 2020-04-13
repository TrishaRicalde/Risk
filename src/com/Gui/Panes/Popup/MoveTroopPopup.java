package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Phase;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

/**
 * The Class MoveTroopPopup.
 */
public class MoveTroopPopup extends TroopPopup {

	/**
	 * Instantiates a new move troop popup.
	 *
	 * @param numTroops - the num of troops being moved
	 * @param board - the board
	 */
	public MoveTroopPopup(int numTroops, Board board) {
		super(numTroops, board);

		this.initStyle(StageStyle.TRANSPARENT);
		this.setWidth(300);
		this.setHeight(50);
		
		TroopBox troopBox = getTroopBox();
		
		troopBox.setAlignment(Pos.CENTER);
		troopBox.setInitialSelected(numTroops);	
		troopBox.getLabelInfo().setText("Move Troops: ");
		board.getInteractivePane().setMouseTransparent(true);
		
	}

	/**
	 * On button click.
	 */
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
				board.getInteractivePane().removeMapBlocker();
				board.getInteractivePane().setMouseTransparent(false);			
				close();	
				//Moves to next phase because only one fortify is allowed.
				if (board.getPhase() == Phase.FORTIFY) {
					board.nextPhase();
				}
				// Activate transition here.			
			}

		});
		
	}

}
