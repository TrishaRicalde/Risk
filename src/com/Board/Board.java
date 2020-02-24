package com.Board;

import java.util.ArrayList;
import java.util.Random;

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
		
		// STARTING TROOPS BELOW
		
		// clone continents list
		ArrayList<Continent> allContinents = new ArrayList<Continent>(continents);
		
		//clone continents list as a new list called currentContinents
		ArrayList<String> currentContinents = new ArrayList<String>();
		for (Continent cont : continents) {
			currentContinents.add(cont.getContinentName());
		}
		
		//ArrayList<Continent> currentContinents = new ArrayList<Continent>(continents);
		
		// clone current country list per continent
		ArrayList<Country> currentNorthAmericaCountries = continents.get(0).getCountries();
		ArrayList<Country> currentSouthAmericaCountries = continents.get(1).getCountries();
		ArrayList<Country> currentEuropeCountries = continents.get(2).getCountries();
		ArrayList<Country> currentAfricaCountries = continents.get(3).getCountries();
		ArrayList<Country> currentAsiaCountries = continents.get(4).getCountries();
		ArrayList<Country> currentAustraliaCountries = continents.get(5).getCountries();
		
		
		// new list, Player's current countries list, there's always going to be 4 players
		int numberOfPlayers = 4;
		int startingTroopsPerPlayer = 100;
		
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
						String chosenContinent = currentContinents.get(randomContinent.nextInt(currentContinents.size()));
						
						// choose random country from the currentCountryList of that continent
						if (chosenContinent == "North America") {
							Random randomCountry = new Random();
							currentCountry = currentNorthAmericaCountries.get(randomCountry.nextInt(currentNorthAmericaCountries.size()));
						}
						if (chosenContinent == "South America") {
							Random randomCountry = new Random();
							currentCountry = currentSouthAmericaCountries.get(randomCountry.nextInt(currentSouthAmericaCountries.size()));
						}
						if (chosenContinent == "Europe") {
							Random randomCountry = new Random();
							currentCountry = currentEuropeCountries.get(randomCountry.nextInt(currentEuropeCountries.size()));
						}
						if (chosenContinent == "Africa") {
							Random randomCountry = new Random();
							currentCountry = currentAfricaCountries.get(randomCountry.nextInt(currentAfricaCountries.size()));
						}
						if (chosenContinent == "Asia") {
							Random randomCountry = new Random();
							currentCountry = currentAsiaCountries.get(randomCountry.nextInt(currentAsiaCountries.size()));
						}
						if (chosenContinent == "Australia") {
							Random randomCountry = new Random();
							currentCountry = currentAustraliaCountries.get(randomCountry.nextInt(currentAustraliaCountries.size()));
						}
						
						int selectTroops = 0;
						// if player number of troops > 2:
						if (currentTroops > 2) {
							// Randomize 1-3 Troops
							Random randomSelectTroops = new Random();
							selectTroops = randomSelectTroops.nextInt(3);
							selectTroops += 1;
							
							// Minus that number from the player's current number of troops
							currentTroops -= selectTroops;
						}
						
						// else
						else {
							// change the number of troops to add as the player's current number of troops
							selectTroops = currentTroops;
							// set player's current number of troops as 0
							currentTroops = 0;
						}
						
						
						// add those troops to that random country
						for (Continent cont : continents) {
				            for (Country country : cont.getCountries()) {
				            	if (country == currentCountry) {
				            		country.addDraftedTroops(selectTroops);
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
					
					// else:
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
							currentCountry.addDraftedTroops(1);
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
					
				}	
				// else:
				else {
					// playerCountWithNoTroops += 1
					playerCountWithNoTroops += 1;
				}
						
			}
			// if playerCountWithNoTroops < numberOfPlayers, playerCountWithNoTroops = 0.
			if (playerCountWithNoTroops < numberOfPlayers) {
				playerCountWithNoTroops = 0;
			}
		}
		
	// END OF STARTING TROOPS ABOVE
	
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

	

}
