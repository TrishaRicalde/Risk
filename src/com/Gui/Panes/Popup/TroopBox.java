package com.Gui.Panes.Popup;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class TroopBox extends HBox {
	ComboBox<Integer> cBox;
	Label lblInfo;
	Button btnConfirm;
	
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
	
	public Button getConfirmButton() {
		return btnConfirm;
	}
	
	public int getNumTroops() {
		return (int) cBox.getValue();
	}
	
	public Label getLblInfo() {
		return lblInfo;
	}
}
