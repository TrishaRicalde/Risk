package com.Board.Map;

import java.util.HashMap;
import javafx.scene.shape.Polygon;

/**
 * The Class CountryShapes.
 */
public class CountryShapes {

	/** The country shapes. */
	private HashMap<String, Polygon> countryShapes;
	
	/**
	 * Instantiates a new country shapes.
	 */
	public CountryShapes() {
		countryShapes = new HashMap<String, Polygon>();
		initializeShapes();
	}
	
	/**
	 * Initializes shapes.
	 */
	public void initializeShapes() {
		/*
		//Test
		
		Polygon test = new Polygon();
		test.getPoints().addAll(new Double[]{ 
			84.0, 117.0,
			76.0, 110.0,
			76.0, 101.0,
			84.0, 97.0,
			84.0, 87.0,
			81.0, 84.0,
			159.0, 85.0,
			140.0, 117.0
		});
		*/
		//NORTH AMERICA
		
		
		
		Polygon alberta = new Polygon();
		alberta.getPoints().addAll(new Double[]{ 
			84.0, 117.0,
			76.0, 110.0,
			76.0, 101.0,
			84.0, 97.0,
			84.0, 87.0,
			81.0, 84.0,
			159.0, 85.0,
			140.0, 117.0
		});
		countryShapes.put("ALBERTA", alberta);
		
		
		
		Polygon alaska = new Polygon();
		alaska.getPoints().addAll(new Double[] {
			8.0, 96.0,
			24.0, 89.0,
			18.0, 87.0,
			22.0, 82.0,
			21.0, 79.0,
			26.0, 75.0,
			33.0, 74.0,
			30.0, 69.0,
			39.0, 64.0,
			45.0, 65.0,
			47.0, 58.0,
			79.0, 52.0,
			101.0, 56.0,
			101.0, 59.0,
			75.0, 81.0,
			85.0, 89.0,
			82.0, 99.0,
			79.0, 99.0,
			79.0, 91.0,
			72.0, 86.0,
			67.0, 86.0,
			57.0, 81.0,
			45.0, 86.0,
			41.0, 86.0,
			15.0, 98.0,
			9.0, 98.0
		});
		
		countryShapes.put("ALASKA", alaska);


		Polygon centralAmerica = new Polygon();
		centralAmerica.getPoints().addAll(new Double[] {
			167.0, 249.0,
			164.0, 248.0,
			159.0, 251.0,
			150.0, 247.0,
			138.0, 232.0,
			134.0, 232.0,
			123.0, 223.0,
			113.0, 224.0,
			92.0, 211.0,
			92.0, 201.0,
			81.0, 201.0,
			68.0, 178.0,
			68.0, 168.0,
			75.0, 167.0,
			88.0, 173.0,
			97.0, 171.0,
			102.0, 174.0,
			104.0, 180.0,
			110.0, 178.0,
			114.0, 180.0,
			117.0, 190.0,
			122.0, 189.0,
			122.0, 193.0,
			118.0, 201.0,
			118.0, 206.0,
			125.0, 213.0,
			133.0, 206.0,
			145.0, 200.0,
			158.0, 197.0,
			168.0, 197.0,
			187.0, 207.0,
			191.0, 207.0,
			199.0, 213.0,
			203.0, 212.0,
			210.0, 215.0,
			206.0, 218.0,
			188.0, 218.0,
			175.0, 217.0,
			154.0, 226.0,
			154.0, 239.0,
			167.0, 240.0,
			171.0, 242.0
		});
		countryShapes.put("CENTRAL AMERICA", centralAmerica);
		
		Polygon easternUnitedStates = new Polygon();
		easternUnitedStates.getPoints().addAll(new Double[] {
			99.0, 171.0,
			109.0, 169.0,
			111.0, 164.0,
			111.0, 158.0,
			113.0, 156.0,
			138.0, 156.0,
			141.0, 140.0,
			139.0, 144.0,
			139.0, 138.0,
			144.0, 127.0,
			144.0, 117.0,
			165.0, 120.0,
			180.0, 127.0,
			180.0, 140.0,
			194.0, 131.0,
			215.0, 127.0,
			220.0, 122.0,
			223.0, 124.0,
			222.0, 131.0,
			191.0, 151.0,
			189.0, 159.0,
			169.0, 175.0,
			171.0, 185.0,
			167.0, 194.0,
			161.0, 186.0,
			161.0, 179.0,
			148.0, 177.0,
			142.0, 182.0,
			135.0, 178.0,
			122.0, 185.0,
			118.0, 190.0,
			115.0, 188.0,
			111.0, 178.0,
			104.0, 180.0
		});
		countryShapes.put("EASTERN UNITED STATES", easternUnitedStates);
		
		Polygon greenland = new Polygon();
		greenland.getPoints().addAll(new Double[] {
			298.0, 85.0,
			287.0, 76.0,
			287.0, 66.0,
			294.0, 57.0,
			282.0, 42.0,
			270.0, 38.0,
			271.0, 35.0,
			286.0, 30.0,
			293.0, 29.0,
			302.0, 24.0,
			309.0, 24.0,
			318.0, 27.0,
			339.0, 22.0,
			364.0, 21.0,
			381.0, 24.0,
			386.0, 29.0,
			382.0, 32.0,
			371.0, 35.0,
			371.0, 38.0,
			359.0, 54.0,
			350.0, 61.0,
			343.0, 63.0,
			311.0, 74.0,
			302.0, 85.0
		});
		countryShapes.put("GREENLAND", greenland);
		
		Polygon northwestTerritory = new Polygon();
		northwestTerritory.getPoints().addAll(new Double[] {
			75.0, 81.0,
			103.0, 56.0,
			128.0, 55.0,
			140.0, 55.0,
			147.0, 50.0,
			161.0, 42.0,
			169.0, 44.0,
			208.0, 44.0,
			222.0, 46.0,
			230.0, 59.0,
			222.0, 68.0,
			182.0, 84.0,
			77.0, 84.0
		});
		countryShapes.put("NORTHWEST TERRITORY", northwestTerritory);
		
		Polygon ontario = new Polygon();
		ontario.getPoints().addAll(new Double[] {
			140.0, 117.0,
			140.0, 115.0,
			151.0, 95.0,
			160.0, 85.0,
			164.0, 85.0,
			181.0, 84.0,
			198.0, 99.0,
			198.0, 112.0,
			193.0, 120.0,
			193.0, 124.0,
			197.0, 127.0,
			200.0, 128.0,
			200.0, 130.0,
			194.0, 130.0,
			188.0, 136.0,
			181.0, 136.0,
			180.0, 134.0,
			188.0, 129.0,
			172.0, 117.0,
			167.0, 120.0
		});
		countryShapes.put("ONTARIO", ontario);
		
		Polygon quebec = new Polygon();
		quebec.getPoints().addAll(new Double[] {
			193.0, 124.0,
			193.0, 120.0,
			205.0, 100.0,
			215.0, 93.0,
			215.0, 87.0,
			223.0, 76.0,
			253.0, 86.0,
			263.0, 105.0,
			245.0, 127.0,
			224.0, 135.0,
			222.0, 130.0,
			223.0, 124.0,
			221.0, 122.0,
			212.0, 128.0,
			200.0, 128.0
		});
		countryShapes.put("QUEBEC", quebec);
		
		Polygon westernUnitedStates = new Polygon();
		westernUnitedStates.getPoints().addAll(new Double[] {
			63.0, 163.0,
			60.0, 148.0,
			78.0, 119.0,
			85.0, 118.0,
			87.0, 117.0,
			143.0, 117.0,
			142.0, 134.0,
			139.0, 138.0,
			138.0, 156.0,
			113.0, 156.0,
			111.0, 158.0,
			111.0, 164.0,
			108.0, 170.0,
			92.0, 173.0,
			86.0, 173.0
		});
		countryShapes.put("WESTERN UNITED STATES", westernUnitedStates);
		
		// SOUTH AMERICA
		
		Polygon argentina = new Polygon();
		argentina.getPoints().addAll(new Double[] {
			232.0, 451.0,
			208.0, 431.0,
			202.0, 421.0,
			195.0, 392.0,
			192.0, 364.0,
			190.0, 330.0,
			195.0, 330.0,
			204.0, 345.0,
			207.0, 343.0,
			216.0, 343.0,
			231.0, 353.0,
			231.0, 359.0,
			236.0, 361.0,
			242.0, 355.0,
			244.0, 356.0,
			237.0, 366.0,
			237.0, 369.0,
			252.0, 380.0,
			239.0, 397.0,
			228.0, 409.0,
			231.0, 427.0,
			228.0, 438.0,
			241.0, 447.0,
			235.0, 451.0,
		});
		countryShapes.put("ARGENTINA", argentina);
		
		Polygon brazil = new Polygon();
		brazil.getPoints().addAll(new Double[] {
			249.0, 379.0,
			246.0, 373.0,
			237.0, 369.0,
			237.0, 366.0,
			244.0, 358.0,
			244.0, 356.0,
			242.0, 351.0,
			237.0, 347.0,
			233.0, 346.0,
			228.0, 336.0,
			230.0, 334.0,
			230.0, 330.0,
			227.0, 324.0,
			224.0, 324.0,
			217.0, 315.0,
			210.0, 314.0,
			202.0, 304.0,
			194.0, 309.0,
			190.0, 305.0,
			186.0, 305.0,
			181.0, 299.0,
			181.0, 292.0, 
			185.0, 287.0,
			192.0, 287.0,
			192.0, 268.0,
			199.0, 266.0,
			203.0, 270.0,
			207.0, 268.0,
			208.0, 259.0,
			212.0, 261.0,
			217.0, 257.0,
			220.0, 259.0,
			220.0, 267.0,
			223.0, 269.0,
			234.0, 264.0,
			236.0, 266.0,
			240.0, 266.0,
			245.0, 261.0,
			298.0, 294.0,
			282.0, 343.0,
			276.0, 348.0,
			260.0, 353.0,
			262.0, 361.0,
			253.0, 379.0
		});
		countryShapes.put("BRAZIL", brazil);
		
		Polygon peru = new Polygon();
		peru.getPoints().addAll(new Double[] {
			234.0, 361.0,
			230.0, 358.0,
			231.0, 353.0,
			219.0, 347.0,
			216.0, 343.0,
			207.0, 343.0,
			204.0, 345.0,
			199.0, 339.0,
			198.0, 333.0,
			195.0, 330.0,
			188.0, 330.0,
			167.0, 310.0,
			157.0, 293.0,
			155.0, 288.0,
			156.0, 278.0,
			160.0, 269.0,
			166.0, 269.0,
			170.0, 272.0,
			173.0, 272.0,
			181.0, 280.0,
			188.0, 280.0,
			189.0, 281.0,
			189.0, 285.0,
			181.0, 292.0,
			181.0, 299.0,
			194.0, 309.0,
			203.0, 304.0,
			210.0, 314.0,
			217.0, 315.0,
			224.0, 324.0,
			228.0, 325.0,
			230.0, 334.0,
			228.0, 336.0,
			233.0, 346.0,
			242.0, 351.0,
			242.0, 355.0,
			236.0, 361.0
		});
		countryShapes.put("PERU", peru);
		
		Polygon venezuela = new Polygon();
		venezuela.getPoints().addAll(new Double[] {
			189.0, 286.0,
			189.0, 280.0,
			181.0, 280.0,
			175.0, 273.0,
			160.0, 269.0,
			166.0, 261.0,
			167.0, 249.0,
			186.0, 232.0,
			215.0, 239.0,
			229.0, 252.0,
			238.0, 253.0,
			247.0, 261.0,
			244.0, 261.0,
			240.0, 266.0,
			236.0, 266.0,
			234.0, 264.0,
			222.0, 269.0,
			219.0, 262.0,
			220.0, 259.0,
			217.0, 257.0,
			211.0, 261.0,
			207.0, 259.0,
			207.0, 268.0,
			202.0, 270.0,
			199.0, 266.0,
			192.0, 268.0,
			192.0, 286.0
		});
		countryShapes.put("VENEZUELA", venezuela);
		
		// EUROPE
		
		Polygon greatBritain = new Polygon();
		greatBritain.getPoints().addAll(new Double[] {
			386.0, 115.0,
			374.0, 110.0,
			375.0, 101.0,
			391.0, 86.0,
			400.0, 93.0,
			407.0, 107.0,
			400.0, 112.0,
			394.0, 114.0
		});
		countryShapes.put("GREAT BRITAIN", greatBritain);
		
		Polygon iceland = new Polygon();
		iceland.getPoints().addAll(new Double[] {
			342.0, 99.0,
			329.0, 77.0,
			376.0, 52.0,
			388.0, 63.0
		});
		countryShapes.put("ICELAND", iceland);
		
		Polygon northernEurope = new Polygon();
		northernEurope.getPoints().addAll(new Double[] {
			444.0, 127.0,
			442.0, 123.0,
			444.0, 121.0,
			444.0, 118.0,
			436.0, 118.0,
			426.0, 123.0,
			420.0, 120.0,
			420.0, 116.0,
			414.0, 116.0,
			406.0, 110.0,
			417.0, 101.0,
			422.0, 91.0,
			426.0, 89.0,
			429.0, 93.0,
			427.0, 97.0,
			433.0, 101.0,
			437.0, 101.0,
			441.0, 98.0,
			454.0, 97.0,
			459.0, 103.0,
			459.0, 107.0,
			462.0, 111.0,
			458.0, 116.0,
			457.0, 124.0
		});
		countryShapes.put("NORTHERN EUROPE", northernEurope);
		
		Polygon scandinavia = new Polygon();
		scandinavia.getPoints().addAll(new Double[] {
			434.0, 98.0,
			428.0, 87.0,
			419.0, 89.0,
			414.0, 85.0,
			414.0, 76.0,
			452.0, 51.0,
			465.0, 53.0,
			467.0, 57.0,
			465.0, 59.0,
			465.0, 62.0,
			470.0, 65.0,
			468.0, 69.0,
			476.0, 77.0,
			471.0, 83.0,
			448.0, 85.0,
			437.0, 99.0
		});
		countryShapes.put("SCANDINAVIA", scandinavia);
		
		Polygon southernEurope = new Polygon();
		southernEurope.getPoints().addAll(new Double[] {
			439.0, 159.0,
			432.0, 151.0,
			419.0, 131.0,
			417.0, 123.0,
			423.0, 121.0,
			427.0, 123.0,
			436.0, 118.0,
			444.0, 119.0,
			442.0, 125.0,
			444.0, 127.0,
			450.0, 127.0,
			457.0, 124.0,
			458.0, 121.0,
			469.0, 120.0,
			478.0, 133.0,
			475.0, 139.0,
			470.0, 139.0,
			470.0, 144.0,
			464.0, 146.0,
			464.0, 155.0,
			460.0, 158.0
		});
		countryShapes.put("SOUTHERN EUROPE", southernEurope);
		
		Polygon ukraine = new Polygon();
		ukraine.getPoints().addAll(new Double[] {
			522.0, 150.0,
			499.0, 132.0,
			476.0, 130.0,
			470.0, 120.0,
			458.0, 120.0,
			458.0, 116.0,
			462.0, 111.0,
			459.0, 107.0,
			459.0, 102.0,
			452.0, 95.0,
			452.0, 91.0,
			459.0, 85.0,
			468.0, 85.0,
			476.0, 77.0,
			468.0, 69.0,
			470.0, 65.0,
			465.0, 61.0,
			468.0, 53.0,
			529.0, 53.0,
			540.0, 57.0,
			543.0, 65.0,
			534.0, 72.0,
			534.0, 76.0,
			538.0, 81.0,
			538.0, 86.0,
			541.0, 90.0,
			539.0, 94.0,
			545.0, 96.0,
			542.0, 100.0,
			549.0, 100.0,
			550.0, 108.0,
			556.0, 109.0,
			555.0, 112.0,
			528.0, 108.0,
			521.0, 115.0,
			521.0, 120.0,
			529.0, 128.0,
			535.0, 148.0,
			531.0, 149.0,
			528.0, 147.0
		});
		countryShapes.put("UKRAINE", ukraine);
		
		Polygon westernEurope = new Polygon();
		westernEurope.getPoints().addAll(new Double[] {
			381.0, 158.0,
			368.0, 148.0,
			387.0, 117.0,
			407.0, 111.0,
			414.0, 116.0,
			420.0, 117.0,
			420.0, 121.0,
			415.0, 125.0,
			418.0, 127.0,
			420.0, 135.0,
			392.0, 158.0
		});
		countryShapes.put("WESTERN EUROPE", westernEurope);
		
		// AFRICA
		
		Polygon congo = new Polygon();
		congo.getPoints().addAll(new Double[] {
			481.0, 315.0,
			478.0, 312.0,
			461.0, 306.0,
			460.0, 294.0,
			457.0, 294.0,
			449.0, 298.0,
			443.0, 292.0,
			429.0, 292.0,
			420.0, 277.0,
			422.0, 266.0,
			443.0, 266.0,
			443.0, 261.0,
			438.0, 254.0,
			444.0, 248.0,
			448.0, 248.0,
			463.0, 238.0,
			477.0, 257.0,
			477.0, 260.0,
			484.0, 258.0,
			487.0, 261.0,
			495.0, 260.0,
			499.0, 266.0,
			493.0, 275.0,
			486.0, 278.0,
			483.0, 292.0,
			486.0, 297.0,
			481.0, 301.0,
			479.0, 309.0,
			484.0, 313.0
		});
		countryShapes.put("CONGO", congo);
		
		Polygon eastAfrica = new Polygon();
		eastAfrica.getPoints().addAll(new Double[] {
			498.0, 327.0,
			493.0, 315.0,
			495.0, 310.0,
			483.0, 292.0,
			486.0, 278.0,
			499.0, 268.0,
			499.0, 265.0,
			495.0, 260.0,
			486.0, 260.0,
			483.0, 258.0,
			478.0, 260.0,
			476.0, 253.0,
			473.0, 253.0,
			462.0, 237.0,
			461.0, 227.0,
			466.0, 222.0,
			465.0, 211.0,
			469.0, 208.0,
			471.0, 202.0,
			496.0, 202.0,
			502.0, 199.0,
			526.0, 237.0,
			544.0, 232.0,
			549.0, 236.0,
			529.0, 269.0,
			513.0, 291.0,
			516.0, 306.0,
			512.0, 306.0,
			507.0, 311.0,
			498.0, 311.0,
			501.0, 319.0,
			501.0, 325.0
			
		});
		countryShapes.put("EAST AFRICA", eastAfrica);
		
		Polygon egypt = new Polygon();
		egypt.getPoints().addAll(new Double[] {
			464.0, 210.0,
			446.0, 199.0,
			435.0, 199.0,
			424.0, 190.0,
			426.0, 182.0,
			424.0, 180.0,
			426.0, 176.0,
			425.0, 174.0,
			433.0, 166.0,
			452.0, 174.0,
			456.0, 166.0,
			477.0, 173.0,
			493.0, 172.0,
			502.0, 199.0,
			491.0, 202.0,
			469.0, 202.0,
			469.0, 208.0,
			467.0, 210.0
		});
		countryShapes.put("EGYPT", egypt);
		
		Polygon madagascar = new Polygon();
		madagascar.getPoints().addAll(new Double[] {
			512.0, 356.0,
			530.0, 314.0,
			550.0, 315.0,
			532.0, 360.0
		});
		countryShapes.put("MADAGASCAR", madagascar);
		
		Polygon northAfrica = new Polygon();
		northAfrica.getPoints().addAll(new Double[] {
			346.0, 264.0,
			339.0, 166.0,
			425.0, 151.0,
			431.0, 168.0,
			425.0, 174.0,
			424.0, 190.0,
			438.0, 200.0,
			447.0, 200.0,
			465.0, 211.0,
			465.0, 223.0,
			461.0, 227.0,
			461.0, 239.0,
			448.0, 248.0,
			438.0, 254.0,
			438.0, 257.0,
			443.0, 262.0,
			443.0, 266.0,
			422.0, 266.0
		});
		countryShapes.put("NORTH AFRICA", northAfrica);
		
		Polygon southAfrica = new Polygon();
		southAfrica.getPoints().addAll(new Double[] {
			424.0, 387.0,
			424.0, 292.0,
			442.0, 292.0,
			450.0, 298.0,
			459.0, 294.0,
			461.0, 306.0,
			481.0, 315.0,
			485.0, 315.0,
			479.0, 309.0,
			484.0, 298.0,
			487.0, 298.0,
			493.0, 304.0,
			492.0, 317.0,
			498.0, 327.0,
			501.0, 325.0,
			501.0, 319.0,
			498.0, 315.0,
			499.0, 311.0,
			507.0, 311.0,
			512.0, 306.0,
			517.0, 306.0,
			517.0, 323.0,
			476.0, 390.0
		});
		countryShapes.put("SOUTH AFRICA", southAfrica);
		
		// ASIA
		
		Polygon afghanistan = new Polygon();
		afghanistan.getPoints().addAll(new Double[] {
			568.0, 160.0,
			565.0, 154.0,
			555.0, 151.0,
			544.0, 151.0,
			531.0, 133.0,
			528.0, 126.0,
			526.0, 122.0,
			521.0, 119.0,
			521.0, 115.0,
			528.0, 108.0,
			556.0, 112.0,
			553.0, 103.0,
			573.0, 98.0,
			586.0, 103.0,
			589.0, 101.0,
			615.0, 123.0,
			615.0, 127.0,
			611.0, 131.0,
			614.0, 139.0,
			604.0, 145.0,
			598.0, 145.0,
			602.0, 152.0,
			596.0, 154.0,
			592.0, 150.0,
			585.0, 155.0,
			579.0, 153.0,
			569.0, 160.0
		});
		countryShapes.put("AFGHANISTAN", afghanistan);
		
		Polygon china = new Polygon();
		china.getPoints().addAll(new Double[] {
			711.0, 216.0,
			701.0, 199.0,
			697.0, 199.0,
			693.0, 202.0, 
			691.0, 201.0,
			687.0, 205.0,
			678.0, 196.0,
			678.0, 185.0,
			665.0, 180.0,
			659.0, 185.0,
			619.0, 172.0,
			619.0, 161.0,
			602.0, 153.0,
			598.0, 146.0,
			614.0, 140.0,
			611.0, 131.0,
			622.0, 124.0,
			621.0, 118.0,
			626.0, 116.0,
			637.0, 127.0,
			642.0, 131.0,
			649.0, 131.0,
			663.0, 143.0,
			667.0, 142.0,
			674.0, 149.0,
			677.0, 147.0,
			683.0, 153.0,
			687.0, 148.0,
			693.0, 152.0,
			705.0, 143.0,
			706.0, 138.0,
			710.0, 140.0,
			715.0, 137.0,
			736.0, 155.0,
			757.0, 203.0
		});
		countryShapes.put("CHINA", china);
		
		Polygon india = new Polygon();
		india.getPoints().addAll(new Double[] {
			623.0, 263.0,
			574.0, 196.0,
			576.0, 185.0,
			566.0, 164.0,
			570.0, 160.0,
			580.0, 153.0,
			585.0, 155.0,
			593.0, 150.0,
			596.0, 154.0,
			604.0, 154.0,
			619.0, 162.0,
			619.0, 172.0,
			661.0, 185.0,
			665.0, 180.0,
			674.0, 183.0,
			667.0, 191.0,
			663.0, 208.0,
			658.0, 203.0,
			639.0, 259.0
		});
		countryShapes.put("INDIA", india);
		
		Polygon irkutsk = new Polygon();
		irkutsk.getPoints().addAll(new Double[] {
			628.0, 114.0,
			626.0, 112.0,
			626.0, 108.0,
			633.0, 108.0,
			637.0, 103.0,
			641.0, 103.0,
			640.0, 90.0,
			645.0, 91.0,
			648.0, 87.0,
			653.0, 87.0,
			648.0, 82.0,
			646.0, 73.0,
			647.0, 72.0,
			661.0, 81.0,
			663.0, 87.0,
			673.0, 82.0,
			694.0, 93.0,
			725.0, 98.0,
			723.0, 102.0,
			735.0, 105.0,
			731.0, 110.0,
			734.0, 117.0,
			720.0, 111.0,
			720.0, 109.0,
			713.0, 103.0,
			704.0, 103.0,
			702.0, 105.0,
			704.0, 109.0,
			700.0, 116.0,
			692.0, 114.0,
			683.0, 117.0,
			649.0, 109.0,
			651.0, 113.0
		});
		countryShapes.put("IRKUTSK", irkutsk);
		
		Polygon japan = new Polygon();
		japan.getPoints().addAll(new Double[] {
			764.0, 175.0,
			759.0, 166.0,
			771.0, 129.0,
			791.0, 129.0,
			790.0, 177.0
		});
		countryShapes.put("JAPAN", japan);
		
		Polygon kamchatka = new Polygon();
		kamchatka.getPoints().addAll(new Double[] {
			753.0, 139.0,
			752.0, 136.0,
			748.0, 136.0,
			746.0, 132.0,
			750.0, 127.0,
			748.0, 120.0,
			739.0, 122.0,
			731.0, 110.0,
			735.0, 106.0,
			733.0, 103.0,
			724.0, 103.0,
			725.0, 97.0,
			719.0, 95.0,
			716.0, 88.0,
			727.0, 84.0,
			725.0, 78.0,
			732.0, 75.0,
			736.0, 77.0,
			733.0, 70.0,
			745.0, 70.0,
			744.0, 64.0,
			750.0, 66.0,
			752.0, 63.0,
			747.0, 60.0,
			749.0, 50.0,
			831.0, 58.0,
			806.0, 113.0,
			774.0, 127.0
		});
		countryShapes.put("KAMCHATKA", kamchatka);
		
		Polygon middleEast = new Polygon();
		middleEast.getPoints().addAll(new Double[] {
			526.0, 233.0,
			494.0, 174.0,
			494.0, 163.0,
			475.0, 158.0,
			469.0, 147.0,
			471.0, 139.0,
			490.0, 137.0,
			512.0, 141.0,
			522.0, 150.0,
			528.0, 147.0,
			542.0, 155.0,
			546.0, 151.0,
			565.0, 154.0,
			568.0, 161.0,
			566.0, 166.0,
			571.0, 175.0,
			570.0, 178.0,
			576.0, 187.0,
			565.0, 224.0
		});
		countryShapes.put("MIDDLE EAST", middleEast);
		
		Polygon mongolia = new Polygon();
		mongolia.getPoints().addAll(new Double[] {
			750.0, 165.0,
			735.0, 149.0,
			729.0, 150.0,
			715.0, 137.0,
			695.0, 148.0,
			693.0, 152.0,
			687.0, 148.0,
			683.0, 153.0,
			649.0, 131.0,
			638.0, 129.0,
			626.0, 116.0,
			632.0, 112.0,
			651.0, 114.0,
			650.0, 109.0,
			683.0, 117.0,
			692.0, 114.0,
			703.0, 116.0,
			702.0, 105.0,
			704.0, 103.0,
			713.0, 103.0,
			730.0, 117.0,
			735.0, 117.0,
			740.0, 122.0,
			747.0, 120.0,
			750.0, 127.0,
			746.0, 132.0,
			751.0, 139.0,
			760.0, 160.0
		});
		countryShapes.put("MONGOLIA", mongolia);
		
		Polygon siam = new Polygon();
		siam.getPoints().addAll(new Double[] {
			699.0, 269.0,
			678.0, 247.0,
			663.0, 208.0,
			668.0, 190.0,
			675.0, 183.0,
			678.0, 196.0,
			686.0, 205.0,
			689.0, 205.0,
			691.0, 201.0,
			694.0, 202.0,
			698.0, 198.0,
			706.0, 205.0,
			706.0, 214.0,
			717.0, 227.0,
			719.0, 236.0,
			705.0, 266.0
		});
		countryShapes.put("SIAM", siam);
		
		Polygon siberia = new Polygon();
		siberia.getPoints().addAll(new Double[] {
			620.0, 118.0,
			612.0, 111.0,
			618.0, 106.0,
			609.0, 95.0,
			617.0, 92.0,
			610.0, 85.0,
			602.0, 83.0,
			598.0, 77.0,
			598.0, 72.0,
			572.0, 57.0,
			568.0, 48.0,
			589.0, 30.0,
			630.0, 39.0,
			630.0, 48.0,
			636.0, 53.0,
			632.0, 58.0,
			646.0, 71.0,
			646.0, 75.0,
			652.0, 86.0,
			640.0, 93.0,
			640.0, 103.0,
			626.0, 108.0,
			628.0, 114.0
		});
		countryShapes.put("SIBERIA", siberia);
		
		Polygon ural = new Polygon();
		ural.getPoints().addAll(new Double[] {
			616.0, 126.0,
			589.0, 101.0,
			584.0, 103.0,
			571.0, 98.0,
			554.0, 102.0,
			551.0, 108.0,
			549.0, 100.0,
			543.0, 100.0,
			533.0, 75.0,
			534.0, 72.0,
			543.0, 65.0,
			541.0, 57.0,
			543.0, 51.0,
			552.0, 47.0,
			568.0, 49.0,
			572.0, 55.0,
			574.0, 60.0,
			578.0, 59.0,
			598.0, 73.0,
			598.0, 77.0,
			603.0, 84.0,
			617.0, 91.0,
			609.0, 95.0,
			618.0, 106.0,
			612.0, 112.0,
			622.0, 120.0,
			622.0, 123.0
		});
		countryShapes.put("URAL", ural);
		
		Polygon yakutsk = new Polygon();
		yakutsk.getPoints().addAll(new Double[] {
			718.0, 97.0,
			692.0, 94.0,
			682.0, 85.0,
			673.0, 82.0,
			670.0, 86.0,
			663.0, 87.0,
			661.0, 85.0,
			661.0, 81.0,
			643.0, 69.0,
			632.0, 58.0,
			636.0, 54.0,
			630.0, 45.0,
			636.0, 42.0,
			701.0, 44.0,
			740.0, 51.0,
			748.0, 55.0,
			748.0, 61.0,
			752.0, 64.0,
			749.0, 66.0,
			747.0, 64.0,
			744.0, 64.0,
			744.0, 70.0,
			733.0, 70.0,
			736.0, 77.0,
			725.0, 76.0,
			726.0, 84.0,
			716.0, 88.0
		});
		countryShapes.put("YAKUTSK", yakutsk);
		
		// AUSTRALIA
		
		Polygon easternAustralia = new Polygon();
		easternAustralia.getPoints().addAll(new Double[] {
			781.0, 415.0,
			785.0, 397.0,
			797.0, 360.0,
			763.0, 355.0,
			774.0, 308.0,
			814.0, 308.0,
			842.0, 357.0,
			797.0, 418.0
		});
		countryShapes.put("EASTERN AUSTRALIA", easternAustralia);
		
		Polygon indonesia = new Polygon();
		indonesia.getPoints().addAll(new Double[] {
			749.0, 310.0,
			694.0, 302.0,
			673.0, 258.0,
			681.0, 253.0,
			702.0, 275.0,
			744.0, 231.0,
			743.0, 211.0,
			755.0, 211.0,
			772.0, 250.0,
			758.0, 289.0,
			772.0, 302.0
		});
		countryShapes.put("INDONESIA", indonesia);
		
		Polygon newGuinea = new Polygon();
		newGuinea.getPoints().addAll(new Double[] {
			836.0, 311.0,
			815.0, 305.0,
			798.0, 305.0,
			798.0, 291.0,
			786.0, 286.0,
			768.0, 286.0,
			768.0, 262.0,
			837.0, 280.0,
			844.0, 280.0,
			875.0, 304.0,
			863.0, 318.0
		});
		countryShapes.put("NEW GUINEA", newGuinea);
		
		Polygon westernAustralia = new Polygon();
		westernAustralia.getPoints().addAll(new Double[] {
			785.0, 398.0,
			721.0, 387.0,
			717.0, 384.0,
			719.0, 339.0,
			763.0, 316.0,
			767.0, 316.0,
			771.0, 320.0,
			763.0, 355.0,
			797.0, 361.0
		});
		countryShapes.put("WESTERN AUSTRALIA", westernAustralia);

	}
	
	/**
	 * Get the polygon corresponding to the name from the HashMap countryShapes.
	 * @param name of the country
	 * @return the polygon
	 */
	public Polygon getPolygon(String name) {
		return countryShapes.get(name.toUpperCase());
	}
}
