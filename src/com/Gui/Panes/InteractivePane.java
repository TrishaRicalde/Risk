package com.Gui.Panes;

import com.Board.Board;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InteractivePane extends BorderPane {

	private Map map;
	private Board board;
	private Label phaseLbl;
	private Label turnLbl;
	private HBox bottomDisplay;
	private boolean activePopup;
	private boolean clickFilter;
	private Stage draftPopup;

	
	public InteractivePane(Board board, Map map) {
		this.map = map;
		this.board = board;
		this.activePopup = false;
		this.clickFilter = false;
		setCountries(map);
		
		bottomDisplay = new HBox(50);
		bottomDisplay.setAlignment(Pos.CENTER);
		this.setBottom(bottomDisplay);
		
		initNextPhaseBtn();
		initLabels();
		
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if (activePopup) {
				draftPopup.close();
				activePopup = false;
			}
		});
		
		
	}

	/**
	 * Adds all the country shapes to the Pane.
	 * 
	 * @param map
	 */
	private void setCountries(Map map) {
		
		for (Continent cont : map.getContinents()) {
			for (Country c : cont.getCountries()) {
				try {					
					this.getChildren().add(c.getImageView());
					if (c.getShape() != null) {						
						this.getChildren().addAll(c.getShape());
					}				
				} catch (Exception e) {
					System.out.println("ImageView Error: " + c.getName());
				}
			}
		}
	}

	private void initNextPhaseBtn() {
		Button btnNextPhase = new Button("Next");
		btnNextPhase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				board.nextPhase();	
				board.resetSelected();
				updateLabels();
			}
			
		});
		bottomDisplay.getChildren().add(btnNextPhase);

	}
	
	private void initLabels() {
		phaseLbl = new Label("" + board.getPhase());
		turnLbl = new Label("");
		phaseLbl.setTextFill(Color.WHITE);
		turnLbl.setTextFill(Color.WHITE);
		bottomDisplay.getChildren().add(0, phaseLbl);
		bottomDisplay.getChildren().add(0, turnLbl);		
	}
	
	public void updateLabels() {
		phaseLbl.setText("" + board.getPhase());
		turnLbl.setText("" + board.getCurrentPlayerName());	
	}

	public void draftPopup(int numTroops) {
		
		draftPopup = new Stage();
        /*VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));*/
		HBox hBox = new HBox();
		Label lblPrompt = new Label("Draft Troops: ");
        ComboBox<Integer> dialogCBox = new ComboBox<Integer>();
        for (int i = 1; i <= numTroops; i ++) {
        	dialogCBox.getItems().add(i);
        }
        dialogCBox.setValue(1);
        hBox.getChildren().add(lblPrompt);
        hBox.getChildren().add(dialogCBox);
        Scene dialogScene = new Scene(hBox, 150, 25);
        draftPopup.setScene(dialogScene);
        draftPopup.setOnCloseRequest(event -> {
        	board.resetSelected();
        	this.setMouseTransparent(false);
        });
        draftPopup.show();
        activePopup = true;
        this.setMouseTransparent(true);
	}
	


}
