package com.Board;

import java.util.ArrayList;

import com.Board.Console.Console;
import com.Board.Continents.Continent;
import com.Player.Player;
import com.Turn.Phase;

public class Board {
	private ArrayList<Continent> continents;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Console console = new Console();
	private Player currentPlayer;
	private Phase currentPhase;
	//Create Dice??
	
	public Board(int numPlayers) {
		for (int i = 0; i < numPlayers; i ++) {			
			players.add(new Player(i));
		}
	}
	
	private void createBoard() {
		
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
		/* get bonus troops(Player)
		 * console will give option to trade in cards(PLayer)
		 * console will print out available countries for player to draft troops(Console) ----------Maybe?
		 * console will prompt: where would you like to place your troops(Console)
		 * Once a country is chosen. Troops will be placed. (Country)
		 * Move to next phase.
		 */
		
	}

	
	//TO DO
	private void attack() {
		printBoardState();
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
		
		
	}

	// TO DO
	private void endTurn() {
		printBoardState();		
		
	}
	
	//TO DO
	public void moveTroops(){}
	
	public int initializePlayers() {
		return console.getScannerNumOfPlayers();
	}
	
}

