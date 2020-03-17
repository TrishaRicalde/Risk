package com.Gui.Panes.Popup;


import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A Pop-up which only contains a single button for attacking.
 * Upon clicking the attack button, the player will attack the 
 * selected Country.
 * @author skusj
 *
 */
public class AttackPopup extends Stage {
	ImageView attackImg;
	Board board;
	Button attack;
	
	public AttackPopup(Board board) {
		this.board = board;
		Image image = new Image("sword.png"); 
		attackImg = new ImageView(image);
		
		attack = new Button("");
		attack.setGraphic(attackImg);
		Scene scene = new Scene(attack);
		this.setScene(scene);
		this.setOpacity(0.9);
		this.initStyle(StageStyle.UNDECORATED);
		onButtonClick();
		
	}
	
	public void onButtonClick() {
		attack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				board.mapController.clear();
				board.getInteractivePane().removeMapBlocker();
				close();
			}

		});
		
	}
}
