package com.Gui.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import com.Board.Board;
import com.Board.Map.Map;
import com.Gui.Panes.InteractivePane;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.HBox;

import javafx.util.Duration;


/**
 * The Class Game.
 * 
 * @author alextanasescu
 */
public class Game extends Application 
{
	
	/** The Constant name. */
	private static final String name = "Risk";
	
	/** The Constant width. */
	private static final int width = 54 * 16;
	
	/** The Constant height. */
	private static final int height = 54 * 9;

	/** The Constant mapImage. */
	private static final Image mapImage = new Image("Risk_Main.png"); 
	
	/** The Constant titleScreen. */
	private static final Image titleScreen = new Image("Risk_Title.gif");

	/** The board. */
	private Board board;

	/** The num of players. */
	private int numOfPlayers = 0;
	
	/** The names. */
	private ArrayList<String> names;
	
	
	/** The music file */
	private String musicFile;
	
	/** The sound */
	private Media sound;
	
	/** The media player */
	private MediaPlayer player;


	

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception - the exception being thrown
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		board = new Board(width, height);
		ImageView imageview = new ImageView(mapImage);
		ImageView titleview = new ImageView(titleScreen);
		
		
		musicFile = "Pirate Music - Cut.mp3";
		sound = new Media(new File(musicFile).toURI().toString());
		player = new MediaPlayer(sound);
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.play();
		

		StackPane startScreen = new StackPane();
		
		StackPane stack = new StackPane();
		for (Pane p : board.getPanes()) {
			stack.getChildren().add(p);
		}

		Button instruction = new Button();
		Button start = new Button();
		Button next = new Button();
		Button secondNext = new Button();
		Button play = new Button();
		
		names = new ArrayList<String>();

		Label greeting = new Label("Welcome to Risk, a Game of War");
		Label playerLabel = new Label("Please enter number of players (between 1 and 4):");
		Label numPlayerLabel = new Label();
		Label playerNames = new Label("Enter player names: ");
		Label welcome = new Label("Welcome");
		Label goodLuck = new Label("May the best strategist win. Good luck");

		TextField numPlayers = new TextField();

		Popup startPopUp = new Popup();
		Popup nextPopUp = new Popup();
		Popup finalPopUp = new Popup();

		BorderPane borderPane = new BorderPane();

		
		VBox vbox = new VBox(10);
		VBox vbox2 = new VBox(10);
		VBox vbox3 = new VBox(10);
		VBox textVBox = new VBox(10);

		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");

		vbox2.setPadding(new Insets(10, 10, 10, 10));
		vbox2.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");
		
		vbox3.setPadding(new Insets(10, 10, 10, 10));
		vbox3.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");

		startPopUp.getContent().add(vbox);
		nextPopUp.getContent().add(vbox2);
		finalPopUp.getContent().add(vbox3);
		
		
		vbox.getChildren().add(greeting);
		vbox.getChildren().add(playerLabel);
		vbox.getChildren().add(numPlayers);
		vbox.getChildren().add(next);
		vbox.setMargin(next, new Insets(0, 0, 0, 265));

		vbox2.getChildren().add(numPlayerLabel);
		vbox2.getChildren().add(playerNames);
		vbox2.getChildren().add(textVBox);
		vbox2.getChildren().add(secondNext);
		vbox2.setMargin(secondNext, new Insets(0, 0, 0, 265));
		
		vbox3.getChildren().add(welcome);
		vbox3.getChildren().add(goodLuck);
		vbox3.getChildren().add(play);
		vbox3.setMargin(play, new Insets(0, 0, 0, 265));
		
		start.setOpacity(0.85);
		instruction.setOpacity(0.85);

		start.setText("Start Game");
		next.setText("Next");
		secondNext.setText("Next");
		play.setText("Play");
		instruction.setText("Instructions");

		start.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) {
				if (!startPopUp.isShowing()) {
					startPopUp.show(primaryStage);
					start.setVisible(false);
					titleview.setVisible(false);

				} else {
				}

			}
		});
		
		
		next.setOnAction(new EventHandler<ActionEvent>()
				{															
					@Override
					public void handle(ActionEvent event) 
					{
						
						String text = numPlayers.getText();
						
						if (text.equals("1")|| text.equals("2") || text.equals("3")|| text.equals("4"))
						{
							try {
								setPlayerNum(Integer.parseInt(text));
								startPopUp.hide();
								numPlayerLabel.setText("You have selected " + numOfPlayers + " players");
								for (int counter = 0; counter < numOfPlayers; counter++) {
									TextField nameField = new TextField();
									textVBox.getChildren().add(nameField);
								}
								
								nextPopUp.show(primaryStage);
								
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else
						{
							playerLabel.setText("Please select a valid number");
						}
						
					}
			
				});
		
		secondNext.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event)
			{
				String welcomeString = "Welcome strategists";
				boolean valid = false ;
				ObservableList<Node>fieldNames = textVBox.getChildren();
				names = new ArrayList<String>();
				//System.out.print(names.get(counter));
				for (int counter = 0; counter < fieldNames.size(); counter++)
				{
					
					TextField tf = (TextField)fieldNames.get(counter);
					String nameString = tf.getText();
					names.add(nameString);
					
				}
				
				for (int counter = 0; counter < fieldNames.size(); counter++)
				{
					
				}
				int countValid = 0;
				for (int n = 0; n < names.size(); n++) {
					if (!names.get(n).equalsIgnoreCase(""))
					{
						countValid++;
						
					}
					else {
						playerNames.setText("Make sure all players have entered name");
					}
				}
				
				if (countValid == names.size()) {
					nextPopUp.hide();
					finalPopUp.show(primaryStage);
					welcome.setText(welcomeString);
					board.initializePlayers(names);
				}
				
				
				
				//nextPopUp.hide();
				//finalPopUp.show(primaryStage);
				
				
			}
		});
		
		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event)
			{
				finalPopUp.hide();
				stack.getChildren().remove(borderPane);
				board.getPanes().get(0).setOpacity(1);
				board.startGame();
			}
		});
		
		
		Group root = new Group(imageview);
		Scene scene = new Scene(root, width, height);
		
		startScreen.getChildren().add(titleview);
		startScreen.getChildren().add(start);
		startScreen.setMargin(start, new Insets(300, 0, 0, 0));
		borderPane.setCenter(startScreen);
		
		borderPane.setPrefSize(width, height);

		stack.getChildren().add(borderPane);

		root.getChildren().add(stack);

		
		String style= getClass().getResource("Gui.css").toExternalForm();
		scene.getStylesheets().add(style);

		primaryStage.setTitle(name);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		
		primaryStage.show();
	}
			
	
 	/**
	 * Sets the player num.
	 *
	 * @param i - the new player num
	 */

	public void setPlayerNum(int i) 
	{
		numOfPlayers = i;
	}

	/**
	 * The main method.
	 *
	 * @param args -  the arguments
	 */
	public static void main(String args[]) 
	{
		launch(args);
	}
}
