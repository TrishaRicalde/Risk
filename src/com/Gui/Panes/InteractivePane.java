package com.Gui.Panes;

import com.Board.Map.Map;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;

public class InteractivePane extends BorderPane {
	
	private Map map;
	private double opacity = 0.5;
	
	public InteractivePane(Map map) {
		this.map = map;
		this.setOpacity(opacity);
		setCountries(map);
		initButtons();
	}
	
	/**
	 * Adds all the country shapes to the Pane.
	 * @param map
	 */
	private void setCountries(Map map) {
		for (Polygon p : map.getShapes()) {
			this.getChildren().addAll(p);
		}
	}
	
	public void initButtons() {
		Button btnNextPhase = new Button();
		Image arrow = new Image("arrow.png");
		btnNextPhase.setGraphic(new ImageView(arrow));
		this.setBottom(btnNextPhase);
	}
	
}
