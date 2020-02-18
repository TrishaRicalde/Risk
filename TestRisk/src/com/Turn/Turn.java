package com.Turn;

import java.util.concurrent.TimeUnit;

import com.Board.Console.Console;
import com.Player.Player;

public class Turn {
	Console console = new Console();
	Player currentPlayer;
	Phase currentPhase;
	
	public Turn() {
		currentPhase = Phase.START;
	}
	

	
	public void turn(Player player) {
		//currentPlayer = new Player(player);
		draft();
		attack();
		fortify();
		endTurn();
		}
	
	private void draft() {
		// TODO Auto-generated method stub
		
	}

	private void attack() {
		// TODO Auto-generated method stub
		
	}

	private void fortify() {
		// TODO Auto-generated method stub
		
	}

	private void endTurn() {
		// TODO Auto-generated method stub
		
	}

	//EXAMPLE USE OF ENUMS	
	public void nextPhase(Phase currentPhase) {
		switch(currentPhase) {
		case START:
			currentPhase = Phase.DRAFT;
		case DRAFT:
			currentPhase = Phase.ATTACK;
		case ATTACK:
			currentPhase = Phase.FORTIFY;
		case FORTIFY:
			currentPhase = Phase.DRAFT;
		default:
			currentPhase = Phase.UNKNOWN;
		}
	}
	
}
