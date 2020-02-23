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

	}

	public Board(Board b) { //Have to make new ArrayList<Players> for all.
		this.players = b.players;
		this.console = b.console;
		this.earthMap = b.earthMap;
		this.commands = b.commands;
		this.continents = b.continents;
		this.firstRound = b.firstRound;
	}

	// TO DO
	private void createBoard() {

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

		console.println("----------------------- " + currentPlayer.getPlayerName() + "'s Turn -----------------------");
		
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
		printBoardState();
		console.println("--------- Draft --------- \n");
		// option to trade in cards --------TO BE IMPLEMENTED
		int bonusTroops = getTroopBonus();
		if (bonusTroops == 0) {
			console.println("You have no bonus Troops \n");
			return;
		}
		if (bonusTroops > 0 && console.getScannerCommand(commands.getDraftCmds()).equalsIgnoreCase("draft")) {
			console.println("Player " + currentPlayer.getPlayerNumber() + " has " + bonusTroops + " bonus troops.");

			while (bonusTroops > 0) {
				console.println("Where would you like to place your troops?");
				String selectedCountry = console.getScannerCountry(getCurrentPlayerOwnedCountries());
				Country c = this.getCountry(selectedCountry);
				int numTroopsToAdd = console.getScannerIntWithinRange(1, bonusTroops,
						"How many troops would you like to place?");
				c.addDraftedTroops(numTroopsToAdd);
				bonusTroops -= numTroopsToAdd;
			}
		}

	}

	// TO DO
	private void attack() {
		printBoardState();
		console.println("--------- Attack --------- \n");
			
		while (console.getScannerCommand(commands.getAttackCmds()).equalsIgnoreCase("attack")) {
			console.println("Which Country would you like to launch your attack from?");
			Country attackFrom = this.getCountry(console.getScannerCountry(getCurrentPlayerOwnedCountries()));
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
		/*
		 * console will print out the players occupied countries(Console)
		 * -----------Maybe? console will prompt: Which country would you like
		 * to attack from?(Console) After user input, print out borders of
		 * chosen country(Console) console will prompt: Which country would you
		 * like to attack? (Console) console will prompt: [blitz] [normal]
		 * etc... (Console) attackingFromCountry.attack(countryBeingAttacked) If
		 * not conquered. Console prompt: attack again? console prompt: option
		 * to move troops (Console) moveTroops(moveToCountryConquered) ---------
		 * (Board/Country) ??
		 * 
		 * TO BE CONTINUED
		 */

	}

	// TO DO
	private void fortify() {
		printBoardState();
		console.println("--------- Fortify --------- \n");
		if (console.getScannerCommand(commands.getFortifyCmds()).equalsIgnoreCase("fortify")) {

		}

	}

	// TO DO
	private void endTurn() {
		printBoardState();
	}

	// TO DO
	private void moveTroops() {
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
					playerOwnedCountries.add(new Country(c));
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
		int playerOwnedCountryNum = getCurrentPlayerOwnedCountries().size();
		return playerOwnedCountryNum; // Still have to loop through continents
		// to see if bonus is applicable.
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
