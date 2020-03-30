package com.Gui.Panes;

import com.Board.Board;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class LabelLayer extends Pane {

	public LabelLayer(Board board) {
		this.setWidth(board.getWidth());
		this.setHeight(board.getHeight());
		
		Label l = new Label("Troop");
		l.setText("Troop");
		l.setTranslateX(100.0);
		l.setTranslateY(100.0);
		
		this.getChildren().add(l);
	}
}
