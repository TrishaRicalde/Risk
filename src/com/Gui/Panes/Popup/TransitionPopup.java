package com.Gui.Panes.Popup;

import com.Board.Board;

import com.Board.Phase;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionPopup extends Stage{
	private StackPane pane;
	private Board board;
	Label playerLabel = new Label();
	Label phaseLabel = new Label();
	//Button next;
	//VBox vbox;
	//Popup animationPopUp = new Popup();
	

	public TransitionPopup(Board board) 
	{
		/*
		vbox = new VBox();
		next = new Button();
		
		next.setText("Next");
		*/
		
		pane = new StackPane();
		
		this.board = board;	
		
		Scene scene = new Scene(pane, 720, 240);
		this.setScene(scene);
		
	/*	
		vbox.getChildren().add(pane);
		vbox.getChildren().add(next);
		vbox.setMargin(pane, new Insets(100, 0, 0, 0));
		vbox.setMargin(next, new Insets(50, 0, 0, 600));
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
		pane.getChildren().add(phaseLabel);
		pt.play();		
		/*
		next.setOnAction(new EventHandler<ActionEvent>()
		{
				@Override
				public void handle(ActionEvent event) 
				{
					vbox.setVisible(false);
				}
		
		});
		
	
		pt.setOnFinished(new EventHandler<ActionEvent>()
		{
				@Override
				public void handle(ActionEvent event) 
				{
					pane.setVisible(false);
				}
		
		});
	*/	
	}


}
