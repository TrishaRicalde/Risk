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
	// Create Dice??

	public Board() {
		players = new ArrayList<Player>();
		console = new Console();
		earthMap = new Map();
		commands = new Command();
		continents = new ArrayList<Continent>(); // earthMap.getContinents();
													// ----TO BE IMPLEMENTED
		initializePlayers();

	}

	private void createBoard() {

	}

	/**
	 * Return ArrayList of Players
	 * 
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void playerTurn(Player p) {
		currentPlayer = new Player(p);
		console.println("----------------------- Player " + currentPlayer.getPlayerNumber()
				+ "'s Turn -----------------------");
		draft();
		attack();
		fortify();
		endTurn(); // Useful??
	}

	// TO DO
	private void printBoardState() {
		/*
		 * Prints out current state of the board. for (continent: continents)
		 * for (country: countries) prints players troops on each
		 * country/continent
		 */
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
		if (console.getScannerCommand(commands.getAttackCmds()).equalsIgnoreCase("draft")) {
			
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
		if (console.getScannerCommand(commands.getFortifyCmds()).equalsIgnoreCase("draft")) {
			
		}

	}

	// TO DO
	private void endTurn() {
		printBoardState();
	}

	// TO DO
	public void moveTroops() {
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

	public void initializePlayers() {
		int numPlayers = console.getScannerNumOfPlayers();
		numAI = 4 - numPlayers;

		for (int i = 1; i <= numPlayers; i++) {
			players.add(new Player(i));
		}

		currentPlayer = new Player(players.get(0));
	}

	/**
	 * 
	 * @return a copy of the ArrayList of Continents (encapsulated)
	 */
	public ArrayList<Continent> getContinentsList() {
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
	public Country getCountry(String countryName) {
		for (Continent cont : continents) {
			for (Country c : cont.getCountries()) {
				if (c.getName().equalsIgnoreCase(countryName))
					return c;
			}
		}
		return null;
	}

	public int getTroopBonus() {
		int playerOwnedCountryNum = getCurrentPlayerOwnedCountries().size();
		return playerOwnedCountryNum; // Still have to loop through continents
										// to see if bonus is applicable.
	}

}
