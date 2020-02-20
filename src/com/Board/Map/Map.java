package com.Board.Map;

import java.util.ArrayList;

public class Map {
	private final String MAP_NAME = "Earth";
	private ArrayList<Continent> continents;
	
	public Map() {
		initMap();
	}
	
	/**
	 * Initializes the entire Map with Continents and Countries
	 */
	//TO BE IMPLEMENTED
	public void initMap() {
		//TEST CONTINENTS
		Continent northAmerica = new Continent("North America");
		
		Country alberta = new Country("Alberta");
		Country britishColoumbia = new Country("British Coloumbia");
		
		alberta.addBorder(britishColoumbia);
		britishColoumbia.addBorder(alberta);
		
		northAmerica.addCountry(alberta);
		northAmerica.addCountry(britishColoumbia);
		
		continents.add(northAmerica);
	}
}
