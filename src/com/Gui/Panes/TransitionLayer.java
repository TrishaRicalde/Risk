package com.Gui.Panes;

import com.Board.Board;
import com.Board.Map.Map;

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
}
