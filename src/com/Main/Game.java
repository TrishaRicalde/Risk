package com.Main;

import java.util.Scanner;

import com.Board.Board;
import com.Board.Console.Console;
import com.Player.Player;

public class Game {

	private Board board;
	
	private static boolean running = false;
	
	public Game() {
		board = new Board();						
	}
	
	public void start() {
		running = true;
	}
	
	public void stop() {
		running = false;
	}
	
	public void run() {
		while (running) {
			for(Player p: board.getPlayers()) {
				
			}
			stop();
		}
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
	}
}
