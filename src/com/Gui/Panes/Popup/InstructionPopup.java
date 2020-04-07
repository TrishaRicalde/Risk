package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class InstructionPopup extends Stage {
	
	private Board board;
	private VBox instructionBox;
	private HBox buttonBox;
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
				"Turns consist of trying to capture enemy territory and defeating "
				+ "your opponent’s armies. Successfully winning battles depends on careful planning "
				+ "and bold moves. To win you must attack when the time is right while fortifying your"
				+ " defenses. Each move in Risk is comprised of three phases:"
				+ "\n"
				+ "\n"
				+ "	1. DRAFT: This is where you obtain new armies and can position them strategically\n"
				+ "\n"
				+ "	2. ATTACK: This is where you can attack other territories and take them over \n"
				+ "\n"
				+ "	3. FORTIFY This is where you move soldiers from one territory, in order to "
				+ "strengthen your position\n", "At the beginning of each turns you will get a certain number of troops, "
						+ "the number of troops you receive depends on a number of factors such as: "
						+ "\n"
						+ "\n"
						+ "	1. The number of territories you occupy\n"
						+ "\n"
						+ "		The number of troops you get for territories is as follows: Number of territories you occupy,"
						+ "ignoring any remainder so if you had 13 territories occupied, you would get 4 troops\n"
						+ "\n"
						+ "	2. The value of the continents you control\n"
						+ "\n"
						+ "		The number of extra armies is different for each continent:\n"
						+ "\n"
						+ "			Asia: 7\n"
						+ "			North America: 5\n"
						+ "			Europe: 5\n"
						+ "			Africa: 3\n"
						+ "			South America: 2\n"
						+ "			Australia: 2\n"
						+ "", "", "", "The game is over when one player occupues all the territories,"
						+ "Good luck and may the odds be even in your favor" };

		next = new Button();
		back = new Button();

		//instructionPopup = new Popup();
		buttonBox = new HBox(30);

		instructionBox = new VBox(10);
		instructionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");
		// instructionBox.setVisible(false);

		title = new Label();
		phaseTitle = new Label();
		instructionFlow = new TextFlow();
		instructionText = new Text();

		title.setFont(new Font(25));
		phaseTitle.setFont(new Font(20));
		instructionText.setFont(new Font(15));
		
		title.setText("Instructions");
		phaseTitle.setText(phaseArray[i]);
		instructionText.setText(instructionArray[i]);
		
		title.setId("instructions");
		phaseTitle.setId("instrunctions");
		instructionFlow.setId("instructions");

		next.setText("Next");
		back.setText("Back");

		instructionFlow.getChildren().add(instructionText);
		
		buttonBox.getChildren().add(back);
		buttonBox.getChildren().add(next);

		instructionBox.getChildren().add(title);
		instructionBox.getChildren().add(phaseTitle);
		instructionBox.getChildren().add(instructionFlow);
		instructionBox.getChildren().add(buttonBox);
		
		//
		instructionBox.setMargin(title,new Insets(0,0,0,150));
		//title.setStyle("-fx-alignment: center;");
		instructionBox.setMargin(phaseTitle,new Insets(0,0,0,150));
		//phaseTitle.setStyle("-fx-alignment: center;");
		//instructionBox.setMargin(instructionFlow,new Insets(0,0,0,150));
		//instructionFlow.setStyle("-fx-alignment: center;");
		instructionBox.setMargin(buttonBox,new Insets(0,0,0,200));
		//buttonBox.setStyle("-fx-alignment: center;");
		
		//buttonBox.setMargin(back, new Insets(0,0,0,200));
		
		initButtons();
		this.scene = new Scene(instructionBox, 500, 500);
		this.setScene(scene);
		//instructionPopup.getContent().add(instructionBox);
		//instructionPopup.show(primaryStage);

	}

	public void initButtons()
	{
		
		
		next.setOnAction(new EventHandler<ActionEvent>() 
		{
			
			@Override
			public void handle(ActionEvent event) 
			{
				if (i < phaseArray.length - 1)
				{
					next.setDisable(false);
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
		
		back.setOnAction(new EventHandler<ActionEvent>() 
		{
			
			@Override
			public void handle(ActionEvent event) 
			{
				if (i > 0)
				{
					back.setDisable(false);
					i--;
					phaseTitle.setText(phaseArray[i]);
					instructionText.setText(instructionArray[i]);
				}
				else
				{
					back.setDisable(true);
				}
			}
		
		});
	}
}
