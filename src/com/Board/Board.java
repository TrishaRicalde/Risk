package com.Board;

import java.util.ArrayList;

import com.Board.Console.Console;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import com.Main.Command;
import com.Player.Player;

public class Board {
	private ArrayList<Continent> continents;
	private ArrayList<Player> players;
	private Console console;
	private Player currentPlayer;
	private int numAI;
	private Map earthMap;
	private Command commands;
	private boolean firstRound;
	final private int totalPlayerNum = 4;
	// Create Dice??

	public Board() {
		players = new ArrayList<Player>();
		console = new Console();
		earthMap = new Map();
		commands = new Command();
		continents = new ArrayList<Continent>(earthMap.getContinents());
		firstRound = true;
		initializePlayers();
		createBoard();

	}

	public Board(Board b) { // Have to make new ArrayList<Players> for all.
		this.players = b.players;
		this.console = b.console;
		this.earthMap = b.earthMap;
		this.commands = b.commands;
		this.continents = b.continents;
		this.firstRound = b.firstRound;
	}

	// TO DO
	private void createBoard() {
		for (Continent cont : continents) {
			int playerIdentity = 1;
			for (Country country : cont.getCountries()) {
				country.setPlayerIdentity(playerIdentity);
				playerIdentity ++;
				if (playerIdentity == 4) {
					playerIdentity = 0;
				}
				country.addDraftedTroops(3);
			}
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

	public void nextTurn() {
		if (firstRound) {
			currentPlayer = players.get(0);
			firstRound = false;
		} else if (currentPlayer.getPlayerNumber() < players.size()) {
			currentPlayer = players.get(currentPlayer.getPlayerNumber());
		} else {
			currentPlayer = players.get(0);
		}

		printBoardState();
		console.println("\n---------------------------------------------- " + currentPlayer.getPlayerName() + "'s Turn ----------------------------------------------");
		draft();
		attack();
		fortify();
		endTurn(); // Useful??
	}

	private void printBoardState() {
		/*
		 * Prints out current state of the board. for (continent: continents)
		 * for (country: countries) prints players troops on each
		 * country/continent
		 */
		
		for(int i = 0; i < continents.size(); i++) {
			console.printBoardState(continents, i, getBoard());
		}
	}

	// TO DO
	private void draft() {
		console.println("\n---------------------- Draft ----------------------\n");
		// option to trade in cards --------TO BE IMPLEMENTED
		int bonusTroops = getTroopBonus();
		if (bonusTroops == 0) {
			console.println("You have no bonus Troops \n");
			return;
		}
		if (bonusTroops > 0 && console.getScannerCommand(commands.getDraftCmds()).equalsIgnoreCase("draft")) {

			while (bonusTroops > 0) {
				console.println("Player " + currentPlayer.getPlayerNumber() + ": has " + bonusTroops + " bonus troops.");
				console.println("Where would you like to place your troops?");
				String selectedCountry = console.getScannerCountry(getCurrentPlayerOwnedCountries());
				Country c = this.getCountry(selectedCountry);
				int numTroopsToAdd = console.getScannerIntWithinRange(1, bonusTroops,
						"How many troops would you like to place?");
				c.addDraftedTroops(numTroopsToAdd);
				bonusTroops -= numTroopsToAdd;
			}
		}
		printBoardState();
	}

	// TO DO
	private void attack() {
		console.println("---------------------- Attack ----------------------\n");
		ArrayList<Country> deployableCountries = new ArrayList<Country>(this.getDeployableCountries());
		
		if (deployableCountries.size() == 0) {
			console.println("You do not have any troops to attack with.\n");
			return;
		}
		
		while (console.getScannerCommand(commands.getAttackCmds()).equalsIgnoreCase("attack")) {
			console.println("Which Country would you like to launch your attack from?");
			Country attackFrom = this.getCountry(console.getScannerCountry(deployableCountries));
			console.println("Which Country would you like to attack?");
			console.getScannerCountry(attackFrom.getBorders());
			/*
			 * Attack - TO BE IMPLEMENTED
			 */

			// Checks if the player has troops left to attack with.
			int count = 0;
			for (Country c : this.getCurrentPlayerOwnedCountries()) {
				if (c.getNumTroops() > 1)
					count++;
			}
			if (count > 0) {
				console.println("Would you like to continue attacking?");
			} else {
				return;
			}
		}
		printBoardState();
	}

	// TO DO
	private void fortify() {
		console.println("---------------------- Fortify ----------------------\n");
		ArrayList<Country> deployableCountries = new ArrayList<Country>(this.getDeployableCountries());		
		if (deployableCountries.size() == 0) {
			console.println("You do not have any troops to fortify with.\n");
			return;
		}

		while (console.getScannerCommand(commands.getFortifyCmds()).equalsIgnoreCase("fortify")) {
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

			//If the ArrayList is of size 0, then gives the option to choose another country or end turn.
			//Else 
			if (fortifiable.size() == 0) {
				console.println("The Country you have selected has does not border any other Countries you own.\n" + ""
						+ "Would you like to choose a different Country?");
				deployableCountries.remove(deployFrom);
			} else {
				console.println("Which Country would you like to fortify?");
				Country fortifiedCountry = getCountry(console.getScannerCountry(deployFrom.getBorders()));
				int numTroopsToAdd = console.getScannerIntWithinRange(1, deployFrom.getNumTroops() - 1,
						"How many troops would you like to place?");
				fortifiedCountry.addDraftedTroops(numTroopsToAdd);
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

	/**
	 * Gets an ArrayList of current Player Owned Countries with more than 1 troop.
	 * @return an ArrayList<Country> 
	 */
	private ArrayList<Country> getDeployableCountries() {
		ArrayList<Country> deployableCountries = new ArrayList<Country>();
		for (Country c :  this.getCurrentPlayerOwnedCountries()) {
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
		for(Continent cont : continents) {
			int contScore = 0;
			for(Country country : cont.getCountries()) {
				if(country.getPlayerOwnerOfCountry() == currentPlayer.getPlayerNumber()) {
					contScore++;
				}
			}
			if(contScore == cont.getCountries().size()) {
				continentBonus = continentBonus + cont.getContinentBonus();
			}
		}
		return playerOwnedCountryNum + continentBonus;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}
	
	public String getPlayerName(int playerNumber) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getPlayerNumber() == playerNumber) {
				return players.get(i).getPlayerName();
			}
		}
		return null;
	}

}
