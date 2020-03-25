package com.Gui.Effects;

import java.util.HashMap;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.SepiaTone;
import javafx.scene.paint.Color;

public class Effects {
	
	private HashMap<String, Effect> effects;
	
	
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
	
	public Effect getEffect(String name) {
		return (effects.get(name));
	}
	
	
}
