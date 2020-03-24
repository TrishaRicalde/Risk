package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Phase;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionPopup extends Stage{
	
	private Board board;
	Label playerLabel = new Label();
	Label phaseLabel = new Label();
	

	public TransitionPopup(Board board) 
	{
		this.board = board;	
	}
	
	public void nextTurnTransition(String playerName)
	{
		playerLabel.setText(playerName);
		/*
		ScaleTransition tt = new ScaleTransition(Duration.millis(2000), playerLabel);
		tt.setByX(1.5);
		tt.setByY(1.5);
		tt.setCycleCount(1);
		tt.setAutoReverse(true);
		*/
		board.getInteractivePane().getChildren().add(playerLabel);
		
		//tt.play();
		
		
	}
	
	public void nextPhaseTransition(Phase phase)
	{
		phaseLabel.setText(phase.toString());
		/*ScaleTransition pt = new ScaleTransition(Duration.millis(2000), phaseLabel);
		pt.setByX(1.5);
		pt.setByY(1.5);
		pt.setCycleCount(1);
		pt.setAutoReverse(true);
		
		pt.play();
		*/
		board.getInteractivePane().getChildren().add(phaseLabel);
	}


}
