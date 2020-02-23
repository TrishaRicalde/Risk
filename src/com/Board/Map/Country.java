package com.Board.Map;

import java.util.ArrayList;


public class Country {
	private String countryName;
	private int currentNumTroops;
	private ArrayList<Country> borders;
	private int playerIdentity;
	
	public Country(String countryName) {
		this.countryName = countryName;
		borders = new ArrayList<Country>();
		currentNumTroops = 0;
	}
	
	
	/**
	 * Copy Constructor
	 * @param c
	 */
	//TO BE IMPLEMENTED
	public Country(Country c) {
		
	}
	
	/**
	 * Adds a border to the Country
	 * @param c
	 */
	public void addBorder(Country c) {
		borders.add(c);
	}
	
	/**
	 * Gets the current Player occupying the Country
	 * @return the player number of the current Player occupying the territory
	 */
	//TO BE IMPLEMENTED
	public int getPlayerOwnerOfCountry() {
		return playerIdentity;
	}
	
	//TO BE IMPLEMENTED
	public void attack(Country attackedCountry){
		
	}
	
	
	//TO BE IMPLEMENTED
	public boolean isAllied() {
		return false;
		
	}
	
	public String getName() {
		return countryName;
	}
	
	
	public void addDraftedTroops(int numTroops) {
		currentNumTroops += numTroops;
	}

	/**
	 * (Non-encapsulated)
	 * @return the ArrayList of this Country's borders. 
	 */
	public ArrayList<Country> getBorders() {
		return borders;
	}
	
	/**
	 * 
	 * @return the current number of troops occupying the country.
	 */
	public int getNumTroops() {
		return currentNumTroops;
	}	
}
