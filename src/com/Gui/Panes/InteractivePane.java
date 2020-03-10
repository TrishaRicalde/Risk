package com.Gui.Panes;

import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;

public class InteractivePane extends BorderPane {

	private Map map;
	private double opacity = 0.1;

	public InteractivePane(Map map) {
		this.map = map;
		this.setOpacity(opacity);
		setCountries(map);
		initButtons();
		
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			
		});
	}

	/**
	 * Adds all the country shapes to the Pane.
	 * 
	 * @param map
	 */
	private void setCountries(Map map) {
		
		for (Continent cont : map.getContinents()) {
			for (Country c : cont.getCountries()) {
				try {
					this.getChildren().add(c.getImageView());
					if (c.getShape() != null) {
						this.getChildren().addAll(c.getShape());
					}
				} catch (Exception e) {
					System.out.println("ImageView Error: " + c.getName());
				}
			}
		}
	}

	public void initButtons() {
		Button btnNextPhase = new Button();
		Image arrow = new Image("arrow.png");
		btnNextPhase.setGraphic(new ImageView(arrow));
		this.setBottom(btnNextPhase);
	}

}
