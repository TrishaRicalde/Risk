package com.Gui;

import java.util.ArrayList;

public class Command {
	private ArrayList<String> draftCmds;
	private ArrayList<String> attackCmds;
	private ArrayList<String> fortifyCmds;
	

	public Command() {
		initCommands();
	}
	
	private void initCommands() {
		draftCmds = new ArrayList<String>();
		attackCmds = new ArrayList<String>();
		fortifyCmds = new ArrayList<String>();
		
		draftCmds.add("Draft");
		draftCmds.add("Next Phase");

		
		attackCmds.add("Attack");
		attackCmds.add("Next Phase");
		
		fortifyCmds.add("Fortify");
		fortifyCmds.add("End Turn");
	}
	
	public ArrayList<String> getDraftCmds() {
		return draftCmds;
	}
	
	public ArrayList<String> getAttackCmds() {
		return attackCmds;
	}
	
	public ArrayList<String> getFortifyCmds() {
		return fortifyCmds;
	}
	
	
}
