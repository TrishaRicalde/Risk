package com.Board;


/**
 * The Class BattleReport.
 */
public class BattleReport {

	/** The start allied troops. */
	private int startAlliedTroops;
	
	/** The start enemy troops. */
	private int startEnemyTroops;
	
	/** The attacking troops lost. */
	private int attackingTroopsLost;
	
	/** The defending troops lost. */
	private int defendingTroopsLost;
	
	/** If its victorious or not. */
	private boolean victorious;
	
	
	/**
	 * Instantiates a new battle report.
	 *
	 * @param startAlliedTroops - the start allied troops
	 * @param startEnemyTroops - the start enemy troops
	 * @param atkTroopsLost - the atk troops lost
	 * @param dfndTroopsLost - the dfnd troops lost
	 * @param won -  the win
	 */
	public BattleReport(int startAlliedTroops, int startEnemyTroops, int atkTroopsLost, int dfndTroopsLost, boolean won) {
		this.startAlliedTroops = startAlliedTroops;
		this.startEnemyTroops = startEnemyTroops;
		this.attackingTroopsLost = atkTroopsLost;
		this.defendingTroopsLost = dfndTroopsLost;
		this.victorious = won;
		
	}

	
	

	
	
	/**
	 * Gets the starting allied troops.
	 *
	 * @return the starting allied troops
	 */
	public int getStartingAlliedTroops() {
		return startAlliedTroops;
	}
	
	/**
	 * Gets the starting enemy troops.
	 *
	 * @return the starting enemy troops
	 */
	public int getStartingEnemyTroops() {
		return startEnemyTroops;
	}
	
	/**
	 * Gets the attacking troops lost.
	 *
	 * @return the attacking troops lost
	 */
	public int getAttackingTroopsLost() {
		return attackingTroopsLost;
	}

	/**
	 * Sets the attacking troops lost.
	 *
	 * @param attackingTroopsLost-  the new number of attacking troops lost
	 */
	public void setAttackingTroopsLost(int attackingTroopsLost) 
	{
		
		if (attackingTroopsLost > 0 && attackingTroopsLost <= startEnemyTroops)
		{
			this.attackingTroopsLost = attackingTroopsLost;

		}
	}

	/**
	 * Gets the defending troops lost.
	 *
	 * @return the defending troops lost
	 */
	public int getDefendingTroopsLost() {
		return defendingTroopsLost;
	}

	/**
	 * Sets the defending troops lost.
	 *
	 * @param defendingTroopsLost - the new number of defending troops lost
	 */
	public void setDefendingTroopsLost(int defendingTroopsLost) 
	{
		if (defendingTroopsLost > 0 && defendingTroopsLost <= startAlliedTroops)
		{
			this.defendingTroopsLost = defendingTroopsLost;
		}
		
	}

	/**
	 * Checks if is victorious.
	 *
	 * @return true, if is victorious and false other
	 */
	public boolean isVictorious() {
		return victorious;
	}

	/**
	 * Sets the victorious.
	 *
	 * @param victorious - the victorious to either true or false
	 */
	public void setVictorious(boolean victorious) {
		this.victorious = victorious;
	}
	
	
}
