package com.Gui.Panes.Popup;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TroopBox extends HBox {
	ComboBox<Integer> cBox;
	Label lblInfo;
	Button btnConfirm;
	
	public TroopBox(int numTroops) {
		cBox = new ComboBox<Integer>();
		lblInfo = new Label("Troops");
		btnConfirm = new Button("Confirm");
		
		for (int i = 1; i <= numTroops; i ++) {
        	cBox.getItems().add(i);
        }
        cBox.setValue(1);
        
        
        this.setPadding(new Insets(5,10,5,10));
        this.getChildren().add(lblInfo);
        this.getChildren().add(cBox);
        this.getChildren().add(btnConfirm);
        this.setSpacing(10);
	}
	
	
	public Button getConfirmButton() {
		return btnConfirm;
	}
	
	public ComboBox<Integer> getCBox() {
		return cBox;
	}
	
	public Label getLblInfo() {
		return lblInfo;
	}
}
