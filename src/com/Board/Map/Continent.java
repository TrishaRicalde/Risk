package com.Board.Map;

import java.util.ArrayList;

/**
 * The Class Continent.
 */
public class Continent {
	
	/** The continent name. */
	private String continentName;
	
	/** The continent bonus. */
	private int continentBonus;
	
	/** The countries. */
	private ArrayList<Country> countries;
	
	//add copy constructor
	
	/**
	 * Instantiates a new continent.
	 *
	 * @param continentName - the continent name
	 */
	public Continent(String continentName) {
		countries = new ArrayList<Country>();
		continentBonus = 0;
		this.continentName = continentName;
	}
	
	/**
	 * Adds the country.
	 *
	 * @param country  - the country to be added
	 */
	public void addCountry(Country country) {
		countries.add(country);
		
	}
	

	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	public ArrayList<Country> getCountries() {
		return new ArrayList<Country>(countries);
	}

	
	/**
	 * Sets the continent bonus.
	 *
	 * @param bonus - the new continent bonus
	 */
	public void setContinentBonus(int bonus) {
		continentBonus = bonus;
	}
	
	/**
	 * Gets the continent bonus.
	 *
	 * @return the continent bonus
	 */
	public int getContinentBonus() {
		return continentBonus;
	}
	
	/**
	 * Gets the continent name.
	 *
	 * @return the continent name
	 */
	public String getContinentName() {
		return this.continentName;
	}
	

}