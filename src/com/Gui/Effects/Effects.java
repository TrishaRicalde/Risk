package com.Gui.Effects;

import java.util.HashMap;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

public class Effects {
	
	private HashMap<String, Effect> effects;
	
	
	public Effects() {
		effects = new HashMap<String, Effect>();
		DropShadow borderGlow;
		int depth = 50;
		borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.WHITE);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);
		effects.put("borderGlow", borderGlow);
	}
	
	public Effect getEffect(String name) {
		return effects.get(name);
	}
	
	
}
