package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class InstructionPopup extends Stage {
	
	private Board board;
	private VBox instructionBox;
	private Label title;
	private Label phaseTitle;
	private TextFlow instructionFlow;
	private Text instructionText;
	private Button next;
	private Button back;
	private int i;
	private Scene scene;

	private String[] phaseArray;
	private String[] instructionArray;

	public InstructionPopup(Board board) {
		
		this.board = board;
		
		
		phaseArray = new String[] { "Objective of the Game", "Gameplay", "Draft Phase", "Attack Phase",
		"Fortify Phase", "Winning the Game" };
		instructionArray = new String[] {
				"The object of the game is to occupy every territory on the board and in doing so, "
						+ "eliminate the other players. The game of world domination!",
				"Very nice", "Bloop", "", "", "The game is over when one player occupues all the territories,"
						+ "Good luck and may the odds be even in your favor" };

		next = new Button();

		//instructionPopup = new Popup();

		instructionBox = new VBox(10);
		instructionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");
		// instructionBox.setVisible(false);

		title = new Label();
		phaseTitle = new Label();
		instructionFlow = new TextFlow();
		instructionText = new Text();

		title.setText("Instructions");
		phaseTitle.setText(phaseArray[i]);
		instructionText.setText(instructionArray[i]);

		next.setText("Next");

		instructionFlow.getChildren().add(instructionText);

		instructionBox.getChildren().add(title);
		instructionBox.getChildren().add(phaseTitle);
		instructionBox.getChildren().add(instructionFlow);
		instructionBox.getChildren().add(next);
		
		initNextBtn();
		this.scene = new Scene(instructionBox, 500, 500);
		this.setScene(scene);
		//instructionPopup.getContent().add(instructionBox);
		//instructionPopup.show(primaryStage);

	}

	public void initNextBtn()
	{
		
		
		next.setOnAction(new EventHandler<ActionEvent>() 
		{
			
			@Override
			public void handle(ActionEvent event) 
			{
				if (i < phaseArray.length - 1)
				{
					i++;
					phaseTitle.setText(phaseArray[i]);
					instructionText.setText(instructionArray[i]);
				}
				else
				{
					next.setDisable(true);
				}
			}
		
		});
	}
}
