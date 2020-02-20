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
		borders.add(new Country(c));
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

	
	
	//TO BE IMPLEMENTED
	public String getName() {
		return countryName;
		
	}

	
}
