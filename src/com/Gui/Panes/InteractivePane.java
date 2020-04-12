package com.Gui.Panes;

import java.util.ArrayList;

import com.Board.BattleReport;
import com.Board.Board;
import com.Board.MapController;
import com.Board.Phase;
import com.Board.Map.Continent;
import com.Board.Map.Country;
import com.Board.Map.Map;
import com.Gui.Effects.Effects;
import com.Gui.Panes.Popup.AttackPopup;
import com.Gui.Panes.Popup.DraftPopup;
import com.Gui.Panes.Popup.InstructionPopup;
import com.Gui.Panes.Popup.MoveTroopPopup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
	private Polygon mapBlocker;
	private DraftPopup draftPopup;
	private AttackPopup atkPopup;
	private InstructionPopup instructPopup;
	private Stage currentPopup;
	private ArrayList<Button> buttons;
	private Button instructionBtn;
	private Button btnNextPhase;
	private Button btnGlobe;

	public InteractivePane(Board board, Map map) {
		this.map = map;
		this.board = board;
		this.effects = new Effects();
		buttons = new ArrayList<Button>();
		
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
	
	/**
	 * Creates a Pop-up which contains a button which, upon being clicked will
	 * proceed with a Players attack.
	 */
	public void attackPopup() {
		atkPopup = new AttackPopup(board);
		currentPopup = atkPopup;
		if(board.currentPlayer.getIsAI() == false) {
			addMapBlocker();
			atkPopup.show();
		}
	}
	
	/**
	 * Creates a Pop-up asking how many troops the Player wishes to move.
	 * @param maxTroops
	 */
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
		instructPopup.setOnCloseRequest(e-> {
			instructionSelected = false;
			System.out.println("hi");
		});
	}
	
	/**
	 * Disables all buttons except btn.
	 * @param btn the button excluded from being disabled.
	 */
	private void disableButtons(Button btn) {
		for (Button b : buttons) {
			if (!b.equals(btn)) b.setDisable(true);
		}
	}
	
	/**
	 * Enables all the buttons. If the current phase is draft, then the Next button
	 * is disabled.
	 */
	private void enableButtons() {
		for (Button b : buttons) {
			b.setDisable(false);
		}		
		if (board.getPhase() == Phase.DRAFT) btnNextPhase.setDisable(true);
	}
	
	//------------------------------------------INITIALIZATION METHODS------------------------------------------------------------------
	
	/**
	 * Initializes all the buttons and adds them to the ArrayList
	 * of buttons.
	 */
	private void initButtons() {
		initNextPhaseButton();
		initGlobeButton();
		initInstructionButton();
		buttons.add(btnGlobe);
		buttons.add(btnNextPhase);
		buttons.add(instructionBtn);
	}
	
	/**
	 * Initializes all the troop labels for each Country.
	 */
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
	
	/**
	 * Initializes all the Labels.
	 */
	private void initLabels() {
		initTroopLabels();
		phaseLbl = new Label("" + board.getPhase());
		turnLbl = new Label("");
		phaseLbl.setTextFill(Color.WHITE);
		turnLbl.setTextFill(Color.WHITE);
		Region filler = new Region();
		HBox.setHgrow(filler, Priority.ALWAYS);
		bottomDisplay.getChildren().add(1, filler);
		bottomDisplay.getChildren().add(2, turnLbl);
		bottomDisplay.getChildren().add(3, phaseLbl);
	}
	
	/**
	 * Initializes the Globe button.
	 */
	private void initGlobeButton() {
		btnGlobe = new Button("");
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
					disableButtons(btnGlobe);
					board.getLabelLayer().setVisible(false);
					showContinentsCover();
				} else {
					btnGlobe.setGraphic(darkGlobe);
					enableButtons();
					board.getLabelLayer().setVisible(true);
					removeContinentsCover();
				}				
			}
		});
		bottomDisplay.getChildren().add(bottomDisplay.getChildren().size(), btnGlobe);
	}
	
	/**
	 * Initializes the Instruction button. This button creates a Pop-up which
	 * displays information on how to play the game.
	 */
	private void initInstructionButton()
	{
		instructionBtn = new Button();
		
		instructionSelected = false;
		instructionBtn.setText("");
		
		ImageView darkInstruction = new ImageView(new Image("scroll_bw.png"));
		ImageView colorInstructions = new ImageView(new Image("scroll.png"));
		
		instructionBtn.setGraphic(darkInstruction);
		instructionBtn.setPadding(Insets.EMPTY);
		instructionBtn.setId("globe");
		
		instructionBtn.setAlignment(Pos.BOTTOM_LEFT);
		bottomDisplay.getChildren().add(0,instructionBtn);
		
		instructionBtn.setOnAction(new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent event) 
			{				
				instructionSelected = !instructionSelected;
				if (instructionSelected) {					
				    colorInstructions.setEffect(effects.getEffect("borderGlow"));
					instructionBtn.setGraphic(colorInstructions);
					//board.mapController.clear();
					instructionPopup();
					
				} else {
					instructionBtn.setGraphic(darkInstruction);
					instructPopup.close();
				}	
								
			}			
		});
		
	}
	
	/**
	 * Initializes the mapBlocker. The mapBlocker is a semi-transparent polygon which covers the entire game.
	 * MapBlocker is used to close any open Pop-ups when the User clicks on the game.
	 */
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
			if (currentPopup.equals(instructPopup)) {
				this.instructionSelected = false;
				instructionBtn.setGraphic(new ImageView(new Image("scroll_bw.png")));
				
			}
			currentPopup.close();
			board.mapController.clear();
			
			removeMapBlocker();
		});
	}
	
	/**
	 * Initializes btnNextPhase. This button is used to shift the game to the 
	 * Next phase.
	 */
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
