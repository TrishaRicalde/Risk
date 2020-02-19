package com.Board.Continents;

import java.util.ArrayList;

import com.Board.Continents.Countries.Country;

public abstract class Continent {
	private String continentName;
	private int size;
	private int continentBonus;
	
	private ArrayList<Country> COUNTRIES;
	
	public abstract void addCountry(Country country);
	
	
	

}
