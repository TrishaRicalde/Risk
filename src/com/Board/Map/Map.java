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
		//All of the Continents 
		Continent northAmerica = new Continent("North America");
		Continent southAmerica = new Continent("South America");
		Continent europe = new Continent("Europe");
		Continent africa = new Continent("Africa");
		Continent asia = new Continent("Asia");
		Continent australia = new Continent("Austalia");
		
		// All of the Countries
			//North America
				Country alberta = new Country("Alberta");
				Country britishColoumbia = new Country("British Coloumbia");
				Country alaska = new Country("Alaska");
				Country centralAmerica = new Country("Central America");
				Country eastrenUnitedStates = new Country("Eastren United States");
				Country greenland = new Country("Greenland");
				Country northwestTerritory = new Country("Northwest Territory");
				Country ontario = new Country("Ontario");
				Country quebec = new Country("Quebec");
				Country westrenunitedstates = new Country("Westren United States");
			//South America
				Country argentina = new Country("Argentina");
				Country brazil = new Country("Brazil");
				Country peru = new Country("Peru");
				Country venezuela = new Country("Venezuela");
			//Europe
				Country greatbritian = new Country("Great Britian");
				Country iceland = new Country("Iceland");
				Country northerneurope = new Country("Northern Europe");
				Country scandinavia = new Country("Scandinavia");
				Country southerneurope = new Country("Sothern Europe");
				Country ukraine = new Country("Ukraine");
				Country westreneurope = new Country("Westren Europe");
			//Africa
				Country congo = new Country("Congo");
				Country eastafrica = new Country("East Africa");
				Country egypt = new Country("Egypt");
				Country madagascar = new Country("Madagascar");
				Country northafrica = new Country("North Africa");
				Country southafrica = new Country("South Africa");
			//Asia
				Country afghanistan = new Country("Afghanistan");
				Country china = new Country("China");
				Country india = new Country("India");
				Country irkutsk = new Country("Irkutsk");
				Country japan = new Country("Japan");
				Country kamchatka = new Country("Kamchatka");
				Country middleeast = new Country("Middle East");
				Country mongolia = new Country("Mongolia");
				Country siam = new Country("Siam");
				Country siberia = new Country("Siberia");
				Country ural = new Country("Ural");
				Country yakutsk = new Country("Yakutsk");
			//Australia
				Country eastrenaustralia = new Country("Eastren Australia");
				Country indonesia = new Country("Indonesia");
				Country newguinea = new Country("New Guinea");
				Country westrenaustralia = new Country("Westren Australia");
				
				

	}
}
