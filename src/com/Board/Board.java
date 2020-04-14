package com.Board;

import java.util.ArrayList;
import java.util.Random;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import com.Gui.Panes.InteractivePane;
import com.Gui.Panes.Popup.TransitionPopup;
import com.Gui.Panes.Popup.VictoryPopup;
import com.Gui.Panes.LabelLayer;
import com.Player.Player;


import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * The Class Board.
 */
public class Board {
	
	/** The continents. */
	private ArrayList<Continent> continents;
	
	/** The players. */
	private ArrayList<Player> players;
	
	/** The current player. */
	public Player currentPlayer;
	
	/** The number of AI. */
	private int numAI;
	
	/** The earth map. */
	private Map earthMap;
	
	/** The first round. */
	private boolean firstRound;
	
	/** The dice. */
	private Dice dice;
	
	/** The turn number. */
	private int turnNum;
	
	/** The width. */
	private int width;
	
	/** The height. */
	private int height;
	

	/** The current phase. */
	private Phase currentPhase;

	/** The panes. */
	private ArrayList<Pane> panes;
	
	/** The interactive pane. */
	private InteractivePane interactivePane;
	
	/** The label layer. */
	private LabelLayer labelLayer;
	
	/** The map controller. */
	public MapController mapController;
	
	/** The transition popup. */
	private TransitionPopup transitionPopup;


	/** The total number of players */
	private final int totalPlayerNum = 4;
	
	/** The absolute power to decide if a country will def win a battle */
	private final int absolutePower = 5;

	/**
	 * Instantiates a new board.
	 *
	 * @param width - the width of the board
	 * @param height - the height of the board
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		currentPhase = Phase.START;
		turnNum = 1;
		firstRound = true;
		players = new ArrayList<Player>();
		earthMap = new Map();
		panes = new ArrayList<Pane>();
		dice = new Dice();
		continents = new ArrayList<Continent>(earthMap.getContinents());
		transitionPopup = new TransitionPopup(this);

		createBoard();
		createPanes();

		// mapController must be initialized last
		mapController = new MapController(this);
		earthMap.setMapController(mapController);
		
	}

	/**
	 * Instantiates a new board.
	 *
	 * @param b - the board itself
	 */
	public Board(Board b) { // Have to make new ArrayList<Players> for all.
		this.players = b.players;
		this.earthMap = b.earthMap;
		this.continents = b.continents;
		this.firstRound = b.firstRound;
	}

	/**
	 * Start game - starts the fame
	 */
	public void startGame() {
		setTurn(players.get(0));
		nextPhase();
		interactivePane.showBottomDisplay();
		for (Pane p : panes) {
			p.setVisible(true);
		}
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	private Board getBoard() {
		return new Board(this);
	}

	/**
	 * Return ArrayList of Players.
	 *
	 * @return the number of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Sets the current Player to the Player p.
	 * @param p - the next Player in the game.
	 */
	public void setTurn(Player p) {
		currentPlayer = p;
	}

	/**
	 * Adds the selected number of troops to the selected Country.
	 * @param c - the Country selected.
	 * @param troopsToDraft - the number of troops to add.
	 */
	public void draftBonusTroops(Country c, int troopsToDraft) {
		c.addTroops(troopsToDraft);
		currentPlayer.setBonusTroops(currentPlayer.getBonusTroops() - troopsToDraft);
	}

	/**
	 * Initiates a battle between two opposing Countries.
	 * @param attacking - the attacking Country.
	 * @param defending - the defending Country.
	 * @return a BattleReport containing the results of the battle between the 
	 * attacking Country and the defending Country.
	 */
	public BattleReport battle(Country attacking, Country defending) {
		int startAlliedTroops = attacking.getNumTroops();
		int startEnemyTroops = defending.getNumTroops();
		int atkTroopsLost = 0;
		int dfndTroopsLost = 0;

		boolean victorious = false;
		boolean wonBattle = false;
		int atkTroops = attacking.getNumTroops();
		int dfndTroops = defending.getNumTroops();

		if (this.getTroopsDifference(atkTroops, dfndTroops) >= absolutePower) {
			victorious = true;
			dfndTroopsLost = dfndTroops;
		}

		while (!victorious && atkTroops > 1) {

			int[] atkRolls = dice.getRolls(atkTroops);
			int[] dfndRolls = dice.getRolls(dfndTroops);
			wonBattle = dice.checkIfVictorious(atkRolls, dfndRolls);

			if (wonBattle) {
				dfndTroopsLost++;
				dfndTroops--;
			} else {
				atkTroopsLost++;
				atkTroops--;
			}

			if (dfndTroops == 0) {
				victorious = true;
			}
		}

		return new BattleReport(startAlliedTroops, startEnemyTroops, atkTroopsLost, dfndTroopsLost, victorious);
	}

	/**
	 * Gets the numerical difference between the attacking troops and defending troops.
	 * @param attackingTroops the number of attacking troops.
	 * @param defendingTroops the number of defending troops.
	 * @return the difference between the number of attacking troops and defending troops.
	 */
	public int getTroopsDifference(int attackingTroops, int defendingTroops) {
		return Math.abs(attackingTroops - defendingTroops);
	}

	/**
	 * Gets a Player from the ArrayList of Players based on the playerNumber.
	 *
	 * @param playerNumber - the player number
	 * @return the Player corresponding to the player number.
	 */
	public Player getPlayer(int playerNumber) {
		for (Player p : players) {
			if (p.getPlayerNumber() == playerNumber) return p;
		}
		return null;
	}
	
	/**
	 * This method should be called whenever a player has
	 * no countries left.
	 *
	 * @param playerNumber - the player number
	 */
	public void playerDefeated(int playerNumber) {
		players.remove(getPlayer(playerNumber));		
	}
	
	
	/**
	 * Gets an ArrayList of current Player Owned Countries with more than 1
	 * troop.
	 * 
	 * @return an ArrayList<Country>
	 */
	private ArrayList<Country> getDeployableCountries() {
		ArrayList<Country> deployableCountries = new ArrayList<Country>();
		for (Country c : this.getCurrentPlayerOwnedCountries()) {
			if (c.getNumTroops() > 1) {
				deployableCountries.add(c);
			}
		}
		

		return deployableCountries;
	}

	/**
	 * Gets the countries owned by the current player.
	 *
	 * @return the ArrayList of Countries owned by the current Player
	 */
	public ArrayList<Country> getCurrentPlayerOwnedCountries() {
		ArrayList<Country> playerOwnedCountries = new ArrayList<Country>();
		for (Continent cont : continents) {
			for (Country c : cont.getCountries()) {
				if (c.getPlayerOccupantOfCountry() == currentPlayer.getPlayerNumber()) {
					playerOwnedCountries.add(c);
				}
			}
		}
		return playerOwnedCountries;
	}

	/**
	 * Initializes the number of Players and their respective names by getting
	 * user input with the Console class.
	 *
	 * @param names - the names
	 */
	public void initializePlayers(ArrayList<String> names) {
		for (int i = 0; i < names.size(); i++) {
			players.add(new Player(i + 1, false, this.getBoard()));
			players.get(i).setPlayerName(names.get(i));
		}

		for (int i = names.size(); i < totalPlayerNum; i++) {
			players.add(new Player(i + 1, true, this.getBoard()));
			players.get(i).setPlayerName(null);
		}

		currentPlayer = players.get(0);
	}

	/**
	 * Gets the ArrayList of Continents on the map.
	 * @return a copy of the ArrayList of Continents (encapsulated)
	 */
	private ArrayList<Continent> getContinentsList() {
		return new ArrayList<Continent>(continents);
	}

	/**
	 * Gets the Country Object corresponding to countryName. The returned
	 * Country Object is non-encapsulated.
	 * 
	 * @param countryName
	 *            Name of the Country
	 * @return the corresponding Country Object. Returns null if no Country is
	 *         found.
	 */
	private Country getCountry(String countryName) {
		for (Continent cont : continents) {
			for (Country c : cont.getCountries()) {
				if (c.getName().equalsIgnoreCase(countryName))
					return c;
			}
		}
		return null;
	}

	/**
	 * Calculates the bonus troops for the current Player based
	 * on the number of owned Countries, and which Continents a Player 
	 * has conquered.
	 * @return the current Player's number of bonus troops.
	 */
	private int getTroopBonus() {
		int troopBonus = getCurrentPlayerOwnedCountries().size() / 3;
		int continentBonus = 0;
		for (Continent cont : continents) {
			int contScore = 0;
			for (Country country : cont.getCountries()) {
				if (country.getPlayerOccupantOfCountry() == currentPlayer.getPlayerNumber()) {
					contScore++;
				}
			}
			if (contScore == cont.getCountries().size()) {
				continentBonus = continentBonus + cont.getContinentBonus();
			}
		}
		if (troopBonus < 3) troopBonus = 3;
		return troopBonus + continentBonus;
	}

	/**
	 * Gets the ArrayList of Continents.
	 * @return the ArrayList of continents on the map (unencapsulated).
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	/**
	 * Gets the player's name based on their player number.
	 *
	 * @param playerNumber - the player number
	 * @return a String containing the player's name
	 */
	public String getPlayerName(int playerNumber) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getPlayerNumber() == playerNumber) {
				return players.get(i).getPlayerName();
			}
		}
		return null;
	}

	/**
	 * Creates the main panes for the board and adds them to the ArrayList of panes.
	 */
	public void createPanes() 
	{
		interactivePane = new InteractivePane(this, earthMap);
		interactivePane.setPrefWidth(width);
		interactivePane.setPrefHeight(height);
		
		labelLayer = new LabelLayer(this);
		labelLayer.setMouseTransparent(true);
		
		panes.add(interactivePane);
		panes.add(labelLayer);

		for (Pane p : panes) {
			p.setVisible(false);
		}
	}

	/**
	 * Gets the ArrayList of panes being used by Board.
	 * @return the ArrayList of panes.
	 */
	public ArrayList<Pane> getPanes() 
	{
		return panes;
	}
	
	/**
	 * shifts the board to the next phase.
	 */
	public void nextPhase() {
		this.interactivePane.setMouseTransparent(false);
		mapController.clear();
		switch (currentPhase) {
		case START:
			currentPhase = Phase.DRAFT;
			resetMap();
			draft();
			break;
		case DRAFT:
			currentPhase = Phase.ATTACK;
			resetMap();
			attack();
			break;
		case ATTACK:
			currentPhase = Phase.FORTIFY;
			resetMap();
			fortify();
			break;
		case FORTIFY:
			currentPhase = Phase.DRAFT;
			nextTurn();
			resetMap();
			draft();
			break;
		}
		// resetMap();
		transitionPopup.show();
		transitionPopup.nextPhaseTransition(currentPhase);
		mapController.setPhase(currentPhase);
		interactivePane.update();
	}

	/**
	 * Method to be called whenever the game moves to the draft phase.
	 */
	private void draft() {
		currentPlayer.setBonusTroops(getTroopBonus());
		interactivePane.setDisableNextButton(true);
		if(currentPlayer.getIsAI() == true) {
			mapController.draftAi();
		}
	}
	
	/**
	 * Method to be called whenever the game moves to the attack phase.
	 */
	private void attack() {
		if(currentPlayer.getIsAI() == true) {
			mapController.attackAi();
		}
	}
	
	/**
	 * Method to be called whenever the game moves to the fortify phase.
	 */
	private void fortify() {
		if(currentPlayer.getIsAI() == true) {
			mapController.fortifyAi();
		}
	}

		
	/**
	 * This method should be called when a Player wins the game.
	 */
	public void victory() {
		new VictoryPopup(this, currentPlayer);
	}
	
	/**
	 * Next turn.
	 */
	private void nextTurn() {
		if (turnNum < players.size())
			turnNum++;
		else
			turnNum = 1;
		setTurn(players.get(turnNum - 1));
	}

	/**
	 * Sets whether or not a Country is clickable based on the current
	 * phase of the game.
	 */
	public void resetMap() {
		switch (currentPhase) {		
		case ATTACK:
			for (Continent cont : continents) {
				for (Country c : cont.getCountries()) {				
					c.setClickable(false);
				}
			}
		break;
		case FORTIFY:
			for (Continent cont : continents) {
				for (Country c : cont.getCountries()) {				
					c.setClickable(false);		
				}
			}
		break;
		default:
			for (Continent cont : continents) {
				for (Country c : cont.getCountries()) {
					if (c.getPlayerOccupantOfCountry() == currentPlayer.getPlayerNumber()) {
						c.setClickable(true);
					} else {
						c.setClickable(false);
					}
				}
			}
		break;
		}
	}

	/**
	 * Current selected countries are unselected.
	 */
	public void resetSelected() {
		mapController.clear();
	}

	/**
	 * Gets the current phase of the game.
	 *
	 * @return the current phase
	 */
	public Phase getPhase() {
		return currentPhase;
	}

	/**
	 * Gets the current Player's name.
	 * @return a String containing the current Player's name.
	 */
	public String getCurrentPlayerName() {
		return currentPlayer.getPlayerName();
	}

	/**
	 * Gets the height of the board calculated by the number of pixels.
	 * @return the height of the board.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width of the board calculated by the number of pixels.
	 * @return the width of the board.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the InteractivePane of the board, where most interactive
	 * objects are contained.
	 * @return the InteractivePane of the board (unencapsulated).
	 */
	public InteractivePane getInteractivePane() {
		return this.interactivePane;
	}

	/**
	 * Gets the LabelLayer which contains the Troop Labels of each Country.
	 * @return the LabelLayer of the board (unencapsulated).
	 */
	public LabelLayer getLabelLayer() {
		return labelLayer;
	}
	
	
	/**
	 * distributes the map based on the number of Players.
	 */
	private void createBoard() {

		// clone continents list as a new list called currentContinents
		ArrayList<String> currentContinents = new ArrayList<String>();
		for (Continent cont : continents) {
			currentContinents.add(cont.getContinentName());
		}

		// ArrayList<Continent> currentContinents = new
		// ArrayList<Continent>(continents);

		// clone current country list per continent
		ArrayList<Country> currentNorthAmericaCountries = continents.get(0).getCountries();
		ArrayList<Country> currentSouthAmericaCountries = continents.get(1).getCountries();
		ArrayList<Country> currentEuropeCountries = continents.get(2).getCountries();
		ArrayList<Country> currentAfricaCountries = continents.get(3).getCountries();
		ArrayList<Country> currentAsiaCountries = continents.get(4).getCountries();
		ArrayList<Country> currentAustraliaCountries = continents.get(5).getCountries();

		// new list, Player's current countries list, there's always going to be
		// 4 players
		int numberOfPlayers = 4;
		int startingTroopsPerPlayer = 25;

		int player1Troops = startingTroopsPerPlayer;
		int player2Troops = startingTroopsPerPlayer;
		int player3Troops = startingTroopsPerPlayer;
		int player4Troops = startingTroopsPerPlayer;

		ArrayList<Country> player1Countries = new ArrayList<Country>();
		ArrayList<Country> player2Countries = new ArrayList<Country>();
		ArrayList<Country> player3Countries = new ArrayList<Country>();
		ArrayList<Country> player4Countries = new ArrayList<Country>();

		// loop if playerCountWithNoTroops != numberOfPlayers:
		for (int playerCountWithNoTroops = 0; playerCountWithNoTroops < numberOfPlayers;) {

			// loop once per player:
			for (int playerTurn = 0; playerTurn != numberOfPlayers; playerTurn++) {

				int currentTroops = 0;

				// get current number of troops for that player
				if (playerTurn == 0) {
					currentTroops = player1Troops;
				}
				if (playerTurn == 1) {
					currentTroops = player2Troops;
				}
				if (playerTurn == 2) {
					currentTroops = player3Troops;
				}
				if (playerTurn == 3) {
					currentTroops = player4Troops;
				}

				// if current number of troops for that player > 0:
				if (currentTroops > 0) {

					// if currentContinents is NOT empty:
					if (currentContinents.size() > 0) {

						Country currentCountry = null;

						// choose random currentContinent
						Random randomContinent = new Random();
						String chosenContinent = currentContinents
								.get(randomContinent.nextInt(currentContinents.size()));

						// choose random country from the currentCountryList of
						// that continent
						if (chosenContinent == "North America") {
							Random randomCountry = new Random();
							currentCountry = currentNorthAmericaCountries
									.get(randomCountry.nextInt(currentNorthAmericaCountries.size()));
						}
						if (chosenContinent == "South America") {
							Random randomCountry = new Random();
							currentCountry = currentSouthAmericaCountries
									.get(randomCountry.nextInt(currentSouthAmericaCountries.size()));
						}
						if (chosenContinent == "Europe") {
							Random randomCountry = new Random();
							currentCountry = currentEuropeCountries
									.get(randomCountry.nextInt(currentEuropeCountries.size()));
						}
						if (chosenContinent == "Africa") {
							Random randomCountry = new Random();
							currentCountry = currentAfricaCountries
									.get(randomCountry.nextInt(currentAfricaCountries.size()));
						}
						if (chosenContinent == "Asia") {
							Random randomCountry = new Random();
							currentCountry = currentAsiaCountries
									.get(randomCountry.nextInt(currentAsiaCountries.size()));
						}
						if (chosenContinent == "Australia") {
							Random randomCountry = new Random();
							currentCountry = currentAustraliaCountries
									.get(randomCountry.nextInt(currentAustraliaCountries.size()));
						}

						int selectTroops = 0;
						// if player number of troops > 2:
						if (currentTroops > 2) {
							// Randomize 1-3 Troops
							Random randomSelectTroops = new Random();
							selectTroops = randomSelectTroops.nextInt(3);
							selectTroops += 1;

							// Minus that number from the player's current
							// number of troops
							currentTroops -= selectTroops;
						}

						else {
							// change the number of troops to add as the
							// player's current number of troops
							selectTroops = currentTroops;
							// set player's current number of troops as 0
							currentTroops = 0;
						}

						// add those troops to that random country
						for (Continent cont : continents) {
							for (Country country : cont.getCountries()) {
								if (country == currentCountry) {
									country.addTroops(selectTroops);
								}
							}
						}

						// remove that random country from currentCountryList
						// if currentCountryList for that continent is empty:
						// remove that continent from currentContinents list
						if (chosenContinent == "North America") {
							currentNorthAmericaCountries.remove(currentCountry);
							if (currentNorthAmericaCountries.size() == 0) {
								currentContinents.remove("North America");
							}
						}
						if (chosenContinent == "South America") {
							currentSouthAmericaCountries.remove(currentCountry);
							if (currentSouthAmericaCountries.size() == 0) {
								currentContinents.remove("South America");
							}
						}
						if (chosenContinent == "Europe") {
							currentEuropeCountries.remove(currentCountry);
							if (currentEuropeCountries.size() == 0) {
								currentContinents.remove("Europe");
							}
						}
						if (chosenContinent == "Africa") {
							currentAfricaCountries.remove(currentCountry);
							if (currentAfricaCountries.size() == 0) {
								currentContinents.remove("Africa");
							}
						}
						if (chosenContinent == "Asia") {
							currentAsiaCountries.remove(currentCountry);
							if (currentAsiaCountries.size() == 0) {
								currentContinents.remove("Asia");
							}
						}
						if (chosenContinent == "Australia") {
							currentAustraliaCountries.remove(currentCountry);
							if (currentAustraliaCountries.size() == 0) {
								currentContinents.remove("Australia");
							}
						}

						// add country to player's current countries list
						if (playerTurn == 0) {
							player1Countries.add(currentCountry);
						}
						if (playerTurn == 1) {
							player2Countries.add(currentCountry);
						}
						if (playerTurn == 2) {
							player3Countries.add(currentCountry);
						}
						if (playerTurn == 3) {
							player4Countries.add(currentCountry);
						}

					}

					else {
						// randomize through player's current countries list
						// if country's number of troops < 3:
						// add 1 troop to that country
						// remove 1 troop from player's current number of troops
						Random previousCountry = new Random();
						Country currentCountry = null;

						if (playerTurn == 0) {
							currentCountry = player1Countries.get(previousCountry.nextInt(player1Countries.size()));
						}
						if (playerTurn == 1) {
							currentCountry = player2Countries.get(previousCountry.nextInt(player2Countries.size()));
						}
						if (playerTurn == 2) {
							currentCountry = player3Countries.get(previousCountry.nextInt(player3Countries.size()));
						}
						if (playerTurn == 3) {
							currentCountry = player4Countries.get(previousCountry.nextInt(player4Countries.size()));
						}

						if (currentCountry.getNumTroops() < 3) {
							currentCountry.addTroops(1);
						}

						currentTroops -= 1;

					}

					// set current Troops to the proper player
					if (playerTurn == 0) {
						player1Troops = currentTroops;
					}
					if (playerTurn == 1) {
						player2Troops = currentTroops;
					}
					if (playerTurn == 2) {
						player3Troops = currentTroops;
					}
					if (playerTurn == 3) {
						player4Troops = currentTroops;
					}

				} else {
					playerCountWithNoTroops += 1;
				}

			}

			if (playerCountWithNoTroops < numberOfPlayers) {
				playerCountWithNoTroops = 0;
			}
		}

		// Give troops to player ID

		for (Country cont : player1Countries) {
			cont.setOccupantID(1);
		}
		for (Country cont : player2Countries) {
			cont.setOccupantID(2);
		}
		for (Country cont : player3Countries) {
			cont.setOccupantID(3);
		}
		for (Country cont : player4Countries) {
			cont.setOccupantID(4);
		}

	}

}
