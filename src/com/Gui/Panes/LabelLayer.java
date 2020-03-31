package com.Gui.Panes;

import java.util.ArrayList;

import com.Board.Board;
import com.Board.Map.Continent;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class LabelLayer extends Pane {
	
	
	private ArrayList<Label> AllLabels;

	public LabelLayer(Board board) {
		AllLabels = new ArrayList<Label>();
		init();
		this.setWidth(board.getWidth());
		this.setHeight(board.getHeight());
		for(Label x:AllLabels) {
		this.getChildren().add(x);
		}
	}

	
	
	
	public void init() {
		
		// NORTH AMERICA
			// ALASKA
			Label alaska = new Label("0");
			alaska.setTranslateX(63.0);
			alaska.setTranslateY(61.0);
			AllLabels.add(alaska);
			// ALBERTA
			Label alberta = new Label("1");
			alberta.setTranslateX(115.0);
			alberta.setTranslateY(90);
			AllLabels.add(alberta);
			// ONTARIO
			Label ontario = new Label("2");
			ontario.setTranslateX(169);
			ontario.setTranslateY(93);
			AllLabels.add(ontario);
			// QUEBEC
			Label quebec = new Label("3");
			quebec.setTranslateX(226);
			quebec.setTranslateY(93);
			AllLabels.add(quebec);
			// CENTRAL AMERICA
			Label centralAmerica = new Label("3");
			centralAmerica.setTranslateX(104);
			centralAmerica.setTranslateY(190);
			AllLabels.add(centralAmerica);
			// WESTERN UNITED STATES
			Label westernunitedstates = new Label("3");
			westernunitedstates.setTranslateX(98);
			westernunitedstates.setTranslateY(132);
			AllLabels.add(westernunitedstates);
			// EASTERN UNITED STATES
			Label easternUnitedStates = new Label("3");
			easternUnitedStates.setTranslateX(155);
			easternUnitedStates.setTranslateY(148);
			AllLabels.add(easternUnitedStates);
			// GREENLAND
			Label greenland = new Label("3");
			greenland.setTranslateX(130);
			greenland.setTranslateY(63);
			AllLabels.add(greenland);
			// NORTHWEST TERRITORY
			Label northwestTerritory = new Label("3");
			northwestTerritory.setTranslateX(323);
			northwestTerritory.setTranslateY(35);
			AllLabels.add(northwestTerritory);
			
			
		// EUROPE
			
			// GREAT BRITIAN
			Label greatbritian = new Label("3");
			greatbritian.setTranslateX(395);
			greatbritian.setTranslateY(96);
			AllLabels.add(greatbritian);
			
			
	}

}
