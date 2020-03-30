package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Phase;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionPopup extends Stage{
	private StackPane pane;
	private Board board;
	Label playerLabel = new Label();
	Label phaseLabel = new Label();
	Popup animationPopUp = new Popup();
	

	public TransitionPopup(Board board) 
	{
		pane = new StackPane();
		this.board = board;	
		
		Scene scene = new Scene(pane, 300, 100);
		this.setScene(scene);
		/*
		animationPopUp.getContent().add(pane);
		animationPopUp.show(this);
		
		*/
	}
	
	public void nextTurnTransition(String playerName)
	{
		pane.getChildren().clear();
		playerLabel = new Label(playerName);
		ScaleTransition tt = new ScaleTransition(Duration.millis(2000), playerLabel);
		tt.setByX(1.5);
		tt.setByY(1.5);
		tt.setCycleCount(1);
		tt.setAutoReverse(true);
	
		
		tt.play();
		pane.getChildren().add(playerLabel);
		
		
		
		
	}
	
	public void nextPhaseTransition(Phase phase)
	{
		pane.getChildren().clear();
		phaseLabel = new Label(phase.toString());
		ScaleTransition pt = new ScaleTransition(Duration.millis(2000), phaseLabel);
		pt.setByX(5);
		pt.setByY(5);
		pt.setCycleCount(1);
		pt.setAutoReverse(true);
		
		pt.play();
		
		pane.getChildren().add(phaseLabel);
		
	}


}
