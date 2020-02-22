package com.Board.Map;

import java.util.ArrayList;

public class Continent {
	private String continentName;
	private int continentBonus;
	private ArrayList<Country> countries;
	
	
	public Continent(String continentName) {
		countries = new ArrayList<Country>();
		continentBonus = 0;
		this.continentName = continentName;
	}
	
	public void addCountry(Country country) {
		countries.add(new Country(country));
	}
	
	public ArrayList<Country> getCountries() {
		return new ArrayList<Country>(countries);
	}
	
	public void setContinentBonus(int bonus) {
		continentBonus = bonus;
	}
	
	public int getContinentBonus() {
		return continentBonus;
	}
	

}
