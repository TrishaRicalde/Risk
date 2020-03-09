package com.Board;

import java.util.ArrayList;
import java.util.Random;
import com.Board.Console.Console;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import com.Gui.Command;
import com.Gui.Panes.InteractivePane;
import com.Player.Player;

import javafx.scene.layout.Pane;

public class Board {
	private ArrayList<Continent> continents;
	private ArrayList<Player> players;
	private Console console;
	private Player currentPlayer;
	private int numAI;
	private Map earthMap;
	private Command commands;
	private boolean firstRound;
	private Dice dice;

	private Phase currentPhase;
	
	private ArrayList<Pane> panes;
	private InteractivePane interactivePane;


	private final int totalPlayerNum = 4;
	private final int absolutePower = 5;
	
	

	public Board() {
		players = new ArrayList<Player>();
		console = new Console();
		earthMap = new Map();
		commands = new Command();
		dice = new Dice();
		continents = new ArrayList<Continent>(earthMap.getContinents());
		panes = new ArrayList<Pane>();
		firstRound = true;
		currentPhase = Phase.START;
		//initializePlayers();
		createBoard();
		createPanes();
	}

	public Board(Board b) { // Have to make new ArrayList<Players> for all.
		this.players = b.players;
		this.console = b.console;
		this.earthMap = b.earthMap;
		this.commands = b.commands;
		this.continents = b.continents;
		this.firstRound = b.firstRound;
	}
	
	public void start() {
		for (Player p : players) {
			setTurn(p);
		}
	}

	private Board getBoard() {
		return new Board(this);
	}

	/**
	 * Return ArrayList of Players
	 * 
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setTurn(Player p) {
		currentPlayer = p;
		nextPhase();
		/*if (firstRound) {
			currentPlayer = players.get(0);
			firstRound = false;
		} else if (currentPlayer.getPlayerNumber() < players.size()) {
			currentPlayer = players.get(currentPlayer.getPlayerNumber());
		} else {
			currentPlayer = players.get(0);
		}

		printBoardState();
		console.println("\n---------------------------------------------- " + currentPlayer.getPlayerName()
				+ "'s Turn ----------------------------------------------");
		draft();
		attack();
		fortify();*/
	}

	private void printBoardState() {
		/*
		 * Prints out current state of the board. for (continent: continents)
		 * for (country: countries) prints players troops on each
		 * country/continent
		 */

		for (int i = 0; i < continents.size(); i++) {
			console.printBoardState(continents, i, getBoard());
		}
	}

	// TO DO
	private void draft() {
		console.println("\n---------------------- Draft ----------------------\n");
		// option to trade in cards --------TO BE IMPLEMENTED
		int bonusTroops = getTroopBonus();
		if (bonusTroops == 0) {
			console.println("You have no bonus Troops. \n");
			return;
		}

		while (bonusTroops > 0) {
			console.println("Player " + currentPlayer.getPlayerNumber() + ": has " + bonusTroops + " bonus troops.");

			console.print("Where would you like to place your troops: ");
			Country c;
			
			if(players.get(currentPlayer.getPlayerNumber() - 1).getIsAI() == false) {
				String selectedCountry = console.getScannerCountry(getCurrentPlayerOwnedCountries());
				c = this.getCountry(selectedCountry);
			} else {
				c = (players.get(currentPlayer.getPlayerNumber() - 1).getCountryToAddTroops(this.getBoard(), getCurrentPlayerOwnedCountries()));
			}
			System.out.println(c.getName());
			
			if(players.get(currentPlayer.getPlayerNumber() - 1).getIsAI() == false) {
				int numTroopsToAdd = console.getScannerIntWithinRange(1, bonusTroops,"How many troops would you like to place?");
				c.addTroops(numTroopsToAdd);
				bonusTroops -= numTroopsToAdd;
			} else {
				c.addTroops(players.get(currentPlayer.getPlayerNumber() - 1).aiDraft(bonusTroops, getBoard()));
				bonusTroops -= players.get(currentPlayer.getPlayerNumber() - 1).aiDraft(bonusTroops, getBoard());
			}

		}

		printBoardState();
	}

	// TO DO
	private void attack() {
		console.println("---------------------- Attack ----------------------\n");
		/*ArrayList<Country> deployableCountries = new ArrayList<Country>(this.getDeployableCountries());

		if (deployableCountries.size() == 0) {
			console.println("You do not have any troops to attack with.\n");
			return;
		}
		
		
		boolean choice;

		if(players.get(currentPlayer.getPlayerNumber() - 1).getIsAI() == false) {
			choice = console.getScannerCommand(commands.getAttackCmds()).equalsIgnoreCase("attack");
		} else {
			choice = (players.get(currentPlayer.getPlayerNumber() - 1).getAttackChoice(this.getBoard(), getCurrentPlayerOwnedCountries()));
		}
		
		while (choice) {
			console.println("Which Country would you like to launch your attack from?");
			Country attackingCountry;
			
			if(players.get(currentPlayer.getPlayerNumber() - 1).getIsAI() == false) {
				attackingCountry = this.getCountry(console.getScannerCountry(deployableCountries));
			} else {
				attackingCountry = (players.get(currentPlayer.getPlayerNumber() - 1).getCountryToAttackFrom(this.getBoard(), getCurrentPlayerOwnedCountries()));
			}
			
			console.println("Which Country would you like to attack?");
			Country defendingCountry;
			
			if (attackableCountries.size() > 0) {
				if(players.get(currentPlayer.getPlayerNumber() - 1).getIsAI() == false) {
					defendingCountry = this.getCountry(console.getScannerCountry(attackingCountry.getBorders()));
				} else {
					defendingCountry = players.get(currentPlayer.getPlayerNumber() - 1).getCountryToAttack(this.getBoard(), getCurrentPlayerOwnedCountries());
				}
				
				BattleReport bReport = this.battle(attackingCountry, defendingCountry);
				attackingCountry.subractTroops(bReport.getAttackingTroopsLost());
				defendingCountry.subractTroops(bReport.getDefendingTroopsLost());
				
				if (bReport.isVictorious()) {
					moveTroops(attackingCountry, defendingCountry);
					defendingCountry.setOccupantID(currentPlayer.getPlayerNumber());
					printBoardState();
				}

			// Checks if the player has troops left to attack with.
			int count = 0;
			for (Country c : this.getCurrentPlayerOwnedCountries()) {
				if (c.getNumTroops() > 1) {
					//count++;
				}
			}
			if (count > 0) {
				console.println("Would you like to continue attacking?");
			} else {
				return;
			}
		}
		printBoardState();*/
	}

	/**
	 * Moves troops from c1 to c2.
	 * 
	 * @param c1
	 *            Country you are taking troops from.
	 * @param c2
	 *            Country you are moving troops to.
	 */
	private void moveTroops(Country c1, Country c2) {
		int max = c1.getNumTroops() - 1;
		int min = 1;
		if (max == 1) {
			c1.subractTroops(1);
			c2.addTroops(1);
			console.println("1 troop was moved");
			return;
		}
		int numTroopsToMove = console.getScannerIntWithinRange(min, max,
				"Enter the amount of troops to move to " + c2.getName() + ": ");
		c1.subractTroops(numTroopsToMove);
		c2.addTroops(numTroopsToMove);

	}

	// TO DO
	private void fortify() {
		console.println("---------------------- Fortify ----------------------\n");
		ArrayList<Country> deployableCountries = new ArrayList<Country>(this.getDeployableCountries());
		if (deployableCountries.size() == 0) {
			console.println("You do not have any troops to fortify with.\n");
			return;
		}
		
		boolean choice;
		
		if(players.get(currentPlayer.getPlayerNumber() - 1).getIsAI() == false) {
			choice = console.getScannerCommand(commands.getFortifyCmds()).equalsIgnoreCase("fortify");
		} else {
			choice = false;//(players.get(currentPlayer.getPlayerNumber() - 1).getAttackChoice(this.getBoard(), getCurrentPlayerOwnedCountries()));
		}
		
		while (choice) {
			console.println("Which Country would you like to take troops from?");

			// Adds the countries owned with more than 1 troop to the ArrayList
			// countryDeployable.

			Country deployFrom;
			deployFrom = getCountry(console.getScannerCountry(deployableCountries));

			// Creates an ArrayList of fortifiable Countries.
			ArrayList<Country> fortifiable = new ArrayList<Country>();
			for (Country i : deployFrom.getBorders()) {
				if (this.getCurrentPlayerOwnedCountries().indexOf(i) >= 0) {
					fortifiable.add(i);
				}
			}

			// If the ArrayList is of size 0, then gives the option to choose
			// another country or end turn.
			// Else
			if (fortifiable.size() == 0) {
				console.println("The Country you have selected has does not border any other Countries you own.\n" + ""
						+ "Would you like to choose a different Country?");
				deployableCountries.remove(deployFrom);
			} else {
				console.println("Which Country would you like to fortify?");
				Country fortifiedCountry = getCountry(console.getScannerCountry(deployFrom.getBorders()));
				int numTroopsToAdd = console.getScannerIntWithinRange(1, deployFrom.getNumTroops() - 1,
						"How many troops would you like to place?");
				fortifiedCountry.addTroops(numTroopsToAdd);
				deployFrom.subractTroops(numTroopsToAdd);
				return;
			}
		}
		printBoardState();

	}

	// TO DO
	private void endTurn() {
		printBoardState();
	}

	
	 
	private BattleReport battle(Country attacking, Country defending) {
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
				dfndTroopsLost ++;
				dfndTroops --;
			} else {
				atkTroopsLost ++;
				atkTroops --;
			}
			
			if (dfndTroops == 0) {
				victorious = true;			
			}
		}
		
		return new BattleReport(atkTroopsLost, dfndTroopsLost, victorious);
	}
	
	public int getTroopsDifference(int attackingTroops, int defendingTroops) {
		return Math.abs(attackingTroops - defendingTroops);
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
	 * Gets the countries owned by the current player
	 * 
	 * @return a copy of the ArrayList of Countries owned by the current Player
	 */
	public ArrayList<Country> getCurrentPlayerOwnedCountries() {
		ArrayList<Country> playerOwnedCountries = new ArrayList<Country>();
		for (Continent cont : continents) {
			for (Country c : cont.getCountries()) {
				if (c.getPlayerOwnerOfCountry() == currentPlayer.getPlayerNumber()) {
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
	 */
	private void initializePlayers() {
		int numPlayers = console.getScannerNumOfPlayers();
		ArrayList<String> playerNames = console.getPlayerNames(numPlayers);
		numAI = 4 - numPlayers;

		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player(i + 1, false, this.getBoard()));
			players.get(i).setPlayerName(playerNames.get(i));
		}

		for (int i = numPlayers; i < totalPlayerNum; i++) {
			players.add(new Player(i + 1, true, this.getBoard()));
			players.get(i).setPlayerName(null);
		}

		currentPlayer = new Player(players.get(0));
	}

	/**
	 * 
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

	private int getTroopBonus() {
		int playerOwnedCountryNum = getCurrentPlayerOwnedCountries().size() / 3;
		int continentBonus = 0;
		for (Continent cont : continents) {
			int contScore = 0;
			for (Country country : cont.getCountries()) {
				if (country.getPlayerOwnerOfCountry() == currentPlayer.getPlayerNumber()) {
					contScore++;
				}
			}
			if (contScore == cont.getCountries().size()) {
				continentBonus = continentBonus + cont.getContinentBonus();
			}
		}
		return playerOwnedCountryNum + continentBonus;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}

	public String getPlayerName(int playerNumber) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getPlayerNumber() == playerNumber) {
				return players.get(i).getPlayerName();
			}
		}
		return null;
	}

	private void createBoard() {

		// Clean this up later...

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

	public void createPanes() {
		interactivePane = new InteractivePane(earthMap); 
		panes.add(interactivePane);

		
	}
	
	public ArrayList<Pane> getPanes() {
		return panes;
	}
	
	public void nextPhase() {
		switch(currentPhase) {
		case START: 
			currentPhase = Phase.DRAFT;
		case DRAFT:
			currentPhase = Phase.ATTACK;
		case ATTACK: 
			currentPhase = Phase.FORTIFY;
		case FORTIFY: 
			currentPhase = Phase.DRAFT;
		}
	}
	
	
	
	}


