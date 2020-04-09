package com.Gui.Panes;

import com.Board.BattleReport;
import com.Board.Board;
import com.Board.MapController;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import com.Gui.Effects.Effects;
import com.Gui.Panes.Popup.AttackPopup;
import com.Gui.Panes.Popup.DraftPopup;
import com.Gui.Panes.Popup.InstructionPopup;
import com.Gui.Panes.Popup.MoveTroopPopup;
import com.Gui.Panes.Popup.TroopBox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class InteractivePane extends BorderPane {

	private Map map;
	private Board board;
	private Label phaseLbl;
	private Label turnLbl;
	private HBox bottomDisplay;
	private boolean globeSelected;
	private boolean instructionSelected;
	private ImageView riskContinents;
	private Effects effects;
	private Button btnNextPhase;
	private Polygon mapBlocker;
	private DraftPopup draftPopup;
	private AttackPopup atkPopup;
	private InstructionPopup instructPopup;
	private Stage currentPopup;

	public InteractivePane(Board board, Map map) {
		this.map = map;
		this.board = board;
		this.effects = new Effects();
		
		Image contImage = new Image("Risk_Continents.png");
		riskContinents = new ImageView(contImage);
		
		setCountries(map);

		bottomDisplay = new HBox(50);
		bottomDisplay.setAlignment(Pos.CENTER);
		bottomDisplay.setPrefWidth(Double.MAX_VALUE);
		
		this.setBottom(bottomDisplay);
		this.hideBottomDisplay();
		

		initButtons();
		initLabels(); 
		initMapBlocker();		
		initTroopLabels();
	}

	/**
	 * updates the labels.
	 */
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
	
	/**
	 * Adds the mapBlocker to the pane.
	 */
	private void addMapBlocker()  {
		this.getChildren().add(mapBlocker);
	}
	
	/**
	 * removes the mapBlocker from the pane.
	 */
	public void removeMapBlocker() {
		this.getChildren().remove(mapBlocker);
	}
	
	/**
	 * shows/adds the image of colour-coded continents.
	 */
	private void showContinentsCover() {
		this.getChildren().add(riskContinents);
	}
	
	/**
	 * removes the continents cover image.
	 */
	private void removeContinentsCover() {
		this.getChildren().remove(riskContinents);
	}	
	
	/**
	 * Disables the next button.
	 * @param value
	 */
	public void setDisableNextButton(boolean value) {
		btnNextPhase.setDisable(value);
	}
	
	/**
	 * Updates the panes's Labels.
	 */
	private void updateLabels() {
		phaseLbl.setText("" + board.getPhase());
		turnLbl.setText("" + board.currentPlayer.getPlayerName());
	}

	/**
	 * Creates a pop-up for drafting troops and shows it on the scene.
	 * @param numTroops the number of bonus troops the current player has
	 */
	public void draftPopup(int bonusTroops) {
		draftPopup = new DraftPopup(bonusTroops, board);
		currentPopup = draftPopup;
		addMapBlocker();
		draftPopup.show();
		//this.setMouseTransparent(true);
	}
	
	public void attackPopup() {
		atkPopup = new AttackPopup(board);
		currentPopup = atkPopup;
		if(board.currentPlayer.getIsAI() == false) {
			addMapBlocker();
			atkPopup.show();
		}
	}
	
	public void fortifyPopup(int maxTroops) {
		MoveTroopPopup moveTroops = new MoveTroopPopup(maxTroops, board);
		this.setMouseTransparent(false);
		currentPopup = moveTroops;
		addMapBlocker();
		moveTroops.show();
	}
	
	
	public void instructionPopup()
	{
		instructPopup = new InstructionPopup(board);
		currentPopup = instructPopup;
		addMapBlocker();
		instructPopup.show();
	}
	
	//------------------------------------------INITIALIZATION METHODS------------------------------------------------------------------
	
	private void initButtons() {
		initNextPhaseButton();
		initGlobeButton();
		initInstructionButton();
		/*initAttackButton();*/
	}
	
	private void initTroopLabels() {
		BorderPane pane = new BorderPane();
		pane.setPrefWidth(board.getWidth());
		pane.setPrefHeight(board.getHeight());
		Label l = new Label("Troop");
		pane.getChildren().add(l);
		l.setTranslateX(100.0);
		l.setTranslateY(100.0);
		this.getChildren().add(pane);

		
	}
	
	
	private void initLabels() {
		phaseLbl = new Label("" + board.getPhase());
		turnLbl = new Label("");
		phaseLbl.setTextFill(Color.WHITE);
		turnLbl.setTextFill(Color.WHITE);
		Region filler = new Region();
		HBox.setHgrow(filler, Priority.ALWAYS);
		/*Button btnFiller = new Button("Filler");
		btnFiller.setMinWidth(50);
		btnFiller.setMinHeight(50);
		btnFiller.setVisible(false);
		bottomDisplay.getChildren().add(1, btnFiller);*/
		bottomDisplay.getChildren().add(1, filler);
		bottomDisplay.getChildren().add(2, turnLbl);
		bottomDisplay.getChildren().add(3, phaseLbl);
	}
	
	private void initGlobeButton() {
		Button btnGlobe = new Button("");
		btnGlobe.setAlignment(Pos.BOTTOM_RIGHT);
		globeSelected = false;
		ImageView darkGlobe = new ImageView(new Image("globe_button_bw.png"));
		ImageView colourGlobe = new ImageView(new Image("globe_button.png"));
		btnGlobe.setGraphic(darkGlobe);
		btnGlobe.setPadding(Insets.EMPTY);
		//Css stylesheet id to make the button round.
		btnGlobe.setId("globe");
		
		

		btnGlobe.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				globeSelected = !globeSelected;
				if (globeSelected) {					
				    colourGlobe.setEffect(effects.getEffect("borderGlow"));
					btnGlobe.setGraphic(colourGlobe);
					board.mapController.clear();
					showContinentsCover();
				} else {
					btnGlobe.setGraphic(darkGlobe);
					removeContinentsCover();
				}				
			}
		});
		bottomDisplay.getChildren().add(bottomDisplay.getChildren().size(), btnGlobe);
	}
	
	private void initInstructionButton()
	{
		Button instructionBtn = new Button();
		instructionBtn.setText("Instructions");
		instructionBtn.setAlignment(Pos.BOTTOM_LEFT);
		bottomDisplay.getChildren().add(0,instructionBtn);
		/*
		
		VBox instructionBox = new VBox();
		this.getChildren().add(instructionBox);
		instructionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");
		instructionBox.setVisible(false);
		
		Label instructionTitle = new Label();
		instructionTitle.setText("Instructions");
		
		instructionBox.getChildren().add(instructionTitle);
		*/
		
		
		instructionBtn.setOnAction(new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent event) 
			{				
					instructionPopup();
								
			}			
		});
		
	}
	
	private void initMapBlocker() {
		//clickable shape which covers entire map from mouse events.
		mapBlocker = new Polygon();
		mapBlocker.getPoints().addAll(new Double[] {
				0.0, 0.0, 
				(double) board.getWidth() + 8.0, 0.0,
				(double) board.getWidth() + 8.0, (double) board.getHeight() + 6.0,
				0.0, (double) board.getHeight() + 6.0,
				0.0, 0.0				
		});
		mapBlocker.setOpacity(0.1);
		
		mapBlocker.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			currentPopup.close();
			board.mapController.clear();
			removeMapBlocker();
		});
	}
	
	private void initNextPhaseButton() {
		btnNextPhase = new Button("Next");
		btnNextPhase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				board.nextPhase();
			}
		});
		Region filler = new Region();
		HBox.setHgrow(filler, Priority.ALWAYS);
		bottomDisplay.getChildren().addAll(btnNextPhase, filler);
	}
}
