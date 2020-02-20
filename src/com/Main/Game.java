package com.Main;

import com.Board.Board;
import com.Player.Player;

public class Game {

	private Board board;
	
	private static boolean running = false;
	
	public Game() {
		board = new Board();						
	}
	
	public void start() {
		running = true;
		run();
	}
	
	public void stop() {
		running = false;
	}
	
	public void run() {
		while (running) {
			for(Player p: board.getPlayers()) {
				board.playerTurn(p);
				stop();
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
	}
}
