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
 *
 */
public abstract class TroopPopup extends Stage {
	
	/** The h box. */
	private TroopBox hBox;
	
	/** The board. */
	protected Board board;
	
	/**
	 * Instantiates a new troop popup.
	 *
	 * @param numTroops the num of troops
	 * @param board the board
	 */
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
	
	/**
	 * Gets the troop box.
	 *
	 * @return the troop box
	 */
	public TroopBox getTroopBox() {
		return hBox;
	}
	
	/**
	 * Gets the num of troops.
	 *
	 * @return the num of troops
	 */
	public int getNumOfTroops() {
		return hBox.getNumTroops();
	}
	
	/**
	 * Gets the bonus troops.
	 *
	 * @return the bonus troops
	 */
	public int getBonusTroops() {
		return board.currentPlayer.getBonusTroops();
	}
		
	/**
	 * Gets the selected country.
	 *
	 * @return the selected country
	 */
	public Country getSelectedCountry() {
		return board.mapController.getSelectedCountry1();
	}
	
	/**
	 * Sets the disable next button.
	 *
	 * @param value the new disable next button
	 */
	public void setDisableNextButton(boolean value) {
		board.getInteractivePane().setDisableNextButton(value);
	}
	
	/**
	 * On button click.
	 */
	public abstract void onButtonClick();
}
