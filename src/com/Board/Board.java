package com.Board;

import java.util.ArrayList;
import java.util.Random;

import com.Board.Console.Console;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import com.Player.Player;


public class Board {
	private ArrayList<Continent> continents;
	private ArrayList<Player> players;
	private Console console;
	private Player currentPlayer;
	private int numAI;
	private Map earthMap;
	//Create Dice??
	
	public Board() {
		players  = new ArrayList<Player>();
		console = new Console();
		earthMap = new Map();
		
		initializePlayers();
						
		
	}
	
	private void createBoard() {
		
		// STARTING TROOPS BELOW
		
		// clone continents list
		
		// clone continents list as a new list called currentContinents
		
		// clone country list per continent
		
		// new list, Player's current countries list, there's always going to be 4 players
		
		int numberOfPlayers = 4;
		int startingTroopsPerPlayer = 100;
		
		int player1Troops = startingTroopsPerPlayer;
		int player2Troops = startingTroopsPerPlayer;
		int player3Troops = startingTroopsPerPlayer;
		int player4Troops = startingTroopsPerPlayer;
		
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
					if (currentContinents.length > 0) {
					
						// choose random currentContinent
						
						// choose random country from the currentCountryList of that continent
				
						// if player number of troops > 2:
							// Randomize 1-3 Troops
							Random selectTroops = new Random(3);
							// Minus that number from the player's current number of troops
							
							
						// else:
							// change the number of troops to add as the player's current number of troops
							// set player's current number of troops as 0
						
						// add those troops to that random country
						// remove that random country from currentCountryList
						// add country to player's current countries list
				
						// if currentCountryList for that continent is empty:
							// remove that continent from currentContinents list
					}
					
					// else:
						// randomize through player's current countries list
						
						// if country's number of troops < 3:
							// add 1 troop to that country
							// remove 1 troop from player's current number of troops
				
				}	
				// else:
					// playerCountWithNoTroops += 1
					playerCountWithNoTroops += 1;
			
			}
			// if playerCountWithNoTroops < numberOfPlayers, playerCountWithNoTroops = 0.
			if (playerCountWithNoTroops < numberOfPlayers) {
				playerCountWithNoTroops = 0;
			}
		}
		
	// END OF STARTING TROOPS ABOVE
		
	}
	
	/**
	 * Return ArrayList of Players
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		ArrayList<Player> player = new ArrayList<Player>();
		for(Player p: players) {
			player.add(new Player(p));
		}
		return player;
	}

	
	public void playerTurn(Player p) {
		currentPlayer = new Player(p);
		console.println("----------------------- Player " + currentPlayer.getPlayerNumber() + " -----------------------");
		draft();
		attack();
		fortify();
		endTurn(); //Useful??
	}

	//TO DO
	private void printBoardState() {
		/*
		 Prints out current state of the board.
		 for (continent: continents)
		 	for (country: countries) prints players troops on each country/continent		  
		 */
	}
	
	
	//TO DO
	private void draft() {
		printBoardState();
		console.println("--------- Draft ---------");
		int bonusTroops = currentPlayer.getTroopBonus();
		console.println("Player " + currentPlayer.getPlayerNumber() + " has " + bonusTroops + " bonus troops.");
		//option to trade in cards --------TO BE IMPLEMENTED
		//console will print out available countries for player to draft troops(Console)--------TO BE IMPLEMENTED
		console.println("Where would you like to place your troops?");

		
	}

	
	//TO DO
	private void attack() {
		printBoardState();
		console.println("--------- Attack ---------");
		/* console will print out the players occupied countries(Console) -----------Maybe?
		 * console will prompt: Which country would you like to attack from?(Console)
		 * After user input, print out borders of chosen country(Console)
		 * console will prompt: Which country would you like to attack? (Console)
		 * console will prompt: [blitz] [normal] etc...  (Console)
		 * attackingFromCountry.attack(countryBeingAttacked)
		 * If not conquered. Console prompt: attack again?
		 * console prompt: option to move troops (Console)
		 * moveTroops(moveToCountryConquered) --------- (Board/Country) ??
		 * 
		 * TO BE CONTINUED
		 */
		
	}

	// TO DO
	private void fortify() {
		printBoardState();
		console.println("--------- Fortify ---------");
		
		
	}

	// TO DO
	private void endTurn() {
		printBoardState();				
	}
	
	//TO DO
	public void moveTroops(){}
	
	public void initializePlayers() {
		int numPlayers = console.getScannerNumOfPlayers();
		numAI = 4 - numPlayers;
		
		for (int i = 0; i < numPlayers; i ++) {			
			players.add(new Player(i));
		}
		
		currentPlayer = new Player(players.get(0));
	}
	
	public ArrayList<Country> getPlayerCountries(Player p) {
		ArrayList<Country> playerCountries = new ArrayList<Country>();
		
		return null;
		
	}
	
}

