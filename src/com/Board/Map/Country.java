package com.Board.Map;

import java.util.ArrayList;

import com.Board.MapController;
import com.Gui.Clickable;
import com.Gui.Effects.Effects;
import com.Player.Alliance;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Country extends Clickable {
	private String countryName;
	private int currentNumTroops;
	private ArrayList<Country> borders;
	private int playerIdentity;
	private Alliance alliance;
	private String countryPathName;
	private Label troopLabel = new Label("");

	private ImageView imageView;
	private MapController mapController;

	private Polygon countryShape;
	private String highlightPath;
	private Effects effects;

	private final double opacity = 0.0;

	public Country(String countryName) {
		this.countryName = countryName;
		borders = new ArrayList<Country>();
		currentNumTroops = 0;
	}

	// For Gui
	public Country(String countryName, Polygon shape) {
		borders = new ArrayList<Country>();
		this.countryName = countryName;
		this.countryShape = shape;
		this.countryPathName = countryName.replaceAll(" ", "").toUpperCase();
		this.highlightPath = "LIGHT";
		this.alliance = Alliance.RED;
		this.countryShape.setOpacity(opacity);
		this.currentNumTroops = 0;
		// Glow effect
		effects = new Effects();
		
		// Temporary Solution to display troop number
		Tooltip troopNumTip = new Tooltip("" + currentNumTroops);
		Tooltip.install(countryShape, troopNumTip);

		

		this.updateHighlight();
		
		//Sets the country's image
		try {
			imageView = new ImageView(new Image(getPath()));
			imageView.setMouseTransparent(true);
		} catch (Exception e) {
			System.out.println("Country Image Error: " + countryPathName + alliance + highlightPath + ".png");
		}

		//updates number of troops whenever the mouse is moved
		countryShape.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
			troopNumTip.setText("" + currentNumTroops);
		});


		countryShape.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if (isClickable()) {
				if (mapController.checkSelectable(this)) {
					onClick();				
				}
			}
		});
	}

	/**
	 * Copy Constructor
	 * 
	 * @param c
	 */
	// TO BE IMPLEMENTED
	/*
	 * public Country(Country c) {
	 * 
	 * }
	 * 
	 * 
	 */
	/**
	 * Adds a border to the Country
	 * 
	 * @param c
	 */
	public void addBorder(Country c) {
		borders.add(c);
	}

	/**
	 * Gets the current Player occupying the Country
	 * 
	 * @return the player number of the current Player occupying the territory
	 */
	public int getPlayerOccupantOfCountry() {
		return playerIdentity;
	}

	public String getName() {
		return countryName;
	}

	public void addTroops(int numTroops) {
		currentNumTroops += numTroops;
		updateLabel();
	}

	public void subractTroops(int numTroops) {
		currentNumTroops -= numTroops;
		updateLabel();
	}

	/**
	 * (Non-encapsulated)
	 * 
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
		switch (this.playerIdentity) {
		case 1:
			this.alliance = Alliance.RED;
			break;
		case 2:
			this.alliance = Alliance.GREEN;
			break;
		case 3:
			this.alliance = Alliance.BLUE;
			break;
		case 4:
			this.alliance = Alliance.YELLOW;
			break;
		}
	}

	public boolean isAllied(Country c) {
		if (c.getPlayerOccupantOfCountry() == this.playerIdentity) {
			return true;
		} else {
			return false;
		}
	}

	// --------------------------------------------------GUI
	// RELATED--------------------------------------------------
	public void setLabel(Label label) {
		troopLabel = label;
	}
	
	public void updateLabel() {
		troopLabel.setText("" + currentNumTroops);
	}
	
	public void updateImageView() {
		this.updateHighlight();
		imageView.setImage(new Image(getPath()));

	}

	public ImageView getImageView() {
		updateImageView();
		return this.imageView;
	}

	public Polygon getShape() {
		return countryShape;
	}

	public void updateHighlight() {
		if (isSelected()) {
			highlightPath = "DARK";
		} else {
			highlightPath = "LIGHT";
		}
	}

	public void setMapController(MapController m) {
		this.mapController = m;
	}

	public String getPath() {
		return countryPathName + alliance + highlightPath + ".png";
	}

	@Override
	public void onClick() {
		super.onClick();
		updateLabel();
		if (isSelected()) {			
			mapController.selectCountry(this);
			imageView.setEffect(effects.getEffect("selectShadow"));						
		} else {
			mapController.clear();
			mapController.unSelected();
			if (isClickable()) {
				//imageView.setImage(new Image(countryPathName + alliance + "DARK.png"));
				imageView.setEffect(effects.getEffect("countryGlow"));
			} else {
				//imageView.setImage(new Image(getPath()));
				imageView.setEffect(null);
			}
		}
		updateAlliance();
		updateImageView();
	}

	public void unSelect() {
		super.unSelect();
		if (isClickable()) {
			//imageView.setImage(new Image(countryPathName + alliance + "DARK.png"));
			imageView.setEffect(effects.getEffect("countryGlow"));
		} else {
			//imageView.setImage(new Image(getPath()));
			imageView.setEffect(null);
		}
		updateImageView();
	}
	
	@Override
	public void setClickable(boolean value) {
		super.setClickable(value);
		if (isClickable()) {
			//imageView.setImage(new Image(countryPathName + alliance + "DARK.png"));
			imageView.setEffect(effects.getEffect("countryGlow"));
		} else {
			//imageView.setImage(new Image(getPath()));
			imageView.setEffect(null);
		}
	}


}
