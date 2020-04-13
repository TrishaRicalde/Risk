package com.Board;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The Class BattleReportTest.
 */
public class BattleReportTest {
	
	/**
	 * Tests to see if the constructor works properly.
	 */

	@Test
	public void testConstructor() 
	{
		BattleReport br = new BattleReport(9, 7, 3, 5, true);
		
		assertEquals(9, br.getStartingAlliedTroops());
		assertEquals(7, br.getStartingEnemyTroops());
		assertEquals(3, br.getAttackingTroopsLost());
		assertEquals(5, br.getDefendingTroopsLost());
		assertEquals(true, br.isVictorious());

	}
	
	/**
	 *
	 *Checks to see if the get methods and set the set methods
	 *work properly for attack troops lost
	 *
	 */
	@Test
	public void getNSetAttackTroopsLostDefault() 
	{
		BattleReport br = new BattleReport(8, 9, 5, 6, true);
		
		assertEquals(5, br.getAttackingTroopsLost());
		
		br.setAttackingTroopsLost(3);
		
		assertEquals(3, br.getAttackingTroopsLost());
		

	}
	
	/**
	 *
	 *Checks to see if the get methods work and
	 * if you set the attack troops lost to
	 * a negative number, you shouldnt be able to
	 *
	 */
	
	
	@Test
	public void getNSetAttackTroopsLostNegative() 
	{
		BattleReport br = new BattleReport(4, 5, 1, 3, true);
		
		assertEquals(1, br.getAttackingTroopsLost());
		
		br.setAttackingTroopsLost(-1);
		
		assertEquals(1, br.getAttackingTroopsLost());
		

	}
	
	
	
	/**
	 *
	 *Checks to see if the get methods work and
	 * if you set the attack troops to a bigger number
	 * than the troops you started with, you
	 * shouldnt be able to
	 *
	 */
	
	
	@Test
	public void getNSetAttackTroopsLostUpperBound() 
	{
		BattleReport br = new BattleReport(2, 3, 1, 9, true);
		
		assertEquals(1, br.getAttackingTroopsLost());
		
		br.setAttackingTroopsLost(5);
		
		assertEquals(1, br.getAttackingTroopsLost());
		

	}
	
	/**
	 * 
	 * 
	 *Checks to see if the get methods and set the set methods
	 *work properly for defend troops lost
	 *
	 *
	 */
	@Test
	public void getNSetDefendTroopsLostDefault() 
	{
		BattleReport br = new BattleReport(3, 7, 4, 2, true);
		
		assertEquals(2, br.getDefendingTroopsLost());
		
		br.setDefendingTroopsLost(1);
		
		assertEquals(1, br.getDefendingTroopsLost());
		

	}
	
	
	/**
	 *
	 *Checks to see if the get methods work and
	 * if you set the defend troops lost to
	 * a negative number, you shouldnt be able to
	 *
	 */
	
	
	@Test
	public void getNSetDefendTroopsLostNegative() 
	{
		BattleReport br = new BattleReport(6, 7, 2, 4, true);
		
		assertEquals(4, br.getDefendingTroopsLost());
		
		br.setAttackingTroopsLost(-5);
		
		assertEquals(4, br.getDefendingTroopsLost());
		

	}
	
	
	/**
	 *
	 *Checks to see if the get methods work and
	 * if you set the defend troops to a bigger number
	 * than the troops you started with, you
	 * shouldnt be able to
	 *
	 */
	
	
	@Test
	public void getNSetDefendTroopsLostUpperBound() 
	{
		BattleReport br = new BattleReport(7, 9, 6, 5, true);
		
		assertEquals(5, br.getDefendingTroopsLost());
		
		br.setAttackingTroopsLost(9);
		
		assertEquals(5, br.getDefendingTroopsLost());
		

	}
	
	
	
	
	/**
	 * 
	 * check to see if you can get the victorious
	 * and if you can set victorious from true to
	 * false
	 * 
	 */
	@Test
	public void getNSetVictoriousTtoF() 
	{
		BattleReport br = new BattleReport(6, 9, 4, 2, true);
		
		assertEquals(true, br.isVictorious());
		
		br.setVictorious(false);;
		
		assertEquals(false, br.isVictorious());
		

	}
	
	/**
	 * 
	 * 
	 *  check to see if you can get the victorious
	 * and if you can set victorious from false to
	 * true
	 * 
	 * 
	 * 
	 */
	@Test
	public void getNSetVictoriousFtoT() 
	{
		BattleReport br = new BattleReport(6, 9, 4, 2, false);
		
		assertEquals(false, br.isVictorious());
		
		br.setVictorious(true);;
		
		assertEquals(true, br.isVictorious());
		

	}

}
