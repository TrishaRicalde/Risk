package com.Board.Map;

import java.util.ArrayList;

import com.Board.MapController;
import com.Gui.Effects.Effects;
import com.Gui.main.Clickable;
import com.Player.Alliance;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;


/**
 * The Class Country.
 */
public class Country extends Clickable {
	
	/** The country name. */
	private String countryName;
	
	/** The current number of troops. */
	private int currentNumTroops;
	
	/** The borders. */
	private ArrayList<Country> borders;
	
	/** The player identity. */
	private int playerIdentity;
	
	/** The alliance. */
	private Alliance alliance;
	
	/** The country path name. */
	private String countryPathName;
	
	/** The troop label. */
	private Label troopLabel = new Label("");

	/** The image view. */
	private ImageView imageView;
	
	/** The map controller. */
	private MapController mapController;

	/** The country shape. */
	private Polygon countryShape;
	
	/** The highlight path. */
	private String highlightPath;
	
	/** The effects. */
	private Effects effects;

	/** The opacity. */
	private final double opacity = 0.0;

	/**
	 * Instantiates a new country.
	 *
	 * @param countryName - the country name
	 */
	public Country(String countryName) {
		this.countryName = countryName;
		borders = new ArrayList<Country>();
		currentNumTroops = 0;
	}

	/**
	 * Instantiates a new country.
	 *
	 * @param countryName- the country name
	 * @param shape - the shape of the country
	 */
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
		
		// Displays country name
		Tooltip troopNumTip = new Tooltip("" + countryName);
		Tooltip.install(countryShape, troopNumTip);

		

		this.updateHighlight();
		
		//Sets the country's image
		try {
			imageView = new ImageView(new Image(getPath()));
			imageView.setMouseTransparent(true);
		} catch (Exception e) {
			System.out.println("Country Image Error: " + countryPathName + alliance + highlightPath + ".png");
		}

		countryShape.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
			troopNumTip.setText("" + countryName);
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
	 * Adds a border to the Country.
	 *
	 * @param c - the country that the border is being added to
	 */
	public void addBorder(Country c) {
		borders.add(c);
	}

	/**
	 * Gets the current Player occupying the Country.
	 *
	 * @return the player number of the current Player occupying the territory
	 */
	public int getPlayerOccupantOfCountry() {
		return playerIdentity;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return countryName;
	}

	/**
	 * Adds the troops.
	 *
	 * @param numTroops - the num troops of troops being added
	 */
	public void addTroops(int numTroops) {
		currentNumTroops += numTroops;
		updateLabel();
	}

	/**
	 * Subract troops.
	 *
	 * @param numTroops - the num troops being subtracted
	 */
	public void subractTroops(int numTroops) {
		currentNumTroops -= numTroops;
		updateLabel();
	}

	/**
	 * (Non-encapsulated).
	 *
	 * @return the ArrayList of this Country's borders.
	 */
	public ArrayList<Country> getBorders() {
		return borders;
	}

	/**
	 * Gets the num troops.
	 *
	 * @return the current number of troops occupying the country.
	 */
	public int getNumTroops() {
		return currentNumTroops;
	}

	/**
	 * Sets the occupant ID.
	 *
	 * @param playerNumber - the new occupant ID
	 */
	public void setOccupantID(int playerNumber) {
		playerIdentity = playerNumber;
		updateAlliance();
	}

	/**
	 * Updates the alliance.
	 */
	public void updateAlliance() {
		switch (this.playerIdentity) {
		case 1:
			this.alliance = Alliance.RED;
			updateImageView();
			break;
		case 2:
			this.alliance = Alliance.GREEN;
			updateImageView();
			break;
		case 3:
			this.alliance = Alliance.BLUE;
			updateImageView();
			break;
		case 4:
			this.alliance = Alliance.YELLOW;
			updateImageView();
			break;
		}
	}

	/**
	 * Checks if is allied.
	 *
	 * @param c the country being checked
	 * @return true, if is allied
	 */
	public boolean isAllied(Country c) {
		if (c.getPlayerOccupantOfCountry() == this.playerIdentity) {
			return true;
		} else {
			return false;
		}
	}

	// --------------------------------------------------GUI
	/**
	 * Sets the label.
	 *
	 * @param label - the new label
	 */
	// RELATED--------------------------------------------------
	public void setLabel(Label label) {
		troopLabel = label;
	}
	
	/**
	 * Update label.
	 */
	public void updateLabel() {
		troopLabel.setText("" + currentNumTroops);
	}
	
	/**
	 * Update image view.
	 */
	public void updateImageView() {
		this.updateHighlight();
		imageView.setImage(new Image(getPath()));

	}

	/**
	 * Gets the image view.
	 *
	 * @return the image view
	 */
	public ImageView getImageView() {
		updateImageView();
		return this.imageView;
	}

	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public Polygon getShape() {
		return countryShape;
	}

	/**
	 * Update highlight.
	 */
	public void updateHighlight() {
		if (isSelected()) {
			highlightPath = "DARK";
		} else {
			highlightPath = "LIGHT";
		}
	}

	/**
	 * Sets the map controller.
	 *
	 * @param m - the new map controller
	 */
	public void setMapController(MapController m) {
		this.mapController = m;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return countryPathName + alliance + highlightPath + ".png";
	}

	/**
	 * On click.
	 */
	@Override
	public void onClick() {
		super.onClick();
		updateLabel();
		System.out.println("1");
		if (isSelected()) {
				mapController.selectCountry(this);
				imageView.setEffect(effects.getEffect("selectShadow"));						
		} else {
			mapController.clear();
			mapController.unSelected();
			if (isClickable()) {
				imageView.setEffect(effects.getEffect("countryGlow"));
			} else {
				imageView.setEffect(null);
			}
		}
		updateAlliance();
		updateImageView();
	}

	/**
	 * Un select.
	 */
	public void unSelect() {
		super.unSelect();
		if (isClickable()) {
			imageView.setEffect(effects.getEffect("countryGlow"));
		} else {
			imageView.setEffect(null);
		}
		updateImageView();
	}
	
	/**
	 * Sets the clickable.
	 *
	 * @param value - the new clickable
	 */
	@Override
	public void setClickable(boolean value) {
		super.setClickable(value);
		if (isClickable()) {
			imageView.setEffect(effects.getEffect("countryGlow"));
		} else {
			imageView.setEffect(null);
		}
	}


}
