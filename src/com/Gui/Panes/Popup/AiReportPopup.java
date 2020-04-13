package com.Gui.Panes.Popup;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.Board.Board;


/**
 * The Class AiReportPopup.
 */
public class AiReportPopup {
	
	/** The board. */
	Board board;
	
	/** The name of the ai. */
	String name = "";
	
	/** The draft. */
	String draft = "";
	
	/** The attack. */
	String attack = "";
	
	/** The fortify. */
	String fortify = "";
	
	/**
	 * Instantiates a new ai report popup.
	 *
	 * @param b  -the board
	 * @param newName - the new name
	 * @param events - the events
	 */
	public AiReportPopup(Board b, String newName, ArrayList<String> events) {
		board = b;
		name = newName;
		for (String items : events) {
			if (items.contains("Drafted")) {
				draft = draft + items;
			} else if (items.contains("Captured") || items.contains("Lost")) {
				attack = attack + items;
			} else if (items.contains("Fortified")) {
				fortify = fortify + items;
			}
		}
		this.show();
	}
	
	/**
	 * Show.
	 */
	public void show() {
		Object[] options = { "DRAFT", "ATTACK", "FORTIFY", "EXIT" };
		int input = JOptionPane.showOptionDialog(null, "Click A Button to see the AI's Action on that Turn.", name, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		while (input != 3) {
			if(input == 0) {
				JOptionPane.showMessageDialog(null, draft, name, JOptionPane.INFORMATION_MESSAGE);
			} else if (input == 1) {
				JOptionPane.showMessageDialog(null, attack, name, JOptionPane.INFORMATION_MESSAGE);
			} else if (input == 2) {
				JOptionPane.showMessageDialog(null, fortify, name, JOptionPane.INFORMATION_MESSAGE);
			}
			input = JOptionPane.showOptionDialog(null, "Click A Button to see the AI's Action on that Turn.", name, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		}
	}
}
