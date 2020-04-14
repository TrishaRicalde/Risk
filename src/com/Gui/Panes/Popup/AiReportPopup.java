package com.Gui.Panes.Popup;


import java.util.ArrayList;
import com.Board.Board;
import com.Gui.Panes.InteractivePane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AiReportPopup extends Stage {
	private Button draftButton;
	private Button attackButton;
	private Button fortifyButton;
	private Button back;
	private Button cont;
	private String name;
	private ArrayList<String> events;
	private Board board;
	private InteractivePane interactPane;
	
	public AiReportPopup(Board b, String inName, ArrayList<String> inList) {
		name = inName;
		events = inList;
		board = b;
		
		Label title = new Label();
		title.setText(b.getCurrentPlayerName());
		title.setStyle("-fx-font-size: 24px;");
		title.setPadding(new Insets(5,5,5,5));
		 
		draftButton = new Button("Draft");
		attackButton = new Button("Attack");
		fortifyButton = new Button("Fortify");
		back = new Button("Back");
		cont = new Button("Continue");
		
		draftButton.setPrefSize(80, 20);
		attackButton.setPrefSize(80, 20);
		fortifyButton.setPrefSize(80, 20);
		back.setPrefSize(80, 20);
		
		HBox container = new HBox(draftButton, attackButton, fortifyButton, cont);
		VBox frame = new VBox(title, container);
				
		container.setSpacing(1.0);
		Scene buttonMenu = new Scene(frame, 300, 100);
		
		draftButton.setPadding(new Insets(5,5,5,5));
		attackButton.setPadding(new Insets(5,5,5,5));
		fortifyButton.setPadding(new Insets(5,5,5,5));
		back.setPadding(new Insets(5,5,5,5));
		cont.setPadding(new Insets(5,5,5,5));
		
		this.setScene(buttonMenu);
		this.setOpacity(0.9);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		onButtonClick();
	}
	
	public void onButtonClick() {
		draftButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				draft();
			}
		});
		attackButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				attack();
			}
		});
		fortifyButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fortify();
			}
		});
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AiReportPopup aiReport = new AiReportPopup(board, name, events);
				aiReport.show();
				close();
			}
		});
		cont.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				events.clear();
				board.nextPhase();
				close();
			}
		});
		
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				AiReportPopup aiReport = new AiReportPopup(board, name, events);
				aiReport.show();
				close();
			}
		});
	}
	
	public void draft() {
		String draftAction = "";
		Label title = new Label(name + "'s Draft");
		for(String items : events) {
			if(items.contains("Drafted")) {
				draftAction = draftAction + items;
			}
		}
		Label draftHolderActions = new Label(draftAction);
		VBox draftHolder = new VBox(title, draftHolderActions, back);
		Scene draftScene = new Scene(draftHolder);
		this.setScene(draftScene);
		this.setOpacity(0.9);
		this.setResizable(false);
		this.setAlwaysOnTop(true);	
	}
	
	public void attack() {
		String attackAction = "";
		Label title = new Label(name + "'s Attack");
		for(String items : events) {
			if(items.contains("Capture") || items.contains("Lost")) {
				attackAction = attackAction + items;
			}
		}
		Label attackHolderActions = new Label(attackAction);
		VBox attackHolder = new VBox(title, attackHolderActions, back);
		Scene attackScene = new Scene(attackHolder);
		this.setScene(attackScene);
		this.setOpacity(0.9);
		this.setResizable(false);
		this.setIconified(false);
		this.setAlwaysOnTop(true);	
	}

	public void fortify() {
		String fortifyAction = "";
		Label title = new Label(name + "'s Fortification");
		for(String items : events) {
			if(items.contains("Fortified")) {
				fortifyAction = fortifyAction + items;
			}
		}
		Label fortifyHolderActions = new Label(fortifyAction);
		VBox fortifyHolder = new VBox(title, fortifyHolderActions, back);
		Scene fortifyScene = new Scene(fortifyHolder);
		this.setScene(fortifyScene);
		this.setOpacity(0.9);
		this.setResizable(false);
		this.setAlwaysOnTop(true);	
	}
}
