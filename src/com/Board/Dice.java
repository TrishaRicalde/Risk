package com.Board;

import java.util.Random;


/**
 * The Class Dice.
 */
public class Dice {
	
	/** a random number. */
	private Random randomNumber;
	
	/**
	 * Instantiates a new dice.
	 */
	public Dice() {
		randomNumber = new Random();
	}

	/**
	 * Roll.
	 *
	 * @return a random number between 1 and 6 inclusive.
	 */
	public int roll() {
		return randomNumber.nextInt(6) + 1;
	}

	
	/**
	 * Generates numbers between 1 and 6 inclusive, based on the amount of troops.
	 *
	 * @param numTroops the num troops
	 * @return the number of rolls
	 */
	public int[] getRolls(int numTroops) {
		int numRolls = 0;
		if (numTroops >= 3) {
			numRolls = 3;
		} else if (numTroops == 2){
			numRolls = 2;
		} else {
			numRolls = 1;
		}
		
		int[] rolls = new int[numRolls];
		for (int i = 0; i < numRolls; i++) {
			rolls[i] = roll();
		}
		return rolls;
	}

	
	/**
	 * Check if victorious.
	 *
	 * @param atckRolls - the number the atck rolls
	 * @param dfdRolls the number the dfd rolls
	 * @return true - if atckRolls contains an int greater than the ints in dfdRolls.
	 */
	public boolean checkIfVictorious(int[] atckRolls, int[] dfdRolls) {
		boolean victorious = false;
		for (int i : atckRolls) {
			for (int j : dfdRolls) {
				if (i > j) {
					victorious = true;
				}
			}
		}
		return victorious;
	}


}
