package com.Gui.Panes;

import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import javafx.scene.layout.BorderPane;

public class InteractivePane extends BorderPane {

	private Map map;
	
	public InteractivePane(Map map) {
		this.map = map;
		setCountries(map);
		
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

	


}
