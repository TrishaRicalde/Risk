package com.Board;

import java.util.Random;

public class Dice {

	
	 // RANDOM NUMBER GENERATOR
	private int faceValue;
	 
	public Dice() 
	{
		Random randomNumber = new Random();
		faceValue = randomNumber.nextInt(6) + 1;
	}
	
	//generate rand. number
	public int roll() 
	{
		Random randomNumber = new Random();
		faceValue = randomNumber.nextInt(6) + 1;
		
		return faceValue;
	}
	
	//return true if Dice.isHigher(otherDice.number)
	public boolean isHigher(int i) 
	{
		Dice oneDie = new Dice();
		boolean isHigher = false;
		
		if (oneDie.faceValue > i)
			isHigher = true;
		return isHigher;
	
	}
	
	public void blitz(int attackingTroops, int defendingTroops) 
	{
		//Still not quite sure about this
	}
	
	public int getTroopsdifference(int attackingTroops, int defendingTroops) 
	{
		int troopDifference;
		if (defendingTroops > attackingTroops)
		{
			troopDifference = defendingTroops - attackingTroops;
		}
		else
		{
			troopDifference = attackingTroops - defendingTroops;
		}
		
		return troopDifference;
	}
	
}
