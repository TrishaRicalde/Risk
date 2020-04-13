package com.Gui.Panes.Popup;


import com.Board.BattleReport;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * The Class BattleReportPopup.
 */
public class BattleReportPopup extends Stage {
	
	/** The battle report. */
	private BattleReport report;
	
	/** The allied info. */
	private VBox alliedInfo;
	
	/** The enemy info. */
	private VBox enemyInfo;
	
	/**
	 * Instantiates a new battle report popup.
	 *
	 * @param report - the battle report
	 */
	public BattleReportPopup(BattleReport report) {
		this.report = report;	
		
		Label lblTitle = new Label();
		if (report.isVictorious()) lblTitle.setText("Victory");
		else lblTitle.setText("Defeat");     
		lblTitle.setStyle("-fx-font-size: 24px;");
		lblTitle.setPadding(new Insets(5,5,5,5));
		
		alliedInfo = new VBox();
		enemyInfo = new VBox();
		VBox container = new VBox(lblTitle, alliedInfo,enemyInfo);
				
		container.setSpacing(1.0);
		Scene dialogScene = new Scene(container, 150, 220);
		
		alliedInfo.setSpacing(5.0);
		alliedInfo.setPadding(new Insets(5,5,5,5));

		enemyInfo.setSpacing(5.0);
		enemyInfo.setPadding(new Insets(5,5,5,5));

				
		initLabels();
		
		
		if (report.isVictorious()) this.setTitle("Victory");
		else this.setTitle("Defeat");
		
		this.setScene(dialogScene);
		this.setOpacity(0.9);
		this.setResizable(false);
		this.setAlwaysOnTop(true);		
	}

	/**
	 * Initiates the labels.
	 */
	public void initLabels() {
		Label lblAllyText = new Label("Your Army");
		Label lblTroops = new Label("Troops: " + report.getStartingAlliedTroops());
		Label lblLosses = new Label("Losses: " + report.getAttackingTroopsLost());
		Label lblSurvivors = new Label("Survivors: " + (report.getStartingAlliedTroops() - report.getAttackingTroopsLost()));
		lblAllyText.setTextFill(Color.GREEN);
		
		alliedInfo.getChildren().addAll(lblAllyText, lblTroops, lblLosses, lblSurvivors);
		
		
		Label lblEnemyText = new Label("Opponent Army");
		Label lblETroops = new Label("Troops: " + report.getStartingEnemyTroops());
		Label lblELosses = new Label("Losses: " + report.getDefendingTroopsLost());
		Label lblESurvivors = new Label("Survivors: " + (report.getStartingEnemyTroops() - report.getDefendingTroopsLost()));
		lblEnemyText.setTextFill(Color.RED);
		
		alliedInfo.getChildren().addAll(lblEnemyText, lblETroops, lblELosses, lblESurvivors);
	}
	
}
