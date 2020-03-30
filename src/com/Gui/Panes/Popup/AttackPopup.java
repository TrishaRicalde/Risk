package com.Gui.Panes.Popup;


import com.Board.BattleReport;
import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
	BattleReport report;
	
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
				Country atkCountry = board.mapController.getSelectedCountry1();
				Country dfdCountry = board.mapController.getSelectedCountry2();
				int defender = dfdCountry.getPlayerOccupantOfCountry();
				report = board.battle(atkCountry, dfdCountry);
				atkCountry.subractTroops(report.getAttackingTroopsLost());
				dfdCountry.subractTroops(report.getDefendingTroopsLost());				
				board.getInteractivePane().removeMapBlocker();
				
				close();
				if (report.isVictorious()) {
					board.mapController.getSelectedCountry2().setOccupantID(board.mapController.getSelectedCountry1().getPlayerOccupantOfCountry());
					//Checks if a Player is defeated.
					if (board.getPlayer(defender).isDefeated(board)) {
						board.playerDefeated(defender);
						System.out.println("Player " + defender + " has been defeated!");
						//Checks if a player has won the game.
						if (board.getPlayers().size() == 1) {
							board.victory();
						}
					}
					//Move troops after attack
					MoveTroopPopup movePopup = new MoveTroopPopup(atkCountry.getNumTroops() - 1, board);
					movePopup.show();
				} else {
					board.mapController.clear();
					board.resetMap();
					board.mapController.setDeployableCountries();
				}
				
			}

		});
		
	}
	
	public BattleReport getBattleReport() {
		return report;
	}
}
