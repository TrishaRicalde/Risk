package com.Board.Continents.Countries;

import java.util.ArrayList;


public abstract class Country {
	private String countryName;
	private int currentNumTroops;
	private ArrayList<Country> borders;
	
	//attack country
	public abstract void attack(Country attackedCountry);
	
	
	//check if country is allied
	public abstract boolean isAllied();
	//EXAMPLE USE OF ENUMS	
	
	/*if (country.getAlliance == Alliance.BLUE) 
	 * 
	 * 
	 * OR
	 * 
	 * switch(currentPhase) {
	case START:
		currentPhase = Phase.DRAFT;
	case DRAFT:
		currentPhase = Phase.ATTACK;
	case ATTACK:
		currentPhase = Phase.FORTIFY;
	case FORTIFY:
		currentPhase = Phase.DRAFT;
	default:
		currentPhase = Phase.UNKNOWN;
	}
	*/
	
	
	//name of country
	public abstract String getName();

	
}
