package com.Player;
import java.util.ArrayList;
import java.util.Random;
import com.Board.Board;
import com.Board.Map.Continent;
import com.Board.Map.Country;


/**
 * The Class Player.
 */
public class Player {
	
	/** The alliance. */
	private Alliance alliance;
	
	/** The player number. */
	private int playerNumber;
	
	/** The possible names. */
	private String[] possibleNames = {"Jefferson Bot", "Billy Bot", "Suzan Bot", "Kong Bot", "Makenzy Bot", "Lora Bot", "Kitten Bot", "Dogo Bot", "Izzy Bot", "Poppy Bot", "Lisa Bot", "John Bot", "Callie Bot", "Shadow Bot", "Whiskers Bot", "Lily Bot", "Charlotte Bot", "Sylvester Bot", "Gamer99 Bot", "Kid Bot", "Alexander_The_Great Bot", "Darcie Bot", "Molly Bot", "Kye Bot", "Evelyn Bot", "Tina Bot", "Christina Bot", "Emmie Bot", "Tanya Bot", "Bryan Bot", "Saif Bot", "Jamal Bot", "Wayne Bot", "Malachi Bot", "Jose Bot", "Leonard Bot", "Owen Bot", "Terry Bot", "Abby Bot", "Lacey Bot", "Lucia Bot", "Felix Bot", "Anisa Bot", "Jessie Bot", "Frankie Bot", "Amber Bot", "Sylvia", "Attila Bot", "Zack Bot", "Colin Bot", "Klaus Bot", "Ash Bot", "Ben Bot", "Russel Bot", "Santiago Bot", "Athenodora Bot", "Jackson Bot", "Rusty Bot", "Green Bot", "Red Bot", "Blue Bot", "Yellow Bot", "White Bot", "Black Bot", "Grey Bot", "Orange Bot", "Zues (God) Bot", "Odin (God) Bot", "Ra (God) Bot"};
	
	/** The player name. */
	private String playerName;
	
	/** The board. */
	private Board board;// = new Board();
	
	/** a bool repping is a player is ai or not */
	private boolean isAI;
	
	/** The bonus troops. */
	private int bonusTroops;

	/**
	 * Instantiates a new player.
	 *
	 * @param playerNumber the player number
	 * @param isai - if a player is ai or not
	 * @param b the board
	 */
	public Player(int playerNumber, boolean isai, Board b) {
		this.playerNumber = playerNumber;
		this.isAI = isai;
		board = new Board(b);
		setAlliance();
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param p - the player being created
	 */
	public Player(Player p) {
		this.playerNumber = p.playerNumber;
		this.isAI = p.isAI;
		this.board = p.board;
	}

	/**
	 * Sets the alliance.
	 */
	private void setAlliance() {
		switch (playerNumber) {
		case 1:
			this.alliance = Alliance.RED;
			break;
		case 2:
			this.alliance = Alliance.GREEN;
			break;
		case 3:
			this.alliance = Alliance.BLUE;
			break;
		case 4:
			this.alliance = Alliance.YELLOW;
			break;
		}
	}


	/**
	 * Checks if is defeated.
	 *
	 * @param board the board
	 * @return true, if is defeated
	 */
	public boolean isDefeated(Board board) {
		boolean defeated = true;
		for (Continent cont : board.getContinents()) {
			for (Country c : cont.getCountries()) {
				if (c.getPlayerOccupantOfCountry() == this.playerNumber) {
					return false;
				}
			}
		}
		return defeated;
	}
	
	
	/**
	 * Check to see if the playerNumber is right
	 *
	 * @param p the player
	 * @return true, if successful
	 */
	public boolean equals(Player p) {
		return this.getPlayerNumber() == p.getPlayerNumber();
	}
	
	/**
	 * Gets the alliance.
	 *
	 * @return the alliance
	 */
	public Alliance getAlliance() {
		return alliance;
	}
	
	/**
	 * Gets the player number.
	 *
	 * @return the player number
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	/**
	 * Sets the player number.
	 *
	 * @param i - the new player number
	 */
	public void setPlayerNumber(int i) {
		this.playerNumber = i;
	}

	/**
	 * Gets the player name.
	 *
	 * @return the player name
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Sets the player name.
	 *
	 * @param name the new player name
	 */
	public void setPlayerName(String name) {
		if(isAI == true) {
			Random r = new Random();
			playerName = new String(possibleNames[r.nextInt(possibleNames.length)]);
		}
		else {
			playerName = name;
		}
	}

	/**
	 * Gets the player name.
	 *
	 * @param PlayerNumber - the player number
	 * @return the player name
	 */
	public String getPlayerName(int PlayerNumber) {
		return playerName;
	}

	/**
	 * Check to see if if is ai or not
	 *
	 * @returnchecks if AI
	 */
	public boolean getIsAI() {
		return isAI;
	}

	/**
	 * Sets the bonus troops.
	 *
	 * @param n the new bonus troops
	 */
	public void setBonusTroops(int n) {
		this.bonusTroops = n;
	}
	
	/**
	 * Gets the bonus troops.
	 *
	 * @return the bonus troops
	 */
	public int getBonusTroops() {
		return bonusTroops;
	}
	
	/**
	 * Subtract bonus troops.
	 *
	 * @param numToSubtract the number of troops to subtract
	 */
	public void subtractBonusTroops(int numToSubtract) {
		bonusTroops -= numToSubtract;
	}
	
	/**
	 * Gets the country to add troops.
	 *
	 * @param b the board
	 * @param c the country
	 * @return the country to add troops
	 */
	public Country getCountryToAddTroops(Board b, ArrayList<Country> c) {
		ArrayList<Continent> continents = b.getContinents();
		int num1 = 0;
		Random random = new Random();
		Continent contWithMostTroops = continents.get(0);
		Country temp = contWithMostTroops.getCountries().get(random.nextInt(contWithMostTroops.getCountries().size()));
		while(temp.getPlayerOccupantOfCountry() != this.playerNumber) {
			for(Continent cont : continents) {
				int contTroopNum = 0;
				for(Country country : cont.getCountries()) {
					if(country.getPlayerOccupantOfCountry() == this.playerNumber) {
						contTroopNum = contTroopNum + country.getNumTroops();
					}
					if(contTroopNum > num1) {
						contWithMostTroops = cont;
						num1 = contTroopNum;
					}
				}
				temp = contWithMostTroops.getCountries().get(random.nextInt(contWithMostTroops.getCountries().size()));
			}
		}
		return temp;
	}

	/**
	 * Gets the attack choice.
	 *
	 * @param b the board
	 * @param c the country
	 * @return the attack choice
	 */
	public boolean getAttackChoice(Board b, ArrayList<Country> c) {
		ArrayList<Continent> continents = b.getContinents();
		for(Continent cont : continents) {
			for(Country country : cont.getCountries()) {
				if(country.getPlayerOccupantOfCountry() == this.playerNumber) {
					ArrayList<Country> borders = country.getBorders();
					for(Country bord : borders) {
						if(country.getNumTroops() > bord.getNumTroops() + bord.getNumTroops() * 0.25) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Gets the country to attack from.
	 *
	 * @param b the board
	 * @param c the country
	 * @return the country to attack from
	 */
	public Country getCountryToAttackFrom(Board b, ArrayList<Country> c) {
		ArrayList<Continent> continents = b.getContinents();

		for(Continent cont : continents) {
			for(Country country : cont.getCountries()) {
				if(country.getPlayerOccupantOfCountry() == this.playerNumber) {
					ArrayList<Country> borders = country.getBorders();
					for(Country bord : borders) {
						if(country.getNumTroops() > bord.getNumTroops() + bord.getNumTroops() * 0.25) {
							System.out.println(country.getName());
							return country;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets the country to attack.
	 *
	 * @param b the board
	 * @param c the country
	 * @return the country to attack
	 */
	public Country getCountryToAttack(Board b, ArrayList<Country> c) {
		ArrayList<Continent> continents = b.getContinents();

		for(Continent cont : continents) {
			for(Country country : cont.getCountries()) {
				if(country.getPlayerOccupantOfCountry() == this.playerNumber) {
					ArrayList<Country> borders = country.getBorders();
					for(Country bord : borders) {
						if(country.getNumTroops() > bord.getNumTroops() + bord.getNumTroops() * 0.25) {
							System.out.println(bord.getName());
							return bord;
						}
					}
				}
			}
		}
		return null;
	}
}
