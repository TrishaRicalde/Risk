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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A Pop-up which only contains a single button for attacking.
 * Upon clicking the attack button, the player will attack the 
 * selected Country.
 */
public class AttackPopup extends Stage {
	
	/** The attack img. */
	ImageView attackImg;
	
	/** The board. */
	Board board;
	
	/** The attack button. */
	Button attack;
	
	/** The battle report. */
	BattleReport report;
	
	/**
	 * Instantiates a new attack popup.
	 *
	 * @param board the board
	 */
	public AttackPopup(Board board) {
		this.board = board;
		
		Image image = new Image("sword.png"); 
		attackImg = new ImageView(image);
		
		attack = new Button("");
		attack.setGraphic(attackImg);
		Background b = new Background(new BackgroundImage(new Image("swordBackground.png"), null, null, null, null));
		//attack.setStyle("-fx-background-color: #832B19;");
		Scene scene = new Scene(attack);
		
		this.setScene(scene);
		this.setOpacity(0.9);
		this.initStyle(StageStyle.UNDECORATED);
		onButtonClick();
		if(board.currentPlayer.getIsAI()) {
			aiAttacker();
		}
	}
	
	/**
	 * Ai attacker.
	 */
	public void aiAttacker() {
		Country atkCountry = board.mapController.getSelectedCountry1();
		Country dfdCountry = board.mapController.getSelectedCountry2();
		int defender = dfdCountry.getPlayerOccupantOfCountry();
		report = board.battle(atkCountry, dfdCountry);
		atkCountry.subractTroops(report.getAttackingTroopsLost());
		dfdCountry.subractTroops(report.getDefendingTroopsLost());						
		board.getInteractivePane().removeMapBlocker();
		board.getInteractivePane().setMouseTransparent(true);
		
		close();
		if (report.isVictorious()) {
			board.mapController.getSelectedCountry2().setOccupantID(board.mapController.getSelectedCountry1().getPlayerOccupantOfCountry());
			System.out.println("Player " + atkCountry.getPlayerOccupantOfCountry() + " has been Won!");
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

	
	/**
	 * On button click.
	 */
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
				board.getInteractivePane().setMouseTransparent(true);
				BattleReportPopup bPop = new BattleReportPopup(report);				
				bPop.show();
				
				
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
					bPop.setOnCloseRequest(e -> {
						MoveTroopPopup movePopup = new MoveTroopPopup(atkCountry.getNumTroops() - 1, board);
						movePopup.show();
						
					});
					
				} else {
					bPop.setOnCloseRequest(e -> {						
						board.getInteractivePane().setMouseTransparent(false);						
					});					
					board.mapController.clear();
					board.resetMap();
					board.mapController.setDeployableCountries();
				}
				
			}

		});
		
	}
	
	/**
	 * Gets the battle report.
	 *
	 * @return the battle report
	 */
	public BattleReport getBattleReport() {
		return report;
	}
}
