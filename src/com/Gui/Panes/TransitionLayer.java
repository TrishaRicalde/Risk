package com.Gui.Panes;

import com.Board.Board;
import com.Board.Phase;
import com.Board.Map.Map;
import javafx.animation.Transition;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TransitionLayer extends Pane 
{

	private Map map;
	private Board board;

	
	
	public TransitionLayer(Board board, Map map)
	{
		this.board = board;
		this.map = map;
		
		Label playerLabel = new Label();
		
		
		
	}
	
	public void nextTurnTransition(String playerName)
	{
		
	}
	
	public void nextPhaseTransition(Phase phase, String playerName)
	{
		
	}
}
