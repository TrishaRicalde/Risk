package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
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
		StackPane pane = new StackPane();
		Canvas canvas = new Canvas();
		hBox = new TroopBox(numTroops);
		
		//canvas.setStyle("-fx-effect: innershadow(gaussian, #039ed3, 2, 1.0, 0, 0);");
		canvas.setMouseTransparent(true);
		
		pane.getChildren().add(hBox);
		pane.getChildren().add(canvas);
		
		Scene dialogScene = new Scene(pane, 215, 35);

		
        
  		
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
