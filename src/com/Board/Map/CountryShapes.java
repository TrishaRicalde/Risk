package com.Board.Map;

import java.util.HashMap;

import javafx.scene.shape.Polygon;

public class CountryShapes {

	private HashMap<String, Polygon> countryShapes;
	
	public CountryShapes() {
		countryShapes = new HashMap<String, Polygon>();
		initializeShapes();
	}
	
	public void initializeShapes() {
		Polygon alberta = new Polygon();
		alberta.getPoints().addAll(new Double[]{ 
				84.0, 117.0,
				76.0, 110.0,
				76.0, 101.0,
				84.0, 97.0,
				84.0, 87.0,
				81.0, 84.0,
				159.0, 85.0,
				140.0, 117.0
		      });
		countryShapes.put("ALBERTA", alberta);
	}
	
	/**
	 * Get the polygon corresponding to the name from the HashMap countryShapes.
	 * @param name
	 * @return
	 */
	public Polygon getPolygon(String name) {
		return countryShapes.get(name.toUpperCase());
	}
}
