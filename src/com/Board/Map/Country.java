package com.Board.Map;

import java.util.ArrayList;
import com.Player.Alliance;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Country {
	private String countryName;
	private int currentNumTroops;
	private ArrayList<Country> borders;
	private int playerIdentity;
	private Alliance alliance;
	private String countryPathName;
	
	private ImageView imageView;
	
	private Polygon countryShape;
	private boolean selected;
	private boolean clickable;
	private String highlightPath;
	
	private final double opacity = 0.1;
	
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
		this.countryPathName = countryName.replaceAll(" ", "").toUpperCase();
		this.highlightPath = "LIGHT";
		this.alliance = Alliance.RED;
		
		this.countryShape.setOpacity(opacity);
		
		currentNumTroops = 0;
		clickable = true;
		selected = false;
		
		this.updateHighlight();
		
		try {
			imageView = new ImageView(new Image(getPath()));
			imageView.setMouseTransparent(true);
		} catch (Exception e) {
			System.out.println("Country Image Error: " + countryPathName + alliance + highlightPath + ".png");
		}
		
		
		
		countryShape.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if (clickable) {
	    	  selected = !selected;
	    	  //System.out.println("selected: " + selected);
	    	  updateAlliance();
	    	  updateImageView();
			}	
			//System.out.println(countryName);
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
		updateAlliance();
	}
	
	public void updateAlliance() {
		switch(this.playerIdentity) {
		case 1: 
			this.alliance = Alliance.RED;
			break;
		case 2: 
			this.alliance = Alliance.BLUE;
			break;
		case 3:
			this.alliance = Alliance.GREEN;
			break;
		case 4:
			this.alliance = Alliance.YELLOW;
			break;
		}
	}
	
	//--------------------------------------------------GUI RELATED--------------------------------------------------
	public void updateImageView() {
		this.updateHighlight();	
		//System.out.println(highlightPath);
		imageView.setImage(new Image(getPath()));	
		
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
	
	public void updateHighlight() {
		if (selected) {
			highlightPath = "DARK";
		} else {
			highlightPath = "LIGHT";
		}	
	}
	
	public String getPath() {
		return countryPathName + alliance + highlightPath + ".png";
	}
	
}
