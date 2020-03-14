package com.Gui.Panes;

import com.Board.Board;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import com.Gui.Panes.Popup.TroopBox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
	private Stage draftPopup;

	
	public InteractivePane(Board board, Map map) {
		this.map = map;
		this.board = board;
		this.activePopup = false;
		setCountries(map);
		
		bottomDisplay = new HBox(50);
		bottomDisplay.setAlignment(Pos.CENTER);
		
		this.setBottom(bottomDisplay);
		this.hideBottomDisplay();
		
		initNextPhaseBtn();
		initLabels();

		this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if (activePopup) {
				draftPopup.close();
				activePopup = false;
			}
		});
		
		
	}
	
	public void update() {
		
		this.updateLabels();
		this.requestLayout();
	}
	
	public void showBottomDisplay() {
		update();
		bottomDisplay.setVisible(true);
	}
	
	public void hideBottomDisplay() {
		bottomDisplay.setVisible(false);
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
				//updateLabels();
			}
			
		});
		bottomDisplay.getChildren().add(btnNextPhase);
		
		Button btnGlobe = new Button("Globe");
		btnGlobe.setMinWidth(50);
		btnGlobe.setMinHeight(50);
		btnGlobe.setAlignment(Pos.BOTTOM_RIGHT);
		bottomDisplay.getChildren().add(btnGlobe);
				

	}
	
	private void initLabels() {
		phaseLbl = new Label("" + board.getPhase());
		turnLbl = new Label("");
		phaseLbl.setTextFill(Color.WHITE);
		turnLbl.setTextFill(Color.WHITE);
		bottomDisplay.getChildren().add(0, phaseLbl);
		bottomDisplay.getChildren().add(0, turnLbl);		
	}
	
	public void initDraftPopup() {
		
	}
	
	private void updateLabels() {
		phaseLbl.setText("" + board.getPhase());
		turnLbl.setText("" + board.currentPlayer.getPlayerName());	
	}
	
	

	public void draftPopup(int numTroops) {
		
		draftPopup = new Stage();
		TroopBox hBox = new TroopBox(numTroops);
		/*HBox hBox = new HBox();
		ComboBox<Integer> cBox = new ComboBox<Integer>();
		Label lblInfo = new Label("Troops");
		Button btnConfirm = new Button("Confirm")*/;
		
		hBox.getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//switch to next phase if there are no troops left
				if ((int) hBox.getCBox().getValue() == board.currentPlayer.getBonusTroops()) board.nextPhase();
				
				Country selectedCountry = board.mapController.getSelectedCountry1();
				board.draftBonusTroops(selectedCountry, (int) hBox.getCBox().getValue());
				draftPopup.close();
				board.resetSelected();
				board.mapController.clear();
				//Transition here
				
	        	setPaneMouseTransparent(false);
			}
			
		});
               
        Scene dialogScene = new Scene(hBox, 180, 25);
        
        draftPopup.setOnCloseRequest(event -> {
        	board.resetSelected();
        	this.setMouseTransparent(false);
        });
        draftPopup.setScene(dialogScene);
        draftPopup.setTitle("Draft");
        draftPopup.setOpacity(0.9);
        draftPopup.setResizable(false);
        draftPopup.setAlwaysOnTop(true);
        draftPopup.show();
        activePopup = true;
        this.setMouseTransparent(true);
	}
	
	public void initDraftBtn() {
		Button draftBtn = new Button("Draft");
		
	}
	
	public void setPaneMouseTransparent(boolean value) {
		this.setMouseTransparent(value);
	}
	

}
