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
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
	private boolean globeSelected;

	public InteractivePane(Board board, Map map) {
		this.map = map;
		this.board = board;
		this.activePopup = false;
		
		
		setCountries(map);

		bottomDisplay = new HBox(50);
		bottomDisplay.setAlignment(Pos.CENTER);
		bottomDisplay.setPrefWidth(Double.MAX_VALUE);
		

		this.setBottom(bottomDisplay);
		this.hideBottomDisplay();

		initButtons();
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

	private void initButtons() {
		initNextPhaseButton();
		initGlobeButton();
	}
	
	private void initNextPhaseButton() {
		Button btnNextPhase = new Button("Next");
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
	
	private void initGlobeButton() {
		Button btnGlobe = new Button("");
		btnGlobe.setAlignment(Pos.BOTTOM_RIGHT);
		globeSelected = false;
		Image darkGlobe = new Image("GlobeNonColoured.png");
		Image colourGlobe = new Image("GlobeColoured.png");
		btnGlobe.setGraphic(new ImageView(darkGlobe));
		btnGlobe.setPadding(Insets.EMPTY);
		

		btnGlobe.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				globeSelected = !globeSelected;
				if (globeSelected) {
					btnGlobe.setGraphic(new ImageView(colourGlobe));
					
				} else {
					btnGlobe.setGraphic(new ImageView(darkGlobe));
				}				
			}			
		});
		bottomDisplay.getChildren().add(bottomDisplay.getChildren().size(), btnGlobe);
	}

	private void initLabels() {
		phaseLbl = new Label("" + board.getPhase());
		turnLbl = new Label("");
		phaseLbl.setTextFill(Color.WHITE);
		turnLbl.setTextFill(Color.WHITE);
		Region filler = new Region();
		HBox.setHgrow(filler, Priority.ALWAYS);
		Button btnFiller = new Button("Filler");
		btnFiller.setMinWidth(50);
		btnFiller.setMinHeight(50);
		btnFiller.setVisible(false);
		bottomDisplay.getChildren().add(0, btnFiller);
		bottomDisplay.getChildren().add(1, filler);
		bottomDisplay.getChildren().add(2, turnLbl);
		bottomDisplay.getChildren().add(3, phaseLbl);
	}

	public void initDraftPopup() {

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
	public void draftPopup(int numTroops) {
		draftPopup = new Stage();
		TroopBox hBox = new TroopBox(numTroops);

		hBox.getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// switch to next phase if there are no troops left
				if ((int) hBox.getCBox().getValue() == board.currentPlayer.getBonusTroops())
					board.nextPhase();

				Country selectedCountry = board.mapController.getSelectedCountry1();
				board.draftBonusTroops(selectedCountry, (int) hBox.getCBox().getValue());
				draftPopup.close();
				board.resetSelected();
				board.mapController.clear();
				// Transition here

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
