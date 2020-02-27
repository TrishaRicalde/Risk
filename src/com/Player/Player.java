package com.Player;
import java.util.ArrayList;
import java.util.Random;

import com.*;
import com.Board.Board;
import com.Board.Map.Continent;
import com.Board.Map.Country;

public class Player {
	private Alliance alliance;
	private int playerNumber;
	private String[] possibleNames = {"Jefferson Bot", "Billy Bot", "Suzan Bot", "Kong Bot", "Makenzy Bot", "Lora Bot", "Kitten Bot", "Dogo Bot", "Izzy Bot", "Poppy Bot", "Lisa Bot", "John Bot", "Callie Bot", "Shadow Bot", "Whiskers Bot", "Lily Bot", "Charlotte Bot", "Sylvester Bot", "Gamer99 Bot", "Kid Bot", "Alexander_The_Great Bot"};
	private String playerName;
	private Board board;// = new Board();
	private boolean isAI;

	public Player(int playerNumber, boolean isai, Board b) {
		this.playerNumber = playerNumber;
		this.isAI = isai;
		System.out.println(isAI);
		System.out.println(playerNumber);
		board = new Board(b);
		setAlliance();
	}

	public Player(Player p) {
		this.playerNumber = p.playerNumber;
		this.isAI = p.isAI;
		this.board = p.board;
	}

	private void setAlliance() {
		switch (playerNumber) {
		case 1:
			this.alliance = Alliance.RED;
		case 2:
			this.alliance = Alliance.BLUE;
		case 3:
			this.alliance = Alliance.GREEN;
		case 4:
			this.alliance = Alliance.YELLOW;
		}
	}


	
	public boolean equals(Player p) {
		return this.getPlayerNumber() == p.getPlayerNumber();
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public void setPlayerName(String name) {
		if(isAI == true) {
			Random r = new Random();
			playerName = new String(possibleNames[r.nextInt(possibleNames.length)]);
		}
		else {
			playerName = name;
		}
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public String getPlayerName(int PlayerNumber) {
		return playerName;
	}
	
	public boolean getIsAI() {
		return isAI;
	}
	
	public Country getCountryToAddTroops(Board b, ArrayList<Country> c) {
		ArrayList<Continent> continents = b.getContinents();
		ArrayList<Country> mostTroops = new ArrayList<Country>();
		int num1 = 0;
		Random random = new Random();
		Continent contWithMostTroops = continents.get(0);
		
		for(Continent cont : continents) {
			int contTroopNum = 0;
			for(Country country : cont.getCountries()) {
				if(country.getPlayerOwnerOfCountry() == this.playerNumber) {
					contTroopNum = contTroopNum + country.getNumTroops();
				}
				if(contTroopNum > num1) {
					contWithMostTroops = cont;
					num1 = contTroopNum;
				}
			}
		}
		return contWithMostTroops.getCountries().get(random.nextInt(contWithMostTroops.getCountries().size()));//.getName();
	}
	
	public int getAiNumberOfTroopsToAdd() {
		return 0;
	}
	
	public int aiDraft(int bonusTroops, Board b) {
		if(isAI == true) {
			
		}
		return 3;
	}
	
	
	public void setPlayerNumber(int i) {
		this.playerNumber = i;
	}

}
