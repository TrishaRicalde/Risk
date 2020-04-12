

package com.Gui.Effects;

import java.util.HashMap;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.SepiaTone;
import javafx.scene.paint.Color;


/**
 * The Class Effects.
 */
public class Effects {
	
	/** The effects. */
	private HashMap<String, Effect> effects;
	
	
	/**
	 * Instantiates a new effect.
	 */
	public Effects() {
		effects = new HashMap<String, Effect>();
		DropShadow borderGlow;
		int depth = 30;
		borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.WHITE);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);
		effects.put("borderGlow", borderGlow);
		
		DropShadow selectShadow = new DropShadow();
		selectShadow.setOffsetX(2.0);
		selectShadow.setOffsetY(2.0);
		selectShadow.setSpread(7);
		selectShadow.setRadius(2);
		effects.put("selectShadow", selectShadow);
		
		Glow countryGlow = new Glow(0.5);
		effects.put("countryGlow", countryGlow);
		
	}
	
	/**
	 * Gets the effect.
	 *
	 * @param name - the name
	 * @return the effect
	 */
	public Effect getEffect(String name) {
		return (effects.get(name));
	}
	
	
}
