package com.Gui.Panes.Popup;

import com.Board.Board;

import com.Board.Phase;
import com.Player.Alliance;

import javafx.animation.PauseTransition;
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
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * The Class TransitionPopup.
 * @author alextanasescu
 */
public class TransitionPopup extends Stage{
	
	/** The pane. */
	private StackPane pane;
	
	/** The board. */
	private Board board;
	
	/** The player label. */
	Label playerLabel = new Label();
	
	/** The phase label. */
	Label phaseLabel = new Label();
	
	//Button next;
	//VBox vbox;
	//Popup animationPopUp = new Popup();
	//Bruh

	/**
	 * Instantiates a new transition popup.
	 *
	 * @param board - the board
	 */
	public TransitionPopup(Board board) 
	{
		/*
		vbox = new VBox();
		next = new Button();
		
		next.setText("Next");
		*/
		
		pane = new StackPane();
		pane.setOpacity(90);
		
		this.board = board;	
		
		Scene scene = new Scene(pane, 720, 240);
		this.setScene(scene);
		//this.initStyle(StageStyle.TRANSPARENT);
		this.initStyle(StageStyle.UNDECORATED);
		
	/*	
		vbox.getChildren().add(pane);
		vbox.getChildren().add(next);
		vbox.setMargin(pane, new Insets(100, 0, 0, 0));
		vbox.setMargin(next, new Insets(50, 0, 0, 600));
	*/

	}

	/**
	 * Next phase transition.
	 *
	 * @param phase - the phase 
	 */
	public void nextPhaseTransition(Phase phase)
	{
		System.out.println("Hey");
		String playerColour = board.currentPlayer.getAlliance().toString();
		
		
		
		pane.getChildren().clear();
		phaseLabel = new Label(phase.toString());
		
		
		if (playerColour.equalsIgnoreCase("RED"))
		{
			phaseLabel.setTextFill(Color.CRIMSON);
		}
		else if (playerColour.equalsIgnoreCase("GREEN"))
		{
			phaseLabel.setTextFill(Color.FORESTGREEN);
		}
		else if (playerColour.equalsIgnoreCase("BLUE"))
		{
			phaseLabel.setTextFill(Color.ROYALBLUE);
		}
		else if (playerColour.equals("YELLOW"))
		{
			phaseLabel.setTextFill(Color.GOLDENROD);
		}
		ScaleTransition pt = new ScaleTransition(Duration.millis(2000), phaseLabel);
		pt.setByX(5);
		pt.setByY(5);
		pt.setCycleCount(1);
		pt.setAutoReverse(true);
		
		
		
		pane.getChildren().add(phaseLabel);
		
		//long mTime = System.currentTimeMillis();
		//long end = mTime + 2000;
		
		PauseTransition delay = new PauseTransition(Duration.seconds(3));
		delay.setOnFinished(event -> this.close());
		
		
		//animationPopUp.getContent().add(pane);
		pt.play();		
		delay.play();
		/*
		while (mTime < end)
		{
			mTime = System.currentTimeMillis();
			System.out.println(mTime);
			//System.out.print("running");
		}
		this.close();
		*/
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
