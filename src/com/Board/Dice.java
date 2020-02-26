package com.Board;

import java.util.Random;

public class Dice {
	
	private Random randomNumber;
	
	public Dice() {
		randomNumber = new Random();
	}

	/**
	 * 
	 * @return a random number between 1 and 6 inclusive.
	 */
	public int roll() {
		return randomNumber.nextInt(6) + 1;
	}

	
	/**
	 * Generates numbers between 1 and 6 inclusive, based on the amount of troops
	 * @param numOfTroops
	 * @return
	 */
	public int[] getRolls(int numTroops) {
		int numRolls = 0;
		if (numTroops >= 3) {
			numRolls = 3;
		} else {
			numRolls = 2;
		}
		
		int[] rolls = new int[numRolls];
		for (int i = 0; i < numRolls; i++) {
			rolls[i] = roll();
		}
		return rolls;
	}

	
	/**
	 * 
	 * @param atckRolls
	 * @param dfdRolls
	 * @return true if atckRolls contains an int greater than the ints in dfdRolls.
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
