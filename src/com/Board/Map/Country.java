package com.Board.Map;

import java.util.ArrayList;

import com.Player.Alliance;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;


public class Country {
	private String countryName;
	private int currentNumTroops;
	private ArrayList<Country> borders;
	private int playerIdentity;
	private Alliance alliance = Alliance.GREEN;
	private ImageView imageView;
	private String pathName;
	
	private Polygon countryShape;
	private boolean selected;
	private boolean clickable;
	
	public Country(String countryName) {
		this.countryName = countryName;
		borders = new ArrayList<Country>();
		currentNumTroops = 0;
	}
	
	//For Gui
	public Country(String countryName, Polygon shape) {		
		borders = new ArrayList<Country>();
		this.countryName = countryName;
		this.countryShape = shape;
		this.pathName = countryName.replaceAll(" ", "").toUpperCase();
		
		currentNumTroops = 0;
		clickable = true;
		selected = false;
		
		
		countryShape.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if (clickable) {
	    	  selected = !selected;
	    	  updateImageView();
			}	
			System.out.println(countryName);
	      });
	}
	
	/**
	 * Copy Constructor
	 * @param c
	 */
	//TO BE IMPLEMENTED
	/*
	public Country(Country c) {
		
	}
	
	
	*/
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
	
	public int getPlayerOccupantOfCountry() {
		return playerIdentity;
	}
	
	//TO BE IMPLEMENTED
	public void attack(Country attackedCountry){
		
	}
	
	

	public String getName() {
		return countryName;
	}
	
	
	public void addTroops(int numTroops) {
		currentNumTroops += numTroops;
	}
	
	public void subractTroops(int numTroops) {
		currentNumTroops -= numTroops;
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
	
	public void setOccupantID(int playerNumber) {
		playerIdentity = playerNumber;
	}
	
	public void setAlliance() {
		switch(this.playerIdentity) {
		case 1: 
			this.alliance = Alliance.BLUE;
		case 2: 
			this.alliance = Alliance.GREEN;
		case 3:
			this.alliance = Alliance.RED;
		case 4:
			this.alliance = Alliance.YELLOW;
		}
	}
	
	//--------------------------------------------------GUI RELATED--------------------------------------------------
	public void updateImageView() {
		String highlight = "";
		if (selected) {
			highlight = "DARK";
		} else {
			highlight = "LIGHT";
		}		
		try {
			Image image = new Image(pathName + alliance + highlight + ".png");
			imageView = new ImageView(image);
			imageView.setMouseTransparent(true);
		} catch (Exception e) {
			System.out.println("Country Image Error: " + pathName + alliance + highlight + ".png");
		}
	}
	
	public ImageView getImageView() {
		updateImageView();
		return this.imageView;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public boolean isClickable() {
		return clickable;
	}
	
	public Polygon getShape() {
		return countryShape;
	}
	
}
