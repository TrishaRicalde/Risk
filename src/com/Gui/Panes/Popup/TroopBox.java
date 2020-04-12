package com.Gui.Panes.Popup;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;


/**
 * The Class TroopBox.
 */
public class TroopBox extends HBox {
	
	/** The combo box. */
	ComboBox<Integer> cBox;
	
	/** The label info. */
	Label lblInfo;
	
	/** The confirm button. */
	Button btnConfirm;
	
	/**
	 * Instantiates a new troop box.
	 *
	 * @param numTroops the num troops
	 */
	public TroopBox(int numTroops) {
		cBox = new ComboBox<Integer>();
		lblInfo = new Label("Troops");
		btnConfirm = new Button("Confirm");
		
		
		//Adds an option for each bonus troop number to the Drop down.
		for (int i = 1; i <= numTroops; i ++) {
        	cBox.getItems().add(i);
        }
        cBox.setValue(1);
        
        this.getChildren().addAll(lblInfo, cBox, btnConfirm);
        this.setPadding(new Insets(5,10,5,10));
        this.setSpacing(10);
        
	}
	
	/**
	 * Gets the confirm button.
	 *
	 * @return the confirm button
	 */
	public Button getConfirmButton() {
		return btnConfirm;
	}
	
	/**
	 * Gets the num of troops.
	 *
	 * @return the num of troops
	 */
	public int getNumTroops() {
		return (int) cBox.getValue();
	}
	
	/**
	 * Sets the initial selected.
	 *
	 * @param num - the new initial selected
	 */
	public void setInitialSelected(int num) {
		cBox.setValue(num);
	}
	
	/**
	 * Gets the lbl info.
	 *
	 * @return the lbl info
	 */
	public Label getLblInfo() {
		return lblInfo;
	}
}
