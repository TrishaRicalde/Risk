package com.Gui.Panes.Popup;

import com.Board.Board;
import com.Board.Map.Country;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * The Class InstructionPopup.
 * @author alextanasescu
 */
public class InstructionPopup extends Stage {
	
	/** The board. */
	private Board board;
	
	/** The instruction box. */
	private VBox instructionBox;
	
	/** The button box. */
	private HBox buttonBox;
	
	/** The title. */
	private Label title;
	
	/** The phase title. */
	private Label phaseTitle;
	
	/** The instruction flow. */
	private TextFlow instructionFlow;
	
	/** The instruction text. */
	private Text instructionText;
	
	/** The next. */
	private Button next;
	
	/** The back. */
	private Button back;
	
	/** The page of instruction youre on */
	private int i = 0;
	
	/** The scene. */
	private Scene scene;

	/** The phase array. */
	private String[] phaseArray;
	
	/** The instruction array. */
	private String[] instructionArray;

	/**
	 * Instantiates a new instruction popup.
	 *
	 * @param board the board
	 */
	public InstructionPopup(Board board) {
		
		this.board = board;
		this.setResizable(false);
		this.initStyle(StageStyle.TRANSPARENT);
		this.setOpacity(0.95);
		i = 0;
		
		phaseArray = new String[] { "Objective of the Game", "Gameplay", "Draft Phase", "Attack Phase",
		"Fortify Phase", "Winning the Game" };
		instructionArray = new String[] {
				"The object of the game is to occupy every territory on the board and in doing so, "
						+ "eliminate the other players. The game of world domination!",
				"Turns consist of trying to capture enemy territory and defeating "
				+ "your opponent's armies. Successfully winning battles depends on careful planning "
				+ "and bold moves. To win you must attack when the time is right while fortifying your"
				+ " defenses. Each move in Risk is comprised of three phases:"
				+ "\n"
				+ "\n"
				+ "	1. DRAFT: This is where you obtain new armies and can position them strategically\n"
				+ "\n"
				+ "	2. ATTACK: This is where you can attack other territories and take them over \n"
				+ "\n"
				+ "	3. FORTIFY This is where you move soldiers from one territory to another, in order to "
				+ "strengthen your position\n"
				+ "\n", "At the beginning of each turn you will acquire a certain number of troops"
						+ " which you can place in any territory you own, "
						+ "the number of troops you receive depends on a number of factors such as: "
						+ "\n"
						+ "\n"
						+ "	1. The number of territories you occupy\n"
						+ "\n"
						+ "		The number of troops you get for territories is as follows: Number of territories you occupy divided by 3,"
						+ "ignoring any remainder. So if you had 13 territories occupied, you would get 4 troops\n"
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
						+ "\n", "After placing your armies at the beginning of your turn, "
								+ "decide if you wish to attack at this time.\n"
								+ "\n"
								+ "The object of an attack is to capture a territory by defeating all the opposing armies already on it."
								+ " The battle is fought by a roll of the dice. Study the board for a moment. Do you want to attack?\n"
								+ "\n"
								+ "If you choose not to attack, click the next button, you may still fortify your position"
								+ " if you wish. If you decide to attack, you must follow these rules: \n"
								+ "\n"
								+ "	1) You may only attack a territory that's adjacent (touching) to one of your own, "
								+ "or connected to it by a dashed line. Examples: Greenland may attack the Northwest Territory, "
								+ "Ontario, Quebec, and Iceland. North Africa may attack Egypt, Western Europe, and Brazil. "
								+ "At the western and eastern edges of the board, Alaska is considered adjacent to and may attack,"
								+ " Kamchatka.\n"
								+ "\n"
								+ "	2) You must always have at least two armies in the territory you're attacking from.\n"
								+ "\n"
								+ "	3) You may continue attacking one territory until you have eliminated all armies on it,"
								+ " or you may shift your attack from one territory to another, attacking each as often as you want"
								+ " and attacking as many territories as you like during one turn.\n"
								+ "\n"
								+ "To Attack: Click both the territory you're attacking and the one you are attacking from and then attack"
								+ "button.\n"
								+ "\n", "No matter what you have done on your turn, you may end your turn by fortifying your position.\n"
										+ "\n"
										+ "Some players refer to this as the \"free move\".\n"
										+ "\n"
										+ "To fortify your position, move as many armies as you would like from one (and only one) of your"
										+ " territories into one (and only one) of your adjacent territories.\n"
										+ "\n"
										+ "Remember to move troops towards borders where they can help in an attack and leave at least one army behind.\n"
										+ "\n"
										+ "", "The winner is the first player to eliminate every opponent by capturing all 42 territories on the board. "
												+ "Good luck and may the odds be ever in your favor\n"
												+ "\n" };

		next = new Button();
		back = new Button();
		


		//instructionPopup = new Popup();
		buttonBox = new HBox(30);

		instructionBox = new VBox(10);
		instructionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");
		instructionBox.setAlignment(Pos.TOP_CENTER);
		// instructionBox.setVisible(false);

		title = new Label();
		phaseTitle = new Label();
		instructionFlow = new TextFlow();
		instructionText = new Text();

		title.setFont(new Font(25));
		phaseTitle.setFont(new Font(20));
		instructionText.setFont(new Font(15));
		
		title.setText("Instructions");
		title.setAlignment(Pos.TOP_CENTER);
		
		phaseTitle.setText(phaseArray[i]);
		instructionText.setText(instructionArray[i]);
		
		title.setId("instructions");
		phaseTitle.setId("instrunctions");
		instructionFlow.setId("instructions");

		next.setText("Next");
		back.setText("Back");

		instructionFlow.getChildren().add(instructionText);
		
		Region filler = new Region();
		buttonBox.getChildren().add(back);
		buttonBox.getChildren().add(filler);
		buttonBox.getChildren().add(next);
		buttonBox.setHgrow(filler, Priority.ALWAYS);

		instructionBox.getChildren().add(title);
		instructionBox.getChildren().add(phaseTitle);
		instructionBox.getChildren().add(instructionFlow);
		instructionBox.getChildren().add(buttonBox);
		instructionBox.setSpacing(5);
		//
		instructionBox.setMargin(title,new Insets(5,5,5,5));
		//title.setStyle("-fx-alignment: center;");
		instructionBox.setMargin(phaseTitle,new Insets(5,5,5,5));
		//phaseTitle.setStyle("-fx-alignment: center;");
		//instructionBox.setMargin(instructionFlow,new Insets(0,0,0,150));
		//instructionFlow.setStyle("-fx-alignment: center;");
		instructionBox.setMargin(buttonBox,new Insets(5,5,5,5));
		instructionBox.setMargin(instructionFlow,new Insets(5,20,5,20));
		//buttonBox.setStyle("-fx-alignment: center;");
		
		//buttonBox.setMargin(back, new Insets(0,0,0,200));
		
		initButtons();
		this.scene = new Scene(instructionBox, 650, 700);
		this.setScene(scene);
		//instructionPopup.getContent().add(instructionBox);
		//instructionPopup.show(primaryStage);

	}

	/**
	 * Initiates the buttons.
	 */
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
