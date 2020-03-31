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
			// SCANDINAVIA
			Label scandinavia = new Label("3");
			scandinavia.setTranslateX(433);
			scandinavia.setTranslateY(65);
			AllLabels.add(scandinavia);
			// SOUTHERN EUROPE
			Label southerneurope = new Label("3");
			southerneurope.setTranslateX(453);
			southerneurope.setTranslateY(127);
			AllLabels.add(southerneurope);
			// NOTHERN EUROPE
			Label northerneurope = new Label("3");
			northerneurope.setTranslateX(430);
			northerneurope.setTranslateY(102);
			AllLabels.add(northerneurope);
			// UKRAINE
			Label ukraine = new Label("3");
			ukraine.setTranslateX(493);
			ukraine.setTranslateY(91);
			AllLabels.add(ukraine);
			// ICELAND
			Label iceland = new Label("3");
			iceland.setTranslateX(357);
			iceland.setTranslateY(65);
			AllLabels.add(iceland);
			// WESTERN EUROPE
			Label westerneurope = new Label("3");
			westerneurope.setTranslateX(402);
			westerneurope.setTranslateY(117);
			AllLabels.add(westerneurope);
			
		// SOUTH AMERICA
			// PERU
			Label peru = new Label("3");
			peru.setTranslateX(193);
			peru.setTranslateY(312);
			AllLabels.add(peru);
			// BRAZIL
			Label brazil = new Label("3");
			brazil.setTranslateX(244);
			brazil.setTranslateY(310);
			AllLabels.add(brazil);
			// ARGENTINA
			Label argentina = new Label("3");
			argentina.setTranslateX(213);
			argentina.setTranslateY(369);
			AllLabels.add(argentina);
			// VENEZUELA
			Label venezuela = new Label("3");
			venezuela.setTranslateX(189);
			venezuela.setTranslateY(246);
			AllLabels.add(venezuela);
		// AUSTRALIA
			// WESTERN AUSTRALIA
			Label westernaustralia = new Label("3");
			westernaustralia.setTranslateX(743);
			westernaustralia.setTranslateY(348);
			AllLabels.add(westernaustralia);
			// NEW GUINEA
			Label newguinea = new Label("3");
			newguinea.setTranslateX(807);
			newguinea.setTranslateY(283);
			AllLabels.add(newguinea);
			// INDONESIA
			Label indonesia = new Label("3");
			indonesia.setTranslateX(727);
			indonesia.setTranslateY(262);
			AllLabels.add(indonesia);
			// EASTERN AUSTRALIA
			Label easternaustralia = new Label("3");
			easternaustralia.setTranslateX(800);
			easternaustralia.setTranslateY(340);
			AllLabels.add(easternaustralia);
			
	}

}
