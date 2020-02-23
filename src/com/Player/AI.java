package com.Player;
import java.util.*;
import com.Board.*;

public class AI {
	private int playerNumber;
	private String[] possibleNames = {"Jefferson", "Billy", "Suzan", "Kong", "Makenzy", "Lora", "Kitten", "Dogo"};
	private String aiName;
	private Board board;// = new Board();
	
	public AI(int inputPlayerNumber, Board b) {
		this.playerNumber = inputPlayerNumber;
		board = new Board(b);
		setPlayerName();
	}
	
	public AI(AI ai) {
		this.playerNumber = ai.playerNumber;
		this.board = ai.board;
		setPlayerName();
	}
	
	public boolean comparePlayerNumber(Player p) {
		return this.getPlayerNumber() == p.getPlayerNumber();
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public void setPlayerName() {
		Random r = new Random();
		aiName = new String(possibleNames[r.nextInt(possibleNames.length)]);
	}
	
	public String getPlayerName() {
		return aiName;
	}
	
	public void aiDraft(Board b) {
		b.getCurrentPlayerOwnedCountries();
		//b.
	}
	
}
