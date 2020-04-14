package com.Gui.Panes;

import java.util.ArrayList;

import com.Board.Board;
import com.Board.Map.Continent;
import com.Board.Map.Country;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


/**
 * The Class LabelLayer.
 */
public class LabelLayer extends Pane {
	
	/** all of the labels. */
	private ArrayList<Label> allLabels;

	/**
	 * Instantiates a new label layer.
	 *
	 * @param board - the board
	 */
	public LabelLayer(Board board) {
		allLabels = new ArrayList<Label>();
		init();

		this.setWidth(board.getWidth());
		this.setHeight(board.getHeight());

		int counter = 0;
		for (Continent cont : board.getContinents()) {
			for (Country country : cont.getCountries()) {
				country.setLabel(allLabels.get(counter));
				country.updateLabel();
				counter = counter + 1;				
			}
		}
		for (Label x : allLabels) {
			this.getChildren().add(x);
		}

	}

	/**
	 * Inits thes labels for all the countries
	 */
	public void init() {

		// NORTH AMERICA
		// 1
		Label alaska = new Label();
		alaska.setTranslateX(63.0);
		alaska.setTranslateY(61.0);
		allLabels.add(alaska);
		// ALBERTA
		Label alberta = new Label();
		alberta.setTranslateX(115.0);
		alberta.setTranslateY(90);
		allLabels.add(alberta);
		// ONTARIO
		Label ontario = new Label();
		ontario.setTranslateX(169);
		ontario.setTranslateY(93);
		allLabels.add(ontario);
		// QUEBEC
		Label quebec = new Label();
		quebec.setTranslateX(226);
		quebec.setTranslateY(93);
		allLabels.add(quebec);
		// CENTRAL AMERICA
		Label centralAmerica = new Label();
		centralAmerica.setTranslateX(104);
		centralAmerica.setTranslateY(190);
		allLabels.add(centralAmerica);
		// WESTERN UNITED STATES
		Label westernunitedstates = new Label();
		westernunitedstates.setTranslateX(98);
		westernunitedstates.setTranslateY(132);
		allLabels.add(westernunitedstates);
		// EASTERN UNITED STATES
		Label easternUnitedStates = new Label();
		easternUnitedStates.setTranslateX(155);
		easternUnitedStates.setTranslateY(148);
		allLabels.add(easternUnitedStates);		
		// NORTHWEST TERRITORY
		Label northwestTerritory = new Label();
		northwestTerritory.setTranslateX(323);
		northwestTerritory.setTranslateY(35);
		allLabels.add(northwestTerritory);
		// GREENLAND
		Label greenland = new Label();
		greenland.setTranslateX(130);
		greenland.setTranslateY(63);
		allLabels.add(greenland);
		// EUROPE

		// GREAT BRITIAN
		Label greatbritian = new Label();
		greatbritian.setTranslateX(395);
		greatbritian.setTranslateY(96);
		allLabels.add(greatbritian);
		// SCANDINAVIA
		Label scandinavia = new Label();
		scandinavia.setTranslateX(433);
		scandinavia.setTranslateY(65);
		allLabels.add(scandinavia);
		// SOUTHERN EUROPE
		Label southerneurope = new Label();
		southerneurope.setTranslateX(453);
		southerneurope.setTranslateY(127);
		allLabels.add(southerneurope);
		// NOTHERN EUROPE
		Label northerneurope = new Label();
		northerneurope.setTranslateX(430);
		northerneurope.setTranslateY(102);
		allLabels.add(northerneurope);
		// UKRAINE
		Label ukraine = new Label();
		ukraine.setTranslateX(493);
		ukraine.setTranslateY(91);
		allLabels.add(ukraine);
		// ICELAND
		Label iceland = new Label();
		iceland.setTranslateX(357);
		iceland.setTranslateY(65);
		allLabels.add(iceland);
		// WESTERN EUROPE
		Label westerneurope = new Label();
		westerneurope.setTranslateX(402);
		westerneurope.setTranslateY(117);
		allLabels.add(westerneurope);

		// SOUTH AMERICA
		// PERU
		Label peru = new Label();
		peru.setTranslateX(193);
		peru.setTranslateY(312);
		allLabels.add(peru);
		// BRAZIL
		Label brazil = new Label();
		brazil.setTranslateX(244);
		brazil.setTranslateY(310);
		allLabels.add(brazil);
		// ARGENTINA
		Label argentina = new Label();
		argentina.setTranslateX(213);
		argentina.setTranslateY(369);
		allLabels.add(argentina);
		// VENEZUELA
		Label venezuela = new Label();
		venezuela.setTranslateX(189);
		venezuela.setTranslateY(246);
		allLabels.add(venezuela);
		// AUSTRALIA
		// WESTERN AUSTRALIA
		Label westernaustralia = new Label();
		westernaustralia.setTranslateX(743);
		westernaustralia.setTranslateY(348);
		allLabels.add(westernaustralia);
		// NEW GUINEA
		Label newguinea = new Label();
		newguinea.setTranslateX(807);
		newguinea.setTranslateY(283);
		allLabels.add(newguinea);
		// INDONESIA
		Label indonesia = new Label();
		indonesia.setTranslateX(727);
		indonesia.setTranslateY(262);
		allLabels.add(indonesia);
		// EASTERN AUSTRALIA
		Label easternaustralia = new Label();
		easternaustralia.setTranslateX(800);
		easternaustralia.setTranslateY(340);
		allLabels.add(easternaustralia);
		// ASIA
		// YAKUTSK
		Label yakutsk = new Label();
		yakutsk.setTranslateX(682);
		yakutsk.setTranslateY(60);
		allLabels.add(yakutsk);
		// URAL
		Label ural = new Label();
		ural.setTranslateX(563);
		ural.setTranslateY(75);
		allLabels.add(ural);
		// SIBERIA
		Label siberia = new Label();
		siberia.setTranslateX(614);
		siberia.setTranslateY(63);
		allLabels.add(siberia);
		// SIAM
		Label siam = new Label();
		siam.setTranslateX(690);
		siam.setTranslateY(214);
		allLabels.add(siam);
		// MONGOLIA
		Label mongolia = new Label();
		mongolia.setTranslateX(682);
		mongolia.setTranslateY(124);
		allLabels.add(mongolia);
		// AFGHANISTAN
		Label afghanistan = new Label();
		afghanistan.setTranslateX(572);
		afghanistan.setTranslateY(117);
		allLabels.add(afghanistan);
		// CHINA
		Label china = new Label();
		china.setTranslateX(666);
		china.setTranslateY(156);
		allLabels.add(china);
		// JAPAN
		Label japan = new Label();
		japan.setTranslateX(776);
		japan.setTranslateY(150);
		allLabels.add(japan);
		// KAMCHATKA
		Label kamchatka = new Label();
		kamchatka.setTranslateX(763);
		kamchatka.setTranslateY(62);
		allLabels.add(kamchatka);
		// IRKUTSK
		Label irkutsk = new Label();
		irkutsk.setTranslateX(667);
		irkutsk.setTranslateY(94);
		allLabels.add(irkutsk);
		// INDIA
		Label india = new Label();
		india.setTranslateX(612);
		india.setTranslateY(180);
		allLabels.add(india);
		// MIDDLE EAST
		Label middleeast = new Label();
		middleeast.setTranslateX(516);
		middleeast.setTranslateY(160);
		allLabels.add(middleeast);
		// AFRICA
		// EGYPT
		Label egypt = new Label();
		egypt.setTranslateX(457);
		egypt.setTranslateY(176);
		allLabels.add(egypt);
		// EAST AFRICA
		Label eastafrica = new Label();
		eastafrica.setTranslateX(494);
		eastafrica.setTranslateY(232);
		allLabels.add(eastafrica);
		// NORTH AFRICA
		Label northafrica = new Label();
		northafrica.setTranslateX(395);
		northafrica.setTranslateY(200);
		allLabels.add(northafrica);
		// SOUTH AFRICA
		Label southafrica = new Label();
		southafrica.setTranslateX(460);
		southafrica.setTranslateY(331);
		allLabels.add(southafrica);
		// MADAGASCAR
		Label madagascar = new Label();
		madagascar.setTranslateX(528);
		madagascar.setTranslateY(327);
		allLabels.add(madagascar);
		// CONGO
		Label congo = new Label();
		congo.setTranslateX(459);
		congo.setTranslateY(266);
		allLabels.add(congo);

		// LOOP TO ADD TROOP NUMBERS INTO COUNTRIES IN GUI

	}

}
