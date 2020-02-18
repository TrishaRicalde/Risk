package com.Main;

import java.util.Scanner;

import com.Board.Board;
import com.Board.Console.Console;
import com.Player.Player;
import com.Turn.Turn;

public class Game {
	
	
	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	private static String title = "Risk";
	private Board board;
	private Turn turn = new Turn();
	
	private static boolean running = false;
	
	public Game() {
		board = new Board(board.initializePlayers());						
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
